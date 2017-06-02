<%--
  Created by IntelliJ IDEA.
  User: Guillaume
  Date: 02/06/2017
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page import="" %> //Import course service
    <%@ page import="" %> //Import course
    <%@ page import="" %> //Import course session
    <%@ page import="" %> //Import location
    <%@ page import="java.text.SimpleDateFormat" %>
    <title>Home</title>
</head>
<body>
    <%
        /*
            getNextCourses() : renvoie une liste de courses sessions, contenant la  prochaine session pour chacune des courses de la table.
            getUnassignedCourses() : renvoie la liste des courses n'ayant aucune session de prévu
         */
        List<CourseSession> sessions = new CourseService.getNextCourses();
        List<Course> unassignedCourses = new CourseService.getUnassignedCourses();
        request.setAttribute("sessions", sessions);
        request.setAttribute("unassignedCourses", unassignedCourses);
    %>

    <h1>Course overview</h1>

    <form action="/search.jsp">
        <input type="submit" value="Recherche...">
    </form>
    <div>
        <h2>Next courses</h2>
        <c:choose>
            <c:when test="${not empty sessions}">
                <ul>
                    <c:forEach var="session" items="${sessions}">
                        <li>
                            <a href="register.jsp?ID=${session.id}">
                                    ${session.course.title} : ${session.location.city}, at <%new SimpleDateFormat("EEE, MMM d, HH:mm").format(((CourseSession)pageContext.getAttribute("courseSession")).getStartDate())%>
                            </a>
                        </li>
                    </c:forEach>
                    <c:forEach var="course" items="${unassignedCourses}">
                    <li>
                        ${course.title} : Aucune session planifiée
                    </li>
                </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                Erreur lors du chargement de la liste -> Demerdenzizich
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
