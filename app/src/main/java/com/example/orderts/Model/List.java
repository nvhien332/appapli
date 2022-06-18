package com.example.orderts.Model;

public class List {
    String name, image, Price, id, category, tskthuat, quantity;

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public List(String name, String image, String Price) {
        this.name = name;
        this.image = image;
        this.Price = Price;
        this.id = id;
        this.category = category;
        this.tskthuat = tskthuat;
        this.quantity=quantity;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTskthuat() {
        return tskthuat;
    }

    public void setTskthuat(String tskthuat) {
        this.tskthuat = tskthuat;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List() {
    }
}
