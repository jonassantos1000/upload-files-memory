package com.upload.file.upload.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UploadFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadFileApplication.class, args);
	}

}
