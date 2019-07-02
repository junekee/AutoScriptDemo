package com.demo.autoscript;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BackGroundService extends Service{

    public static final String TAG = "BackGroundService";  
    ThreadAdbshell thshell;
    int distance;
     //创建服务时调用
     @Override  
     public void onCreate() {  
         super.onCreate();  
         Log.d(TAG, "onCreate");  
         thshell=new ThreadAdbshell(90,185);
         distance=1000;
     }  
   
     //服务执行的操作
     @Override  
     public int onStartCommand(Intent intent, int flags, int startId) {  
         Log.d(TAG, "onStartCommand");  
         distance=intent.getIntExtra("distance", 1000);
         thshell.setDistance(distance);
         thshell.setX(intent.getIntExtra("x", 90));
         thshell.setY(intent.getIntExtra("y", 185));
         if(!thshell.isAlive())
         {
        	 Log.d("线程是否正在执行", thshell.isAlive()+"");
        	 thshell.start();      	 
         }
         
         return super.onStartCommand(intent, flags, startId);  
     }  
       
     //销毁服务时调用
     @Override  
     public void onDestroy() {  
         super.onDestroy();  
         Log.d(TAG, "onDestroy");  
     }  
   
     //bindService()启动Service时才会调用onBind()方法
    @Override  
    public IBinder onBind(Intent intent) {  
         return null;  
     }
		
}
