package com.example.szallitodrawer.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.szallitodrawer.fragment.BeerkezettRendelesekFragment;
import com.example.szallitodrawer.R;
import com.example.szallitodrawer.fragment.UjRendelesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BeerkezettActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beerkezett);

        drawerLayout = findViewById(R.id.drawer_layout);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.drawer_layout, new UjRendelesFragment()).commit();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.frame, new UjRendelesFragment())
                        .addToBackStack("Matykó")
                        .commit();

            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new BeerkezettRendelesekFragment())
                .commit();
    }

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickBeerkezett(View view) {
        recreate();
    }

    public void ClickKesz(View view) {
        redirectActivity(this, KeszActivity.class);
    }

    public void ClickFutarok(View view) {
        redirectActivity(this, FutarokActivity.class);
    }

    public void ClickKilep(View view) {
        logout(this);
    }

    /**
     * this looked nicer in a dedicated Util class, not in a random activity
     */
    public static void logout(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

}
