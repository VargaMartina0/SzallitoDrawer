package com.example.szallitodrawer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.szallitodrawer.R;

public class FutarokActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futarok);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        BeerkezettActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        BeerkezettActivity.closeDrawer(drawerLayout);
    }

    public void ClickBeerkezett(View view){
        BeerkezettActivity.redirectActivity(this, BeerkezettActivity.class);
    }
    public void ClickKesz(View view){
        BeerkezettActivity.redirectActivity(this, KeszActivity.class);
    }

    public void ClickFutarok(View view){
        recreate();
    }
    public void ClickKilep(View view){
        BeerkezettActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BeerkezettActivity.closeDrawer(drawerLayout);
    }
}
