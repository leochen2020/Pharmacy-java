package com.easy.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.data.dao.UserRepository;
import com.easy.data.entity.User;

@CrossOrigin
@RestController // This means that this class is a Controller
@RequestMapping(path="/admin") // This means URL's start with /demo (after Application path)
public class AdminController {
  @Autowired 
  private UserRepository userRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String name
      , @RequestParam String password) {
    User n = new User();
    n.setName(name);
    n.setPassword(password);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/users")
  @PreAuthorize("hasRole('admin')")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }
  
  @GetMapping(path="/all")
  @PreAuthorize("hasRole('user')")
  public @ResponseBody User getUsers(@RequestParam String name) {
    // This returns a JSON or XML with the users
    return userRepository.getUserByName(name);
  }
}
