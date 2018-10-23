package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.domain.UserEntity;

public interface UserMapper {
	@Select("select * from user")
	List<UserEntity> getAll();
	
	@Select("select * from user where id = #{id}")
	UserEntity getOne(Long id);
	
	@Insert("insert into user(username,password,age) values(#{username},#{password},#{age})")
	void insert(UserEntity user);
	
	@Update("update user set username = #{username},password = #{password},age = #{age} where id = #{id}")
	void update(UserEntity user);
	
	@Delete("delete from user where id = #{id}")
	void delete(Long id);
}
