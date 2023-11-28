package oit.is.team7.schedule.service;

import oit.is.team7.schedule.model.GroupScheduleMapper;
import oit.is.team7.schedule.model.GroupSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
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
  // @Async
  // public void asyncTime(SseEmitter emitter) {
  // Calendar calendar = Calendar.getInstance();
  // System.out.println(calendar.getTime());
  // emitter.send(calendar);
  // }

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
