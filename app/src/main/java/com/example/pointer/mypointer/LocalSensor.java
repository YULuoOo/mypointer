package com.example.pointer.mypointer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

class LocalSensor implements SensorEventListener {
    private LocationManager mlocationmanager;
    private SensorManager msensormanager;
    private Sensor accelerometer;
    private  Sensor magnetic;

    private float value[];
    private float[] accelerometerValues = new float[3];
    private float[] magneticFieldValues = new float[3];
    //初始化传感器
    public void init(){
        //获取系统服务
        msensormanager= (SensorManager) MyLocationActivity.getInstance().
                getSystemService(Context.SENSOR_SERVICE);
        //获取传感器
        if(msensormanager!=null) {
            accelerometer = msensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            magnetic = msensormanager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }
        mlocationmanager= (LocationManager) MyLocationActivity.getInstance().getSystemService(Context.LOCATION_SERVICE);
        //开始请求GPS定位
        requestPermission();

    }

    private void requestPermission() {
        String[] permissions=new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION};
        //判断权限
        if(ContextCompat.checkSelfPermission(MyLocationActivity.getInstance(),Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            startLocation();
        }else
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            MyLocationActivity.getInstance().requestPermissions(permissions,1);
        }
    }

    @SuppressLint("MissingPermission")
    private void startLocation() {
        if(ActivityCompat.checkSelfPermission(MyLocationActivity.getInstance(),
                Manifest.permission.ACCESS_FINE_LOCATION  )!=PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(MyLocationActivity.getInstance(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mlocationmanager .requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1,
                new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        //GPS信息发生改变时，更新位置
                        MyLocationActivity.getInstance().locationUpdates(location);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });
        //从GPS获取最新的定位信息
        Location location=mlocationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        MyLocationActivity.getInstance().locationUpdates(location);
    };



    public void register(){
        msensormanager.registerListener((SensorEventListener) this,
                accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        msensormanager.registerListener((SensorEventListener) this, magnetic,
                SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerValues = sensorEvent.values;
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticFieldValues = sensorEvent.values;
        }
            value = calculateOrientation(accelerometerValues, magneticFieldValues);
          MyLocationActivity.getInstance().updateAttitudeData(value);
        }


    private float[] calculateOrientation(float[] accelerometerValues, float[] magneticFieldValues) {
        float[] values = new float[3];
        float[] R = new float[9];
        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues);
        SensorManager.getOrientation(R, values);
        values[0] =(float) Math.round(10*(float) Math.toDegrees(values[0]))/10;
        values[1] = (float) Math.round(10*(float) Math.toDegrees(values[1]))/10;
        values[2] = (float) Math.round(10*(float) Math.toDegrees(values[2]))/10;
        return values;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
