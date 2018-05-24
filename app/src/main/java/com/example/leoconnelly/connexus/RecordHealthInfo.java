package com.example.leoconnelly.connexus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leoconnelly on 5/6/18.
 */



public class RecordHealthInfo  extends AppCompatActivity{


    private static final int CAPTURE_CAMERA_RC = 1;
    private static final int OPEN_IMAGE_RC = 2;
    private static final int REQUEST_WRITE_STORAGE_RC = 3;
    private static final int RC_PHOTO_PICKER =  2;


    ImageView imageView;
    TextView notifcation;
    Button savedImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_health_info);

        notifcation = (TextView) findViewById(R.id.notifcationAboutPic);

        Button SavedImages = (Button) findViewById(R.id.saved_images);
        Button btnCamera = (Button)findViewById(R.id.camera);
        imageView = (ImageView)findViewById(R.id.imageView);


        SavedImages.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {

                openFolder();

            }
        });


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(RecordHealthInfo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(RecordHealthInfo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        // todo: show explanation
                    }
                    else {
                        ActivityCompat.requestPermissions(RecordHealthInfo.this,
                                new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_WRITE_STORAGE_RC);
                    }
                }
                else {
                    captureImage();
                }

                /*File pictureDirectoty = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                String pictureName = getPictureName();
                File imageFile = new File(pictureDirectoty, pictureName);
                Uri pictureUri =  Uri.fromFile(imageFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                notifcation.setText(("Your Image has been stored on your SD Card."));
                startActivityForResult(intent, CAPTURE_CAMERA_RC);*/
            }
        });
    }

    public void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_CAMERA_RC);
        notifcation.setText(("Your Image has been stored on your SD Card. Make sure you enable show SD card. " +
                "Your images are in pictures folder inside of interal storage"));

    }

    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = sdf.format(new Date());
        return "ConnexusImages" + timeStamp + ".jpg";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE_RC:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    captureImage();
                }
                else {
                    Log.e("IMAGE", " Permission denied for writing to external storage");
                }
                break;
        }
    }

    //WE CAN ONLY RECORD IMAGES AND DISPLAY THEM OR SAVE THEM NOT BOTH OR ELSE NULL POINTER EXCPETION
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TEST", "onResult");
        if(requestCode == CAPTURE_CAMERA_RC) {
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
            byte[] bytes = byteStream.toByteArray();
            try {
                String imagePath = Environment.getExternalStorageDirectory() + "/"+ System.currentTimeMillis() + "_image.png";
                FileOutputStream fos = new FileOutputStream(imagePath);
                fos.write(bytes);
                fos.flush();
                fos.close();

                imageView.setImageBitmap(bmp);
               /* File cameraImage = new File(imagePath);
                Intent openIntent = new Intent(Intent.ACTION_SEND)
                        .setType("image/png")
                        .putExtra(Intent.EXTRA_SUBJECT,"Image just captured from camera")
                        .putExtra(Intent.EXTRA_STREAM,Uri.fromFile(cameraImage));
                startActivityForResult( Intent.createChooser(openIntent, "Open Camera Image"), OPEN_IMAGE_RC);*/
            } catch (IOException e) {
                Log.e("IMAGE", "error saving the image");
            }

        }
       // Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        //imageView.setImageBitmap(bitmap);




    }

    public void openFolder()
    {

        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //Uri uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
        //intent.setDataAndType(uri, "*/*");
        //startActivity(Intent.createChooser(intent, "Open folder"));
/*
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(galleryIntent, "Complete action using"), RC_PHOTO_PICKER);

*/
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                + "/Pictures/");
        intent.setDataAndType(uri, "text/csv");
        startActivity(Intent.createChooser(intent, "Open folder"));


    }


}








