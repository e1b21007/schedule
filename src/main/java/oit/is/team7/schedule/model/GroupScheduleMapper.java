package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface GroupScheduleMapper {

  /**
   * groupidからscheduleのリストを日付が若い順かつ同日なら開始時刻が早い順かつどちらも同じならスケジュールID順に取得する
   *
   * @param groupid int グループID
   * @return ArrayList<Integer> GroupScheduleのリスト
   */
  @Select("SELECT * FROM groupSchedule WHERE GROUPID = #{groupid} ORDER BY hizuke, kaisi, scheduleid")
  ArrayList<GroupSchedule> selectgroupScheduleByGroupid(int groupid);

  /**
   * scheduleidからgroupScheduleを取得する
   *
   * @param scheduleid int スケジュールid
   * @return groupSchedule groupSchedule一つ
   */
  @Select("SELECT * FROM groupSchedule WHERE Scheduleid = #{scheduleid}")
  GroupSchedule getgroupScheduleByScheduleid(int scheduleid);

  /**
   * scheduleidからGroupScheduleを修正する
   *
   * @param scheduleid int スケジュールid
   * @param hizuke     String 予定の日付
   * @param kaisi      String 予定の開始時刻
   * @param owari      String 予定の終了時刻
   * @param groupid    int グループID
   * @param title      String タイトル
   * @param content    String 本文
   * @return void
   */

  @Update("UPDATE GroupSchedule SET hizuke=#{hizuke}, kaisi= #{kaisi}, owari= #{owari}, title= #{title},content= #{content} WHERE ScheduleId = #{ScheduleId}")
  void UpdateGroupScheduleByScheduleId(int ScheduleId, String hizuke, String kaisi, String owari, String title,
      String content);

  /**
   * groupScheduleのインサート文
   *
   * @param hizuke  String 予定の日付
   * @param kaisi   String 予定の開始時刻
   * @param owari   String 予定の終了時刻
   * @param groupid int グループID
   * @param title   String タイトル
   * @param content String 本文
   */
  @Insert("INSERT INTO groupSchedule (hizuke, kaisi, owari, groupid,title,content) VALUES (#{hizuke}, #{kaisi}, #{owari}, #{groupid},#{title},#{content});")
  @Options(useGeneratedKeys = true, keyColumn = "scheduleid")
  void insertGroupSchedule(String hizuke, String kaisi, String owari, int groupid, String title, String content);

  /**
   * groupScheduleのDelete文
   *
   * @param scheduleid int スケジュールID
   * @return void
   */
  @Delete("DELETE FROM GroupSchedule WHERE ScheduleId = #{ScheduleId};")
  void DeleteGroupScheduleByScheduleId(int ScheduleId);

}
