package rktechltd.aucklandfishing.models;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by romelyn on 30/05/2016.
 */
public class FishingExperience {

    private int experienceId;
    private int location;
    private Date date;
    private Time time;
    private String remark;


    public FishingExperience(int experienceId, int location, Date date, Time time, String remark) {
        this.experienceId = experienceId;
        this.location = location;
        this.date = date;
        this.time = time;
        this.remark = remark;
    }

    public int getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(int experienceId) {
        this.experienceId = experienceId;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

