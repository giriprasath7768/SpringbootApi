package com.api.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.demo.Entity.User;
import com.api.demo.Exception.ResourceNotFoundException;
import com.api.demo.Repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	@Autowired
	private UserRepository userrepo;
	
	@GetMapping
	public List<User>getAllUser()
	{
		return this.userrepo.findAll();
		
		
	}
	
	//get user By ID
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value="id") long userid)
	{
		return this.userrepo.findById(userid)
		.orElseThrow(()-> new ResourceNotFoundException("User Not found wit this id:"+userid));
		
	}
	//create users
	@PostMapping
	public User createUser(@RequestBody User user)
	{
		return this.userrepo.save(user);
		
	}
	
	
	//update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user ,@PathVariable("id") long userid)
	{
		
		User existinguser=this.userrepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User Not found wit this id:"+userid));
		
		existinguser.setFirstName(user.getFirstName());
		existinguser.setLastName(user.getLastName());
		existinguser.setEmail(user.getEmail());
		
		
		
		return this.userrepo.save(existinguser);
		
	}
	
	//delete user By id
	@DeleteMapping("/{id}")
	public ResponseEntity<User>deleteuser(@PathVariable("id") long userid)
	{
		User existinguser=this.userrepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User Not found wit this id:"+userid));
	     this.userrepo.delete(existinguser);
		return ResponseEntity.ok().build();
	     
		
	}
	

}
