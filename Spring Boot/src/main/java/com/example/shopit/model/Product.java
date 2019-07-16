package com.example.shopit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

    private long productid;
    private String productname;
    private float productvalue;
    private String productdescription;

    public Product(long productid, String productname, float productvalue, String productdescription) {
        this.productid = productid;
        this.productname = productname;
        this.productvalue = productvalue;
        this.productdescription = productdescription;
    }

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getProductid() {
        return productid;
    }

    public void setProductid(long productid) {
        this.productid = productid;
    }

    @NotNull
    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    @NotNull
    public float getProductvalue() {
        return productvalue;
    }

    public void setProductvalue(float productvalue) {
        this.productvalue = productvalue;
    }


    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }
}
