package com.demo.autoscript;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import android.util.Log;

public class ThreadAdbshell extends Thread {
    // 申请获取root权限，这一步很重要，不然会没有作用
    public int start = 1;
    private final static String TAG = "[ThreadAdbshell]";

    int x = -1, y = -1;
    int distance = 0;//刷新间隔，单位毫秒
    int duration = 0;//刷新间隔，单位毫秒
    private String twoPositionStr = "0";
    private String commandStr = "0";
    private String fullCommandStr = "0";

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

        /*try {
            Thread.sleep(distance);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        commandStr = "0";

        if (duration!=0) {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!fullCommandStr.equals("0")) {
            commandStr = fullCommandStr;
        }

        if (!twoPositionStr.equals("0")) {
            commandStr = "input touchscreen swipe " + twoPositionStr;
        }


        if (x != 0) {
            Log.d("点击位置：", x + "," + y);
            start++;
            //String str="input tap 252 252";
            String str = "input tap " + x + " " + y;
            commandStr = str;

        }
        if (!commandStr.equals("0")) {
            try {
                //Log.d(TAG, commandStr);
                excuteShellCMD(commandStr);
            } catch (IOException e) {
                Log.d(TAG,e.getMessage());
            }
        }



        //}
    }

    public void excuteShellCMD(String cmd) throws IOException {

        // 申请获取root权限，这一步很重要，不然会没有作用
//        Process process = Runtime.getRuntime().exec("su");
        Process process = Runtime.getRuntime().exec("su");

        //获取输入流
        OutputStream outputStream = process.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(
                outputStream);
        dataOutputStream.writeBytes(cmd);
        dataOutputStream.flush();
        dataOutputStream.close();
        outputStream.close();
        Log.d(TAG,cmd);


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

    public void setComStr(String positionStr) {
        this.twoPositionStr = positionStr;
    }

    public void setFullCom(String fullCommand) {
        this.fullCommandStr = fullCommand;
    }

    public void delParam(){
        this.x=0;
        this.y=0;
        this.commandStr="0";
        this.fullCommandStr="0";
        this.twoPositionStr="0";
        this.duration=0;
    }
}
