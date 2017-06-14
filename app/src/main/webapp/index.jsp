<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="com.github.lo54_project.app.entity.CourseSession" %>
    <%@ page import="com.github.lo54_project.app.service.CourseService" %>
    <%@ page import="java.util.List" %>
    <%@ page import="com.github.lo54_project.app.repository.CourseDao" %>
    <%@ page import="com.github.lo54_project.app.entity.Location" %>
    <%@ page import="com.github.lo54_project.app.repository.LocationDao" %>
    <%@ page import="com.github.lo54_project.app.service.CourseSessionService" %>
    <title>Home</title>
    <link rel="stylesheet" href="assets/css/main.css" />
</head>
<body>
    <%
        /*
            getNextCoursesSessions() : renvoie une liste de courses sessions, contenant la  prochaine session pour chacune des courses de la table.
         */
        List<CourseSession> coursesSessions = new CourseService().getNextCoursesSessions();
        //request.setAttribute("courseSessions", coursesSessions);
        String dynContent = "";

        if(coursesSessions != null && !coursesSessions.isEmpty())
        {
            //CourseDao courseDao = new CourseDao();
            //LocationDao locationDao = new LocationDao(courseDao.get
            CourseSessionService courseSessionService = new CourseSessionService();
            StringBuilder builder = new StringBuilder();
            for (CourseSession courseSession : coursesSessions)
            {
                builder.append("<li>\n");
                builder.append("   <a href=");
                builder.append(request.getContextPath());
                builder.append("/register?id=");
                builder.append(courseSession.getId());
                builder.append(">");
                builder.append("\n");
                builder.append(/*courseSessionService.getCourse(*/courseSession.getCourse().getTitle());
                builder.append(":");
                builder.append(courseSessionService.getLocation(courseSession).getCity());
                builder.append(" at ");
                builder.append(new SimpleDateFormat("EEE, MMM d, HH:mm").format(courseSession.getStartDate()));
                builder.append("\n");
                builder.append("   </a>\n</li>");
            }
            dynContent = builder.toString();
            //((CourseSession)pageContext.getAttribute("courseSession")).getStartDate()
        }
        else
        {
            dynContent = "Erreur lors du chargement de la liste";
        }
        //request.setAttribute("dynContent",dynContent);
        pageContext.setAttribute("dynContent", dynContent);

    %>

    <form action="<%=request.getContextPath()%>/search" method="post">
        <input type="submit" value="Recherche...">
    </form>
    <div>
        <h2>Next courses</h2>
        <%= dynContent%>
    </div>
</body>
</html>
