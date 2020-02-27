package com.easy.controler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easy.data.dao.UserRoleRepository;
import com.easy.data.entity.Batch;
import com.easy.data.entity.History;
import com.easy.data.entity.Item;
import com.easy.data.entity.User;
import com.easy.data.entity.UserRole;
import com.easy.filter.jwt.JwtUtil;
import com.easy.rest.ResultVO;
import com.easy.service.ItemService;
import com.easy.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/all")
public class OtherUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@GetMapping("/token")
	public ResultVO login(@RequestParam String name, @RequestParam String password) {

		if (name == null || password == null || name.trim().length() == 0 || password.trim().length() == 0) {
			return new ResultVO(40, "用户名和密码不能为空");
		}

		User user = userService.getUser(name);

		if (user == null) {
			return new ResultVO(40, "用户名或密码错误");
		}

		if (!new BCryptPasswordEncoder().matches(password, user.getPassword().substring(8))) {
			return new ResultVO(40, "用户名或密码错误");
		}

		return new ResultVO(10, "", user.getName());
	}

	@GetMapping("/item")
	public ResultVO addItem(@RequestParam String id, @RequestParam String name, @RequestParam String buyPoint,
			@RequestParam String code, @RequestParam String minNumber, @RequestParam String maxNumber,
			@RequestParam String unit) {

		Item item = null;
		Long itemId = Long.parseLong(id);
		if (itemId < 0) {
			item = new Item();
		} else {
			item = itemService.getById(itemId);
		}

		if (item == null) {
			return new ResultVO(40, "找不到该耗材");
		}

		item.setName(name.trim());
		item.setCode(code);
		item.setBuyPoint(Integer.parseInt(buyPoint));
		item.setMinNumber(Integer.parseInt(minNumber));
		item.setMaxNumber(Integer.parseInt(maxNumber));
		item.setUnit(unit);
		itemService.saveItem(item);
		return new ResultVO(10, "", toRestful(item));
	}

	@GetMapping("/addItemBatch")
	public ResultVO addItemBatch(@RequestParam String itemId, String overTime, String number, String user)
			throws NumberFormatException, ParseException {

		Item item = itemService.getById(Long.parseLong(itemId));
		if (item == null) {
			return new ResultVO(40, "找不到该耗材");
		}

		itemService.addBatch(item, Integer.parseInt(number), Item.DATA_FORMAT.parse(overTime), user);
		return new ResultVO(10, "", toRestful(item));
	}

	@GetMapping("/outItemBatch")
	public ResultVO outItemBatch(@RequestParam String itemId, String number, String user)
			throws NumberFormatException, ParseException {

		Item item = itemService.getById(Long.parseLong(itemId));
		if (item == null) {
			return new ResultVO(40, "找不到该耗材");
		}

		int out = Integer.parseInt(number);
		if (item.getNumber() < out) {
			return new ResultVO(40, "库存不足");
		}

		itemService.removeBatch(item, out, user);
		return new ResultVO(10, "", toRestful(item));
	}

	public Item toRestful(Item item) {
		for (Batch b : item.getBatch()) {
			b.setItem(null);
		}

		for (History b : item.getHistory()) {
			b.setItem(null);
		}

		return item;
	}

	@GetMapping("/items")
	public ResultVO items() {
		List<Item> items = itemService.getAllItems();
		for (Item item : items) {
			for (Batch b : item.getBatch()) {
				b.setItem(null);
			}

			for (History b : item.getHistory()) {
				b.setItem(null);
			}
		}
		return new ResultVO(10, "", items);
	}

//    @GetMapping("/items")
//    public ResultVO items() {
//    	
////    	itemService.get
//
//        return new ResultVO(10, "", JwtUtil.generateToken(user));
//    }

	@PostMapping("/oauth")
	public ResultVO oauth(@RequestParam String openId, @RequestParam String nickName) {
		User user = userService.getUser(nickName);

		if (user == null) {
			user = new User();
			user.setName(nickName);
			user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(openId));

			UserRole role = userRoleRepository.getByName("user");
			List<UserRole> roles = new ArrayList<UserRole>();
			if (role != null) {
				roles.add(role);
			}
			user.setRoles(roles);
			userService.save(user);
		}

		return new ResultVO(10, "", JwtUtil.generateToken(user));
	}
}
