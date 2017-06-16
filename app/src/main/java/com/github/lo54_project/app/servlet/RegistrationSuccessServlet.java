package com.github.lo54_project.app.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "RegistrationSuccessServlet", urlPatterns="/registration/success")
public class RegistrationSuccessServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeServlet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeServlet(request, response);
    }

    private void executeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        try{
            PrintWriter out = response.getWriter();
            out.print("<html><head><title>");
            out.print("Registered!");
            out.print("</title><link rel=\"stylesheet\" href=\"assets/css/main.css\" /></head><body>");
            out.print("Registration was made successfully<br/><br/>");
            out.print("<a href=\"");
            out.print(request.getContextPath());
            out.print("/index.jsp\">Course overview</a>");
            out.print("</body></html>");
        }catch(Exception e){
            e.printStackTrace();
            response.sendError(500);
        }
    }
}
