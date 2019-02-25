package com.project.tryboardgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.project.tryboardgames.R;
import com.project.tryboardgames.activities.BarcodeDetectActivity;
import com.project.tryboardgames.activities.YoutubeListActiviy;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scanBarcode(View view) {

        Intent intent = new Intent(this, BarcodeDetectActivity.class);
        Log.d("MainActivity", "start Barcode activity ************************************************************************");
        startActivity(intent);
    }


     public void openCameraApp(View view){

        Intent intent = new Intent(this, YoutubeListActiviy.class);
        Log.d("MainActivity","get Youtube playlist ************************************************************************");
        startActivity(intent);
    }

}
