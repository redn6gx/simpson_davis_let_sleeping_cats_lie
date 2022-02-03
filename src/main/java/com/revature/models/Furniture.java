package com.revature.models;

//@Entity
public class Furniture {
//    @Id
    int furnitureId;
    String type;
    boolean cozy;

    public Furniture(){}
    public Furniture(String type, boolean cozy){
        this.type = type;
        this.cozy = cozy;
    }

    public int getFurnitureId() {
        return furnitureId;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public boolean isCozy() {
        return cozy;
    }
    public void setCozy(boolean cozy) {
        this.cozy = cozy;
    }
}
