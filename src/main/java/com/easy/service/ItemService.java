package com.easy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.data.dao.BatchRepository;
import com.easy.data.dao.ItemRepository;
import com.easy.data.entity.Batch;
import com.easy.data.entity.History;
import com.easy.data.entity.Item;

@Service
public class ItemService {
	
	private static Date COUNT_DATE = null;

	@Autowired
	private ItemRepository repository;
	
	@Autowired
	private BatchRepository batchRepository;
	
	public void saveItem(Item item) {
		item.setUpdateTime(Item.DATA_FORMAT.format(new Date()));
		
		repository.save(item);
	}
	
	public Item getById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void updateImg(Long id, String path) {
		Item item = getById(id);
		if (item != null) {
			item.setImg(path);
			saveItem(item);
		}
	}
	
	public void addBatch(Item item, int number, Date overTime, String user) {
		item.addBatch(number, user, overTime);
		item.addHistory(user, History.ADD);
		saveItem(item);
	}
	
	public void removeBatch(Item item, int out, String user) {
		List<Batch> batchs = item.getBatch();
        Collections.sort(batchs);
		
        int all = out;
		for (int i = batchs.size() - 1; i >=0; i--) {
			Batch batch = batchs.get(i);
			if (batch.getNumber() >= out) {
				batch.setNumber(batch.getNumber() - out);
				break;
			}
			
			batch.setNumber(0);
			out -= batch.getNumber();
		}
		
		for (int i = batchs.size() - 1; i >=0; i--) {
			Batch batch = batchs.get(i);
			if (batch.getNumber() < 1) {
				batchs.remove(batch);
				batchRepository.delete(batch);
			}
		}
		
		item.setNumber(item.getNumber() - all);		
		item.addHistory(user, History.DELETE);
		item.updateOverTime();
		saveItem(item);
	}
	
	public String getUniName(String name) {
		String newName = name;
		 List<Item> items = repository.getByName(newName);
		 int i = 0;
		 while (!items.isEmpty()) {
			 newName = name + i++;
			 items = repository.getByName(newName);
		 }
		 
		 return newName;
	}
	
	public List<Item> getAllItems() {
		
		boolean count = false;
		
		if (COUNT_DATE == null) {
			COUNT_DATE = new Date();
			count = true;
		} else {
			Date now = new Date();
			int hour = (int) (now.getTime() - COUNT_DATE.getTime() / (1000*3600));
			if (hour > 1) {
				count = true;
			}
		}
		
		List<Item> all = new ArrayList<>();
		Iterable<Item> items = repository.findAll();
		for (Item item : items) {
			if (count) {
				item.updateOverTime();
				item.isOver();
				repository.save(item);
			}
			all.add(item);
		}
		return all;
	}
	
	//库存小于采购点
	public List<Item> getBuyPointItems() {
		List<Item> all = new ArrayList<>();
		Iterable<Item> items = repository.findAll();
		for (Item item : items) {
//			item.
		}
		return all;
	} 

}
