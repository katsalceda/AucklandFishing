package rktechltd.aucklandfishing.models;

/**
 * Class representing Checklist
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */
public class Checklist {

    private int checkListId;
    private String title;
    private String description;
    private byte[] image;

    /**
     * Constructor
     */
    public Checklist(){}

    /**
     * Constructor
     * @param checkListId
     * @param title
     * @param description
     * @param image
     */
    public Checklist(int checkListId, String title, String description, byte[] image) {
        this.checkListId = checkListId;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    /**
     * gets checklist id
     * @return
     */
    public int getCheckListId() {
        return checkListId;
    }

    /**
     * sets the checklist id
     * @param checkListId
     */
    public void setCheckListId(int checkListId) {
        this.checkListId = checkListId;
    }

    /**
     * Gets the checklist title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the Checklist title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the checklist desciption
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the checklist description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the checklist image
     * @return
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Gets the checklist image
     * @param image
     */
    public void setImage(byte[] image) {
        this.image = image;
    }


}

