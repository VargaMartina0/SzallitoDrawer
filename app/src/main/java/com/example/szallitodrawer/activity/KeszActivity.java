package com.example.szallitodrawer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.szallitodrawer.R;
import com.example.szallitodrawer.fragment.UjRendelesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class KeszActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesz);

        drawerLayout = findViewById(R.id.drawer_layout);

        FloatingActionButton fab = findViewById(R.id.fabKesz);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.drawer_layout, new UjRendelesFragment()).commit();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.frameKesz, new UjRendelesFragment())
                        .addToBackStack("Matykó")
                        .commit();

            }
        });
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
