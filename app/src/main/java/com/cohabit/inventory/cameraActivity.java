package com.cohabit.inventory;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class cameraActivity extends AppCompatActivity {
    Button capture;
    ImageView preview;
    Button previewbtn;
    public void upload(String encoded){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image", encoded)
                .build();
        Request request = new Request.Builder()
                .url("https://api.imgur.com/3/image")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + "5dd4dde53704f5b6071034a59e4b8f84e041d1dc")
                .build();
        try {
            Response response = client.newCall(request).execute();
            Log.wtf("RESPONSE",""+response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        capture = findViewById(R.id.capturebtn);
        preview = findViewById(R.id.preview);
        previewbtn = findViewById(R.id.previewBTN);
        if (ContextCompat.checkSelfPermission(cameraActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(cameraActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //Bitmap part
                        Intent data = result.getData();
                        Bitmap captureImage=(Bitmap) data.getExtras().get("data");
                        preview.setImageBitmap(captureImage);
                        preview.setVisibility(View.INVISIBLE);
                        //Base-64 part
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        captureImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream .toByteArray();
                        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                upload(encoded);  //background stuff

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        // do onPostExecute stuff

                                    }
                                });
                            }
                        }).start();
                        //profilePic.setImageBitmap(captureImage);
                    }
                });


        capture.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //startActivityForResult(intent, 100);
            someActivityResultLauncher.launch(intent);

        });

        previewbtn.setOnClickListener(v -> {
            if (preview.getVisibility() == v.INVISIBLE) {
                preview.setVisibility(v.VISIBLE);
            } else {
                preview.setVisibility(v.INVISIBLE);
            }

        });
    }
}





