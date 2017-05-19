package com.github.lo54jeeminiprojet.miniprojet.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="CLIENT")
public class Client implements Serializable
{

	@Id private long id;

	@Column(name="LASTNAME",nullable = false)
	private String lastName;

	@Column(name="FIRSTNAME",nullable = false)
	private String firstName;

	@Column(name="ADDRESS",nullable = false)
	private String address;

	@Column(name="PHONE",nullable = false)
	private String phoneNumber;

	@Column(name="EMAIL",nullable = false)
	private String email;

	private long courseSessionId;


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

	public long getCourseSessionId()
	{
		return courseSessionId;
	}

	public void setCourseSessionId(long courseSessionId)
	{
		this.courseSessionId = courseSessionId;
	}
}
