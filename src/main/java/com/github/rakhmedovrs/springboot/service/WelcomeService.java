package com.github.rakhmedovrs.springboot.service;

import org.springframework.stereotype.Component;

/**
 * @author RakhmedovRS
 * @created 13-Mar-20
 */
@Component
public class WelcomeService
{
	public String getWelcomeResponse()
	{
		return "welcome";
	}
}
