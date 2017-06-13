<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.github.lo54_project.app.service.ClientService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="assets/css/main.css" />
</head>
<body>

    <%!
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
    %>

    <%
        String firstName = request.getParameter("firstname");
        String lastName =request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String sessionId = request.getParameter("sessionId");


        //sorry about that
        if(StringUtils.isEmpty(firstName))
        {
            %>
                Woops! The firstname is not valid
            <%
        }
        else if(StringUtils.isEmpty(lastName))
        {
            %>
                Woops! The lastname is not valid
            <%
        }
        else if(StringUtils.isEmpty(address))
        {
            %>
                Woops! The adress is not valid
            <%
        }
        else if(StringUtils.isEmpty(phone))
        {
            %>
                Woops! The phone is not valid
            <%
        }
        else if(StringUtils.isEmpty(email))
        {
            %>
                Woops! The email is not valid
            <%
        }
        else if(!isSessionIdValid(sessionId))
        {

            %>
                What a terrible failure! The CourseSession id is not valid, this is not on your part, maybe network issues ?
            <%
        }
        else
        {
            try
            {
                ClientService clientService = new ClientService();
                clientService.createClient(firstName,lastName,address,phone,email,Long.parseLong(sessionId));
                %>
                    Success !
                <%
            }
            catch (Exception ex)
            {
                %>
                    An internal erro happened while saving your registration !
                <%
            }



        }
    %>

</body>
</html>
