package com.github.rakhmedovrs.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author RakhmedovRS
 * @created 12-Mar-20
 */
@SpringBootApplication
public class Application
{
	public static void main(String[] args)
	{
		ApplicationContext context = SpringApplication.run(Application.class, args);
	}
}
