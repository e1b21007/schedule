package oit.is.team7.schedule.service;

import oit.is.team7.schedule.model.GroupScheduleMapper;
import oit.is.team7.schedule.model.Reminder;
import oit.is.team7.schedule.model.GroupSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncCalendar {
  boolean dbUpdated = false;
  int count = 1;
  private final Logger logger = LoggerFactory.getLogger(AsyncCalendar.class);
  @Autowired
  GroupScheduleMapper groupschedulemapper;

  public void syncInsertSchedule(Integer id,
      String title, String date,
      String start, String end,
      String content) {
    groupschedulemapper.insertGroupSchedule(date, start, end, id, title, content);
    this.dbUpdated = true;
  }

  public void syncDeleteSchedule(int scheduleId) {
    groupschedulemapper.DeleteGroupScheduleByScheduleId(scheduleId);
    this.dbUpdated = true;
  }

  public void syncUpdateSchedule(Integer id,
      String date,
      String title,
      String start, String end,
      String content) {
    groupschedulemapper.UpdateGroupScheduleByScheduleId(id, date, start, end, title, content);
    this.dbUpdated = true;
  }

  public ArrayList<GroupSchedule> syncShowGroupSchedule(int groupid) {
    return groupschedulemapper.selectgroupScheduleByGroupid(groupid);
  }

  @Async
  public void asyncGroupSchedule(SseEmitter emitter, int groupid) {
    dbUpdated = true;
    try {
      while (true) {// 無限ループ
        // DBが更新されていなければ0.5s休み
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        // DBが更新されていれば更新後のグループスケジュールリストを取得してsendし，1s休み，dbUpdatedをfalseにする
        ArrayList<GroupSchedule> Schedule = this.syncShowGroupSchedule(groupid);
        emitter.send(Schedule);
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncCalendar complete");
  }

  @Async
  public void asyncTime(SseEmitter emitter, int id) {

    try {
      while (true) {
        // Get current time in Japan
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

        ArrayList<GroupSchedule> schedule_list = groupschedulemapper.selectgroupScheduleByGroupid(id);
        ArrayList<Reminder> reminders = new ArrayList<>();


        for (GroupSchedule schedule : schedule_list) {
          String sc_date = schedule.getHizuke();
          String sc_start = schedule.getKaisi();
          String sc_end = schedule.getOwari();

          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
          ZonedDateTime scheduleStartTime = ZonedDateTime.parse(sc_date + " " + sc_start, formatter.withZone(ZoneId.of("Asia/Tokyo")));
          ZonedDateTime scheduleEndTime = ZonedDateTime.parse(sc_date + " " + sc_end, formatter.withZone(ZoneId.of("Asia/Tokyo")));

          Duration duration = Duration.between(now, scheduleStartTime);
          long minutes = duration.toMinutes();

          if (scheduleEndTime.isAfter(now) && minutes < 1440) {
            reminders.add(new Reminder(schedule, minutes));
          }
        }

        emitter.send(reminders);
        TimeUnit.SECONDS.sleep(1);
        // now = ZonedDateTime.now();
        // localDate = now.toLocalDate();
        // nowDate = Date.valueOf(localDate);
        // emitter.send(nowDate);
        // ArrayList<GroupSchedule> schedule_list =
        // groupschedulemapper.selectgroupScheduleByGroupid(id);
        // for (GroupSchedule schedule : schedule_list) {
        // if (schedule.getHizuke().equals(now.toString())) {

        // }
        // if(nowDate.compareTo(schedule.getHizuke())){}
        // }
        // TimeUnit.SECONDS.sleep(1);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      emitter.complete();
    }

  }

  // @Async
  // public void count(SseEmitter emitter) throws IOException {
  // try {
  // while (true) {// 無限ループ
  // // sendによってcountがブラウザにpushされる
  // emitter.send(count);
  // count++;
  // // 1秒STOP
  // TimeUnit.SECONDS.sleep(1);
  // }
  // } catch (InterruptedException e) {
  // // 例外の名前とメッセージだけ表示する
  // }
  // }

}

