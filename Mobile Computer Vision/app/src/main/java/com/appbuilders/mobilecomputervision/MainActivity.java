package com.appbuilders.mobilecomputervision;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbuilders.mobilecomputervision.MCV.OCR.ImageReader;
import com.appbuilders.mobilecomputervision.MCV.OCR.LiveCameraReader;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    Button button;
    TextView text;

    Button gallery;

    Button liveCamera;

    private String selectedImagePath;
    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.text);
        gallery = (Button) findViewById(R.id.gallery);
        liveCamera = (Button) findViewById(R.id.liveDefault);


        final Bitmap bit = BitmapFactory.decodeResource(this.getResources(), R.drawable.placa);
        image.setImageBitmap(bit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ImageReader imageReader = new ImageReader(getApplicationContext()) {
                new ImageReader(getApplicationContext()) {

                    @Override
                    public void onProcessStart() {
                        Log.d("AB_DEV", "Empezo a procesar");
                        text.setText("Procesando");
                    }

                    @Override
                    public void onProcessEnd() {
                        Log.d("AB_DEV", "Termino de procesar");
                    }

                    @Override
                    public void processText(StringBuilder result) {

                        Log.d("AB_DEV", "no ma erick, rifado = " + result);
                        if ( !String.valueOf(result).contains("DISTRITO FEDERAL") ) {
                            Log.d("AB_DEV", "me muero X_x");
                            text.setText(result);
                        }

                    }
                //}.setImageBitmap(bit).process();
                }.setImageFromImageView(image).process();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
            }
        });

        liveCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LiveCameraReader(MainActivity.this).start();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }
}
