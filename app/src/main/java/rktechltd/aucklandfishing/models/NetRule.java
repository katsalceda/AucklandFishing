package rktechltd.aucklandfishing.models;

/**
 * Created by romelyn on 30/05/2016.
 */
public class NetRule {

    private int rulesId;
    private String description;
    private String title;
    private double penalty;
    private byte[] image;


    public NetRule(int rulesId, String description, String title, double penalty, byte[] image) {
        this.rulesId = rulesId;
        this.description = description;
        this.title = title;
        this.penalty = penalty;
        this.image = image;
    }

    public int getRulesId() {
        return rulesId;
    }

    public void setRulesId(int rulesId) {
        this.rulesId = rulesId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

