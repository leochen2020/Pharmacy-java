package com.easy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.easy.data.dao.UserRepository;
import com.easy.data.dao.UserRoleRepository;
import com.easy.data.entity.User;
import com.easy.data.entity.UserRole;

@SpringBootTest
class XcApplicationTestsUser {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
	@Test
	void contextLoads() {
//		System.out.println("init");
//		
//		userRepository.deleteAll();
//		userRoleRepository.deleteAll();
//		
//		User u = new User();
//		u.setName("a");
//		u.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode("a"));
//		
//		List<UserRole> rs = new ArrayList<UserRole>();
//		
//		UserRole r = new UserRole();
//		r.setRole("admin");
//		userRoleRepository.save(r);
//		rs.add(r);
//		
//		r = new UserRole();
//		r.setRole("user");
//		userRoleRepository.save(r);
//		rs.add(r);
//		
//		u.setRoles(rs);
//		userRepository.save(u);
//		List<Item> removeItems = itemService.getRemoved();
//		System.out.println(removeItems.size());
	}

}
