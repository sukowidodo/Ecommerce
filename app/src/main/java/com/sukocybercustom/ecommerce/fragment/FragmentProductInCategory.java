package com.sukocybercustom.ecommerce.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sukocybercustom.ecommerce.R;
import com.sukocybercustom.ecommerce.adapter.ProductAdapter;
import com.sukocybercustom.ecommerce.model.Product;
import com.sukocybercustom.ecommerce.model.ProductList;
import com.sukocybercustom.ecommerce.retrofit.ApiService;
import com.sukocybercustom.ecommerce.retrofit.RetroClient;
import com.sukocybercustom.ecommerce.utils.InternetConnection;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentProductInCategory extends Fragment {

    @BindView(R.id.rvProduk)
    RecyclerView rvProduk;
    @BindView(R.id.parentPanel) View parentView;
    private int id_category;
    private ArrayList<Product> artikellist;
    ProductAdapter adapter;

    public FragmentProductInCategory(int id_category) {
        this.id_category = id_category;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Category");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_product_all, container, false);
        ButterKnife.bind(this,rootView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        rvProduk.setLayoutManager(layoutManager);

        ShowList();
        /**
         * Getting List and Setting List Adapter
         */

        return rootView;
    }

    public void ShowList(){
        /**
         * Checking Internet Connection
         */
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
            Call<ProductList> call = api.getMyProductinCategory(id_category);

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<ProductList>() {
                @Override
                public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if(response.isSuccessful()) {
                        /**
                         * Got Successfully
                         */
                        artikellist = response.body().getProducts();

                        /**
                         * Binding that List to Adapter
                         */
                        adapter = new ProductAdapter(getContext(), artikellist);
                        for (Product art : artikellist) {
                            Log.d("Informasi", art.getName());
                        }
                        rvProduk.setAdapter(adapter);

                    } else {
                        //Snackbar.make(parentView, "bermasalah", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ProductList> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            //Snackbar.make(parentView, "internet bermasalah", Snackbar.LENGTH_LONG).show();
        }
    }


}
