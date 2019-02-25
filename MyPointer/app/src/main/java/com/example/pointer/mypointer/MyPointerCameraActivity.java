package com.example.pointer.mypointer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.pointer.mypointer.Camera.CameraCallBack;
import com.example.pointer.mypointer.Camera.MyCamera;
import com.example.pointer.mypointer.OpenGL.OpenGlRander;
import com.example.pointer.mypointer.Utils.Accelerometer;

public class MyPointerCameraActivity extends AppCompatActivity {
    String TAG="camerass";


    //摄像头操作
  MyCamera mMyCamera;

    Accelerometer mAccelerometer;
    private SurfaceView mSurfaceViewOverlap;
    private GLSurfaceView mGLSurfaceView;
    //private CameraDisplay mCameraDisplay;
    Paint mPaint;
    float mCurrentCpuRate;
    SurfaceHolder msurfaceHolder;
    CameraCallBack callback = new CameraCallBack();
    OpenGlRander myrander;
    private SurfaceTexture mSurfaceTexture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pointer_camera);
        Log.i(TAG,"onCreate");
        initview();
    }

    private void initview()
    {

        mMyCamera=new MyCamera();
        mSurfaceViewOverlap=(SurfaceView)findViewById(R.id.surfaceViewOverlap);
        mGLSurfaceView=(GLSurfaceView)findViewById(R.id.id_gl_sv);
        setCameraSurfaceHolder(MyPointerCameraActivity.this,mSurfaceViewOverlap);
        //myrander=new OpenGlRander(MyPointerCameraActivity.this);

       /* OpenGlRander myrander=new OpenGlRander(getApplicationContext());
        Log.i("ganma","ganni");
        mAccelerometer = new Accelerometer(getApplicationContext());
        GLSurfaceView glSurfaceView = (GLSurfaceView) findViewById(R.id.id_gl_sv);
        mSurfaceViewOverlap = (SurfaceView) findViewById(R.id.surfaceViewOverlap);
        mCameraDisplay = new CameraDisplay(getApplicationContext(), glSurfaceView, mSurfaceViewOverlap,new Handler());
        mSurfaceViewOverlap.getHolder().setFormat(PixelFormat.TRANSLUCENT);
*/
        mPaint = new Paint();
        mPaint.setColor(Color.rgb(240, 100, 100));
        int strokeWidth = 2;
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        Log.i(TAG,"initview ok");
    }
    /**
     * 设置相机界面SurfaceView的Holder
     * @param context 从相机所在的Activity传入的context
     * @param surfaceView Holder所绑定的响应的SurfaceView
     * */
    public void setCameraSurfaceHolder(Context context, SurfaceView surfaceView) {
        msurfaceHolder = surfaceView.getHolder();
        msurfaceHolder.addCallback(callback);
        msurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        callback.setContext(context);
    }




    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

}
