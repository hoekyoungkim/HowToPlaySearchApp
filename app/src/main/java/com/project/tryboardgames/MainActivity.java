package com.project.tryboardgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void openCameraApp(View view){

        // 1. open the camera app
        Log.d("openCameraApp","button open camera app");
//        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivity(intent);
        // 2. take a photo of a barcode on the boardgame box
        // 3. process the barcode
        // 4. find related videos on Youtube and Google search page

    }

}
