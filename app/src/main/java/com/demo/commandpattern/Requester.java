package com.demo.commandpattern;

import android.util.Log;

import com.demo.autoscript.ThreadAdbshell;

import java.util.logging.Logger;

public class Requester {

    private static final String TAG="Requester";


    public void click(int xoff,int yoff ){
        //后台执行 adbshell 模拟点击....
        System.out.println(TAG+"click"+xoff+""+yoff);
        ThreadAdbshell shellTh = new ThreadAdbshell();
        shellTh.setX(xoff);
        shellTh.setY(yoff);
        shellTh.start();
    }


    public void rest(int time){
        //后台执行 adbshell 模拟点击....
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(TAG+"rest"+ time);
    }

}
