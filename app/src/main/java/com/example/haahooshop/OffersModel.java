package com.example.haahooshop;

class OffersModel {
    public  String name;
    public String radio;

    public OffersModel(String s) {

        this.name=s;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }
}
