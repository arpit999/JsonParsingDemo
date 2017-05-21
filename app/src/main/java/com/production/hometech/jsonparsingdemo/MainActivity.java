package com.production.hometech.jsonparsingdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.production.hometech.jsonparsingdemo.fragment.GsonFragment;
import com.production.hometech.jsonparsingdemo.fragment.OkhttpFragment;
import com.production.hometech.jsonparsingdemo.fragment.RetrofitFragment;
import com.production.hometech.jsonparsingdemo.fragment.SimpleFragment;

public class MainActivity extends AppCompatActivity {

    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new OkhttpFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.okhttp);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.retrofit);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.gson);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.simple);

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(item1, item2, item3, item4)
                .withShowDrawerOnFirstLaunch(true)
//                .withShowDrawerUntilDraggedOpened(true)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        Fragment fragment = null;
                        String title = getString(R.string.app_name);

                        switch (position) {

                            case 0:

                                fragment = new OkhttpFragment();
                                title = getString(R.string.okhttp);

                                break;
                            case 1:

                                fragment = new RetrofitFragment();
                                title = getString(R.string.retrofit);
                                break;
                            case 2:

                                fragment = new GsonFragment();
                                title = getString(R.string.gson);

                                break;
                            case 3:

                                fragment = new SimpleFragment();
                                title = getString(R.string.simple);

                                break;

                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, fragment);
                            fragmentTransaction.commit();

                            if (getSupportActionBar() != null) {

                                getSupportActionBar().setTitle(title);
                            }
                        }

                        return false;
                    }
                })
                .build();

    }
}
