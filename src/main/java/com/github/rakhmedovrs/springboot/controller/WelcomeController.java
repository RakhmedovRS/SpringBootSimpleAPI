package com.github.rakhmedovrs.springboot.controller;

import com.github.rakhmedovrs.springboot.configuration.BasicConfiguration;
import com.github.rakhmedovrs.springboot.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RakhmedovRS
 * @created 13-Mar-20
 */
@RestController
public class WelcomeController
{
	@Autowired
	private WelcomeService welcomeService;
	@Autowired
	private BasicConfiguration basicConfiguration;

	@RequestMapping(path = "/welcome")
	public String welcome()
	{
		return welcomeService.getWelcomeResponse();
	}

	@RequestMapping(path = "/dynamic-configuration")
	public Map<String, Object> dynamicConfiguration()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("value", basicConfiguration.isValue());
		map.put("number", basicConfiguration.getNumber());
		map.put("message", basicConfiguration.getMessage());
		return map;
	}
}
