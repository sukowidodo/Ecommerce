package com.sukocybercustom.ecommerce.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by suko on 20/09/17.
 */

public class StartActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent ii = new Intent(StartActivity.this,SplashScreenActivity.class);
        startActivity(ii);
        finish();
    }
}
