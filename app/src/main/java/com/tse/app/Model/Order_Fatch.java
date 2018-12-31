package com.tse.app.Model;

public class Order_Fatch {
    private String ordercode;
    private String category;
    private String subcategory;
    private String product;
    private String area1;
    private String dimension;
    private String gsm;
    private String colour;
    private String remark;
    private String qualitiy;
    private String paymentterms;
    private String rate;
    private String Status;

    public Order_Fatch(String ordercode, String category, String subcategory, String product, String area1, String dimension, String gsm, String colour, String remark, String qualitiy, String paymentterms, String rate, String status) {
        this.ordercode = ordercode;
        this.category = category;
        this.subcategory = subcategory;
        this.product = product;
        this.area1 = area1;
        this.dimension = dimension;
        this.gsm = gsm;
        this.colour = colour;
        this.remark = remark;
        this.qualitiy = qualitiy;
        this.paymentterms = paymentterms;
        this.rate = rate;
        Status = status;
    }

    public Order_Fatch() {
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQualitiy() {
        return qualitiy;
    }

    public void setQualitiy(String qualitiy) {
        this.qualitiy = qualitiy;
    }

    public String getPaymentterms() {
        return paymentterms;
    }

    public void setPaymentterms(String paymentterms) {
        this.paymentterms = paymentterms;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
