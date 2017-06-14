package com.github.lo54_project.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="COURSE_SESSION", schema = "ADMIN")
public class CourseSession implements Serializable,IEntity
{

	@Id
	@Column(name="ID")
	private long id;

	@Column(name="START_DATE",nullable = false, columnDefinition = "date")
	private Date startDate;

	@Column(name="END_DATE",nullable = false, columnDefinition = "date")
	private Date endDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="COURSE_CODE")
	private Course course;

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="LOCATION_ID")
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
