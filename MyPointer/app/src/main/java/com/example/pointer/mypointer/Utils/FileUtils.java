package com.example.pointer.mypointer.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sensetime on 16-11-16.
 */

public class FileUtils {

    public static final String FACE_TRACK_MODEL_NAME = "M_SenseME_Face_Video_5.3.3.model";
    public static final String FACE_DETECT_MODEL_NAME = "M_SenseME_Face_Picture_5.3.3.model";
    public static final String HAND_MODEL_NAME = "M_SenseME_Hand_5.1.0.model";
    public static final String EYEBALL_CONTOUR_MODEL_NAME = "M_SenseME_Iris_1.7.0.model";
    public static final String FACE_EXTRA_MODEL_NAME = "M_SenseME_Face_Extra_5.1.0.model";

    public static final String FIGURE_SEGMENT_MODEL_NAME = "M_SenseME_Segment_1.5.0.model";
    public static final String SMALL_BODY_MODEL_NAME = "M_SenseME_Body_Four_1.0.0.model";
    public static final String LARGE_BODY_MODEL_NAME = "M_SenseME_Body_Fourteen_1.3.1.model";
    public static final String BODY_CONTOUR_MODEL_NAME = "M_SenseME_Body_Contour_73_1.2.0.model";
    public static final String FACE_ATTRIBUTE_MODEL_NAME = "M_SenseME_Attribute_1.0.1.model";
    public static final String HAIR_SEGMENT_MODEL_NAME = "M_Segment_4x_Hair_1.1.0_v2_origin.model";

    public static final String pic_name = "HNHY-104人";




    public static boolean openpicfile(Context context) {
        String path = getFilePath(context, "HNHY-104人");
        if (path != null) {
            File file = new File(path);
            if (!file.exists())
            {
                //如果模型文件不存在
                Log.i("zaima",path);
                Log.i("zaima","bucunzai");

            }
            else
            {
                Log.i("zaima","cuznai");
            }
        }

        return true;
    }


    public static boolean copyFileIfNeed(Context context, String fileName) {
        String path = getFilePath(context, fileName);
        if (path != null) {
            File file = new File(path);
            if (!file.exists()) {
                //如果模型文件不存在
                try {
                    if (file.exists())
                        file.delete();

                    file.createNewFile();
                    InputStream in = context.getApplicationContext().getAssets().open(fileName);
                    if(in == null)
                    {
                        LogUtils.e("copyMode", "the src is not existed");
                        return false;
                    }
                    OutputStream out = new FileOutputStream(file);
                    byte[] buffer = new byte[4096];
                    int n;
                    while ((n = in.read(buffer)) > 0) {
                        out.write(buffer, 0, n);
                    }
                    in.close();
                    out.close();
                } catch (IOException e) {
                    file.delete();
                    return false;
                }
            }
        }
        return true;
    }

    public static String getFilePath(Context context, String fileName) {
        String path = null;
        File dataDir = context.getApplicationContext().getExternalFilesDir(null);
        if (dataDir != null) {
            path = dataDir.getAbsolutePath() + File.separator + fileName;
        }
        return path;
    }

    public static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                LogUtils.e("FileUtil", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINESE).format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }

    public static void copyModelFiles(Context context) {
        copyFileIfNeed(context, FACE_ATTRIBUTE_MODEL_NAME);
        copyFileIfNeed(context, FACE_TRACK_MODEL_NAME);
        copyFileIfNeed(context, FACE_DETECT_MODEL_NAME);
    }

    public static String getTrackModelPath(Context context) {
        return getFilePath(context, FACE_TRACK_MODEL_NAME);

    }

    public static String getFaceDetectModelPath(Context context) {
        return getFilePath(context, FACE_DETECT_MODEL_NAME);
    }

    public static ArrayList<HandActionItem> getHandActionIcons(Context context){
        ArrayList<HandActionItem> icons = new ArrayList<HandActionItem>();

    /*    Bitmap palm = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_palm);
        Bitmap ok = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_ok);
        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_thumb);
        Bitmap oneFinger = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_one_finger);
        Bitmap pistol = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_pistol);

        Bitmap fingerHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_finger_heart);
        Bitmap heartHand = BitmapFactory.decodeResource(context.getResources(), R.drawable.heand_action_heart_hand);
        Bitmap scissor = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_scissor);
        Bitmap congratulate = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_acton_congratulate);
        Bitmap palmUp = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_palm_up);

        Bitmap hand666 = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_666);
        Bitmap bless = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_bless);
        Bitmap fist = BitmapFactory.decodeResource(context.getResources(), R.drawable.hand_action_fist);

        icons.add(new HandActionItem("ok", ok));
        icons.add(new HandActionItem("scissor", scissor));
        icons.add(new HandActionItem("thumb", thumb));
        icons.add(new HandActionItem("palm", palm));
        icons.add(new HandActionItem("pistol", pistol));

        icons.add(new HandActionItem("heartHand", heartHand));
        icons.add(new HandActionItem("palmUp", palmUp));
        icons.add(new HandActionItem("congratulate", congratulate));
        icons.add(new HandActionItem("fingerHeart", fingerHeart));
        icons.add(new HandActionItem("oneFinger", oneFinger));

        icons.add(new HandActionItem("666", hand666));
        icons.add(new HandActionItem("bless", bless));
        icons.add(new HandActionItem("fist", fist));
*/
        return icons;
    }

}
