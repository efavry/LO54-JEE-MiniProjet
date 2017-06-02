package com.github.lo54_project.app.repository;

import com.github.lo54_project.app.entity.Client;
import com.github.lo54_project.app.entity.Course;
import com.github.lo54_project.app.entity.CourseSession;
import com.github.lo54_project.app.entity.Location;
import com.github.lo54_project.app.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    private List<CourseSession> allCourseSessions;
    private List<Location> allLocations;

    public List<CourseSession> getCourseSessions(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from com.github.lo54_project.app.entity.CourseSession as session where session.course.code = :code");
            query.setParameter("code", course.getCode());
            List<Object> results = query.list();
            List<CourseSession> reply = new ArrayList<>();
            for(Object result : results){
                if(CourseSession.class.isInstance(result))
                    reply.add(CourseSession.class.cast(result));
            }
            session.getTransaction().commit();
            return reply;
        } catch (HibernateException he) {
            he.printStackTrace();
            if (session.getTransaction() != null) {
                try {
                    session.getTransaction().rollback();
                } catch (HibernateException he2) {
                    he2.printStackTrace();
                }
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public CourseSession getCourseSession(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from com.github.lo54_project.app.entity.CourseSession as session where session.id = :id");
            query.setParameter("id", id);
            return CourseSession.class.cast(query.uniqueResult());
        } catch (HibernateException he) {
            he.printStackTrace();
            if (session.getTransaction() != null) {
                try {
                    session.getTransaction().rollback();
                } catch (HibernateException he2) {
                    he2.printStackTrace();
                }
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<CourseSession> getAllCourseSessions() {
        return internal_getAllBeans(CourseSession.class);
    }

    public List<Location> getAllLocations() {
        return internal_getAllBeans(Location.class);
    }

    public List<Course> getAllCourses() {
        return internal_getAllBeans(Course.class);
    }

    private <T> List<T> internal_getAllBeans(Class<T> clazz){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from "+clazz.getSimpleName());
            List<Object> results = query.list();
            List<T> reply = new ArrayList<>();
            for(Object result : results){
                if(clazz.isInstance(result))
                    reply.add(clazz.cast(result));
            }
            session.getTransaction().commit();
            return reply;
        } catch (HibernateException he) {
            he.printStackTrace();
            if (session.getTransaction() != null) {
                try {
                    session.getTransaction().rollback();
                } catch (HibernateException he2) {
                    he2.printStackTrace();
                }
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean saveClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean reply;
        try {
            session.beginTransaction();
            session.save(client);
            reply = true;
        } catch (HibernateException he) {
            reply = false;
            he.printStackTrace();
            if (session.getTransaction() != null) {
                try {
                    session.getTransaction().rollback();
                } catch (HibernateException he2) {
                    he2.printStackTrace();
                }
            }
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return reply;
    }
}
