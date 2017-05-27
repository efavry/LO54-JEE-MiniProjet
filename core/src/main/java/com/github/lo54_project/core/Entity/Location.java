package com.github.lo54jeeminiprojet.miniprojet.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="LOCATION")
public class Location implements Serializable
{
	@Id
	private long id;

	@Column(name="CITY",nullable = false)
	private String city;


	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}
}
