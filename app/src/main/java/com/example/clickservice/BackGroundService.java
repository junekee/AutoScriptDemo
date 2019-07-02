package com.example.clickservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BackGroundService extends Service{

    public static final String TAG = "BackGroundService";  
    ThreadAdbshell thshell;
    int distance;
     //��������ʱ����
     @Override  
     public void onCreate() {  
         super.onCreate();  
         Log.d(TAG, "onCreate");  
         thshell=new ThreadAdbshell(90,185);
         distance=1000;
     }  
   
     //����ִ�еĲ���
     @Override  
     public int onStartCommand(Intent intent, int flags, int startId) {  
         Log.d(TAG, "onStartCommand");  
         distance=intent.getIntExtra("distance", 1000);
         thshell.setDistance(distance);
         thshell.setX(intent.getIntExtra("x", 90));
         thshell.setY(intent.getIntExtra("y", 185));
         if(!thshell.isAlive())
         {
        	 Log.d("�߳��Ƿ�����ִ��", thshell.isAlive()+"");
        	 thshell.start();      	 
         }
         
         return super.onStartCommand(intent, flags, startId);  
     }  
       
     //���ٷ���ʱ����
     @Override  
     public void onDestroy() {  
         super.onDestroy();  
         Log.d(TAG, "onDestroy");  
     }  
   
     //bindService()����Serviceʱ�Ż����onBind()����
    @Override  
    public IBinder onBind(Intent intent) {  
         return null;  
     }
		
}
