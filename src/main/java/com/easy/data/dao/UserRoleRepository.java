package com.easy.data.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.easy.data.entity.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>, QueryByExampleExecutor<UserRole> {

	@Query(value = "from UserRole where role = ?1")
    public UserRole getByName(String name);
}
