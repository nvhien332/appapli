package com.example.orderts.Model;

public class Store {
    String name, image, Price, id;

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public Store(String name, String image, String Price) {
        this.id=id;
        this.name = name;
        this.image = image;
        this.Price = Price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Store() {
    }
}
