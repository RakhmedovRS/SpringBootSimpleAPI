package com.github.rakhmedovrs.springboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author RakhmedovRS
 * @created 13-Mar-20
 */
@Component
public class WelcomeService
{
	@Value("${welcome.message}")
	private String welcomeMessage;

	public String getWelcomeResponse()
	{
		return welcomeMessage;
	}
}
