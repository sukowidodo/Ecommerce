package com.sukocybercustom.ecommerce.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.sukocybercustom.ecommerce.R;
import com.sukocybercustom.ecommerce.model.Product;
import com.sukocybercustom.ecommerce.retrofit.ApiService;
import com.sukocybercustom.ecommerce.retrofit.RetroClient;
import com.sukocybercustom.ecommerce.utils.InternetConnection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    @BindView(R.id.slProduct) SliderLayout slProduct;
    Product product;
    List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        ShowDetail(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void ShowDetail(Context context){
        if (InternetConnection.checkConnection(context)) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(context);
            dialog.setMessage("please wait...");
            dialog.show();

            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Log.d("id cobaaa",String.valueOf(getIntent().getStringExtra("id")));
            Call<Product> call = api.getMyProductDetail(getIntent().getStringExtra("id"));

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if(response.isSuccessful()) {
                        /**
                         * Got Successfully*/
                        product = response.body();
                        images  = response.body().getImages();
                        for (String listgambar : images) {
                            TextSliderView textSliderView = new TextSliderView(getBaseContext());
                            textSliderView.description("").image(listgambar).setScaleType(BaseSliderView.ScaleType.CenterInside);
                            slProduct.addSlider(textSliderView);
                        }

                        slProduct.setPresetTransformer(SliderLayout.Transformer.Tablet);
                        slProduct.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                        slProduct.setCustomAnimation(new DescriptionAnimation());
                        slProduct.setDuration(3000);


                    } else {
                        Log.e("Error","respon bermasalah");
                        //Toast.makeText(context,"Respon dari server bermasalah",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            //Toast.makeText(context,"Koneksi bermasalah",Toast.LENGTH_LONG).show();
            Log.e("Error","koneksi bermasalah");
        }
    }

}
