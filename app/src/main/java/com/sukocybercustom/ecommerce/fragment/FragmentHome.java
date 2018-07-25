package com.sukocybercustom.ecommerce.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.sukocybercustom.ecommerce.R;
import com.sukocybercustom.ecommerce.adapter.CategoryAdapter;
import com.sukocybercustom.ecommerce.adapter.ProductAdapter;
import com.sukocybercustom.ecommerce.model.Banner;
import com.sukocybercustom.ecommerce.model.Category;
import com.sukocybercustom.ecommerce.model.Product;
import com.sukocybercustom.ecommerce.model.Home;
import com.sukocybercustom.ecommerce.retrofit.ApiService;
import com.sukocybercustom.ecommerce.retrofit.RetroClient;
import com.sukocybercustom.ecommerce.utils.InternetConnection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {
    @BindView(R.id.rvCategory) RecyclerView rvCategory;
    @BindView(R.id.slBanner) SliderLayout slBanner;
    List<Category> categoryList;
    Home home;
    List<Banner> bannerList;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        ButterKnife.bind(this,view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),4);
        rvCategory.setLayoutManager(layoutManager);
        ShowCategory();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }

    public void ShowCategory(){
        if (InternetConnection.checkConnection(getActivity().getApplicationContext())) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("please wait...");
            dialog.show();

            //Creating an object of our api interface
            ApiService api = RetroClient.getApiService();

            /**
             * Calling JSON
             */
            Call<Home> call = api.getMyHome();

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<Home>() {
                @Override
                public void onResponse(Call<Home> call, Response<Home> response) {
                    //Dismiss Dialog
                    dialog.dismiss();
                    if(response.isSuccessful()) {
                        categoryList = response.body().getCategory();
                        bannerList = response.body().getBanner();

                        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(),categoryList);
                        rvCategory.setAdapter(categoryAdapter);

                        for (Banner listbanner : bannerList) {
                            TextSliderView textSliderView = new TextSliderView(getContext());
                            textSliderView.description("").image(listbanner.getImage()).setScaleType(BaseSliderView.ScaleType.CenterInside);
                            slBanner.addSlider(textSliderView);
                        }

                        slBanner.setPresetTransformer(SliderLayout.Transformer.Tablet);
                        slBanner.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                        slBanner.setCustomAnimation(new DescriptionAnimation());
                        slBanner.setDuration(3000);
                    } else {
                        Log.e("error","Respon dari server bermasalah");
                    }
                }

                @Override
                public void onFailure(Call<Home> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            //Snackbar.make(parentView, "internet bermasalah", Snackbar.LENGTH_LONG).show();
        }
    }
}
