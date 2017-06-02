<%--
  Created by IntelliJ IDEA.
  User: Guillaume
  Date: 02/06/2017
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%@ page import="com.github.lo54_project.app.service.CourseService" %>
    <%@ page import="com.github.lo54_project.app.entity.CourseSession" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <title>Search</title>
</head>
<body>
    <%
        /*
            getNextCoursesSessions() : renvoie une liste de courses rawSessionList, contenant la  prochaine session pour chacune des courses de la table.
         */
        CourseService courseService = new CourseService();

        boolean hasKeyword = request.getAttribute("keywords")!=null && !((String)request.getAttribute("keywords")).isEmpty(),
                hasDate = request.getAttribute("hasDate")!=null && !((String)request.getAttribute("date")).isEmpty(),
                hasLocation = request.getAttribute("hasLocation")!=null && !((String)request.getAttribute("location")).equals(" -- ");

        List<CourseSession> rawSessionList = courseService.getAllCoursesSessions();
        List<CourseSession> sessionList = new ArrayList<>();
        List<CourseSession> toRemove = new ArrayList<>();

        if(hasKeyword){
            for(String keyword : ((String)request.getAttribute("keywords")).split("\\s"))
                rawSessionList.stream().filter(currentSession->currentSession.getCourse().getCode().contains(keyword)).forEach(currentSession->sessionList.add(currentSession));
        }
        if(hasDate){
            if(!sessionList.isEmpty()){
                for(CourseSession currentSession : sessionList){
                    if( new SimpleDateFormat("yyyy-MM-dd").format(currentSession.getStartDate()).compareTo((String)request.getAttribute("date"))!=0)
                        toRemove.add(currentSession);
                }
            }else{
                rawSessionList.stream()
                        .filter(currentSession->new SimpleDateFormat("yyyy-MM-dd").format(currentSession.getStartDate()).compareTo((String)request.getAttribute("date"))==0)
                        .forEach(currentSession->sessionList.add(currentSession));
            }
        }
        if(hasLocation){
            if(!sessionList.isEmpty()){
                for(CourseSession currentSession : sessionList){
                    if( !currentSession.getLocation().getCity().equals(request.getAttribute("location")))
                        toRemove.add(currentSession);
                }
            }else{
                rawSessionList.stream()
                        .filter(currentSession->currentSession.getLocation().getCity().equals(request.getAttribute("location")))
                        .forEach(currentSession->sessionList.add(currentSession));
            }
        }

        sessionList.removeAll(toRemove);

        request.setAttribute("sessions", sessionList);
        request.setAttribute("locations", courseService.getAllLocations());
    %>

    <h1>Course overview</h1>

    <form action="/search.jsp" method="post" id="searchForm">

        Keywords (separed by blanks) :
        <input type="text" name="keywords"><br/>

        Date :
        <input type="date" name="date"><br/>

        Location :
        <select name="location" form="searchForm">
            <option value="null"> -- </option>
            <c:forEach var="location" items="${locations}">
                <option value="${location.city}">${location.city}</option>
            </c:forEach>
        </select><br/>

        <input type="submit" value="Rechercher">
    </form>
    <div>
        <c:choose>
            <c:when test="${not empty sessions}">
                <h2>Results</h2>
                <ul>
                    <c:forEach var="session" items="${sessions}">
                        <li>
                            <a href="register.jsp?ID=${session.id}">
                                    ${session.course.title} : ${session.location.city}, at <%new SimpleDateFormat("EEE, MMM d, HH:mm").format(((CourseSession)pageContext.getAttribute("courseSession")).getStartDate());%>
                            </a>
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
