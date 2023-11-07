package oit.is.team7.schedule.controller;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class schedule_controller {

  @GetMapping("/calendar")
  public String calendar() {
    return "calendar.html";
  }

  @GetMapping("/home")
  public String home() {
    return "home.html";
  }
}
