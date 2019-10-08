package com.icesi.pdg_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class PlanificationActivity extends AppCompatActivity {

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planification);


        navigation=findViewById(R.id.planification_navigation);

        navigation.setSelectedItemId(R.id.menu_planning);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.menu_home){
                    Intent home = new Intent(PlanificationActivity.this, HomeActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_client){
                    Intent home = new Intent(PlanificationActivity.this, ClientsInfo.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_metric){
                    Intent home = new Intent(PlanificationActivity.this, MetricsActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_diagram){
                    Intent home = new Intent(PlanificationActivity.this, DiagramsActivity.class);
                    startActivity(home);

                }

                return false;
            }
        });


        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(PlanificationActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

}
