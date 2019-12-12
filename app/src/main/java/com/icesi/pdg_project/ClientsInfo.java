package com.icesi.pdg_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.icesi.pdg_project.Entity.Client;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientsInfo extends AppCompatActivity {

    private ArrayList<Client> clients;

    private TableLayout tableLayout;

    private HashMap<String, ArrayList<Client>> segmentedClients;

    private BottomNavigationView navigation;

    private Button btnInfo;

    public Dialog dialogInfo;

    public ImageView closeDialog;

    public TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_info);

        tableLayout = (TableLayout) findViewById(R.id.tableLayoutClients);
        navigation = findViewById(R.id.metrics_navigation);
        btnInfo = findViewById(R.id.clients_info_buttonr);
        dialogInfo = new Dialog(this);

        createColumns();


        navigation.setSelectedItemId(R.id.menu_client);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.menu_home){
                    Intent home = new Intent(ClientsInfo.this, HomeActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_diagram){
                    Intent home = new Intent(ClientsInfo.this, DiagramsActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_planning){
                    Intent home = new Intent(ClientsInfo.this, PlanificationActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_metric){
                    Intent home = new Intent(ClientsInfo.this, MetricsActivity.class);
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


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        clients = new ArrayList<>();
        segmentedClients = new HashMap<>();

        segmentedClients.put("clients", clients);



        ClientsInfo.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initTestClients();
            }
        });

        //fillDataIntoTable(clients);

    }





    private void initTestClients() {

        try {
            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard,"Download/"+"churn.csv");
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] nextLine;
            int i=0;
            nextLine = reader.readNext();
            //Log.e(">>>>>>>> 2 ", nextLine[0]);

            while ((nextLine = reader.readNext()) != null) {
                //Client client = new Client();
                //Log.e(">>>>>>>> 1 ", nextLine[0]);

                String[] line = nextLine[0].split(";");
                if(line.length>2){

                    TableRow tableRow = new TableRow(this);
                    tableRow.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    int y=0;
                    // Colege
                    TextView textViewCollege = new TextView(this);
                    textViewCollege.setText(line[y]);

                    textViewCollege.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewCollege);

                    y++;
                    // Income
                    TextView textViewIncome = new TextView(this);
                    textViewIncome.setText(line[y]);

                    textViewIncome.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewIncome);

                    y++;
                    // Overage
                    TextView textViewOverage = new TextView(this);
                    textViewOverage.setText(line[y]);

                    textViewOverage.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewOverage);

                    y++;
                    // LeftOver
                    TextView textViewLeftover = new TextView(this);
                    textViewLeftover.setText(line[y]);

                    textViewLeftover.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewLeftover);

                    y++;
                    // House
                    TextView textViewHouse = new TextView(this);
                    textViewHouse.setText(line[y]);

                    textViewHouse.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewHouse);

                    y++;
                    // Handset Price
                    TextView textViewHandsetPrice = new TextView(this);
                    textViewHandsetPrice.setText(line[y]);

                    textViewHandsetPrice.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewHandsetPrice);

                    y++;
                    // Average calls Over 15 minutes
                    TextView textViewOver15 = new TextView(this);
                    textViewOver15.setText(line[y]);

                    textViewOver15.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewOver15);

                    y++;
                    // Average Call Duration
                    TextView textViewAverageCallDuration = new TextView(this);
                    textViewAverageCallDuration.setText(line[y]);

                    textViewAverageCallDuration.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewAverageCallDuration);

                    y++;
                    // Reported Satisfaction
                    TextView textViewReportedSatisfaction = new TextView(this);
                    textViewReportedSatisfaction.setText(line[y]);

                    textViewReportedSatisfaction.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewReportedSatisfaction);

                    y++;
                    // Reported Usage Level
                    TextView textViewReportedUsageLevel = new TextView(this);
                    textViewReportedUsageLevel.setText(line[y]);

                    textViewReportedUsageLevel.setPadding(15, 5, 5, 0);
                    tableRow.addView(textViewReportedUsageLevel);

//                    int y=0;
//                    client.setCollege(line[y]);
//                    y++;
//                    client.setIncome(Integer.parseInt(line[y]));
//                    y++;
//                    client.setOverage(Integer.parseInt(line[y]));
//                    y++;
//                    client.setLeftover(Integer.parseInt(line[y]));
//                    y++;
//                    client.setHouse(Integer.parseInt(line[y]));
//                    y++;
//                    client.setHandsetPrice(Integer.parseInt(line[y]));
//                    y++;
//                    client.setOver15(Integer.parseInt(line[y]));
//                    y++;
//                    client.setAverageCallDuration(Integer.parseInt(line[y]));
//                    y++;
//                    client.setReportedSatisfaction(line[y]);
//                    y++;
//                    client.setReportedUsageLevel(line[y]);

