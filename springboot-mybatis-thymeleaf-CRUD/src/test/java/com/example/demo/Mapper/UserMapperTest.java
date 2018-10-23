package com.example.demo.Mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.UserEntity;
import com.example.mapper.UserMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
public class UserMapperTest {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testInsert() throws Exception {
		userMapper.insert(new UserEntity("aa","123456",12));
		userMapper.insert(new UserEntity("bb","123456",34));
		userMapper.insert(new UserEntity("cc","123456",23));
		
		Assert.assertEquals(3, userMapper.getAll().size());
	}
	
	@Test
	public void testQuery() throws Exception {
		List<UserEntity> list = userMapper.getAll();
		System.out.println(list.toString());
	}
	
	@Test
	public void testUpdate() {
		UserEntity user = userMapper.getOne(3l);
		System.out.println(user.toString());
		user.setUsername("123");
		user.setPassword("123");
		user.setAge(22);
		userMapper.update(user);
		Assert.assertTrue("123".equals(userMapper.getOne(3l).getUsername()));
	}
}
