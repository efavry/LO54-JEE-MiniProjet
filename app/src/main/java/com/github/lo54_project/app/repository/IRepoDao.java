package com.github.lo54_project.app.repository;

import com.github.lo54_project.app.entity.IEntity;
import org.hibernate.Session;

/**
 * Created by qsm on 12/06/17.
 */
public interface IRepoDao<T extends IEntity>
{


    Session getOpenedSession();
    boolean save(T entity);
}
