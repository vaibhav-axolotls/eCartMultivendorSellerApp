package com.axolotls.prachetaseller.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PriceVariation implements Serializable {
    String id, product_id, type, measurement, measurement_unit_id, price, discounted_price, serve_for, stock, stock_unit_id, measurement_unit_name, stock_unit_name;

    public PriceVariation() {
    }
    ArrayList<String> images;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getMeasurement_unit_id() {
        return measurement_unit_id;
    }

    public void setMeasurement_unit_id(String measurement_unit_id) {
        this.measurement_unit_id = measurement_unit_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(String discounted_price) {
        this.discounted_price = discounted_price;
    }

    public String getServe_for() {
        return serve_for;
    }

    public void setServe_for(String serve_for) {
        this.serve_for = serve_for;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStock_unit_id() {
        return stock_unit_id;
    }

    public void setStock_unit_id(String stock_unit_id) {
        this.stock_unit_id = stock_unit_id;
    }

    public String getMeasurement_unit_name() {
        return measurement_unit_name;
    }

    public void setMeasurement_unit_name(String measurement_unit_name) {
        this.measurement_unit_name = measurement_unit_name;
    }

    public String getStock_unit_name() {
        return stock_unit_name;
    }

    public void setStock_unit_name(String stock_unit_name) {
        this.stock_unit_name = stock_unit_name;
    }
}
