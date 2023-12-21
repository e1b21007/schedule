package oit.is.team7.schedule.controller;

import oit.is.team7.schedule.model.Groups;
import oit.is.team7.schedule.model.GroupsMapper;
import oit.is.team7.schedule.model.GroupSchedule;
import oit.is.team7.schedule.model.GroupScheduleMapper;
import oit.is.team7.schedule.model.Entry;
import oit.is.team7.schedule.model.EntryMapper;
import oit.is.team7.schedule.model.User;
import oit.is.team7.schedule.model.UserMapper;
import oit.is.team7.schedule.service.AsyncCalendar;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ScheduleController {
  @Autowired
  GroupsMapper groupmapper;
  @Autowired
  GroupScheduleMapper groupschedulemapper;
  @Autowired
  EntryMapper entrymapper;
  @Autowired
  UserMapper usermapper;
  @Autowired
  AsyncCalendar asyncCalendar;

  @GetMapping("/calendar")
  public String calendar(@RequestParam Integer id, ModelMap model, Principal prin) {
    if (!checkUser(prin, id)) {
      return "accessError";
    }

    ArrayList<GroupSchedule> groupSchedules = asyncCalendar.syncShowGroupSchedule(id);

    model.addAttribute("groupSchedules", groupSchedules);
    model.addAttribute("groupid", id);

    return "calendar.html";
  }

  @GetMapping("/post")
  public String post(@RequestParam Integer id, ModelMap model, Principal prin) {
    if (!checkUser(prin, id)) {
      return "accessError";
    }

    Groups group = groupmapper.selectSgroupByGroupid(id);
    ArrayList<GroupSchedule> scheduleList = groupschedulemapper.selectgroupScheduleByGroupid(id);
    model.addAttribute("group", group);
    model.addAttribute("groupSchedules", scheduleList);

    return "post.html";
  }

  @PostMapping("/post")
  public String calendar(@RequestParam Integer id,
      @RequestParam String title, @RequestParam String date,
      @RequestParam String start, @RequestParam String end,
      @RequestParam String content,
      ModelMap model) {

    this.asyncCalendar.syncInsertSchedule(id, title, date, start, end, content);
    ArrayList<GroupSchedule> scheduleList = asyncCalendar.syncShowGroupSchedule(id);
    Groups group = groupmapper.selectSgroupByGroupid(id);

    model.addAttribute("group", group);
    model.addAttribute("groupSchedules", scheduleList);
    model.addAttribute("groupid", id);

    return "post.html";
  }

  @GetMapping("/home")
  public String home(Principal prin, ModelMap model) {
    String username = prin.getName();
    User user = usermapper.selectByname(username);
    ArrayList<Entry> entries = entrymapper.selectEntryByUserid(user.getUserid());
    ArrayList<Groups> entryGroup = new ArrayList<>();
    ArrayList<User> allusers = usermapper.selectAllByUsers();
    for (Entry entry : entries) {
      if (entry.getUserid() == user.getUserid()) {
        entryGroup.add(groupmapper.selectSgroupByGroupid(entry.getGroupid()));
        System.out.println(entry.getGroupid());
      }
    }


    model.addAttribute("groups", entryGroup);
    model.addAttribute("allusers", allusers);

    //グループの編集用追加箇所
    ArrayList<Groups> AdminGroups = new ArrayList<>();
    ArrayList<User> otherusers = usermapper.selectOtherByUserid(user.getUserid());
    AdminGroups = groupmapper.selectAdminSgroup(user.getUserid());
    model.addAttribute("otherusers", otherusers);
    model.addAttribute("AdminGroup", AdminGroups);

    return "home.html";
  }

  @GetMapping("/detail")
  public String content(@RequestParam Integer id, @RequestParam Integer groupid, ModelMap model, Principal prin) {
    int flag = 0;
    int groupallschedule[] = groupschedulemapper.selectScheduleidByGroupid(groupid);
    GroupSchedule checkScheduleId = groupschedulemapper.getgroupScheduleByScheduleid(id);

    for (int scheduleid : groupallschedule) {
      if (scheduleid == id) {
        flag = 1;
      }
    }

    if (!checkUser(prin, groupid)) {
      return "accessError";
    }
    // if (!checkUser(prin, checkScheduleId.getGroupid())) {
    // return "accessError";
    // }

    if (flag == 1) {
      GroupSchedule groupSchedule = groupschedulemapper.getgroupScheduleByScheduleid(id);
      ArrayList<GroupSchedule> scheduleList = groupschedulemapper
          .selectgroupScheduleByGroupid(groupSchedule.getGroupid());
      Groups group = groupmapper.selectSgroupByGroupid(groupSchedule.getGroupid());

      model.addAttribute("group", group);
      model.addAttribute("groupSchedule", groupSchedule);
      model.addAttribute("scheduleList", scheduleList);

      return "content.html";
    }
    model.addAttribute("groupid", groupid);
    return "delete.html";
  }

  @GetMapping("/edit")
  public String edit(@RequestParam Integer id, ModelMap model) {
    boolean edit_flag = true;
    GroupSchedule groupSchedule = groupschedulemapper.getgroupScheduleByScheduleid(id);
    Groups group = groupmapper.selectSgroupByGroupid(groupSchedule.getGroupid());
    ArrayList<GroupSchedule> scheduleList = groupschedulemapper
        .selectgroupScheduleByGroupid(groupSchedule.getGroupid());

    model.addAttribute("group", group);
    model.addAttribute("groupSchedule", groupSchedule);
    model.addAttribute("scheduleList", scheduleList);
    model.addAttribute("edit_flag", edit_flag);

    return "content.html";
  }

  @PostMapping("/detail")
  public String edit(@RequestParam Integer id,
      @RequestParam String date,
      @RequestParam String title,
      @RequestParam String start, @RequestParam String end,
      @RequestParam String content,
      ModelMap model) {
    this.asyncCalendar.syncUpdateSchedule(id, date, title, start, end, content);
    GroupSchedule groupSchedule = groupschedulemapper.getgroupScheduleByScheduleid(id);
    Groups group = groupmapper.selectSgroupByGroupid(groupSchedule.getGroupid());
    ArrayList<GroupSchedule> scheduleList = groupschedulemapper
        .selectgroupScheduleByGroupid(groupSchedule.getGroupid());
    model.addAttribute("groupSchedule", groupSchedule);
    model.addAttribute("group", group);
    model.addAttribute("groupSchedule", groupSchedule);
    model.addAttribute("scheduleList", scheduleList);

    return "content.html";
  }

  @GetMapping("/delete")
  public String deleteYes(@RequestParam Integer id, @RequestParam Integer gid, ModelMap model) {
    this.asyncCalendar.syncDeleteSchedule(id);
    ArrayList<GroupSchedule> groupSchedules = asyncCalendar.syncShowGroupSchedule(gid);

    model.addAttribute("groupSchedules", groupSchedules);
    model.addAttribute("groupid", gid);

    return "calendar.html";
  }

  @GetMapping({ "/calendar/update", "/post/update", "/detail/update" })
  public SseEmitter asyncCalendar(@RequestParam Integer id) {

    // finalは初期化したあとに再代入が行われない変数につける（意図しない再代入を防ぐ）
    final SseEmitter emitter = new SseEmitter();//
    this.asyncCalendar.asyncGroupSchedule(emitter, id);

    return emitter;
  }

  @GetMapping("/current_date")
  public SseEmitter asynctime(@RequestParam Integer id) {

    // finalは初期化したあとに再代入が行われない変数につける（意図しない再代入を防ぐ）
    final SseEmitter emitter = new SseEmitter();//
    this.asyncCalendar.asyncTime(emitter, id);

    return emitter;
  }

  @GetMapping("/newgroup")
  public String newgroup(Principal prin, ModelMap model) {
    boolean newgroupflag = true;
    String username = prin.getName();
    User user = usermapper.selectByname(username);
    ArrayList<Entry> entries = entrymapper.selectEntryByUserid(user.getUserid());
    ArrayList<Groups> entryGroup = new ArrayList<>();

    for (Entry entry : entries) {
      if (entry.getUserid() == user.getUserid()) {
        entryGroup.add(groupmapper.selectSgroupByGroupid(entry.getGroupid()));
        System.out.println(entry.getGroupid());
      }
    }

    model.addAttribute("groups", entryGroup);
    model.addAttribute("newgroupflag", newgroupflag);

    //グループの編集用追加箇所
    ArrayList<Groups> AdminGroups = new ArrayList<>();
    ArrayList<User> otherusers = usermapper.selectOtherByUserid(user.getUserid());
    AdminGroups = groupmapper.selectAdminSgroup(user.getUserid());
    model.addAttribute("otherusers", otherusers);
    model.addAttribute("AdminGroup", AdminGroups);

    return "home.html";
  }

  @PostMapping("/makenewgroup")
  public String makenewgroup(Principal prin, ModelMap model, @RequestParam String[] imputUsers, String newGroupName) {
    ArrayList<User> alluser = usermapper.selectAllByUsers();
    Groups newgroup = new Groups();
    newgroup.setGroupname(newGroupName);
    String username = prin.getName();
    User user = usermapper.selectByname(username);
    newgroup.setUserid(user.getUserid());//グループ作成者を管理者に設定
    int imputuser;
    groupmapper.InsertGroupbyGroup(newgroup);
    int newgroupid = groupmapper.selectMaxGroupByGroupname(newGroupName);
    for (String stringuser : imputUsers) {
      imputuser = Integer.parseInt(stringuser);
      entrymapper.InsertEntry(imputuser, newgroupid);
    }
      entrymapper.InsertEntry(user.getUserid(), newgroupid);
    ArrayList<Entry> entries = entrymapper.selectEntryByUserid(user.getUserid());
    ArrayList<Groups> entryGroup = new ArrayList<>();

    for (Entry entry : entries) {
      if (entry.getUserid() == user.getUserid()) {
        entryGroup.add(groupmapper.selectSgroupByGroupid(entry.getGroupid()));
      }
    }

    model.addAttribute("groups", entryGroup);
    model.addAttribute("allusers", alluser);

    //グループの編集用追加箇所
    ArrayList<Groups> AdminGroups = new ArrayList<>();
    ArrayList<User> otherusers = usermapper.selectOtherByUserid(user.getUserid());
    AdminGroups = groupmapper.selectAdminSgroup(user.getUserid());
    model.addAttribute("otherusers", otherusers);
    model.addAttribute("AdminGroup", AdminGroups);

    return "home.html";
  }

  private boolean checkUser(Principal prin, int groupid) {
    String username = prin.getName();
    User user = usermapper.selectByname(username);
    ArrayList<Entry> entries = entrymapper.selectEntryByGroupid(groupid);

    for (Entry entry : entries) {
      if (entry.getUserid() == user.getUserid()) {
        return true;
      }
    }
    return false;
  }

  @GetMapping("/deleteGroupFlag")
  public String deleteGroupFlag(ModelMap model, Principal prin) {
    boolean deleteGroupFlag = true;
    ArrayList<Groups> entryGroup = new ArrayList<>();
    String username = prin.getName();
    User user = usermapper.selectByname(username);
    ArrayList<Entry> entries = entrymapper.selectEntryByUserid(user.getUserid());

    for (Entry entry : entries) {
      if (entry.getUserid() == user.getUserid()) {
        entryGroup.add(groupmapper.selectSgroupByGroupid(entry.getGroupid()));
        System.out.println(entry.getGroupid());
      }
    }

    model.addAttribute("groups", entryGroup);
    model.addAttribute("deleteGroupFlag", deleteGroupFlag);

    //グループの編集用追加箇所
    ArrayList<Groups> AdminGroups = new ArrayList<>();
    ArrayList<User> otherusers = usermapper.selectOtherByUserid(user.getUserid());
    AdminGroups = groupmapper.selectAdminSgroup(user.getUserid());
    model.addAttribute("otherusers", otherusers);
    model.addAttribute("AdminGroup", AdminGroups);
    return "home.html";
  }

  @PostMapping("/deleteGroup")
  public String deleteGroup(@RequestParam String selectedGroup, Principal prin, ModelMap model) {
    entrymapper.DeleteEntryByGroupId(Integer.parseInt(selectedGroup));
    groupschedulemapper.DeleteGroupScheduleByGroupId(Integer.parseInt(selectedGroup));
    groupmapper.DeleteGroupByGroupid(Integer.parseInt(selectedGroup));
    String username = prin.getName();
    User user = usermapper.selectByname(username);
    ArrayList<Entry> entries = entrymapper.selectEntryByUserid(user.getUserid());
    ArrayList<Groups> entryGroup = new ArrayList<>();

    for (Entry entry : entries) {
      if (entry.getUserid() == user.getUserid()) {
        entryGroup.add(groupmapper.selectSgroupByGroupid(entry.getGroupid()));
        System.out.println(entry.getGroupid());
      }
    }

    model.addAttribute("groups", entryGroup);

    //グループの編集用追加箇所
    ArrayList<Groups> AdminGroups = new ArrayList<>();
    ArrayList<User> otherusers = usermapper.selectOtherByUserid(user.getUserid());
    AdminGroups = groupmapper.selectAdminSgroup(user.getUserid());
    model.addAttribute("otherusers", otherusers);
    model.addAttribute("AdminGroup", AdminGroups);
    return "home.html";
  }

  @GetMapping("/home/update/group")
  SseEmitter GroupUpdate(@RequestParam Integer id) {
    final SseEmitter emitter = new SseEmitter();
    ArrayList<Entry> groupEntries = entrymapper.selectEntryByGroupid(id);
    ArrayList<User> users = usermapper.selectAllByUsers();
    ArrayList<User> members = new ArrayList<>();

    for (User user: users) {
      for (Entry entry: groupEntries) {
        if (user.getUserid() == entry.getUserid()) {
          members.add(user);
          break;
        }
      }
    }

    try {
      Map<String, ArrayList<User>> data = new HashMap<>();
      data.put("members", members);
      emitter.send(data);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return emitter;
  }
  @PostMapping("/home/update/group")
  public String GroupUpdate(@RequestParam int groupid, @RequestParam(required=false) int[] groupusers, ModelMap model, Principal prin) {
    ArrayList<User> allusers = usermapper.selectAllByUsers();

    entrymapper.DeleteEntryByGroupId(groupid);
    if(groupusers == null) {
      return "redirect:/home";
    }

    for (User user : allusers) {
      for (int id : groupusers) {
        if (user.getUserid() == id) {
          entrymapper.InsertEntry(id, groupid);
        }
      }
    }

    String username = prin.getName();
    User user = usermapper.selectByname(username);
    entrymapper.InsertEntry(user.getUserid(), groupid);//管理者=操作者のエントリー

    return "redirect:/home";
  }

}
