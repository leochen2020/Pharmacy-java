package com.easy.data.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.easy.data.entity.User;

public interface UserRepository extends CrudRepository<User, Long>, QueryByExampleExecutor<User>  {

	@Query(value = "from User where name = ?1 and password = ?2")
    public User getUser(String name, String password);
	
	@Query(value = "from User where name = ?1")
    public User getUserByName(String name);
}
