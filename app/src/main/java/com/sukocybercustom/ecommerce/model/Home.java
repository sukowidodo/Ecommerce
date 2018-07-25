package com.sukocybercustom.ecommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by suko on 3/1/18.
 */

public class Home {
    @SerializedName("category")
    @Expose
    private List<Category> category = null;
    @SerializedName("banner")
    @Expose
    private List<Banner> banner = null;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Banner> getBanner() {
        return banner;
    }

    public void setBanner(List<Banner> banner) {
        this.banner = banner;
    }
}
