package rktechltd.aucklandfishing.models;

/**
 * Class representing NetRule
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */
public class NetRule {
    private int rulesId;
    private String description;
    private String title;
    private double penalty;
    private byte[] image;
    private int index;

    /**
     * Constructor
     * @param rulesId
     * @param description
     * @param title
     * @param penalty
     * @param image
     */
    public NetRule(int rulesId, String description, String title, double penalty, byte[] image,int index) {
        this.rulesId = rulesId;
        this.description = description;
        this.title = title;
        this.penalty = penalty;
        this.image = image;
        this.index=index;
    }

    /**
     * Gets the rules id
     * @return
     */
    public int getRulesId() {
        return rulesId;
    }

    /**
     * Sets the rules id
     * @param rulesId
     */
    public void setRulesId(int rulesId) {
        this.rulesId = rulesId;
    }

    /**
     * Gets the description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the penalty
     * @return
     */
    public double getPenalty() {
        return penalty;
    }

    /**
     * Sets the penalty
     * @param penalty
     */
    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    /**
     * Gets the image
     * @return
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the image
     * @param image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
}

