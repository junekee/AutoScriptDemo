package com.example.clickservice;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.util.Log;

public class ThreadAdbshell extends Thread {
	// �����ȡrootȨ�ޣ���һ������Ҫ����Ȼ��û������  
	public int start=1;
	private final static String TAG="[ThreadAdbshell]";
    
    int x,y;
    int distance;//ˢ�¼������λ����
	public ThreadAdbshell(int x,int y)
	{
		this.x=x;
		this.y=y;	
		distance=1000;
	}
	@Override
	public void run() {
		while(true)
		{
			if(start==1)
			{
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					Thread.sleep(distance);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Log.d("���λ�ã�",x+","+y);
			start++;
			//String str="input tap 252 252";
			String str="input tap "+x+" "+y; 
			try {
				excuteShellCMD(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void excuteShellCMD(String cmd) throws IOException
	{
		// �����ȡrootȨ�ޣ���һ������Ҫ����Ȼ��û������  
		Process process= Runtime.getRuntime().exec("su");
		//��ȡ������
        OutputStream outputStream = process.getOutputStream();  
        DataOutputStream dataOutputStream = new DataOutputStream(  
                outputStream);  
        dataOutputStream.writeBytes(cmd);  
        dataOutputStream.flush();  
        dataOutputStream.close();  
        outputStream.close();
        Log.d(TAG, "����¼�ִ��");
	}
	public void setDistance(int dis)
	{
		this.distance=dis;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int y)
	{
		this.y=y;
	}
}
