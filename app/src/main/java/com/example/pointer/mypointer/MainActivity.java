package com.example.pointer.mypointer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mCameraStartBtn, mInfoStartBtn,mtest,mlocation;
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private boolean mLicenseChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraStartBtn=(Button) findViewById(R.id.btn_start_video);
        mInfoStartBtn =(Button) findViewById(R.id.btn_start_info);
        mtest=(Button)findViewById(R.id.test);
        mlocation=findViewById(R.id.location);

        mCameraStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Permission has not been granted and must be requested.
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            // Provide an additional rationale to the user if the permission was not granted
                            // and the user would benefit from additional context for the use of the permission.
                        }
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                PERMISSION_REQUEST_CAMERA);
                    } else {
                          startActivity(new Intent(getApplicationContext(), MyPointerCameraActivity.class));
                    }
                } else {
                     startActivity(new Intent(getApplicationContext(), MyPointerCameraActivity.class));
                }
            }
        });

        mInfoStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Permission has not been granted and must be requested.
                        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            // Provide an additional rationale to the user if the permission was not granted
                            // and the user would benefit from additional context for the use of the permission.
                        }
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
                    } else {
                         startActivity(new Intent(getApplicationContext(), MyPointerMineActivity.class));
                    }
                } else {
                     startActivity(new Intent(getApplicationContext(), MyPointerMineActivity.class));
                }
            }
        });
        mtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Permission has not been granted and must be requested.
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            // Provide an additional rationale to the user if the permission was not granted
                            // and the user would benefit from additional context for the use of the permission.
                        }
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                PERMISSION_REQUEST_CAMERA);
                    } else {
                        startActivity(new Intent(getApplicationContext(), test.class));
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), test.class));
                }
            }
        });
        mlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyLocationActivity.class));
            }
        });

    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            // Request for camera permission.
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                // startActivity(new Intent(getApplicationContext(), HumanActionCameraActivity.class));
            } else {
                // Permission request was denied.
                Toast.makeText(this, "Camera权限被拒绝", Toast.LENGTH_SHORT)
                        .show();
            }
        }else if(requestCode == PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // startActivity(new Intent(getApplicationContext(), HumanActionImageActivity.class));
            } else {
                // Permission request was denied.
                Toast.makeText(this, "存储卡读写权限被拒绝", Toast.LENGTH_SHORT).show();
            }
        }
    }


}