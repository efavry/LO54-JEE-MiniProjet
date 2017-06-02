package com.github.lo54_project.app.service;

import com.github.lo54_project.app.entity.Course;
import com.github.lo54_project.app.repository.CourseDao;
import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.entity.CourseSession;

import java.util.List;

public class CourseService {

	private CourseDao dao;

	public CourseService(){
		dao = new CourseDao();
	}

	public void registerClient(Client client, CourseSession session) {

	}

	/**
	 * renvoie une liste de courses sessions, contenant la  prochaine session pour chacune des courses de la table.
	 * @return
	 */
    public List<CourseSession> getNextCourses()  {
    	
    }

	/**
	 * renvoie la liste des courses n'ayant aucune session enregistrée
	 * @return
	 */
	public List<Course> getUnassignedCourses() {

	}
}
