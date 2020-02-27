package com.easy;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.easy.data.dao.ItemRepository;
import com.easy.data.entity.History;
import com.easy.data.entity.Item;

@SpringBootTest
class EasySystemApplicationTests {

	@Autowired
    private ItemRepository itemRepository;
	
	@Test
	void contextLoads() {
//		itemRepository.deleteAll();
//		System.out.println(itemRepository.count());
//		Random r = new Random();
//		for (int i = 0; i < 15; i++) {
//			Item item = new Item();
//			item.setName("20ml注射器" + i);
//			item.setUnit("个");
//			item.setCode("123456");
//			item.setMinNumber(20);
//			item.setMaxNumber(100);
//			item.setBuyPoint(50);
//			
//			History h = new History();
//			h.setName(History.NEW);
//			h.setTime(new Date());
//			h.setItem(item);
//			h.setUser("a");
//			
//			item.getHistory().add(h);
//			
//			Calendar c = Calendar.getInstance();  
//            c.setTime(new Date());  
//            c.add(Calendar.DAY_OF_MONTH, 1);
//			item.addBatch(r.nextInt(50), "张三", c.getTime());
//			item.addBatch(r.nextInt(50), "李四", c.getTime());
//			
//			
//			itemRepository.save(item);
//		}
		
		
		
		
//		Item item = itemRepository.findById(1l).get();
//		System.out.println(item.getHistory());
	}

}
