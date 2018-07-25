package com.sukocybercustom.ecommerce.retrofit;


import com.sukocybercustom.ecommerce.model.Home;
import com.sukocybercustom.ecommerce.model.Product;
import com.sukocybercustom.ecommerce.model.ProductList;
import com.sukocybercustom.ecommerce.model.Session;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Pratik Butani.
 */
public interface ApiService {

    @GET("/login.php")
    Call<Session> getsessionid();
    @GET("products.php")
    Call<ProductList> getMyProduct();
    @GET("productcategory.php")
    Call<Home> getMyHome();
    @GET("productincategory.php")
    Call<ProductList> getMyProductinCategory(@Query("cat") int category);
    @GET("productdetail.php")
    Call<Product> getMyProductDetail(@Query("id") String product_id);
}
