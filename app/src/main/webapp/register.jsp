<%--
  Created by IntelliJ IDEA.
  User: Guillaume
  Date: 02/06/2017
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="com.github.lo54_project.app.entity.CourseSession" %>
    <%@ page import="com.github.lo54_project.app.service.CourseService" %>
    <title>Register</title>
</head>
<body>

    <% if(request.getAttribute("ID")!=null){
            CourseSession courseSession = new CourseService().getCourseSession(Integer.parseInt((String)request.getAttribute("ID")));
            request.setAttribute("courseSession", courseSession);
    %>

        <p>
            Course : ${courseSession.course.code}<br>
            Date : <%new SimpleDateFormat("EEE, MMM d, HH:mm").format(courseSession.getStartDate());%><br>
            Location : ${courseSession.location.city}<br>
        </p>
        <hr/>
        <p>
            <form action="/registration" method="post">
            <fieldset>
                <legend>Personal informations</legend>
                First name:<br/>
                <input type="text" name="firstname" required /><br/>
                Last name:<br/>
                <input type="text" name="lastname" required /><br/>
                Address:<br/>
                <input type="text" name="address" required /><br/>
                Phone number:<br/>
                <input type="number" name="phone" maxlength="10" required /><br/>
                E-mail address:<br/>
                <input type="text" name="email" /><br/>
                <input type="hidden" name="session" value="${param.id}" required />

                <input type="submit" value="Submit">
                <input type="reset" value="Clear">
            </fieldset>
            </form>
        </p>

    <%}else{%>
        Woops! It seems that your course's id wasn't recovered.<br>
        Please go back to <a href="index.jsp">Course overview</a> or <a href="search.jsp">Course selection</a>.
    <%}%>

</body>
</html>
