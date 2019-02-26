package com.project.howtoplaysearchapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.project.howtoplaysearchapp.R;

import java.io.IOException;

public class BarcodeDetectActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    private String BARCODE_VALUE = null;
    private Uri imageUri = null;
    private Bitmap imageBitmap = null;
    private Bitmap testQR = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e("BarcodeDetectActivity","!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_detect);

        Intent intent = getIntent();
        ImageView imageView = (ImageView) findViewById(R.id.imgview);
        imageView.setImageURI(imageUri);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            imageUri = Uri.parse(extras.getString("imageUri"));
            imageView.setImageURI(imageUri);
            try{
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            imageBitmap = BitmapFactory.decodeResource( getApplicationContext().getResources(), R.drawable.qr_monopoly);
            imageView.setImageBitmap(imageBitmap);

        }


        Button btn = (Button) findViewById(R.id.processBarcode);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processBarcode();
            }
        });

    }

    public void processBarcode(){
        Log.e("Barcode Detect Activity","Process Button Clicked");

        BarcodeDetector detector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                        .build();

        Barcode thisCode = null;

        if(!detector.isOperational()){
            ((TextView) findViewById(R.id.barcode_txtView)).setText("Could not set up the detector!");
            return;
        }

        if(imageBitmap!= null){

//            Frame frame = new Frame.Builder().setBitmap(imageBitmap).build();
            Frame frame = new Frame.Builder().setBitmap(imageBitmap).build();

            SparseArray<Barcode> barcodes = detector.detect(frame);

            try{
                thisCode = barcodes.valueAt(0);
                TextView txtView = (TextView) findViewById(R.id.txtContent);
                txtView.setText(thisCode.rawValue);
                BARCODE_VALUE = thisCode.rawValue;
                Log.e("Barcode Detect Activity","BARCODE_VALUE = "+BARCODE_VALUE);


            }catch (Exception e) {
                e.printStackTrace();
                Log.e("IO","IO"+e);
            }
        }   else{
            Log.e("Barcode Detect Activity","Process Button Clicked: imageBitmap is null");
        }

        if(BARCODE_VALUE!= null){
            Intent intent = new Intent(getApplicationContext(), YoutubeListActiviy.class);
            intent.putExtra("KEY_WORD", BARCODE_VALUE);
            startActivity(intent);

        }
    }

}
