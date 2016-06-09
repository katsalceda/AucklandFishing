package rktechltd.aucklandfishing.models;

/**
 * Class representing FishCatch
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */

public class FishCatch {

    private int fishCatchId;
    private int fx;
    private double length;
    private double weight;
    private byte[] picture;
    private String name;
    private String remark;

    /**
     * Constructor
     * @param fishCatchId
     * @param fx
     * @param length
     * @param weight
     * @param name
     * @param remark
     */
    public FishCatch(int fishCatchId, int fx, double length, double weight, String name, String remark) {
        this.fishCatchId = fishCatchId;
        this.fx = fx;
        this.length = length;
        this.weight = weight;
        this.name = name;
        this.remark = remark;
        this.picture=null;
    }

    /**
     * Constructor
     * @param fishCatchId
     * @param fx
     * @param length
     * @param weight
     * @param picture
     * @param name
     * @param remark
     */
    public FishCatch(int fishCatchId, int fx, double length, double weight, byte[] picture, String name, String remark) {
        this.fishCatchId = fishCatchId;
        this.fx = fx;
        this.length = length;
        this.weight = weight;
        this.picture = picture;
        this.name = name;
        this.remark = remark;
    }

    /**
     * Gets the fishcatch id
     * @return
     */
    public int getFishCatchId() {
        return fishCatchId;
    }

    /**
     * Sets the fishcatch id
     * @param fishCatchId
     */
    public void setFishCatchId(int fishCatchId) {
        this.fishCatchId = fishCatchId;
    }

    /**
     * Gets the fishing experience id
     * @return
     */
    public int getFx() {
        return fx;
    }

    /**
     * Sets the fishing experience id
     * @param fx
     */
    public void setFx(int fx) {
        this.fx = fx;
    }

    /**
     * Gets the length of the fish caught
     * @return
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the length of the fish caught
     * @param length
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Gets the weight of the fish caught
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the of the fish caught
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets the picture of the fish
     * @return
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Sets the picture of the fish
     * @param picture
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    /**
     * Gets the name of the fish caught
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the fish caught
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the remark of the fish caught
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the remark of the fish caught
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}

