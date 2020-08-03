package cz.deepvision.pos.orderlibrary.models;

import java.util.ArrayList;
import java.util.List;


public class OrderItem {
    private String category;
    private Integer count;
    private String name;
    private Double price;
    private String note = "";
    private String code;
    private List<OrderItem> sideDishes = new ArrayList<>();
    private List<OrderItem> cover = new ArrayList<>();
    private Double vatPrice;


    public OrderItem(Integer count, String name, Double price, Double vat) {
        this.count = count;
        this.name = name;
        this.price = price;
        this.vatPrice = vat;
        this.category = "";
    }

    public OrderItem(Integer count, String name, Double price, Double vat, String code, String category,String note) {
        this.count = count;
        this.name = name;
        this.price = price;
        this.vatPrice = vat;
        this.note = note;
        this.code = code;
        this.category = category;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public List<OrderItem> getSideDish() {
        return sideDishes;
    }

    public void setSideDish(List<OrderItem> sideDish) {
        this.sideDishes = sideDish;
    }

    public List<OrderItem> getCover() {
        return cover;
    }

    public void setCover(List<OrderItem> cover) {
        this.cover = cover;
    }

    public Double getVatPrice() {
        return vatPrice;
    }

    public void setVatPrice(Double vatPrice) {
        this.vatPrice = vatPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Item: " + name +
                "| count= " + count +
                "| price= " + price +
                "| sideDishes= " + sideDishes +
                "| cover= " + cover +
                "| vatPrice= " + vatPrice;
    }
}
