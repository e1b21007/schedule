package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
  @Select("SELECT GROUPID, GROUPNAME, USERID FROM GROUPS")
  ArrayList<Groups> selectAllSgroup();

  /**
   * groupTableを取得する
   * @parm int userid 管理者id
   * @return ArrayList<group> userid の管理するグループのリスト
   */
  @Select("SELECT GROUPID, GROUPNAME, USERID FROM GROUPS Where userid = #{userid}")
  ArrayList<Groups> selectAdminSgroup(int userid);

  /**
   * groupのインサート文
   *
   * @param group Groups グループ
   */
  @Insert("INSERT INTO Groups (groupname,userid) VALUES (#{groupname},#{userid})")
  @Options(useGeneratedKeys = true, keyColumn = "groupid")
  void InsertGroupbyGroup(Groups group);

  @Delete("DELETE FROM GROUPS WHERE GROUPID = #{groupid}")
  void DeleteGroupByGroupid(int groupid);


}
