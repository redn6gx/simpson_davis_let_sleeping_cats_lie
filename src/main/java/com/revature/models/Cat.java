package com.revature.models;

//@Entity(name="cats")
public class Cat {
//    @Id
    private int catId;
//    @Length(size="30")
    private String name;
    private String breed;
//    @OrderBy(direction="DESC")
    private double weight;


    public Cat(){}
    public Cat(String name, String breed, double weight){
        this.name = name;
        this.breed = breed;
        this.weight = weight;
    }

    public int getCatId() {
        return catId;
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

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
