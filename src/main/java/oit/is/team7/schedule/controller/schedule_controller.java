package oit.is.team7.schedule.controller;

import oit.is.team7.schedule.model.sgroup;
import oit.is.team7.schedule.model.sgroupMapper;
import oit.is.team7.schedule.model.groupScheduleMapper;
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
  public String calendar(@RequestParam Integer id) {
    return "calendar.html";
  }

  @GetMapping("/post")
  public String post(@RequestParam Integer id, ModelMap model) {
    model.addAttribute("groupid", id);
    return "post.html";
  }

  @PostMapping ("/calendar")
  public String calendar(@RequestParam Integer id,
                         @RequestParam String title, @RequestParam String date,
                         @RequestParam String start, @RequestParam String end,
                         @RequestParam String content,
                         ModelMap model) {
    sgroup group = sgroupmapper.selectSgroupByGroupid(id);
    String groupname = group.getGroupname();
    groupschedulemapper.insertGroupSchedule(date, start, end, id, title, content);

    model.addAttribute("groupname", groupname);
    return "calendar.html";
  }

  @GetMapping("/home")
  public String home() {
    return "home.html";
  }
}
