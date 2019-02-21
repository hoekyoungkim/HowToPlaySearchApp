package com.project.tryboardgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;







public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scanBarcode(View view) {

        Intent intent = new Intent(this, BarcodeDetectActivity.class);
        Log.d("MainActivity", "start Barcode activity");
        startActivity(intent);
    }


     public void openCameraApp(View view){

        //Intent intent = new Intent(this, CameraActivity.class);
        Intent intent = new Intent(this, YoutubeListActiviy.class);
        TextView textView = (TextView) findViewById(R.id.textView);
        String message = textView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        Log.d("MainActivity","get Youtube playlist");
        startActivity(intent);



        // 1. open the camera app
        //Log.d("openCameraApp","button open camera app");
//        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivity(intent);
        // 2. take a photo of a barcode on the boardgame box

        // 3. process the barcode

        // 4. find related videos on Youtube and Google search page
        // search for "How to play ~"
        // find the wikipedia page

    }

}
