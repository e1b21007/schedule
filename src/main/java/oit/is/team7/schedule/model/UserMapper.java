package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  // 一致するidの情報をとってくる
  @Select("select * from users where userid = #{id}")
  User selectById(int id);

  // 一致する名前の情報をとってくる
  @Select("select * from users where username = #{name}")
  User selectByname(String name);

  // ユーザ全員の情報をとってくる
  @Select("select * from users")
  ArrayList<User> selectAllByUsers();

  // 他ユーザー全員の情報をとってくる
  @Select("select * from users where userid != #{userid}")
  ArrayList<User> selectOtherByUserid(int userid);
}
