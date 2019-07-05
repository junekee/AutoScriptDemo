package com.demo.autoscript;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.util.Log;

public class ThreadAdbshell extends Thread {
    // 申请获取root权限，这一步很重要，不然会没有作用
    public int start = 1;
    private final static String TAG = "[ThreadAdbshell]";

    int x=-1, y=-1;
    int distance=0;//刷新间隔，单位毫秒
    int duration=0;//刷新间隔，单位毫秒

    public ThreadAdbshell(int x, int y) {
        this.x = x;
        this.y = y;
        distance = 0;
    }
    public ThreadAdbshell() {
    }

    @Override
    public void run() {
        //while(true) {
        /*if (start == 1) {
            try {
                Thread.sleep(distance);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                Thread.sleep(distance);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/

        try {
            Thread.sleep(distance);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (x!=0) {
            Log.d("点击位置：", x + "," + y);
            start++;
            //String str="input tap 252 252";
            String str = "input tap " + x + " " + y;
            try {
                Log.d(TAG, str);
                excuteShellCMD(str);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //}
    }

    public void excuteShellCMD(String cmd) throws IOException {
        // 申请获取root权限，这一步很重要，不然会没有作用
        Process process = Runtime.getRuntime().exec("su");
        //获取输入流
        OutputStream outputStream = process.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(
                outputStream);
        dataOutputStream.writeBytes(cmd);
        dataOutputStream.flush();
        dataOutputStream.close();
        outputStream.close();
        Log.d(TAG, "点击事件执行");
    }

    public void setDistance(int dis) {
        this.distance = dis;
    }
    public void setDuration(int dis) {
        this.duration = dis;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
