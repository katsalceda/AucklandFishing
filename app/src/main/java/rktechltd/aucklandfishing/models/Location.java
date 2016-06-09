package rktechltd.aucklandfishing.models;

/**
 * Created by romelyn on 30/05/2016.
 */
public class Location {

    private int locationId;
    private double latitude;
    private double longitude;
    private String note;
    private String locationName;

    /**
     * Constructor
     * @param locationId
     * @param latitude
     * @param longitude
     * @param note
     * @param locationName
     */
    public Location(int locationId, double latitude, double longitude, String note, String locationName) {
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.note = note;
        this.locationName = locationName;
    }

    /**
     * Gets the location id
     * @return
     */
    public int getLocationId() {
        return locationId;
    }

    /**
     * Sets the location id
     * @param locationId
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     * GEts the latitude
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
     * Gets the longitude
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
     * Gets note
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets note
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets location name
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
}

