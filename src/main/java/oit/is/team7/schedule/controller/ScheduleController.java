package oit.is.team7.schedule.controller;

import oit.is.team7.schedule.model.Group;
import oit.is.team7.schedule.model.GroupMapper;
import oit.is.team7.schedule.model.GroupSchedule;
import oit.is.team7.schedule.model.GroupScheduleMapper;
import oit.is.team7.schedule.model.Entry;
import oit.is.team7.schedule.model.EntryMapper;
import oit.is.team7.schedule.model.User;
import oit.is.team7.schedule.model.UserMapper;
import oit.is.team7.schedule.service.AsyncCalendar;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class ScheduleController {
  @Autowired
  GroupMapper groupmapper;
  @Autowired
  GroupScheduleMapper groupschedulemapper;
  @Autowired
  EntryMapper entrymapper;
  @Autowired
  UserMapper usermapper;
  @Autowired
  AsyncCalendar asyncCalendar;

  @GetMapping("/calendar")
  public String calendar(@RequestParam Integer id, ModelMap model) {
    ArrayList<GroupSchedule> groupSchedules = asyncCalendar.syncShowGroupSchedule(id);

    model.addAttribute("groupSchedules", groupSchedules);
    model.addAttribute("groupid", id);

    return "calendar.html";
  }

  @GetMapping("/post")
  public String post(@RequestParam Integer id, ModelMap model) {
    Group group = groupmapper.selectSgroupByGroupid(id);

    model.addAttribute("group", group);

    return "post.html";
  }

  @PostMapping("/calendar")
  public String calendar(@RequestParam Integer id,
      @RequestParam String title, @RequestParam String date,
      @RequestParam String start, @RequestParam String end,
      @RequestParam String content,
      ModelMap model) {
    this.asyncCalendar.syncInsertSchedule(id, title, date, start, end, content);
    ArrayList<GroupSchedule> scheduleList = asyncCalendar.syncShowGroupSchedule(id);
    model.addAttribute("groupSchedules", scheduleList);
    model.addAttribute("groupid", id);

    return "calendar.html";
  }

  @GetMapping("/home")
  public String home(Principal prin, ModelMap model) {
    String username = prin.getName();
    User user = usermapper.selectByname(username);
    ArrayList<Entry> entries = entrymapper.selectEntryByUserid(user.getUserid());
    ArrayList<Group> entryGroup = new ArrayList<>();

    for (Entry entry : entries) {
      if (entry.getUserid() == user.getUserid()) {
        entryGroup.add(groupmapper.selectSgroupByGroupid(entry.getGroupid()));
        System.out.println(entry.getGroupid());
      }
    }

    model.addAttribute("groups", entryGroup);

    return "home.html";
  }

  @GetMapping("/detail")
  public String content(@RequestParam Integer id, ModelMap model) {
    GroupSchedule groupSchedule = groupschedulemapper.getgroupScheduleByScheduleid(id);

    model.addAttribute("groupSchedule", groupSchedule);

    return "content.html";
  }

  @GetMapping("/edit")
  public String edit(@RequestParam Integer id, ModelMap model) {
    boolean edit_flag = true;
    GroupSchedule groupSchedule = groupschedulemapper.getgroupScheduleByScheduleid(id);

    model.addAttribute("groupSchedule", groupSchedule);
    model.addAttribute("edit_flag", edit_flag);

    return "content.html";
  }

  @PostMapping("/edit")
  public String edit(@RequestParam Integer id,
      @RequestParam String date,
      @RequestParam String title,
      @RequestParam String start, @RequestParam String end,
      @RequestParam String content,
      ModelMap model) {
    this.asyncCalendar.syncUpdateSchedule(id, date, title, start, end, content);
    GroupSchedule groupSchedule = groupschedulemapper.getgroupScheduleByScheduleid(id);
    model.addAttribute("groupSchedule", groupSchedule);

    return "content.html";
  }

  @GetMapping("/delete")
  public String delete(@RequestParam Integer id, ModelMap model) {
    boolean delete_flag = true;
    GroupSchedule groupSchedule = groupschedulemapper.getgroupScheduleByScheduleid(id);
    model.addAttribute("delete_flag", delete_flag);
    model.addAttribute("groupSchedule", groupSchedule);
    return "content.html";
  }

  @GetMapping("/deleteYes")
  public String deleteYes(@RequestParam Integer id, @RequestParam Integer gid, ModelMap model) {
    asyncCalendar.syncDeleteSchedule(id);
    // calendar(gid, model);
    ArrayList<GroupSchedule> groupSchedules = asyncCalendar.syncShowGroupSchedule(gid);

    model.addAttribute("groupSchedules", groupSchedules);
    model.addAttribute("groupid", gid);

    return "calendar.html";
  }

  @GetMapping("/calendar/update")
  public SseEmitter asyncCalendar(@RequestParam Integer id) {

    // finalは初期化したあとに再代入が行われない変数につける（意図しない再代入を防ぐ）
    final SseEmitter emitter = new SseEmitter();//
    System.out.println("****************" + id);
    this.asyncCalendar.asyncGroupSchedule(emitter, id);

    return emitter;
  }

}
