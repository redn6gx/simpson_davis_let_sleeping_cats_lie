package com.revature.models;

import annotations.Entity;
import annotations.Id;
import annotations.OrderBy;

@Entity(name="furniture")
public class Furniture {
    @Id
    int furnitureId;
    @OrderBy(direction="DESC")
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
