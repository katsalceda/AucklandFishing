package rktechltd.aucklandfishing.models;

/**
 * Created by romelyn on 30/05/2016.
 */
public class Checklist {

    private int checkListId;
    private String title;
    private String description;
    private byte[] image;

    public Checklist(){}
    public Checklist(int checkListId, String title, String description, byte[] image) {
        this.checkListId = checkListId;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public int getCheckListId() {
        return checkListId;
    }

    public void setCheckListId(int checkListId) {
        this.checkListId = checkListId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}

