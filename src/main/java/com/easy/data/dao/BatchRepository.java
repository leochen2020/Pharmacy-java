package com.easy.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.easy.data.entity.Batch;
import com.easy.data.entity.Item;

public interface BatchRepository extends CrudRepository<Batch, Long>, QueryByExampleExecutor<Batch> {

}