package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GroupsMapper {
  /**
   * groupidからgroupnameを取得する
   *
   * @param groupid int グループID
   * @return ArrayList<entry> groupid-useridのリスト
   */
  @Select("SELECT GROUPNAME FROM GROUPS WHERE GROUPID = #{getGroupname(groupid)}")
  String selectGroupNameByGroupid(int groupid);

  @Select("SELECT GROUPID, GROUPNAME FROM GROUPS where GROUPID = #{groupid}")
  Groups selectSgroupByGroupid(int groupid);

  @Select("SELECT  GROUPID, GROUPNAME FROM GROUPS WHERE  GROUPID = #{groupid}")
  ArrayList<Groups> selectGroupByGroupid(int groupid);

  @Select("SELECT MAX(GROUPID), FROM (SELECT GROUPID FROM GROUPS WHERE GROUPNAME = #{groupname})")
  int selectMaxGroupByGroupname(String groupname);

  /**
   * groupTableを取得する
   *
   * @return ArrayList<group> groupid-groupnameのリスト
   */
  @Select("SELECT GROUPID, GROUPNAME FROM GROUPS")
  ArrayList<Groups> selectAllSgroup();

  /**
   * groupのインサート文
   *
   * @param group Groups グループ
   */
  @Insert("INSERT INTO Groups (groupname) VALUES (#{groupname})")
  @Options(useGeneratedKeys = true, keyColumn = "groupid")
  void InsertGroupbyGroup(Groups group);

  @Delete("Delete from Groups where groupid=(#{groupid})")
  void deleteById(int groupid);

}
