package com.neledva.task;

public class Manufacturer {

    private String manufacName;
    private String country;

    public Manufacturer(String manufacName, String country) {
        this.manufacName = manufacName;
        this.country = country;
    }

    public String getManufacName() {
        return manufacName;
    }

    public void setManufacName(String manufacName) {
        this.manufacName = manufacName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "manufacName='" + manufacName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
