package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.List;

@RestController
@RequestMapping("/adminRest")
public class AdminsRestController {
	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	public AdminsRestController(UserServiceImp userService, RoleServiceImp roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@PutMapping()
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.saveUser(user);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(userService.getUser(id));
	}

	@GetMapping("/user")
	public ResponseEntity<User> getUserByUsername () {
		return ResponseEntity.ok().body((User) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal());
	}

	@GetMapping()
	public ResponseEntity<List<User>> printUser() {
		return ResponseEntity.ok().body(userService.listAll());
	}

	@GetMapping("/roles")
	public ResponseEntity<List<Role>> printRoles() {
		return ResponseEntity.ok().body(roleService.listAll());
	}

	@GetMapping("/role/{id}")
	public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(roleService.getRole(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().body("User deleted");
	}

	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userService.saveUser(user);
		return ResponseEntity.ok().body(user);
	}
}
