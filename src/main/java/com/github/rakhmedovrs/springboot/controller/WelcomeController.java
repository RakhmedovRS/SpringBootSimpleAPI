package com.github.rakhmedovrs.springboot.controller;

import com.github.rakhmedovrs.springboot.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RakhmedovRS
 * @created 13-Mar-20
 */
@RestController
public class WelcomeController
{
	@Autowired
	private WelcomeService welcomeService;

	@RequestMapping(path = "/welcome")
	public String welcome()
	{
		return welcomeService.getWelcomeResponse();
	}
}
