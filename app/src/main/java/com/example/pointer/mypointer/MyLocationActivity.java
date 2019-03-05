package com.example.pointer.mypointer;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLocationActivity extends Activity {
    private LocalSensor localSensor;
    private static MyLocationActivity self;
    private TextView location_text;
    private TextView tv_longitude;
    private TextView tv_latitude;
    private TextView orientationx_text;
    private TextView orientationy_text;
    private TextView orientationz_text;

    public static MyLocationActivity getInstance(){
        return self;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        self=this;
        location_text=findViewById(R.id.location_text);
        tv_latitude=findViewById(R.id.tv_latitude);
        tv_longitude=findViewById(R.id.tv_longitude);
        orientationx_text=findViewById(R.id.orientationx_text);
        orientationy_text=findViewById(R.id.orientationy_text);
        orientationz_text=findViewById(R.id.orientationz_text);

        localSensor=new LocalSensor();
        localSensor.init();
    }
    @Override
    protected void onResume() {
        super.onResume(); // 为加速度传感器注册监听器
        localSensor.register();
    }
    protected void onDestroy() {
        super.onDestroy();
    }

    public void locationUpdates(Location location) {
        if(location!=null){
            double longitude=location.getLongitude();
            double latitude=location.getLatitude();
            float longitude1=Math.round(10*longitude)/10;
            float latitude1=Math.round(10*latitude)/10;
            StringBuilder stringbuilder=new StringBuilder();
            stringbuilder.append("当前位置信息：\n");
            stringbuilder.append("经度：");
            stringbuilder.append(longitude1);
            stringbuilder.append("\n维度：");
            stringbuilder.append(latitude1);
            stringbuilder.append("\n高度：");
            stringbuilder.append(location.getAltitude());
            stringbuilder.append("\n方向：");
            stringbuilder.append(location.getBearing());
            stringbuilder.append("\n速度：");
            stringbuilder.append(location.getSpeed());
            stringbuilder.append("\n时间：");
            //设置日期时间格式
            SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH: mm: ss");
            stringbuilder.append(dateformat.format(new Date(location.getTime())));
            location_text.setText("当前位置信息"+stringbuilder);
            tv_latitude.setText("纬度为"+latitude1);
            tv_longitude.setText("经度为"+longitude1);
        }else{
            location_text.setText("当前位置信息未返回数据");
        }
    }

    /**
     *  更新姿态数据
     */
    public void updateAttitudeData(float[] value){
        if (value.length < 3){
            return;
        }
        orientationx_text.setText("姿态传感器数据：\n与z轴夹角： " + value[0]);
        orientationy_text.setText("与x轴夹角： " + value[1]);
        orientationz_text.setText("与y轴夹角： " + value[2]);
    }
}
