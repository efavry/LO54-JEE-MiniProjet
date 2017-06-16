package com.github.lo54_project.app.repository;

import com.github.lo54_project.app.entity.IEntity;
import org.hibernate.Session;


public interface IRepoDao<T extends IEntity>
{


    Session getOpenedSession();
    boolean save(T entity);
}
