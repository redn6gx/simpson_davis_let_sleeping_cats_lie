package com.revature.models;

import annotations.Entity;
import annotations.Id;
import annotations.Length;
import annotations.OrderBy;

@Entity(name="cats")
public class Cat {
    @Id
    public int catid;
    @Length(size="30")
    @OrderBy
    public String name;
    public String breed;
//    private double weight;


    public Cat(){}
//    public Cat(String name, String breed, double weight){
//        this.name = name;
//        this.breed = breed;
//        this.weight = weight;
//    }
public Cat(String name, String breed){
    this.name = name;
    this.breed = breed;
}

    public int getCatId() {
        return catid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

//    public double getWeight() {
//        return weight;
//    }
//    public void setWeight(double weight) {
//        this.weight = weight;
//    }
}
