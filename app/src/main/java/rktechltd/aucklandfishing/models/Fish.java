package rktechltd.aucklandfishing.models;

/**
 * Created by romelyn on 30/05/2016.
 */
public class Fish {

    private int fishId;
    private String fishName;
    private String fishDescription;
    private byte[] fishImage;
    private int fishCat;
    private int minFishLengthCm;
    private int maxDailyLimit;
    private int isCombinedBag;


    public Fish(){}
    public Fish(int fishId, String fishName, String fishDescription, byte[] fishImage, int fishCat, int minFishLengthCm, int maxDailyLimit, int isCombinedBag){
        this.fishId = fishId;
        this.fishName = fishName;
        this.fishDescription = fishDescription;
        this.fishImage = fishImage;
        this.fishCat = fishCat;
        this.minFishLengthCm = minFishLengthCm;
        this.maxDailyLimit = maxDailyLimit;
        this.isCombinedBag = isCombinedBag;
    }


    public int getFishId() {
        return fishId;
    }

    public void setFishId(int fishId) {
        this.fishId = fishId;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public String getFishDescription() {
        return fishDescription;
    }

    public void setFishDescription(String fishDescription) {
        this.fishDescription = fishDescription;
    }

    public byte[] getFishImage() {
        return fishImage;
    }

    public void setFishImage(byte[] fishImage) {
        this.fishImage = fishImage;
    }

    public int getFishCat() {
        return fishCat;
    }

    public void setFishCat(int fishCat) {
        this.fishCat = fishCat;
    }

    public int getMinFishLengthCm() {
        return minFishLengthCm;
    }

    public void setMinFishLengthCm(int minFishLengthCm) {
        this.minFishLengthCm = minFishLengthCm;
    }

    public int getMaxDailyLimit() {
        return maxDailyLimit;
    }

    public void setMaxDailyLimit(int maxDailyLimit) {
        this.maxDailyLimit = maxDailyLimit;
    }

    public int isCombinedBag() {
        return isCombinedBag;
    }

    public void setCombinedBag(int combinedBag) {
        isCombinedBag = combinedBag;
    }
}
