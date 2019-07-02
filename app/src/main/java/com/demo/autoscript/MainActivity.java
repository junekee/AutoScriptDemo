package com.demo.autoscript;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	  int width,x;
      int height,y;
      private TextView txt,location_tip;
      private EditText txt_edit;
      private Button start,stop,scrollview,setting,location_btn;
      private int distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        width = getWindowManager().getDefaultDisplay().getWidth();       
        height = getWindowManager().getDefaultDisplay().getHeight();
        distance=1000;
        x=90;y=185;
        Log.d("width，height",width+","+height);
        txt=(TextView) findViewById(R.id.tiptxt);
        start=(Button) findViewById(R.id.start);
        stop=(Button) findViewById(R.id.end);
        setting=(Button) findViewById(R.id.txt_setting);
        txt_edit=(EditText) findViewById(R.id.txt_canshu);
        scrollview=(Button) findViewById(R.id.scrollview);
        location_btn=(Button) findViewById(R.id.location_btn);
        location_tip=(TextView) findViewById(R.id.location_tip);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        scrollview.setOnClickListener(this);
        setting.setOnClickListener(this);
        location_btn.setOnClickListener(this);
        txt_edit.setText("1");
        //new ThreadClass(width,height).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	// TODO Auto-generated method stub
    	x=(int) event.getX();
    	y=(int) event.getY();
    	location_tip.setText("你点击的屏幕位置为:"+x+","+y);
    	return super.onTouchEvent(event);
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int id=arg0.getId();
		switch(id){
			case R.id.start:
				//Toast.makeText(this, "你点击了停止按钮", 1000).show();
				Intent startIntent = new Intent(this, BackGroundService.class);
				startIntent.putExtra("distance", distance);
				startIntent.putExtra("x", x);
				startIntent.putExtra("y", y);
				startService(startIntent);
				break;
			case R.id.end:
				//Toast.makeText(this, "你点击了开始按钮", 1000).show();
				Intent stopIntent = new Intent(this, BackGroundService.class);
				stopService(stopIntent);
				//unbindService(stopIntent);
				break;
			case R.id.scrollview:
				Intent scrollactivity=new Intent(this,myScrollView.class);
				startActivity(scrollactivity);
			case R.id.txt_setting:
				distance=Integer.parseInt(txt_edit.getText().toString())*1000;
				txt.setText("设置刷新间隔为:"+distance/1000+"秒");
				break;
			case R.id.location_btn:
				Toast.makeText(this, "设置成功", 1500).show();
				break;
			default:
				break;
		}
	}
}
