package com.sukocybercustom.ecommerce.model;

/**
 * Created by suko on 2/14/18.
 */

public class Cart {
    private int idproduct;
    private int quantity;

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
