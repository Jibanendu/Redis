package com.redistest;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class redisApplication implements CommandLineRunner{
	private final org.slf4j.Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(redisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		LOG.info("Saving users. Current user count is {}.", userRepository.count());
	    User jibanendu = new User(2,"Jibanendu");
	    User sonali = new User(1,"Sonali");
	    
	    userRepository.save(jibanendu);
	    userRepository.save(sonali);
	    
	    LOG.info("All Users are Saved:"+userRepository.findAll());
	  
	}
	


}
