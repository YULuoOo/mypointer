package com.example.pointer.mypointer.tests;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MySurfaceRander extends GLSurfaceView {

    public MySurfaceRander(Context context){
        super(context);

        // Set the Renderer for drawing on the GLSurfaceView

        setEGLContextClientVersion(2);
        //setRenderer(new MyGL20Randerer());
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}