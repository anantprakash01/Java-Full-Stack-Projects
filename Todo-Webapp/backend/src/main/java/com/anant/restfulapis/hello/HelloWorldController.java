package com.anant.restfulapis.hello;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path="/basicauth")
	public String BasicAuthCheck() {
		return "Success";
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World v2";
	}
	
	@GetMapping("/hello-world")
	public HelloBean helloWorldBean() {
		return new HelloBean("Hello World Bean");
	}
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloBean helloWorldParam(@PathVariable String name) {
		return new HelloBean(String.format("Hello World, %s", name));
	}
}
