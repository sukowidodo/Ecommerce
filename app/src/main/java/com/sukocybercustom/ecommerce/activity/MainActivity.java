package com.sukocybercustom.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sukocybercustom.ecommerce.fragment.FragmentHome;
import com.sukocybercustom.ecommerce.fragment.FragmentProductAll;
import com.sukocybercustom.ecommerce.fragment.FragmentProductInCategory;
import com.sukocybercustom.ecommerce.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static Menu menu;
    public static boolean isChangeMenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (getIntent().hasExtra("category_id")){
            SelectView(Integer.parseInt(getIntent().getStringExtra("category_id")));
        }else {
            SelectView(999);
        }



        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        SelectView(999);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!isChangeMenu){
            menu.findItem(R.id.buy_actionbar).setIcon(R.drawable.cart);
        }else {
            menu.findItem(R.id.buy_actionbar).setIcon(R.drawable.cart2);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.buy_actionbar:
                Intent intent = new Intent(this,CheckoutActivity.class);
                startActivity(intent);
                break;
            case R.id.search_actionbar:
                Intent intent1 = new Intent(this,SearchActivity.class);
                startActivity(intent1);
        }

        return super.onOptionsItemSelected(item);
    }

    public void SelectNavigationView(int id){
        Fragment fragment = null;

        switch (id){
            case R.id.nav_1:
                fragment = new FragmentProductAll();
                break;
            case R.id.nav_2:
                fragment = new FragmentProductInCategory(24);
                break;
            case  R.id.nav_3:
                fragment = new FragmentProductInCategory(34);
                break;
            case  R.id.nav_4:
                fragment = new FragmentProductInCategory(33);
                break;
            case  R.id.nav_5:
                fragment = new FragmentProductInCategory(28);
                break;
            case  R.id.nav_user:
                Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        if(fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content,fragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void SelectView(int id_category){
        Fragment fragment = null;
        if (id_category==999){
            fragment = new FragmentHome();
        }else {
            fragment = new FragmentProductInCategory(id_category);
        }

        if(fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content,fragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        SelectNavigationView(id);
        return true;
    }

}
