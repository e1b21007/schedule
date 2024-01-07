package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
  // 一致するidの情報をとってくる
  @Select("select * from Account where userid = #{id}")
  Account selectById(int id);

  // 一致する名前の情報をとってくる
  @Select("select * from Account where username = #{name}")
  Account selectByname(String name);

  // ユーザ全員の情報をとってくる
  @Select("select * from Account")
  ArrayList<Account> selectAllByAccount();

  // 他ユーザー全員の情報をとってくる
  @Select("select * from Account where userid != #{userid}")
  ArrayList<Account> selectOtherByUserid(int userid);

  @Insert("INSERT INTO account (user_name, user_pass) VALUES (#{userName},#{userPass})")
  @Options(useGeneratedKeys = true, keyColumn = "account_Id")
  void InsertAccount(String userName, String userPass);
}
