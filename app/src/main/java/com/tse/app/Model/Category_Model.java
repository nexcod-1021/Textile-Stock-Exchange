package com.tse.app.Model;

public class Category_Model {
    private String id;
    private String user_id;
    private String cat_id;
    private String subcategory;
    private String qty;
    private String productname;
    private String qty_type;

    public Category_Model(String id, String user_id, String cat_id, String subcategory, String qty, String productname, String qty_type) {
        this.id = id;
        this.user_id = user_id;
        this.cat_id = cat_id;
        this.subcategory = subcategory;
        this.qty = qty;
        this.productname = productname;
        this.qty_type = qty_type;
    }

    public Category_Model() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getQty_type() {
        return qty_type;
    }

    public void setQty_type(String qty_type) {
        this.qty_type = qty_type;
    }
}
