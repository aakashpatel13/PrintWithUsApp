package com.example.sampleapp.model;

public class ProductItem {
    int product_item_id;
    String item_name;
    String image_url;
    String description;
    String price;
    int sub_product_id;

    public ProductItem(String item_name, String image_url, String description, String price, int sub_product_id) {
        this.item_name = item_name;
        this.image_url = image_url;
        this.description = description;
        this.price = price;
        this.sub_product_id = sub_product_id;
    }

    public ProductItem() {
    }

    public int getProduct_item_id() {
        return product_item_id;
    }

    public void setProduct_item_id(int product_item_id) {
        this.product_item_id = product_item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSub_product_id() {
        return sub_product_id;
    }

    public void setSub_product_id(int sub_product_id) {
        this.sub_product_id = sub_product_id;
    }
}
