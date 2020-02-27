package com.easy;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.easy.data.dao.ItemRepository;
import com.easy.data.entity.Batch;
import com.easy.data.entity.History;
import com.easy.data.entity.Item;
import com.easy.service.ItemService;

@SpringBootTest
class EasySystemApplicationTests2 {

	@Autowired
    private ItemService itemService;
	@Test
	void contextLoads() {
		System.out.println(itemService.getAllItems());
	}

}
