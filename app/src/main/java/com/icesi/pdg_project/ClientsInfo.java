package com.icesi.pdg_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.icesi.pdg_project.Entity.Client;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientsInfo extends AppCompatActivity {

    private ArrayList<Client> clients;

    private TableLayout tableLayout;

    private Spinner spinnerClients;

    private HashMap<String, ArrayList<Client>> segmentedClients;

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients_info);

        tableLayout = (TableLayout) findViewById(R.id.tableLayoutClients);
        spinnerClients = findViewById(R.id.clients_spinner_chooser);
        navigation = findViewById(R.id.metrics_navigation);

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        clients = new ArrayList<>();
        segmentedClients = new HashMap<>();

        segmentedClients.put("clients", clients);

        initTestClients();
        initView();
        fillDataIntoTable(clients);

    }


    private void initView() {

        String[] segments = {" Clients ", " SegmentA ", " SegmentB "};
        spinnerClients.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, segments));
    }


    private void initTestClients() {
        Client client = new Client();

        client.setCollege("zero");
        client.setIncome(31953);
        client.setOverage(0);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("one");
        client.setIncome(40000);
        client.setOverage(5);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(3);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("zero");
        client.setIncome(35666);
        client.setOverage(10);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("one");
        client.setIncome(38600);
        client.setOverage(15);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(3);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("zero");
        client.setIncome(27596);
        client.setOverage(20);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("one");
        client.setIncome(33614);
        client.setOverage(25);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("zero");
        client.setIncome(42371);
        client.setOverage(30);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("one");
        client.setIncome(29964);
        client.setOverage(35);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("zero");
        client.setIncome(42587);
        client.setOverage(40);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("one");
        client.setIncome(46300);
        client.setOverage(45);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("zero");
        client.setIncome(49860);
        client.setOverage(50);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("one");
        client.setIncome(47645);
        client.setOverage(55);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("zero");
        client.setIncome(42331);
        client.setOverage(60);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("one");
        client.setIncome(36451);
        client.setOverage(65);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);

        client = new Client();
        client.setCollege("zero");
        client.setIncome(31930);
        client.setOverage(70);
        client.setLeftover(6);
        client.setHouse(313378);
        client.setHandsetPrice(161);
        client.setOver15(0);
        client.setAverageCallDuration(4);
        client.setReportedSatisfaction("unsat");
        client.setReportedUsageLevel("little");

        clients.add(client);



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
}
