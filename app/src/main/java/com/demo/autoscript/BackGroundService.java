package com.demo.autoscript;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.demo.commandpattern.ClickExcuter;
import com.demo.commandpattern.CommandHandler;
import com.demo.commandpattern.PressKeyExcuter;
import com.demo.commandpattern.RestExcuter;
import com.demo.commandpattern.SwipeExcuter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class BackGroundService extends Service {

    public static final String TAG = "BackGroundService";
    ThreadAdbshell thshell;
    private String commandStr;
    private ArrayList<String> data;

    //创建服务时调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        thshell = new ThreadAdbshell();
    }

    //服务执行的操作
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         /*Log.d(TAG, "onStartCommand");
         distance=intent.getIntExtra("distance", 1000);
         thshell.setDistance(distance);
         thshell.setX(intent.getIntExtra("x", 90));
         thshell.setY(intent.getIntExtra("y", 185));
         if(!thshell.isAlive())
         {
        	 Log.d("线程是否正在执行", thshell.isAlive()+"");
        	 thshell.start();
         }*/


        if (!thshell.isAlive()) {
            Log.d("线程是否正在执行", thshell.isAlive() + "");
            thshell.run();
        }

        CommandHandler commandHandler = new CommandHandler();

        data = (ArrayList<String>) intent.getSerializableExtra("command");

        for (String commandStr : data) {
            String head = commandStr.substring(0, 1);
            switch (head) {
                case "t":
                    int time = Integer.parseInt(commandStr.substring(2));
                    commandHandler.addRequset(new RestExcuter(thshell, time));
                    break;
                case "x":

                    int x = Integer.parseInt(commandStr.substring(2, commandStr.indexOf("*")));
                    int y = Integer.parseInt(commandStr.substring(commandStr.indexOf("*") + 1 + 2));
                    commandHandler.addRequset(new ClickExcuter(thshell, x, y));
                    break;
                case "s":
                    String towPosithonStr = (commandStr.substring(2));
                    commandHandler.addRequset(new SwipeExcuter(thshell, towPosithonStr));
                    break;
                case "h":
                    commandHandler.addRequset(new PressKeyExcuter(thshell, 3));
                    break;
                case "r":
                    commandHandler.addRequset(new PressKeyExcuter(thshell, 4));
                    break;
                default:
                    Log.d(TAG, "无默认操作");
                    Toast.makeText(getBaseContext(), commandStr + "无默认操作", Toast.LENGTH_SHORT).show();

            }

        }

        commandHandler.handleRequest();

        return super.onStartCommand(intent, flags, startId);
    }

    //销毁服务时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        data.clear();
        Log.d(TAG, "onDestroy");
    }

    //bindService()启动Service时才会调用onBind()方法
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
