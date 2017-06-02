package servlet;

import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.entity.CourseSession;
import com.github.lo54_project.app.service.CourseService;
import com.github.lo54_project.app.service.PublisherService;
import com.github.lo54_project.app.service.exceptions.PublisherServiceException;

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
@WebServlet(name = "RegistrationServlet", urlPatterns="/registration")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeServlet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeServlet(request, response);
    }

    private void executeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String  firstname = (String)request.getAttribute("firstname"),
                lastname = (String)request.getAttribute("lastname"),
                address = (String)request.getAttribute("address"),
                phone = (String)request.getAttribute("phone"),
                email = (String)request.getAttribute("email");
        Integer sessionId = Integer.parseInt((String)request.getAttribute("session"));
        CourseService courseService = new CourseService();
        RequestDispatcher dispatcher;
        CourseSession courseSession = courseService.getCourseSession(sessionId);


        if(courseSession!=null) {

            Client client = new Client(firstname, lastname, address, phone, email, courseSession);

            if (courseService.registerClient(client)) {
                dispatcher = request.getRequestDispatcher("/registration/success");

                try {
                    //TODO Ajouter url broker
                    PublisherService publisherService = new PublisherService(null);

                    publisherService.startService();
                    publisherService.publishRegistrationMessage(client);

                } catch (PublisherServiceException e) {
                    e.printStackTrace();
                }
            } else {
                dispatcher = request.getRequestDispatcher("/registration/failure");
            }
        }else
            dispatcher = request.getRequestDispatcher("/registration/failure");

        dispatcher.forward(request, response);
    }
}