//                    clients.add(client);

                    tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                            TableRow.LayoutParams.FILL_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                }



            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
          catch (IOException x){
              x.printStackTrace();
          }

    }



    private void createColumns() {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        // College
        TextView textViewCollege = new TextView(this);
        textViewCollege.setText("College  ");
        textViewCollege.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewCollege.setPadding(15, 5, 5, 0);
        textViewCollege.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewCollege);

        // Income
        TextView textViewIncome = new TextView(this);
        textViewIncome.setText("Income  ");
        textViewIncome.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewIncome.setPadding(15, 5, 5, 0);
        textViewIncome.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewIncome);

        // Overage
        TextView textViewOverage = new TextView(this);
        textViewOverage.setText("Overage per month  ");
        textViewOverage.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewOverage.setPadding(15, 5, 5, 0);
        textViewOverage.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewOverage);

        // LeftOver
        TextView textViewLeftover = new TextView(this);
        textViewLeftover.setText("Leftover per month  ");
        textViewLeftover.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewLeftover.setPadding(15, 5, 5, 0);
        textViewLeftover.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewLeftover);

        // House
        TextView textViewHouse = new TextView(this);
        textViewHouse.setText("House  ");
        textViewHouse.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewHouse.setPadding(15, 5, 5, 0);
        textViewHouse.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewHouse);

        // Handset Price
        TextView textViewHandsetPrice = new TextView(this);
        textViewHandsetPrice.setText("Handset Price  ");
        textViewHandsetPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewHandsetPrice.setPadding(15, 5, 5, 0);
        textViewHandsetPrice.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewHandsetPrice);

        // Average calls Over 15 minutes
        TextView textViewOver15 = new TextView(this);
        textViewOver15.setText("Calls over 15 min per month  ");
        textViewOver15.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewOver15.setPadding(15, 5, 5, 0);
        textViewOver15.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewOver15);

        // Average Call Duration
        TextView textViewAverageCallDuration = new TextView(this);
        textViewAverageCallDuration.setText("Average call duration  ");
        textViewAverageCallDuration.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewAverageCallDuration.setPadding(15, 5, 5, 0);
        textViewAverageCallDuration.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewAverageCallDuration);

        // Reported Satisfaction
        TextView textViewReportedSatisfaction = new TextView(this);
        textViewReportedSatisfaction.setText("Reported satisfaction level  ");
        textViewReportedSatisfaction.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewReportedSatisfaction.setPadding(15, 5, 5, 0);
        textViewReportedSatisfaction.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewReportedSatisfaction);

        // Reported Usage Level
        TextView textViewReportedUsageLevel = new TextView(this);
        textViewReportedUsageLevel.setText("Reported usage level  ");
        textViewReportedUsageLevel.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewReportedUsageLevel.setPadding(15, 5, 5, 0);
        textViewReportedUsageLevel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tableRow.addView(textViewReportedUsageLevel);


        tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));


    }

    private void fillDataIntoTable(ArrayList<Client> clientsToFill) {
        int size = clientsToFill.size();
        Client currentClient = null;
        for (int i = 0; i < size; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            currentClient = clientsToFill.get(i);

            // College
            TextView textViewCollege = new TextView(this);
            textViewCollege.setText(currentClient.getCollege());

            textViewCollege.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewCollege);

            // Income
            TextView textViewIncome = new TextView(this);
            textViewIncome.setText(currentClient.getIncome()+"");

            textViewIncome.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewIncome);

            // Overage
            TextView textViewOverage = new TextView(this);
            textViewOverage.setText(currentClient.getOverage()+"");

            textViewOverage.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewOverage);

            // LeftOver
            TextView textViewLeftover = new TextView(this);
            textViewLeftover.setText(currentClient.getLeftover()+"");

            textViewLeftover.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewLeftover);

            // House
            TextView textViewHouse = new TextView(this);
            textViewHouse.setText(currentClient.getHouse()+"");

            textViewHouse.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewHouse);

            // Handset Price
            TextView textViewHandsetPrice = new TextView(this);
            textViewHandsetPrice.setText(currentClient.getHandsetPrice()+"");

            textViewHandsetPrice.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewHandsetPrice);

            // Average calls Over 15 minutes
            TextView textViewOver15 = new TextView(this);
            textViewOver15.setText(currentClient.getOver15()+"");

            textViewOver15.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewOver15);

            // Average Call Duration
            TextView textViewAverageCallDuration = new TextView(this);
            textViewAverageCallDuration.setText(currentClient.getAverageCallDuration()+"");

            textViewAverageCallDuration.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewAverageCallDuration);

            // Reported Satisfaction
            TextView textViewReportedSatisfaction = new TextView(this);
            textViewReportedSatisfaction.setText(currentClient.getReportedSatisfaction());

            textViewReportedSatisfaction.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewReportedSatisfaction);

            // Reported Usage Level
            TextView textViewReportedUsageLevel = new TextView(this);
            textViewReportedUsageLevel.setText(currentClient.getReportedUsageLevel());

            textViewReportedUsageLevel.setPadding(15, 5, 5, 0);
            tableRow.addView(textViewReportedUsageLevel);


            tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

        }
    }


    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(ClientsInfo.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    private void showPopup(){

        dialogInfo.setContentView(R.layout.info_popup);
        closeDialog = (ImageView) dialogInfo.findViewById(R.id.btn_close_info);
        textViewInfo = (TextView) dialogInfo.findViewById(R.id.textView_info);
        textViewInfo.setMovementMethod(new ScrollingMovementMethod());

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfo.dismiss();
            }
        });


        dialogInfo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogInfo.show();


    }
}
