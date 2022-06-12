package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.List;

@RestController
@RequestMapping("/adminRest")
public class AdminsRestController {
	private final UserServiceImp userService;

	@Autowired
	public AdminsRestController(UserServiceImp userService) {
		this.userService = userService;
	}

	@PutMapping()
	public User updateUser(@RequestBody User user) {
		userService.saveUser(user);
		return user;
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Long id) {
		 return userService.getUser(id);
	}

	@GetMapping("/user")
	public User getUserByUsername () {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@GetMapping()
	public List<User> printUser() {
		return userService.listAll();
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "Its all";
	}

	@PostMapping()
	public User createUser(@RequestBody User user) {
		userService.saveUser(user);
		return user;
	}
}
