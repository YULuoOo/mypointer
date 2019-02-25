package com.example.pointer.mypointer;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.pointer.mypointer.tests.*;

import com.example.pointer.mypointer.tests.MyGL20Randerer;

public class test extends AppCompatActivity {

    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView=(GLSurfaceView)findViewById(R.id.gl_test);
        new MyGL20Randerer(test.this,mGLView);
        //mGLView.setRenderer(new MyGL20Randerer());
        //mGLView.setEGLContextClientVersion(2);

       // mGLView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

       // setContentView(mGLView);
    }
}
