package com.example.groupproject404;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GroupProject404Application {

	public static void main(String[] args) {
		SpringApplication.run(GroupProject404Application.class, args);
	}

}
