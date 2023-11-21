package oit.is.team7.schedule.service;

import oit.is.team7.schedule.model.GroupScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


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

    @Async
    public void asyncGroupSchedule() {

    }

}