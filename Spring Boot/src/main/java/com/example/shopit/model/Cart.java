package com.example.shopit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Cart {

    private String cartid;
    private String name;
    private String description;
    private float cost;
    private long quantity;
    private float costofeach;

    public Cart(String cartid, String name, String description, float cost, long quantity, float costofeach) {
        this.cartid = cartid;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.quantity = quantity;
        this.costofeach = costofeach;
    }

    public Cart() {
    }

    @Id
    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Min(value = 0)
    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @NotNull
    @Min(value = 1)
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @NotNull
    @Min(value = 0)
    public float getCostofeach() {
        return costofeach;
    }

    public void setCostofeach(float costofeach) {
        this.costofeach = costofeach;
    }
}
