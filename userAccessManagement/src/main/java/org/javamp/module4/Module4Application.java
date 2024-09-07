package org.javamp.module4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ConfigurationProperties
@PropertySource("classpath:application.yml")
public class Module4Application {

	public static void main(String[] args) {
		SpringApplication.run(Module4Application.class, args);
	}

}
