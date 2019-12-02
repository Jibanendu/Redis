package com.redistest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	private final UserRepository userRepository; 
	 
	@Autowired
	public Controller(UserRepository userRepository) {
	    this.userRepository = userRepository;
	  }
	
	@GetMapping(value="/{userId}")
	@Cacheable(value = "users", key = "#userId")
	public User userInfo(@PathVariable Integer userId) {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent())
		{
			return user.get(); 
			
		}
		return null;
		
	}
	
	
	@CacheEvict(value = "users", allEntries=true)
	@DeleteMapping("/delete/{id}")
	public void deleteUserByID(@PathVariable int id) {
	  System.out.println("deleting person with id {}"+id);
	  userRepository.deleteById(id);
	}

}
