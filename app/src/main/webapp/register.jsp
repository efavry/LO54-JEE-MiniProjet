<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="com.github.lo54_project.app.entity.CourseSession" %>
    <%@ page import="com.github.lo54_project.app.service.CourseService" %>
    <%@ page import="org.apache.commons.lang3.StringUtils" %>
    <title>Register</title>
    <link rel="stylesheet" href="assets/css/main.css" />
</head>
<body>

<%
    String idAsString = request.getParameter("id");
    if(StringUtils.isNotEmpty(idAsString))
    {
        CourseSession courseSession = new CourseService().getCourseSession(Long.parseLong(idAsString));
        request.setAttribute("courseSession", courseSession);

%>

<p>
    Course : <%=courseSession.getCourse().getCode() + " : " + courseSession.getCourse().getTitle()%><br>
    Date : <%=new SimpleDateFormat("EEE, MMM d, HH:mm").format(courseSession.getStartDate())%><br>
    Location : <%=courseSession.getLocation().getCity()%><br>
</p>
<hr/>
<div>
    <form action="<%=request.getContextPath()%>/registration" method="get">
        <fieldset>
            <legend>Personal informations</legend>
            First name:
            <label>
                <input type="text" name="firstname" required/>
            </label>
            Last name:
            <label>
                <input type="text" name="lastname" required/>
            </label>
            Address:
            <label>
                <input type="text" name="address" required/>
            </label>
            Phone number:
            <label>
                <input type="number" name="phone" maxlength="10" required/>
            </label>
            E-mail address:
            <label>
                <input type="text" name="email"/>
            </label>
            <input type="hidden" name="sessionId" value="<%=request.getParameter("id")%>" required />
            <input type="submit" value="Submit">
            <input type="reset" value="Clear">
        </fieldset>
    </form>
</div>
    <%
    }
    else
    {%>
    Woops! It seems that your course's id wasn't recovered.<br>
    Please go back to <a href="<%=request.getContextPath()%>/index.jsp">Course overview</a> or <a href="<%=request.getContextPath()%>/search">Course selection</a>.
    <%
        }
    %>

</body>
</html>
