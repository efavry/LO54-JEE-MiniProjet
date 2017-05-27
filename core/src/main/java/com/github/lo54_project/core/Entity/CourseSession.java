package com.github.lo54_project.core.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="COURSE_SESSION")
public class CourseSession implements Serializable
{

	@Id private long id;

	@Column(name="START_DATE",nullable = false)
	private Date startDate;

	@Column(name="END_DATE",nullable = false)
	private Date endDate;
	
	@Column(name="COURSE_CODE", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Course course;
	
	@Column(name="LOCATION_ID", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private Location location;


	public long getId()
	{
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setCourse (Course course)
	{
		this.course = course;
	}

	public Course getCourse ()
	{
		return course;
	}

	public void setLocation (Location location)
	{
		this.location = location;
	}

	public Location getLocation ()
	{

		return location;
	}

}
