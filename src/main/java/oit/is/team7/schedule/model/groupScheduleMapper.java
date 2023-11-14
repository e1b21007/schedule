package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface groupScheduleMapper {

  /**
   * groupidからscheduleidを取得する
   *
   * @param groupid int グループID
   * @return ArrayList<Integer> scheduleidのリスト
   */
  @Select("SELECT * FROM groupSchedule WHERE GROUPID = #{groupid}")
  ArrayList<groupSchedule> selectgroupScheduleByGroupid(int groupid);

  /**
   * groupidとhizukeからscheduleidを取得する
   *
   * @param groupid int グループID
   * @param hizuke String 日付
   * @return ArrayList<Integer> scheduleidのリスト
   */
  @Select("SELECT scheduleid FROM groupSchedule WHERE GROUPID = #{groupid} and hizuke = #{hizuke}")
  ArrayList<Integer> getgroupScheduleidByGroupidAndHizuke(int groupid, String hizuke);

    /**
   * scheduleidからgroupScheduleを取得する
   *
   * @param scheduleid int スケジュールid
   * @return groupSchedule groupSchedule一つ
   */
  @Select("SELECT * FROM groupSchedule WHERE Scheduleid = #{scheduleid}")
  groupSchedule getgroupScheduleByScheduleid(int scheduleid);

    /**
   * scheduleidから日付を取得する
   *
   * @param scheduleid int スケジュールid
   * @return String hizuke 日付
   */
  @Select("SELECT hizuke FROM groupSchedule WHERE Scheduleid = #{scheduleid}")
  String getHizukeByScheduleid(int scheduleid);

    /**
   * scheduleidからtitleを取得する
   *
   * @param scheduleid int スケジュールid
   * @return String title タイトル
   */
  @Select("SELECT title FROM groupSchedule WHERE Scheduleid = #{scheduleid}")
  String getTitleByScheduleid(int scheduleid);

    /**
   * scheduleidから開始時刻を取得する
   *
   * @param scheduleid int スケジュールid
   * @return String kaisi 開始時刻
   */
  @Select("SELECT kaisi FROM groupSchedule WHERE Scheduleid = #{scheduleid}")
  String getKaisiByScheduleid(int scheduleid);

    /**
   * scheduleidから終了時刻を取得する
   *
   * @param scheduleid int スケジュールid
   * @return String owari 終了時刻
   */
  @Select("SELECT owari FROM groupSchedule WHERE Scheduleid = #{scheduleid}")
  String getOwariByScheduleid(int scheduleid);

    /**
   * scheduleidから本文を修正する
   *
   * @param scheduleid int スケジュールid
   * @param content String 本文
   * @return void
   */
  @Update("UPDATE groupSchedule SET content= #{content} WHERE Scheduleid = #{scheduleid}")
  void UpdateContentbyScheduleid(int scheduleid, String content);

    /**
   * scheduleidからタイトルを修正する
   *
   * @param scheduleid int スケジュールid
   * @param title String タイトル
   * @return void
   */
  @Update("UPDATE groupSchedule SET title= #{title} WHERE Scheduleid = #{scheduleid}")
  void UpdateTitlebyScheduleid(int scheduleid, String title);

    /**
   * scheduleidから日付を修正する
   *
   * @param scheduleid int スケジュールid
   * @param hizuke String 日付
   * @return void
   */
  @Update("UPDATE groupSchedule SET hizuke= #{hizuke} WHERE Scheduleid = #{scheduleid}")
  void UpdateHizukebyScheduleid(int scheduleid, String hizuke);

    /**
   * scheduleidから開始時刻を修正する
   *
   * @param scheduleid int スケジュールid
   * @param kaisi String 開始時刻
   * @return void
   */
  @Update("UPDATE groupSchedule SET kaisi= #{kaisi} WHERE Scheduleid = #{scheduleid}")
  void UpdateKaisibyScheduleid(int scheduleid, String kaisi);

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
