package rktechltd.aucklandfishing.models;

/**
 * Class representing Category
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */
public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryDescription;

    /**
     * Constructor
     */
    public Category(){
    }

    /**
     * Constructor
     * @param categoryId
     * @param categoryName
     * @param categoryDescription
     */
    public Category(int categoryId,String categoryName, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    /**
     * Gets the category's id
     * @return
     */
    public int getCategoryId(){
        return this.categoryId;
    }

    /**
     * Sets the categor's id
     * @param categoryId
     */
    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }

    /**
     * Gets the category's name
     * @return
     */
    public String getCategoryName(){
        return this.categoryName;
    }

    /**
     * Sets the category name
     * @param categoryName
     */
    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    /**
     * gets the category description
     * @return
     */
    public String getCategoryDescription(){
        return this.categoryDescription;
    }

    /**
     * Sets the category description
     * @param categoryDescription
     */
    public void setCategoryDescription(String categoryDescription){
        this.categoryDescription = categoryDescription;
    }
}
