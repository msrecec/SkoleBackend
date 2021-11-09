package com.baze.skole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SkoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkoleApplication.class, args);
	}

}
