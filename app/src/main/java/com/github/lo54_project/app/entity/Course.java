package com.github.lo54_project.app.entity;

import com.github.lo54_project.app.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="COURSE", schema = "ADMIN")
public class Course implements Serializable,IEntity
{

	@Id
	@Column(name = "CODE", nullable = false, columnDefinition = "char")
	private String code;

	@Column(name="TITLE",nullable = false)
	private String title;


	public String getCode()

	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}


}
