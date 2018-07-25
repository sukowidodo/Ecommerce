package com.sukocybercustom.ecommerce.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.sukocybercustom.ecommerce.R;
import com.sukocybercustom.ecommerce.model.Session;
import com.sukocybercustom.ecommerce.retrofit.ApiService;
import com.sukocybercustom.ecommerce.retrofit.RetroClient;
import com.sukocybercustom.ecommerce.utils.InternetConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.Login(this);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void Login(Context context){
        /**
         * Checking Internet Connection
         */
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
            Call<Session> call = api.getsessionid();

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<Session>() {
                @Override
                public void onResponse(Call<Session> call, Response<Session> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if(response.isSuccessful()) {
                        Log.d("Session_id",response.body().getSessionid());
                    } else {
                        //Snackbar.make(parentView, "bermasalah", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Session> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            //Snackbar.make(parentView, "internet bermasalah", Snackbar.LENGTH_LONG).show();
        }
    }
}

