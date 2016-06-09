package rktechltd.aucklandfishing.models;

/**
 * Class representing Fish
 * Created by romelyn on 30/05/2016.
 */
public class Fish {

    private int fishId;
    private String fishName;
    private String fishDescription;
    private byte[] fishImage;
    private int fishCat;
    private double minFishLengthCm;
    private int maxDailyLimit;
    private int isCombinedBag;

    /**
     * Constructor
     */
    public Fish(){}

    /**
     * Constructor
     * @param fishId
     * @param fishName
     * @param fishDescription
     * @param fishImage
     * @param fishCat
     * @param minFishLengthCm
     * @param maxDailyLimit
     * @param isCombinedBag
     */
    public Fish(int fishId, String fishName, String fishDescription, byte[] fishImage, int fishCat, double minFishLengthCm, int maxDailyLimit, int isCombinedBag){
        this.fishId = fishId;
        this.fishName = fishName;
        this.fishDescription = fishDescription;
        this.fishImage = fishImage;
        this.fishCat = fishCat;
        this.minFishLengthCm = minFishLengthCm;
        this.maxDailyLimit = maxDailyLimit;
        this.isCombinedBag = isCombinedBag;
    }

    /**
     * Gets the fish id
     * @return
     */
    public int getFishId() {
        return fishId;
    }

    /**
     * Sets the fish id
     * @param fishId
     */
    public void setFishId(int fishId) {
        this.fishId = fishId;
    }

    /**
     * Gets the fish name
     * @return
     */
    public String getFishName() {
        return fishName;
    }

    /**
     * Sets the fish name
     * @param fishName
     */
    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    /**
     * Gets the fish description
     * @return
     */
    public String getFishDescription() {
        return fishDescription;
    }

    /**
     * Sets the fish description
     * @param fishDescription
     */
    public void setFishDescription(String fishDescription) {
        this.fishDescription = fishDescription;
    }

    /**
     * Gets the fish image
     * @return
     */
    public byte[] getFishImage() {
        return fishImage;
    }

    /**
     * Sets the fish Image
     * @param fishImage
     */
    public void setFishImage(byte[] fishImage) {
        this.fishImage = fishImage;
    }

    /**
     * Gets the fish category
     * @return
     */
    public int getFishCat() {
        return fishCat;
    }

    /**
     * Sets the fish category
     * @param fishCat
     */
    public void setFishCat(int fishCat) {
        this.fishCat = fishCat;
    }

    /**
     * Gets the minimum fish length
     * @return
     */
    public double getMinFishLengthCm() {
        return minFishLengthCm;
    }

    /**
     * Sets the minimum fish length
     * @param minFishLengthCm
     */
    public void setMinFishLengthCm(int minFishLengthCm) {
        this.minFishLengthCm = minFishLengthCm;
    }

    /**
     * Gets the maximum daily limit
     * @return
     */
    public int getMaxDailyLimit() {
        return maxDailyLimit;
    }

    /**
     * Sets the maximum daily limit
     * @param maxDailyLimit
     */
    public void setMaxDailyLimit(int maxDailyLimit) {
        this.maxDailyLimit = maxDailyLimit;
    }

    /**
     * Gets if the fish is in combined bag
     * @return
     */
    public int isCombinedBag() {
        return isCombinedBag;
    }

    /**
     * Sets the is combinedBag
     * @param combinedBag
     */
    public void setCombinedBag(int combinedBag) {
        isCombinedBag = combinedBag;
    }
}
