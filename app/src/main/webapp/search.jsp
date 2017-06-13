<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%@ page import="com.github.lo54_project.app.service.CourseService" %>
    <%@ page import="com.github.lo54_project.app.entity.CourseSession" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="org.apache.commons.lang3.StringUtils" %>
    <title>Search</title>
    <link rel="stylesheet" href="assets/css/main.css" />
</head>
<body>
    <%
        CourseService courseService = new CourseService();

        String keywords = request.getParameter("keywords");
        String dateASString = request.getParameter("hasDate");
        String locationAsString = request.getParameter("hasLocation");

        List<CourseSession> rawSessionList = courseService.getAllCoursesSessions();
        List<CourseSession> sessionList = new ArrayList<CourseSession>();
        List<CourseSession> toRemove = new ArrayList<CourseSession>();

        if(StringUtils.isNotEmpty(keywords))
            for(String keyword : keywords.split("\\s"))
                for(CourseSession currentSession : rawSessionList)
                    if(currentSession.getCourse().getCode().contains(keyword))
                        sessionList.add(currentSession);




        if(StringUtils.isNotEmpty(dateASString))
        {
            if(!sessionList.isEmpty())
            {
                for(CourseSession currentSession : sessionList)
                    if( new SimpleDateFormat("yyyy-MM-dd").format(currentSession.getStartDate()).compareTo(dateASString)!=0)
                        toRemove.add(currentSession);
            }
            else
                for(CourseSession currentSession : rawSessionList)
                    if(new SimpleDateFormat("yyyy-MM-dd").format(currentSession.getStartDate()).compareTo(dateASString)==0)
                        sessionList.add(currentSession);
        }

        if(StringUtils.isNotEmpty(locationAsString) && !StringUtils.equalsIgnoreCase(locationAsString," -- "))
        {
            if(!sessionList.isEmpty())
            {
                for(CourseSession currentSession : sessionList){
                    if( !currentSession.getLocation().getCity().equals(request.getAttribute("location")))
                        toRemove.add(currentSession);
                }
            }
            else{
                for(CourseSession currentSession : rawSessionList){
                    if(currentSession.getLocation().getCity().equals(request.getAttribute("location")))
                        sessionList.add(currentSession);
                }
            }
        }

        sessionList.removeAll(toRemove);

        request.setAttribute("sessions", sessionList);
        request.setAttribute("locations", courseService.getAllLocations());
    %>

    <h1>Course overview</h1>

    <form action="<%=request.getContextPath()%>/search.jsp" method="post" id="searchForm">

        Keywords (separed by blanks) :
        <label>
            <input type="text" name="keywords">
        </label><br/>

        Date :
        <label>
            <input type="date" name="date">
        </label><br/>

        Location :
        <label>
            <select name="location" form="searchForm">
                <option value="null"> --</option>
                <c:if test="${not empty locations}">
                    <c:forEach var="location" items="${locations}">
                        <option value="${location.city}"> ${location.city} </option>
                    </c:forEach>
                </c:if>
            </select>
        </label><br/>

        <input type="submit" value="Rechercher">
    </form>
    <div>
        <c:choose>
            <c:when test="${not empty sessions}">
                <h2>Results</h2>
                <ul>
                    <c:forEach var="session" items="${sessions}">
                        <li>
                            <a href="<%=request.getContextPath()%>/register.jsp?ID=${session.id}">
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
