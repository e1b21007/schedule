package oit.is.team7.schedule.controller;

import oit.is.team7.schedule.model.Group;
import oit.is.team7.schedule.model.GroupMapper;
import oit.is.team7.schedule.model.GroupSchedule;
import oit.is.team7.schedule.model.GroupScheduleMapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {
  @Autowired
  GroupMapper sgroupmapper;
  @Autowired
  GroupScheduleMapper groupschedulemapper;

  @GetMapping("/calendar")
  public String calendar(@RequestParam Integer id, ModelMap model) {
    ArrayList<GroupSchedule> groupSchedules = groupschedulemapper.selectgroupScheduleByGroupid(id);

    model.addAttribute("groupSchedules", groupSchedules);

    return "calendar.html";
  }

  @GetMapping("/post")
  public String post(@RequestParam Integer id, ModelMap model) {
    Group group = sgroupmapper.selectSgroupByGroupid(id);

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
    ArrayList<Group> groups = sgroupmapper.selectAllSgroup();

    model.addAttribute("groups", groups);

    return "home.html";
  }

  @GetMapping("/detail")
  public String content(ModelMap model) {
    GroupSchedule groupSchedule = groupschedulemapper.getgroupScheduleByScheduleid(1);

    model.addAttribute("groupSchedule", groupSchedule);

    return "content.html";
  }

}
