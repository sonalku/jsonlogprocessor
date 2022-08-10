package com.jsonlogprocessor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
/**
 * Command Line Runner
 * 
 * @author Sonal Kumbhare
 *
 */
@Configuration
public class CommandLineTaskExecutor implements CommandLineRunner {

	private TaskService taskService;
	
	
	public CommandLineTaskExecutor(TaskService taskService) {
		this.taskService = taskService;
	}


	public void run(String... args) throws Exception {
		 taskService.execute(args[0]);
	}

}
