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
    <%@ page import="com.github.lo54_project.app.entity.Course" %>
    <%@ page import="com.github.lo54_project.app.entity.Location" %>
    <title>Search</title>
    <link rel="stylesheet" href="assets/css/main.css" />
</head>
<body>

    <%!
        private String buildSessionList(String contextPath,List<CourseSession> sessionsList)
        {
            StringBuilder builder = new StringBuilder();
            String simpleForm ="";
            if(!sessionsList.isEmpty())
            {

                builder.append("<h2>Results</h2>\n<ul>");
                for(CourseSession session : sessionsList)
                {
                    builder.append("<li>\n<a href=\"");
                    builder.append(contextPath);
                    builder.append("/register?id=");
                    builder.append(session.getId());
                    builder.append("\">");
                    builder.append(session.getCourse().getTitle());
                    builder.append(" : ");
                    builder.append(session.getLocation().getCity());
                    builder.append(" at ");
                    builder.append(new SimpleDateFormat("EEE, MMM d, HH:mm").format(session.getStartDate()));
                    builder.append("</a>\n</li>");
                }
                builder.append("</ul>");
                simpleForm = builder.toString();
            }
            else
            {
                simpleForm = "No results";
            }
            return simpleForm;
        }

        private String buildLocationList(List<Location> locations)
        {
            StringBuilder builder = new StringBuilder();
                if(!locations.isEmpty())
                    for( Location location : locations)
                    {
                        builder.append("<option value=\"");
                        builder.append(location.getCity());
                        builder.append("\">");
                        builder.append(location.getCity());
                        builder.append("</option>");
                    }

            return builder.toString();
        }

    %>

    <%
        CourseService courseService = new CourseService();

        String keywords = request.getParameter("keywords");
        String dateASString = request.getParameter("date");
        String locationAsString = request.getParameter("location");

        List<CourseSession> rawSessionList = courseService.getAllCoursesSessions();
        List<CourseSession> sessionList = new ArrayList<CourseSession>();
        List<CourseSession> toRemove = new ArrayList<CourseSession>();

        if(StringUtils.isNotEmpty(keywords))
            for(String keyword : keywords.split("\\s"))
                for(CourseSession currentSession : rawSessionList)
                {
                    Course course = currentSession.getCourse();
                    String courseCode = course.getCode();
                    String courseTitle = course.getTitle();
                    if(courseCode.contains(keyword) || StringUtils.containsIgnoreCase(courseTitle,keyword))
                        sessionList.add(currentSession);
                }




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

        if(StringUtils.isNotEmpty(locationAsString) && !StringUtils.equalsIgnoreCase(locationAsString,"-1")) //we should use id, it would be less ugly
        {
            if(!sessionList.isEmpty())
            {
                for(CourseSession currentSession : sessionList)
                {
                    if( !currentSession.getLocation().getCity().equals(locationAsString))
                        toRemove.add(currentSession);
                }
            }
            else
            {
                for(CourseSession currentSession : rawSessionList)
                    if(currentSession.getLocation().getCity().equals(locationAsString))
                        sessionList.add(currentSession);
            }
        }

        sessionList.removeAll(toRemove);
    %>

    <h1>Course overview</h1>

    <form action="<%=request.getContextPath()%>/search" method="get" id="searchForm">

        Keywords (separed by blanks) :
        <label>
            <input type="text" name="keywords">
        </label><br/>

        Date (ex : 2017-06-23) :
        <label>
            <input type="date" name="date">
        </label><br/>

        Location :
        <label>
            <select name="location" form="searchForm">
                <option value="-1"> --</option>
                <%=buildLocationList(courseService.getAllLocations())%>
            </select>
        </label><br/>

        <input type="submit" value="Search">
    </form>
    <div>
        <%=buildSessionList(request.getContextPath(),sessionList)%>
    </div>
</body>
</html>
