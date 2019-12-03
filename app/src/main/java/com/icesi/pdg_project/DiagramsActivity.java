package com.icesi.pdg_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.icesi.pdg_project.Model.WekaTest;
import com.icesi.pdg_project.View.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DiagramsActivity extends AppCompatActivity {

    private int nodeCount = 1;
    private BottomNavigationView navigation;
    private Button generateTree;
    private ImageView image_tree;
    private EditText text_level;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagrams);

        generateTree = findViewById(R.id.btn_generate_tree);
        image_tree = findViewById(R.id.image_tree);
        text_level = findViewById(R.id.txt_levels);

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

        generateTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    run(Integer.parseInt(text_level.getText().toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        Intent homeIntent = new Intent(DiagramsActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        initView();
    }

    public void run(int numberOfLevels) throws IOException {
        String ipv4Address = "http://18.222.220.36";
        String portNumber = "8082";

        String serverURL= ipv4Address+":"+portNumber+"/"+"get_tree?level="+numberOfLevels;
        //?var=" + variable;
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"Download/"+"churn.csv");

        if(file.exists()){
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(),
                            RequestBody.create(MediaType.parse("text/csv"), file))
                    .build();


            Request request = new Request.Builder()
                    .url(serverURL)
                    .post(requestBody)
                    .build();


            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.i("ERROR: " , e.getMessage());
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        final Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        // Remember to set the bitmap in the main thread.
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {

                                Bitmap resized = Bitmap.createScaledBitmap(bitmap, 500, 300, true);
                                image_tree.setImageBitmap(resized);
                            }
                        });
                    }else {
                        //Handle the error
                    }
                }
            });
        }
        else{
            Log.i("FILE DOES NOT EXITS: " , "XD");
        }



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


