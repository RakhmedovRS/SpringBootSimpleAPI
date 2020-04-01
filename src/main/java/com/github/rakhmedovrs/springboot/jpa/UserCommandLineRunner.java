package com.github.rakhmedovrs.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author RakhmedovRS
 * @created 01-Apr-20
 */
@Component
public class UserCommandLineRunner implements CommandLineRunner
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserCommandLineRunner.class);

	@Autowired
	private UserRepository repository;

	@Override
	public void run(String... args) throws Exception
	{
		repository.save(new User("user", "user"));
		repository.save(new User("administrator", "administrator"));
		repository.save(new User("manager", "manager"));

		for (User user: repository.findAll())
		{
			LOGGER.info(user.toString());
		}

		for (User user: repository.findByRole("administrator"))
		{
			LOGGER.info(user.toString());
		}
	}
}
