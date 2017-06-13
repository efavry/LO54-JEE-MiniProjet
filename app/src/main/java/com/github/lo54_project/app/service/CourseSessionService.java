package com.github.lo54_project.app.service;

import com.github.lo54_project.app.entity.Course;
import com.github.lo54_project.app.entity.CourseSession;
import com.github.lo54_project.app.entity.Location;
import com.github.lo54_project.app.repository.CourseDao;
import com.github.lo54_project.app.repository.CourseSessionDao;
import com.github.lo54_project.app.repository.LocationDao;

/**
 * Created by qsm on 12/06/17.
 */
public class CourseSessionService
{
    CourseSessionDao dao;
    public CourseSessionService()
    {
        dao = new CourseSessionDao();
    }

    public Course getCourse(CourseSession courseSession)
    {
        CourseDao courseDao = new CourseDao(dao.getOpenedSession());
        //we re-get a coursession because there is a lot of chance coursession was
        // lazily fetched from a previous session
        //and i don't want to use Hibernate.initialize()
        return courseDao.get(dao.get(courseSession.getId()).getCourse().getCode());
    }

    public Location getLocation(CourseSession courseSession)
    {
        LocationDao locationDao = new LocationDao(dao.getOpenedSession());
        //we re-get a coursession because there is a lot of chance coursession was
        // lazily fetched from a previous session
        //and i don't want to use Hibernate.initialize()
        return locationDao.get(dao.get(courseSession.getId()).getLocation().getId());
    }
}
