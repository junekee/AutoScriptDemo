package com.demo.autoscript;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

public class ThreadClass extends Thread {
	private int x,y;
	//400,689
	@Override
	public void run() {
		while(true)
		{			
			//利用ProcessBuilder执行shell命令
	        /*String[] order = {
	                "input",
	                "tap",
	                "" + x,
	                "" + y
	        };
	        Log.d("点击位置", x+","+y);
	        try {
	            new ProcessBuilder(order).start();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }*/
			// 可以不用在 Activity 中增加任何处理，各 Activity 都可以响应 
			 try {  
				Instrumentation inst = new Instrumentation();  
				inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),   
				    MotionEvent.ACTION_DOWN, x, y, 0));  
				inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),   
					MotionEvent.ACTION_UP, x, y, 0));
				Log.d("点击位置", x+","+y);
			 }catch(Exception e) {  
                 Log.e("Exception when sendPointerSync", e.toString());  
             }  
	        //线程睡眠10s
	        try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public ThreadClass(int x,int y){
		this.x=x;
		this.y=y;
	}
}
