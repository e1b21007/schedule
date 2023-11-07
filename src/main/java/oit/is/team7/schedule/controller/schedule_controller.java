package oit.is.team7.schedule.controller;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class schedule_controller {

  @GetMapping("/calendar")
  public String calendar(@RequestParam Integer id) {
    return "calendar.html";
  }

  @GetMapping("/post")
  public String post(@RequestParam Integer id) {
    return "post.html";
  }

  @GetMapping("/home")
  public String home() {
    return "home.html";
  }
}
