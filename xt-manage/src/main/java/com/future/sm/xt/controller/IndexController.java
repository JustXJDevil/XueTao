package com.future.sm.xt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	@Value("${server.port}")
	private String port;

	/**
	 * @PathVariable("moduleName") String moduleName
	 * 当接收参数名和传递参数名一致时可以省略注解@PathVariable里面的参数
	 */
	@RequestMapping("/page/{moduleName}")
	public String module(@PathVariable("moduleName") String moduleName) {
		return moduleName;
	}


	@RequestMapping("hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}

	@ResponseBody
	@RequestMapping("/getPort")
	public String getPort(){
		return port;
	}
}
