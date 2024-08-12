package com.cac.autord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.cac.autord.persistence.entity")
@EnableJpaRepositories
public class AutorDeadApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutorDeadApplication.class, args);
	}

}
