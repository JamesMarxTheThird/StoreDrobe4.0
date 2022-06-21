package com.varsitycollege.storedrobe;

public class ItemClass {


    public ItemClass(String itemName) {
        this.itemName = itemName;
    }

    public ItemClass(){

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private String itemName;

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    private String itemDate;

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    private String itemDescription;

    public void pullItem()
    {

    }

    @Override
    public String toString(){

        return "Item Name: " + itemName + "\n" + "Date of acquisition: " + itemDate + "\n" + "Description: " + itemDescription ;

    }
}
