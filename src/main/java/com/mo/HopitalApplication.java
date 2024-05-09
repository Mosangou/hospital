package com.mo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class HopitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//@Bean
	CommandLineRunner commandLineRunnerJdbcUserDetailsManager(JdbcUserDetailsManager jdbcUserDetailsManager) {
		PasswordEncoder passwordEncoder = passwordEncoder();
		return args ->{
			jdbcUserDetailsManager.createUser(
					User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build()
			);
			jdbcUserDetailsManager.createUser(
					User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build()
			);
			jdbcUserDetailsManager.createUser(
					User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build()
			);
			
		};
	}

}
