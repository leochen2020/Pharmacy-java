package com.easy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.easy.data.dao.UserRepository;
import com.easy.data.entity.User;
import com.easy.data.entity.UserRole;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public Optional<User> getById(long id) {
		return userRepository.findById(id);
	}
	
	public User getUser(String name, String password) {
		return userRepository.getUser(name, password);
	}
	
	public User getUser(String name) {
		if (userRepository.count() < 1) {
			User u = new User();
			u.setName("admin");
			u.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode("admin"));
//			u.setRoles(rs);
			userRepository.save(u);
		}
		return userRepository.getUserByName(name);
	}
	
	@Transactional
	public List<String> getUserRole(String name) {
		User u = userRepository.getUserByName(name);
		List<UserRole> rs = u.getRoles();
		for (UserRole r : rs) {
			System.out.println(r);
		}
		return u.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList());
	}

	@Transactional
	public void update(User user) {
		Optional<User> sessionUser = userRepository.findById(user.getId());
		if (sessionUser.isPresent()) {
			User u = sessionUser.get();
			u.setName(user.getName());
			u.setPassword(user.getPassword());
			userRepository.save(u);
		}
	}
}
