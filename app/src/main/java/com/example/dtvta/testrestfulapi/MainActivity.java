package com.example.dtvta.testrestfulapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.dtvta.testrestfulapi.common.Config;
import com.example.dtvta.testrestfulapi.common.DowloadJSON;
import com.example.dtvta.testrestfulapi.fragment.AboutFragment;
import com.example.dtvta.testrestfulapi.fragment.HomeFragment;
import com.example.dtvta.testrestfulapi.fragment.LocateFragment;
import com.example.dtvta.testrestfulapi.fragment.SearchFragment;
import com.example.dtvta.testrestfulapi.fragment.SettingFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private FragmentManager manager;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar= (Toolbar) findViewById(R.id.toolBar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView= (NavigationView) findViewById(R.id.navigationView);

        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Home");

        toggle= new ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.addDrawerListener(toggle);
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        toggle.syncState();


        manager=this.getSupportFragmentManager();
        FragmentTransaction transactionHome=manager.beginTransaction();
        HomeFragment homeFragment=new HomeFragment();
        transactionHome.replace(R.id.content,homeFragment);
        transactionHome.commit();

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        toolBar.setTitle(item.getTitle());
        drawerLayout.closeDrawers();
        switch (id){
            case R.id.mnu_home:
                FragmentTransaction transactionHome=manager.beginTransaction();
                HomeFragment homeFragment=new HomeFragment();
                transactionHome.replace(R.id.content,homeFragment);
                transactionHome.commit();
                break;
            case R.id.mnu_location:
                FragmentTransaction transactionLocation=manager.beginTransaction();
                LocateFragment locationFragment=new LocateFragment();
                transactionLocation.replace(R.id.content,locationFragment);
                transactionLocation.commit();
                break;
            case R.id.mnu_search:
                FragmentTransaction transactionSearch=manager.beginTransaction();
                SearchFragment searchFragment=new SearchFragment();
                transactionSearch.replace(R.id.content,searchFragment);
                transactionSearch.commit();
                break;
            case R.id.mnu_setting:
                FragmentTransaction transactionSetting=manager.beginTransaction();
                SettingFragment settingFragment=new SettingFragment();
                transactionSetting.replace(R.id.content,settingFragment);
                transactionSetting.commit();
                break;
            case R.id.mnu_about:
                FragmentTransaction transactionAbout=manager.beginTransaction();
                AboutFragment aboutFragment=new AboutFragment();
                transactionAbout.replace(R.id.content,aboutFragment);
                transactionAbout.commit();
                break;
        }
        return true;
    }

}
