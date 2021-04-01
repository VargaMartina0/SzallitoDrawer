package com.example.szallitodrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class KeszActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesz);

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
        recreate();
    }

    public void ClickFutarok(View view){
        BeerkezettActivity.redirectActivity(this, FutarokActivity.class);
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
