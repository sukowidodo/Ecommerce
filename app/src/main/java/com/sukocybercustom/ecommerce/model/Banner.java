package com.sukocybercustom.ecommerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suko on 3/1/18.
 */

public class Banner {
    @SerializedName("banner_image_id")
    @Expose
    private String bannerImageId;
    @SerializedName("image")
    @Expose
    private String image;

    public String getBannerImageId() {
        return bannerImageId;
    }

    public void setBannerImageId(String bannerImageId) {
        this.bannerImageId = bannerImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
