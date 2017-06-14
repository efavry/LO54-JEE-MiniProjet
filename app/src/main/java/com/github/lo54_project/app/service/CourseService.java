package com.github.lo54_project.app.service;

import com.github.lo54_project.app.entity.Course;
import com.github.lo54_project.app.entity.Location;
import com.github.lo54_project.app.repository.ClientDao;
import com.github.lo54_project.app.repository.CourseDao;
import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.entity.CourseSession;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

	private CourseDao dao;

    public CourseService(){
		dao = new CourseDao();
	}

	public boolean registerClient(Client client)
    {
        ClientDao clientDao = new ClientDao(dao.getOpenedSession());
        return clientDao.save(client);
	}

	/**
	 * renvoie une liste de courses sessions, contenant la  prochaine session pour chacune des courses de la table.
	 * @return
	 */
    public List<CourseSession> getNextCoursesSessions()
    {
    	List<CourseSession> reply = new ArrayList<>();
    	List<Course> courses = dao.getAllCourses();

    	courses
                .stream()
                .filter(course -> dao.getCourseSessions(course).size()>0)
                .forEach((course)->reply.add(getFirstSessionInTime(dao.getCourseSessions(course))));
    	return reply;
    }

    private CourseSession getFirstSessionInTime(List<CourseSession> sessions){
        if(sessions==null)
            return null;

        CourseSession first = null;
        for(CourseSession session : sessions){
            if (first==null || session.getStartDate().before(first.getStartDate()))
                first = session;
        }
        return first;
    }

    public CourseSession getCourseSession(long id) {
        return dao.getCourseSession(id);
    }


    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }

    public List<CourseSession> getAllCoursesSessions() {
        return dao.getAllCourseSessions();
    }
}
