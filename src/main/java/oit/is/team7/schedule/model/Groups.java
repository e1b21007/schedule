package oit.is.team7.schedule.model;

public class Groups {
    private int groupid;
    private String groupname;
    private int userid;

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int id) {
        this.groupid = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String name) {
      this.groupname = name;
    }

    public int getUserid() {
      return userid;
    }

    public void setUserid(int userid) {
      this.userid = userid;
    }
}
