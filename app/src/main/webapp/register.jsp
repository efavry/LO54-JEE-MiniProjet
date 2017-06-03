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
        <div>
            <form action="<%=request.getContextPath()%>/registration" method="post">
            <fieldset>
                <legend>Personal informations</legend>
                First name:<br/>
                <label>
                    <input type="text" name="firstname" required/>
                </label><br/>
                Last name:<br/>
                <label>
                    <input type="text" name="lastname" required/>
                </label><br/>
                Address:<br/>
                <label>
                    <input type="text" name="address" required/>
                </label><br/>
                Phone number:<br/>
                <label>
                    <input type="number" name="phone" maxlength="10" required/>
                </label><br/>
                E-mail address:<br/>
                <label>
                    <input type="text" name="email"/>
                </label><br/>
                <input type="hidden" name="session" value="${param.id}" required />

                <input type="submit" value="Submit">
                <input type="reset" value="Clear">
            </fieldset>
            </form>
        </div>
    <%}else{%>
        Woops! It seems that your course's id wasn't recovered.<br>
        Please go back to <a href="<%=request.getContextPath()%>/index.jsp">Course overview</a> or <a href="<%=request.getContextPath()%>/search.jsp">Course selection</a>.
    <%}%>

</body>
</html>
