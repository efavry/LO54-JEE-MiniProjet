package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Notmoo on 02/06/2017.
 */
@WebServlet(name = "RegistrationFailureServlet", urlPatterns="/registration/fail")
public class RegistrationFailureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeServlet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeServlet(request, response);
    }

    private void executeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //TODO
    }
}