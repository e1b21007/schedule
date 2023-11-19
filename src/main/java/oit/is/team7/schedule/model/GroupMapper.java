package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GroupMapper {
  /**
   * groupidからgroupnameを取得する
   *
   * @param groupid int グループID
   * @return ArrayList<entry> groupid-useridのリスト
   */
  @Select("SELECT GROUPNAME FROM SGROUP WHERE GROUPID = #{groupid}")
  String selectGroupNameByGroupid(int groupid);

  @Select("SELECT GROUPID, GROUPNAME FROM SGROUP where GROUPID = #{groupid}")
  Group selectSgroupByGroupid(int groupid);

  /**
   * groupTableを取得する
   *
   * @return ArrayList<group> groupid-groupnameのリスト
   */
  @Select("SELECT GROUPID, GROUPNAME FROM SGROUP")
  ArrayList<Group> selectAllSgroup();
}
