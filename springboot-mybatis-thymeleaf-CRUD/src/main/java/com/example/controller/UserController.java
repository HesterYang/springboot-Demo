package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.UserEntity;
import com.example.mapper.UserMapper;

@Controller
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/list";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "user/add";
	}
	
	@RequestMapping("/addUser")
	public String addUser(UserEntity user) {
		userMapper.insert(user);
		return "redirect:/list";
	}

	@RequestMapping("/list")
	public String userList(Model model) {
		model.addAttribute("users", userMapper.getAll());
		return "user/list";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(Long id,Model model) {
		UserEntity user = userMapper.getOne(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	@RequestMapping("/edit")
	public String edit(UserEntity user) {
		userMapper.update(user);
		return "redirect:/list";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(Long id) {
		userMapper.delete(id);
		return "redirect:/list";
	}
	
	@RequestMapping("/getUsers")
	@ResponseBody
	public List<UserEntity> getUsers(){
		List<UserEntity> list = userMapper.getAll();
		return list;
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public UserEntity getUser(Long id) {
		UserEntity user = userMapper.getOne(id);
		return user;
	}
	
	@RequestMapping("/add")
	public void save(UserEntity user) {
		userMapper.insert(user);
	}
	
	@RequestMapping(value="update")
	public void uodate(UserEntity user) {
		userMapper.update(user);
	}
	
	@RequestMapping(value="/delete/{id}")
	public void delete(@PathVariable("id")Long id) {
		userMapper.delete(id);
	}
}
