package com.neledva.task;

import java.time.LocalDate;

public class Souvenir {

    private String name;
    private double price;
    private Manufacturer manufacturer;
    private LocalDate date;

    public Souvenir(String name, double price, Manufacturer manufacturer, LocalDate date) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                ", date=" + date +
                '}';
    }
}
