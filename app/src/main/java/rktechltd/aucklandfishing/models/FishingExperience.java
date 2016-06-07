package rktechltd.aucklandfishing.models;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by romelyn on 30/05/2016.
 */
public class FishingExperience {

    private int experienceId;
    private String locationName;
    private double latitude;
    private double longitude;
    private Date date;
    private Time time;
    private String remark;

    public FishingExperience(int experienceId, String locationName, double latitude, double longitude, Date date, Time time, String remark) {
        this.experienceId = experienceId;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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