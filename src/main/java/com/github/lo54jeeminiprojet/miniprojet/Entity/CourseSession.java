package com.github.lo54jeeminiprojet.miniprojet.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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


	private String courseCode;

	private long locationId;


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

	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}

	public String getCourseCode()
	{
		return courseCode;
	}

	public void setLocationId(long locationId)
	{
		this.locationId = locationId;
	}

	public long getLocationId()
	{

		return locationId;
	}

}
