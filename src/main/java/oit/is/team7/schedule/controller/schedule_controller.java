package oit.is.team7.schedule.controller;

import oit.is.team7.schedule.model.sgroup;
import oit.is.team7.schedule.model.sgroupMapper;
import oit.is.team7.schedule.model.groupSchedule;
import oit.is.team7.schedule.model.groupScheduleMapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class schedule_controller {
  @Autowired
  sgroupMapper sgroupmapper;
  @Autowired
  groupScheduleMapper groupschedulemapper;

  @GetMapping("/calendar")
  public String calendar(@RequestParam Integer id, ModelMap model) {
    ArrayList<groupSchedule> groupSchedules = groupschedulemapper.selectgroupScheduleByGroupid(id);

    model.addAttribute("groupSchedules", groupSchedules);

    return "calendar.html";
  }

  @GetMapping("/post")
  public String post(@RequestParam Integer id, ModelMap model) {
    sgroup group = sgroupmapper.selectSgroupByGroupid(id);

    model.addAttribute("group", group);

    return "post.html";
  }

  @PostMapping("/calendar")
  public String calendar(@RequestParam Integer id,
      @RequestParam String title, @RequestParam String date,
      @RequestParam String start, @RequestParam String end,
      @RequestParam String content,
      ModelMap model) {
    groupschedulemapper.insertGroupSchedule(date, start, end, id, title, content);

    return "calendar.html";
  }

  @GetMapping("/home")
  public String home(ModelMap model) {
    ArrayList<sgroup> groups = sgroupmapper.selectAllSgroup();

    model.addAttribute("groups", groups);

    return "home.html";
  }

  @GetMapping("/detail")
  public String content(ModelMap model) {
    groupSchedule groupSchedule = groupschedulemapper.getgroupScheduleByScheduleid(1);

    model.addAttribute("groupSchedule", groupSchedule);

    return "content.html";
  }

}
