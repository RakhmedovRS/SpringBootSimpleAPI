package com.github.rakhmedovrs.springboot.jpa;

import java.util.StringJoiner;
import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 01-Apr-20
 */
@Entity
@Table(name = "USERS")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String role;

	protected User(){}

	public User(String name, String role)
	{
		this.name = name;
		this.role = role;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
			.add("id=" + id)
			.add("name='" + name + "'")
			.add("role='" + role + "'")
			.toString();
	}
}
