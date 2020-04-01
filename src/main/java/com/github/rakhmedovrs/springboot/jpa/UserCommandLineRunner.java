package com.github.rakhmedovrs.springboot.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author RakhmedovRS
 * @created 01-Apr-20
 */
@Component
public class UserCommandLineRunner implements CommandLineRunner
{
	@Override
	public void run(String... args) throws Exception
	{
		System.out.println("UserCommandLineRunner");
	}
}
