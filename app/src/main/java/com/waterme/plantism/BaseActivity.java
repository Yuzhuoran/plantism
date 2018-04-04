package com.waterme.plantism;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.waterme.plantism.R;
import com.waterme.plantism.model.MyTextView;

public class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;
    protected LinearLayout mNavigationHeader;
    protected Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initToolbar();
        initNav();
        initHead();
        // for subclass, super.onCreate// set content

        // override content frames!

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator =
                    VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void initHead(){
        //Todo get the header and set the username
        View headerLayout=mNavigationView.getHeaderView(0);
        MyTextView user_name = headerLayout.findViewById(R.id.tv_user_name);
        SharedPreferences sharedPre = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_name.setText(sharedPre.getString("uname","").split(" ")[0]);
        //user_name.setText("Tony Stark");

    }
    private void initNav() {
        mDrawerLayout = findViewById(R.id.drawer);
        mDrawerLayout.setForegroundGravity(Gravity.CENTER);
        mNavigationView = (NavigationView) findViewById(R.id.nav);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setForegroundGravity(Gravity.CENTER);
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        // handle
                        mDrawerLayout.closeDrawers();
                        // Handle Intent for drawer
                        return true;
                    }
                }
        );
    }

    protected void setToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memu_navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            /*case R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;*/
            case R.id.menu_item_garden:
                Log.d("ITEM CLICKED", "clicked garden");
                Intent intent1 = new Intent(this, HomeActivity.class);
                this.startActivity(intent1);
                return true;
            case R.id.menu_item_sensor:
                Intent intent2 = new Intent(this, SensorHomeActivity.class);
                this.startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
