package com.github.lo54_project.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CLIENT")
public class Client implements Serializable
{

	public Client(){

	}

	public Client(String firstName, String lastName, String address, String phoneNumber, String email){
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.phoneNumber=phoneNumber;
		this.email=email;
	}

	@Id private long id;

	@Column(name="LASTNAME",nullable = false)
	private String lastName;

	@Column(name="FIRSTNAME",nullable = false)
	private String firstName;

	@Column(name="ADDRESS",nullable = false)
	private String address;

	@Column(name="PHONE",nullable = false)
	private String phoneNumber;

	@Column(name="EMAIL")
	private String email;
	
	
	/**
	 * TODO : This doc
	 */
	@Column(name="COURSE_SESSION_ID")
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private CourseSession courseSession;


	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public CourseSession getCourseSession ()
	{
		return courseSession;
	}

	public void setCourseSession (CourseSession courseSession)
	{
		this.courseSession = courseSession;
	}
}
