package com.github.lo54_project.app.repository;


import com.github.lo54_project.app.entity.Course;
import com.github.lo54_project.app.entity.CourseSession;
import com.github.lo54_project.app.entity.IEntity;
import com.github.lo54_project.app.entity.Location;
import com.github.lo54_project.app.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CourseDao implements IRepoDao<Course>
{

    private List<CourseSession> allCourseSessions;
    private List<Location> allLocations;


    private Session session;
    public CourseDao()
    {
        session = HibernateUtil.getSessionFactory().openSession(); // no try catchy to be sure that it is catched upper in the stack call

    }

    public CourseDao(Session session)
    {
        this.session = session;
    }

    public void close()
    {
        if(session != null)
        {
            try
            {
                session.close();
            }
            catch(HibernateException hex)
            {
                hex.printStackTrace();
            }
        }
    }

    @Override
    public boolean save(Course c)
    {
        return HibernateUtil.persist(session,c);
    }

    public Course get(String id)
    {
        try
        {
            session.beginTransaction();
            Course client = (Course) session.get(Course.class, id);
            session.getTransaction().commit();
            return client;
        }
        catch (HibernateException hex)
        {
            hex.printStackTrace();
        }
        return null;
    }

    @Override
    public Session getOpenedSession()
    {
        return session;
    }


    public List<CourseSession> getCourseSessions(Course course) {
        try
        {
            session.beginTransaction();
            Query query = session.createQuery("from com.github.lo54_project.app.entity.CourseSession as session where session.course.code = :code");
            query.setParameter("code", course.getCode());
            List<Object> results = query.list();
            List<CourseSession> reply = new ArrayList<>();
            for(Object result : results)
                if(CourseSession.class.isInstance(result))
                    reply.add(CourseSession.class.cast(result));

            session.getTransaction().commit();
            return reply;
        }
        catch (HibernateException he)
        {
            he.printStackTrace();
            if (session.getTransaction() != null)
                try
                {
                    session.getTransaction().rollback();
                }
                catch (HibernateException he2)
                {
                    he2.printStackTrace();
                }
        }
        return null;
    }

    public CourseSession getCourseSession(long id)
    {

        CourseSessionDao courseSessionDao = new CourseSessionDao(session);
        return courseSessionDao.get(id);
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


    private <T> List<T> internal_getAllBeans(Class<T> clazz)
    {
        try
        {
            session.beginTransaction();
            List<T> list;
            list = (List<T>) session.createCriteria(clazz).list();
            session.getTransaction().commit();
            return new ArrayList<T>(list);
        }
        catch (HibernateException hex)
        {
            hex.printStackTrace();
            if (session.getTransaction() != null)
                try
                {
                    session.getTransaction().rollback();
                }
                catch (HibernateException he2)
                {
                    he2.printStackTrace();
                }
        }
        return new ArrayList<T>();
    }
}
