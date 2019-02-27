package com.example.pointer.mypointer.tests;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.pointer.mypointer.OpenGL.TextureRotationUtil;
import com.example.pointer.mypointer.test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MyGL20Randerer implements GLSurfaceView.Renderer {

    private GLSurfaceView mGlSurfaceView;
    Context mContext;
    Paint mPaint;
    private FloatBuffer mTextureBuffer;


    public MyGL20Randerer(Context context, GLSurfaceView glSurfaceView){
        mGlSurfaceView = glSurfaceView;
        mContext = context;
        //glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(this);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        mPaint = new Paint();
        mPaint.setColor(Color.rgb(240, 100, 100));
        int strokeWidth = 10;
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mTextureBuffer = ByteBuffer.allocateDirect(TextureRotationUtil.TEXTURE_NO_ROTATION.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        mTextureBuffer.put(TextureRotationUtil.TEXTURE_NO_ROTATION).position(0);
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, javax.microedition.khronos.egl.EGLConfig eglConfig) {
        GLES20.glClearColor(0.2f, 0.2f, 0.5f, 0.4f);
    }


    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width/2, height/2);
    }
}
