package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

        response.setContentType("text/html");
        try{
            PrintWriter out = response.getWriter();
            out.print("<html><head><title>");
            out.print("Error");
            out.print("</title></head><body>");
            if(request.getAttribute("error")!=null && !((String)request.getAttribute("error")).isEmpty()) {
                out.print("An error occured during registration : ");
                out.print((String)request.getAttribute("error"));
            }else{
                out.print("An unspecified error occured during registration");
            }
            out.print("<br/><br/>");
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
