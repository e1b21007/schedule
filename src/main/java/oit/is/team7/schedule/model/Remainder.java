package oit.is.team7.schedule.model;

public class Remainder {
    private GroupSchedule schedule;
    private long remainingMinutes;

    public Remainder(GroupSchedule schedule, long minutes) {
        this.schedule = schedule;
        this.remainingMinutes = minutes;
    }
    public GroupSchedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(GroupSchedule schedule) {
        this.schedule = schedule;
    }

    public long getRemainingMinutes() {
        return this.remainingMinutes;
    }

    public void setRemainingMinutes(long remainingminutes) {
        this.remainingMinutes = remainingminutes;
    }

}