package oit.is.team7.schedule.service;

import oit.is.team7.schedule.model.GroupScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import oit.is.team7.schedule.model.GroupSchedule;

@Service
public class AsyncCalendar {
    boolean dbUpdated = false;
    @Autowired GroupScheduleMapper groupschedulemapper;
    int count = 1;
    @Async
    public void count(SseEmitter emitter) throws IOException {
        try {
            while (true) {// 無限ループ
                // sendによってcountがブラウザにpushされる
                emitter.send(count);
                count++;
                // 1秒STOP
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            // 例外の名前とメッセージだけ表示する
        }
    }
/*
    @Async
    public void asyncGroupSchedule(SseEmitter emitter ) {
    dbUpdated = true;
    try {
      while (true) {// 無限ループ
        // DBが更新されていなければ0.5s休み
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        // DBが更新されていれば更新後のフルーツリストを取得してsendし，1s休み，dbUpdatedをfalseにする
        ArrayList<GroupSchedule> Schedule = this.selectgroupScheduleByGroupid(groupid);
        emitter.send(fruits7);
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowFruitsList complete");
  }

    }
*/
}
