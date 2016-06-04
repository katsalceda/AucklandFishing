package rktechltd.aucklandfishing.models;

/**
 * Created by romelyn on 30/05/2016.
 */
public class FishCatch {

    private int fishCatchId;
    private int fx;
    private double length;
    private double weight;
    private byte[] picture;
    private String name;
    private String remark;


    public FishCatch(int fishCatchId, int fx, double length, double weight, byte[] picture, String name, String remark) {
        this.fishCatchId = fishCatchId;
        this.fx = fx;
        this.length = length;
        this.weight = weight;
        this.picture = picture;
        this.name = name;
        this.remark = remark;
    }

    public int getFishCatchId() {
        return fishCatchId;
    }

    public void setFishCatchId(int fishCatchId) {
        this.fishCatchId = fishCatchId;
    }

    public int getFx() {
        return fx;
    }

    public void setFx(int fx) {
        this.fx = fx;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

