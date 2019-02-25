package com.example.pointer.mypointer.OpenGL;

public class RotateThread extends Thread
{
    public boolean flag=true;
    @Override
    public void run()
    {
        while(flag)
        {
           // mRenderer.tle.xAngle=mRenderer.tle.xAngle+ANGLE_SPAN;
            try
            {
                Thread.sleep(20);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
