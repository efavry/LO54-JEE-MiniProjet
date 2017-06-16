package com.github.lo54_project.app.service;

import com.github.lo54_project.app.entity.Course;
import com.github.lo54_project.app.entity.CourseSession;
import com.github.lo54_project.app.entity.Location;
import com.github.lo54_project.app.repository.CourseDao;
import com.github.lo54_project.app.repository.CourseSessionDao;
import com.github.lo54_project.app.repository.LocationDao;

public class CourseSessionService
{
    private CourseSessionDao dao;
    public CourseSessionService()
    {
        dao = new CourseSessionDao();
    }

    public Course getCourse(CourseSession courseSession)
    {
        CourseDao courseDao = new CourseDao(dao.getOpenedSession());
        return courseDao.get(dao.get(courseSession.getId()).getCourse().getCode());
    }

    public Location getLocation(CourseSession courseSession)
    {
        LocationDao locationDao = new LocationDao(dao.getOpenedSession());
        return locationDao.get(dao.get(courseSession.getId()).getLocation().getId());
    }
}
