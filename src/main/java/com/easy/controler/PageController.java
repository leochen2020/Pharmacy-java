package com.easy.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.easy.data.dao.UserRoleRepository;
import com.easy.service.ItemService;
import com.easy.service.UserService;

@CrossOrigin
@Controller
public class PageController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserRoleRepository userRoleRepository;

    @GetMapping("/login")
    public String login() {
    	
        return "login.html";
    }
}
