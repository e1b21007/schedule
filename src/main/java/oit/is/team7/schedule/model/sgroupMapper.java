package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface sgroupMapper {
  /**
   * groupidからsgroupTableを取得する
   * 
   * @param groupid int グループID
   * @return ArrayList<entry> groupid-useridのリスト
   */
  @Select("SELECT GROUPNAME FROM SGROUP WHERE GROUPID = #{groupid}")
  sgroup selectSgroupByGroupid(int groupid);

  /**
   * groupTableを取得する
   * 
   * @param
   * @return ArrayList<group> groupid-groupnameのリスト
   */
  @Select("SELECT GROUPID, GROUPNAME FROM SGROUP")
  ArrayList<sgroup> selectAllGroup();
}
