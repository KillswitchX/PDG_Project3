package com.icesi.pdg_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.icesi.pdg_project.View.Adapter_Planification;
import com.icesi.pdg_project.View.Item_Plan;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.io.Serializable;
import java.util.ArrayList;

public class PlanificationActivity extends AppCompatActivity implements Serializable {

    private BottomNavigationView navigation;

    private Button btn_add;

    private Spinner variables;

    private Spinner symbols;

    private EditText et_value;

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Item_Plan> items;

    private TextView textView_cost;

    private Button invest;

    private Integer cost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planification);
        items = new ArrayList<>();

        navigation=findViewById(R.id.planification_navigation);
        btn_add = findViewById(R.id.btn_planification_add);
        et_value = findViewById(R.id.et_value_planification);
        variables = findViewById(R.id.spinner_planification);
        invest = findViewById(R.id.planification_invest);
        textView_cost = findViewById(R.id.planification_cost);
        symbols = findViewById(R.id.spinner_symbols);
        cost=0;

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adapter_Planification(items, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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


        String[] segments = {"COLLEGE", "INCOME", "OVERAGE","LEFTOVER", "HOUSE", "HANDSET_PRICE", "OVER_15MINS_CALLS_PER_MONTH",
                "AVERAGE_CALL_DURATION", "REPORTED_SATISFACTION", "REPORTED_USAGE_LEVEL", "CONSIDERING_CHANGE_OF_PLAN"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlanificationActivity.this,
                android.R.layout.simple_spinner_dropdown_item, segments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        variables.setAdapter(adapter);

        String[] symbolsArray = {"=", ">", "<"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(PlanificationActivity.this,
                android.R.layout.simple_spinner_dropdown_item, symbolsArray);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        symbols.setAdapter(adapter2);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String var = variables.getSelectedItem().toString();
                String value = et_value.getText().toString() +"";
                String sym = symbols.getSelectedItem().toString();

                Item_Plan newItem = new Item_Plan(var, sym, value);
                items.add(newItem);
                adapter.notifyDataSetChanged();
                cost+=300;
                textView_cost.setText("$ " + cost);
            }
        });

        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempMoney = Stash.getInt("money");
                Stash.put("money", tempMoney - cost);
                Toast.makeText(PlanificationActivity.this, "You have invested: $" + cost + "Successfully.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(PlanificationActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

}
