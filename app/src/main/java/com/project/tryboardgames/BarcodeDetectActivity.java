package com.project.tryboardgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.vision.barcode.*;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class BarcodeDetectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_detect);



        Button btn = (Button) findViewById(R.id.processBarcode);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        ImageView myImageView = (ImageView) findViewById(R.id.imgview);
        Bitmap myBitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.puppy);
        myImageView.setImageBitmap(myBitmap);

        BarcodeDetector detector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                        .build();
        if(!detector.isOperational()){
          ((TextView) findViewById(R.id.barcode_txtView)).setText("Could not set up the detector!");
            return;
        }

        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);

        try{
            Barcode thisCode = barcodes.valueAt(0);
            TextView txtView = (TextView) findViewById(R.id.txtContent);
            txtView.setText(thisCode.rawValue);

        }catch (Exception e) {
            e.printStackTrace();
            Log.e("IO","IO"+e);
        }


    }
}
