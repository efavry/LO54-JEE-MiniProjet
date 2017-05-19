package com.github.lo54jeeminiprojet.miniprojet.Entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="COURSE")
public class Course implements Serializable
{

	@Column(
			name="columnName";//name (optional): the column name (default to the property name)
			boolean unique() default false;//unique (optional): set a unique constraint on this column or not (default false)
			boolean nullable() default true;//nullable (optional): set the column as nullable (default true).
			boolean insertable() default true;//insertable (optional): whether or not the column will be part of the insert statement (default true)
			boolean updatable() default true;//updatable (optional): whether or not the column will be part of the update statement (default true)
			String columnDefinition() default "";//columnDefinition (optional): override the sql DDL fragment for this particular column (non portable)
			String table() default "";//table (optional): define the targeted table (default primary table)
			int length() default 255;//length (optional): column length (default 255)
	int precision() default 0; // decimal precision//precision (optional): column decimal precision (default 0)
	int scale()) default 0; // decimal scale//scale (optional): column decimal scale if useful (default 0)


	@Id private String code;

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
