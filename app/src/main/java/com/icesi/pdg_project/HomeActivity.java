package com.icesi.pdg_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.icesi.pdg_project.Model.WekaTest;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView navigation;

    private TextView textViewMoney;

    private TextView textViewClients;

    private TextView textViewTurn;

    private Button btn_nextTurn;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewMoney = findViewById(R.id.et_home_money);
        textViewClients = findViewById(R.id.et_home_clients);
        textViewTurn = findViewById(R.id.et_home_turn);

        navigation = findViewById(R.id.home_navigation);
        btn_nextTurn = findViewById(R.id.btn_next_turn);

        navigation.setSelectedItemId(R.id.menu_home);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.menu_client){
                    Intent clients = new Intent(HomeActivity.this, ClientsInfo.class);
                    startActivity(clients);

                }
                if(menuItem.getItemId()==R.id.menu_metric){
                    Intent home = new Intent(HomeActivity.this, MetricsActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_diagram){
                    Intent home = new Intent(HomeActivity.this, DiagramsActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_planning){
                    Intent home = new Intent(HomeActivity.this, PlanificationActivity.class);
                    startActivity(home);

                }
                //Wow, this is awesome


                return false;
            }
        });


        btn_nextTurn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                WekaTest weka = new WekaTest();
                try {
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        Log.v("DDD","Permission is granted");
                        weka.ejecution();
                    } else {

                        Log.v("ddd","Permission is revoked");
                        ActivityCompat.requestPermissions(HomeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
//                alertDialogBuilder.setTitle("Confirm Turn...");
//                alertDialogBuilder.setIcon(R.drawable.ic_exit);
//                alertDialogBuilder.setMessage("Are you sure you did finish your turn?" +"\n"+ "\n");
//
//                alertDialogBuilder.setCancelable(false);
//
//                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
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
    protected void onResume() {
        super.onResume();

        setMoney(2000);
        setClients(100);
        setTurn(1);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirm Exit...");
        alertDialogBuilder.setIcon(R.drawable.ic_exit);
        alertDialogBuilder.setMessage("Are you sure you want to leave?" +"\n"+ "\n" +"You will be declared bankrupt!");

        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.finishAffinity(HomeActivity.this);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    public void setMoney(int money){
        String textMoney = "$" + money;
        textViewMoney.setText(textMoney);
    }

    public void setClients(int clients){
        String textClients = "" + clients;
        textViewClients.setText(textClients);
    }

    public void setTurn(int turn){
        String textTurn = "" + turn;
        textViewTurn.setText(textTurn);
    }

    public String getMoney(){
        return  textViewMoney.getText().toString();
    }

    public String getClients(){
        return  textViewClients.getText().toString();
    }

    public String getTurn(){
        return  textViewTurn.getText().toString();
    }




}
