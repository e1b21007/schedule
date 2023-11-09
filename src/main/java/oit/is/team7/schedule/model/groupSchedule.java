package oit.is.team7.schedule.model;

public class groupSchedule {
    private int scheduleid;
    private String hizuke;
    private String kaisi;
    private String owari;
    private int groupid;
    private String title;
    private String content;

    public int getScheduleid() {
        return this.scheduleid;
    }

    public void setScheduleid(int id) {
        this.scheduleid = id;
    }

    public String getHizuke() {
        return this.hizuke;
    }

    public void setHizuke(String hizuke) {
        this.hizuke = hizuke;
    }

    public int getGroupid() {
        return this.groupid;
    }

    public void setGroupid(int id) {
        this.groupid = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String name) {
        this.content = name;
    }

    public String getKaisi() {
      return kaisi;
    }

    public void setKaisi(String kaisi) {
      this.kaisi = kaisi;
    }

    public String getOwari() {
      return owari;
    }

    public void setOwari(String owari) {
      this.owari = owari;
    }

}
