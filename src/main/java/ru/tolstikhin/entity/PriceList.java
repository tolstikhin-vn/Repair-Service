package ru.tolstikhin.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PriceList {

//    private int id;
    private String name;
    private String terms;
    private int price;

    public PriceList(String name, String terms, int price) {
//        this.id = id;
        this.name = name;
        this.terms = terms;
        this.price = price;
    }

    public PriceList() {}

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
