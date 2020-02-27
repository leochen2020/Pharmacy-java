package com.easy.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.easy.data.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long>, QueryByExampleExecutor<Item> {
	
	@Query(value = "from Item where name = ?1")
    public List<Item> getByName(String name);
	
	@Query(value = "from Item where code = ?1")
    public List<Item> getByCode(String code);
	
	@Query(value = "from Item where removed = true")
	public List<Item> getRemoved();
	
	@Query(value = "from Item where number <= buyPoint")
    public List<Item> getByBuyPoint();
	
	@Query(value = "from Item where number <= minNumber")
    public List<Item> getByMin();
	
	@Query(value = "from Item where number <= minNumber")
    public List<Item> getByOver();
}