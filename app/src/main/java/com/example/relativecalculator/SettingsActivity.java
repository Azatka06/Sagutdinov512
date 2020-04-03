package com.example.relativecalculator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.io.File;

public class SettingsActivity extends AppCompatActivity {
    private EditText text;
    private static final int REQUEST = 323;
    private static final String IMAGE_PATH_KEY = "IMAGE_PATH_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    public static String getImagePathFromIntent(Intent intent) {
        return intent.getStringExtra(IMAGE_PATH_KEY);
    }

    private void init() {
        text = findViewById(R.id.editText);
        Button butOK = findViewById(R.id.butOK);
        butOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SettingsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SettingsActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST);
                    if(ActivityCompat.checkSelfPermission(SettingsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        setImage();
                    }
                }

                setImage();
            }
        });
    }

    private void setImage() {
        File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), text.getText().toString());
        if (!imageFile.exists()) {
            Toast.makeText(getApplicationContext(), R.string.fileNotFound, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(IMAGE_PATH_KEY, imageFile.getAbsolutePath());
            setResult(RESULT_OK, intent);
            SettingsActivity.this.finish();
        }
    }
}
