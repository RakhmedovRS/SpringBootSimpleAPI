package com.github.rakhmedovrs.springboot.model;

import java.util.List;
import javax.validation.constraints.NotEmpty;

/**
 * @author RakhmedovRS
 * @created 15-Mar-20
 */
public class Question
{
	private String id;
	@NotEmpty
	private String description;
	@NotEmpty
	private String correctAnswer;
	@NotEmpty
	private List<String> options;

	public Question()
	{
	}

	public Question(String id, String description, String correctAnswer, List<String> options)
	{
		this.id = id;
		this.description = description;
		this.correctAnswer = correctAnswer;
		this.options = options;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCorrectAnswer()
	{
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}

	public List<String> getOptions()
	{
		return options;
	}

	public void setOptions(List<String> options)
	{
		this.options = options;
	}

	@Override
	public String toString()
	{
		return String
			.format("Question [id=%s, description=%s, correctAnswer=%s, options=%s]",
				id, description, correctAnswer, options);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Question other = (Question) obj;
		if (id == null)
		{
			return other.id == null;
		}
		else
		{
			return id.equals(other.id);
		}
	}
}
