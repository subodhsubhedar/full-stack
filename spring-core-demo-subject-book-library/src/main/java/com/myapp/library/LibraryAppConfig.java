package com.myapp.library;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.myapp.library.client.LibraryClient;

@Configuration
@ComponentScan("com.myapp.library")
public class LibraryAppConfig {

	public static void main(String[] args) {

		try {
			ApplicationContext ctx = new AnnotationConfigApplicationContext(LibraryAppConfig.class);

			LibraryClient libraryClient = ctx.getBean(LibraryClient.class);
			System.out.println(Arrays.asList(ctx.getBeanDefinitionNames()));

			libraryClient.initClient();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
