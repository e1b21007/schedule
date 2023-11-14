package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GroupScheduleMapper {

  /**
   * groupidからscheduleidを取得する
   *
   * @param groupid int グループID
   * @return ArrayList<Integer> scheduleidのリスト
   */
  @Select("SELECT * FROM groupSchedule WHERE GROUPID = #{groupid}")
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
   * scheduleidから終了時刻を修正する
   *
   * @param scheduleid int スケジュールid
   * @param owari String 終了時刻
   * @return void
   */
  @Update("UPDATE groupSchedule SET owari= #{owari} WHERE Scheduleid = #{scheduleid}")
  void UpdateOwaribyScheduleid(int scheduleid, String owari);

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

}
