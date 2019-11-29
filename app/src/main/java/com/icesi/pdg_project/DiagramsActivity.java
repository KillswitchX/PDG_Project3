package com.icesi.pdg_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.icesi.pdg_project.Model.WekaTest;
import com.icesi.pdg_project.View.ViewHolder;

import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;

public class DiagramsActivity extends AppCompatActivity {

    private int nodeCount = 1;
    private BottomNavigationView navigation;
    //private CustomView cv;
//    private Spinner spinnerVariablesOne;
//    private Spinner spinnerVariablesTwo;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagrams);

        //TreeView treeView = findViewById(R.id.tree);

        BaseTreeAdapter adapter = new BaseTreeAdapter<ViewHolder>(this, R.layout.node) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(View view) {
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {
                viewHolder.mTextView.setText(data.toString());
            }
        };
        //treeView.setAdapter(adapter);


        // example tree
        TreeNode rootNode = new TreeNode("N0" + "\n" + "Fix this algorithm" );
        final TreeNode n1 = new TreeNode("N1 Fix this algorithm" + "\n" + "<= 603802");
//        final TreeNode n6 = new TreeNode("N6 INCOME" + "\n" + "> 603802");
//
//
//        final TreeNode n2 = new TreeNode("N2 LEFTOVER" + "\n" + "<= 85");
//        final TreeNode n5 = new TreeNode("N5 LEAVE (1066.0/244.0)" + "\n" + "> 85");
//
//        n1.addChild(n2);
//        n1.addChild(n5);
//
//        final TreeNode n3 = new TreeNode("N3 HOUSE" + "\n" + "<= 24");
//        final TreeNode n4 = new TreeNode("N4 HOUSE" + "\n" + "> 24");
//
//        n2.addChild(n3);
//        n2.addChild(n4);
//
//        final TreeNode n7 = new TreeNode("N7 STAY (1023.0/208.0)" + "\n" + "<= 100437");
//        final TreeNode n8 = new TreeNode("N8 OVERAGE" + "\n" + "> 100437");
//
//        n6.addChild(n7);
//        n6.addChild(n8);
//
//        final TreeNode n9 = new TreeNode("N9 STAY (323.0/149.0)" + "\n" + "<= 148");
//        final TreeNode n10 = new TreeNode("N10 LEAVE (144.0/36.0)" + "\n" + "> 148");
//
//        n8.addChild(n9);
//        n8.addChild(n10);

        rootNode.addChild(n1);
        //rootNode.addChild(n6);

//        final TreeNode child3 = new TreeNode(getNodeText());
//        child3.addChild(new TreeNode(getNodeText()));
//        final TreeNode child6 = new TreeNode(getNodeText());
//        child6.addChild(new TreeNode(getNodeText()));
//        child6.addChild(new TreeNode(getNodeText()));
//        child3.addChild(child6);
//        rootNode.addChild(child3);
//        final TreeNode child4 = new TreeNode(getNodeText());
//        child4.addChild(new TreeNode(getNodeText()));
//        child4.addChild(new TreeNode(getNodeText()));
//        rootNode.addChild(child4);

        adapter.setRootNode(rootNode);



        //cv = findViewById(R.id.diagrams_cv);

//        findViewById(R.id.tv_insert_levelone).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //cv.swapColorLevelOne();
//                WekaTest weka = new WekaTest();
//                try {
//                    if (ActivityCompat.checkSelfPermission(DiagramsActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                            == PackageManager.PERMISSION_GRANTED) {
//                        Log.v("DDD","Permission is granted");
//                        weka.ejecution();
//                    } else {
//
//                        Log.v("ddd","Permission is revoked");
//                        ActivityCompat.requestPermissions(DiagramsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//
//                    }
//
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        findViewById(R.id.tv_insert_leveltwo).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //cv.swapColorLevelTwo();
//            }
//        });

        navigation= findViewById(R.id.diagrams_navigation);

        navigation.setSelectedItemId(R.id.menu_diagram);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.menu_home){
                    Intent home = new Intent(DiagramsActivity.this, HomeActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_client){
                    Intent home = new Intent(DiagramsActivity.this, ClientsInfo.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_planning){
                    Intent home = new Intent(DiagramsActivity.this, PlanificationActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_metric){
                    Intent home = new Intent(DiagramsActivity.this, MetricsActivity.class);
                    startActivity(home);

                }

                return false;
            }
        });

//        findViewById(R.id.diagrams_btn_sg).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"All sub-groups were successfully created",Toast.LENGTH_LONG).show();
//            }
//        });

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
        Intent homeIntent = new Intent(DiagramsActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        initView();
    }

    private void initView() {
//        spinnerVariablesOne = findViewById(R.id.diagrams_spinner_levelone);
//        spinnerVariablesTwo = findViewById(R.id.diagrams_spinner_leveltwo);
        String[] segments = {"College", "Income", "Overage per month", "House", "Handset Price, Calls over 15 min per month",
                "Average call duration", "Reported satisfaction level", "Reported usage level"};
//        spinnerVariablesOne.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, segments));
//        spinnerVariablesTwo.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, segments));

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    private String getNodeText() {
        return "Node " + nodeCount++;
    }

}


