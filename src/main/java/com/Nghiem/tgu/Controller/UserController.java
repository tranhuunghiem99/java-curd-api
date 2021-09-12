package com.Nghiem.tgu.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


import com.Nghiem.tgu.Model.User;
import com.Nghiem.tgu.Repository.UserRepository;

import Exception.ResourceNotFoundException;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@GetMapping("/listsv")
	public List<User> getList(){
		return userRepository.findAll();
	}
	@GetMapping("/listsv/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		return ResponseEntity.ok(user);
	}
	@PostMapping("/listsv")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	@PutMapping("/listsv/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("USER not exist with id :" + id));
		user.setDoi_tuong(userDetails.getDoi_tuong());
		user.setEmail(userDetails.getEmail());
		user.setHo_ten(userDetails.getHo_ten());
		user.setPassword(userDetails.getPassword());
		user.setUsername(userDetails.getUsername());
		User updateUser = userRepository.save(user);
		return ResponseEntity.ok(updateUser);
	}
	@DeleteMapping("/listsv/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
