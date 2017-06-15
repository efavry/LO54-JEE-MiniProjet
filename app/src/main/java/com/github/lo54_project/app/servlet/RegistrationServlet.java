package com.github.lo54_project.app.servlet;

import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.entity.CourseSession;
import com.github.lo54_project.app.service.ClientService;
import com.github.lo54_project.app.service.CourseService;
import com.github.lo54_project.app.service.PublisherService;
import com.github.lo54_project.app.service.exceptions.PublisherServiceException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Notmoo on 02/06/2017.
 */
@WebServlet(name = "RegistrationServlet", value="/registration")
public class RegistrationServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        executeServlet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeServlet(request, response);
    }

    private boolean isSessionIdValid(String sessionId)
    {
        if(StringUtils.isEmpty(sessionId))
            return false;
        try
        {
            Long.parseLong(sessionId);
            return true;
        }
        catch (NumberFormatException nEx)
        {
            return false;
        }
    }

    private void executeServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        String firstName = request.getParameter("firstname");
        String lastName =request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String sessionId = request.getParameter("sessionId");

        //sorry about that
        if(StringUtils.isEmpty(firstName))
        {
            request.setAttribute("error", "Woops! The firstname is not valid");
            dispatcher = request.getRequestDispatcher("/registration/failure");
        }
        else if(StringUtils.isEmpty(lastName))
        {
            request.setAttribute("error", "Woops! The lastname is not valid");
            dispatcher = request.getRequestDispatcher("/registration/failure");
        }
        else if(StringUtils.isEmpty(address))
        {
            request.setAttribute("error", "Woops! The adress is not valid");
            dispatcher = request.getRequestDispatcher("/registration/failure");
        }
        else if(StringUtils.isEmpty(phone))
        {
            request.setAttribute("error", "Woops! The phone is not valid");
            dispatcher = request.getRequestDispatcher("/registration/failure");
        }
        else if(StringUtils.isEmpty(email))
        {
            request.setAttribute("error", "Woops! The email is not valid");
            dispatcher = request.getRequestDispatcher("/registration/failure");
        }
        else if(!isSessionIdValid(sessionId))
        {
            request.setAttribute("error", "No session specified or Invalid session specified");
            dispatcher = request.getRequestDispatcher("/registration/failure");
        }
        else
        {
            try
            {
                ClientService clientService = new ClientService();
                Client client = clientService.createClient(firstName,lastName,address,phone,email,Long.parseLong(sessionId));
                if(client != null)
                {
                    dispatcher = request.getRequestDispatcher("/registration/success");
                    try
                    {
                        PublisherService publisherService = new PublisherService("tcp://localhost:61616");

                        publisherService.startService();
                        publisherService.publishRegistrationMessage(client);
                        publisherService.closeService();
                    }
                    catch (PublisherServiceException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    request.setAttribute("error", "Registration failed");
                    dispatcher = request.getRequestDispatcher("/registration/failure");
                }
            }
            catch (Exception ex)
            {
                request.setAttribute("error", "An internal error happened while saving your registration !");
                dispatcher = request.getRequestDispatcher("/registration/failure");
            }
        }
        dispatcher.forward(request, response);
    }
}
