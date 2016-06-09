package rktechltd.aucklandfishing.models;


import java.sql.Time;
import java.util.Date;

/**
 * Class representing FishingExperience
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

    /**
     * Constructor
     * @param experienceId
     * @param locationName
     * @param latitude
     * @param longitude
     * @param date
     * @param time
     * @param remark
     */
    public FishingExperience(int experienceId, String locationName, double latitude, double longitude, Date date, Time time, String remark) {
        this.experienceId = experienceId;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.time = time;
        this.remark = remark;
    }

    /**
     * Gets the experience id
     * @return
     */
    public int getExperienceId() {
        return experienceId;
    }

    /**
     * Sets the experience id
     * @param experienceId
     */
    public void setExperienceId(int experienceId) {
        this.experienceId = experienceId;
    }

    /**
     * Gets the location name
     * @return
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets the location name
     * @param locationName
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * Gets the latitude
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * GEts the longitude
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the date
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the time
     * @return
     */
    public Time getTime() {
        return time;
    }

    /**
     * Sets the time
     * @param time
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * GEts the remark
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the remark
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}

