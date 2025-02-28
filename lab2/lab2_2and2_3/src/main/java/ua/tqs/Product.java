package ua.tqs;

import java.util.Objects;

public class Product {
    private int id;
    private String image;
    private String description;
    private double price;
    private String title;
    private String category;

    public Product() {}

    public Product(int id, String image, String description, double price, String title, String category) {
        this.id = id;
        this.image = image;
        this.price = price;
        this.description = description;
        this.title = title;
        this.category = category;
    }
    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
