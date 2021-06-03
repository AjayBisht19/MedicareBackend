package com.medicare;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.medicare.model.User;
import com.medicare.repo.UserRepository;

@SpringBootApplication
@EnableAsync
public class MedicareBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MedicareBackendApplication.class, args);
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepoitory;

	@Override
	public void run(String... args) throws Exception {
		try {
			System.out.println(this.userRepoitory.findByUsername("admin123"));
			if (this.userRepoitory.findByUsername("admin123") == null) {
				User user = new User();
				user.setName("Administrator");
				user.setEmail("admin@medicare.com");
				user.setIsEnabled(true);
				user.setPassword(passwordEncoder.encode("123456"));
				user.setUsername("admin123");
				user.setRole("admin");
				userRepoitory.save(user);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("admin created");
		}

	}
}
