package com.icesi.pdg_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.ortiz.touchview.TouchImageView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;

public class MetricsActivity extends AppCompatActivity  {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private BottomNavigationView navigation;
    private TouchImageView imageView;
    private Spinner spinner_metrics;
    //private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);

        navigation = findViewById(R.id.metrics_navigation);

        //graph =findViewById(R.id.metrics_graph);
        imageView = findViewById(R.id.image_metrics);
        spinner_metrics = findViewById(R.id.spinner_metrics);

        navigation.setSelectedItemId(R.id.menu_metric);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.menu_home){
                    Intent home = new Intent(MetricsActivity.this, HomeActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_client){
                    Intent home = new Intent(MetricsActivity.this, ClientsInfo.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_planning){
                    Intent home = new Intent(MetricsActivity.this, PlanificationActivity.class);
                    startActivity(home);

                }
                if(menuItem.getItemId()==R.id.menu_diagram){
                    Intent home = new Intent(MetricsActivity.this, DiagramsActivity.class);
                    startActivity(home);

                }


                return false;
            }
        });

        spinner_metrics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int permission = ActivityCompat.checkSelfPermission(MetricsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // We don't have permission so prompt the user
                    ActivityCompat.requestPermissions(
                            MetricsActivity.this,
                            PERMISSIONS_STORAGE,
                            REQUEST_EXTERNAL_STORAGE
                    );
                }


                try{
                    String text = spinner_metrics.getSelectedItem().toString();
                    run(text);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        initView();

    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(MetricsActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    private void initView() {

        String[] segments = {"COLLEGE", "INCOME", "OVERAGE","LEFTOVER", "HOUSE", "HANDSET_PRICE", "OVER_15MINS_CALLS_PER_MONTH",
                "AVERAGE_CALL_DURATION", "REPORTED_SATISFACTION", "REPORTED_USAGE_LEVEL", "CONSIDERING_CHANGE_OF_PLAN"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MetricsActivity.this,
                android.R.layout.simple_spinner_dropdown_item, segments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_metrics.setAdapter(adapter);


        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    public void run(String variable) throws IOException{
        String ipv4Address = "http://18.222.220.36";
        String portNumber = "8082";

        String serverURL= ipv4Address+":"+portNumber+"/"+"get_image?var=" + variable;
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

                                Bitmap resized = Bitmap.createScaledBitmap(bitmap, 2000, 1000, true);
                                imageView.setImageBitmap(resized);
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



//    private void barChart(){
//        graph.removeAllSeries();
//        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, -1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph.addSeries(series);
//
//        // styling
//        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
//            @Override
//            public int get(DataPoint data) {
//                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
//            }
//        });
//
//        series.setSpacing(50);
//
//        // draw values on top
//        series.setDrawValuesOnTop(true);
//        series.setValuesOnTopColor(Color.RED);
//        //series.setValuesOnTopSize(50);
//    }
//
//    private void lineGraph(){
//        graph.removeAllSeries();
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph.addSeries(series);
//    }
//
//    private void scaterPlot(){
//        graph.removeAllSeries();
//        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, -2),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph.addSeries(series);
//        series.setShape(PointsGraphSeries.Shape.POINT);
//
//        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(0, -1),
//                new DataPoint(1, 4),
//                new DataPoint(2, 2),
//                new DataPoint(3, 1),
//                new DataPoint(4, 5)
//        });
//        graph.addSeries(series2);
//        series2.setShape(PointsGraphSeries.Shape.RECTANGLE);
//        series2.setColor(Color.RED);
//
//        PointsGraphSeries<DataPoint> series3 = new PointsGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 0),
//                new DataPoint(1, 3),
//                new DataPoint(2, 1),
//                new DataPoint(3, 0),
//                new DataPoint(4, 4)
//        });
//        graph.addSeries(series3);
//        series3.setShape(PointsGraphSeries.Shape.TRIANGLE);
//        series3.setColor(Color.YELLOW);
//
//        PointsGraphSeries<DataPoint> series4 = new PointsGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 2),
//                new DataPoint(2, 0),
//                new DataPoint(3, -1),
//                new DataPoint(4, 3)
//        });
//        graph.addSeries(series4);
//        series4.setColor(Color.GREEN);
//        series4.setCustomShape(new PointsGraphSeries.CustomShape() {
//            @Override
//            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
//                paint.setStrokeWidth(10);
//                canvas.drawLine(x-20, y-20, x+20, y+20, paint);
//                canvas.drawLine(x+20, y-20, x-20, y+20, paint);
//            }
//        });
//    }


}
