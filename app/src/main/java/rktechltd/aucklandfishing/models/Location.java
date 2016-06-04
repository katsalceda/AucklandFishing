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


    public Location(int locationId, double latitude, double longitude, String note, String locationName) {
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.note = note;
        this.locationName = locationName;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}

