package com.demo.autoscript;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.adapter.CommandAdapter;
import com.demo.scriptutils.VpnUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements OnClickListener {

    private static final String TAG="[MainActivity]";
    int width, x;
    int height, y;
    private TextView txt, location_tip;
    private EditText txt_edit,commandBtn;
    private Button start, stop, scrollview, setting, location_btn, backhome,addC;
    private Button script01,cleanScript;
    private int distance;

    private Context mContext;
    ArrayList<String> data = new ArrayList<>();
    BaseAdapter adaper;

    ListView commandlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();
        distance = 1000;
        x = 90;
        y = 185;

        mContext  = getBaseContext();

        Log.d("width，height", width + "," + height);
        txt = (TextView) findViewById(R.id.tiptxt);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.end);
        setting = (Button) findViewById(R.id.txt_setting);
        txt_edit = (EditText) findViewById(R.id.txt_canshu);
        scrollview = (Button) findViewById(R.id.scrollview);
        location_btn = (Button) findViewById(R.id.location_btn);
        location_tip = (TextView) findViewById(R.id.location_tip);
        backhome = findViewById(R.id.backhome);

        script01 = findViewById(R.id.script01);
        cleanScript = findViewById(R.id.cleanScript);

        cleanScript.setOnClickListener(this);
        script01.setOnClickListener(this);
        backhome.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        scrollview.setOnClickListener(this);
        setting.setOnClickListener(this);
        location_btn.setOnClickListener(this);
        txt_edit.setText("1");

        initAdapter();
        initAddCommand();
    }

    private void initAddCommand() {
        addC = (Button) findViewById(R.id.addC_tv);
        commandBtn = (EditText) findViewById(R.id.command_tv);
        addC.setOnClickListener(this);
    }

    private void initAdapter() {
        commandlv = findViewById(R.id.commandlv);
        final Context mContext = getBaseContext();
        adaper = new CommandAdapter(data, mContext);
        commandlv.setAdapter(adaper);
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
        x = (int) event.getX();
        y = (int) event.getY();
        location_tip.setText("你点击的屏幕位置为:" + x + "," + y);
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        int id = arg0.getId();
        switch (id) {
            case R.id.start:
                //Toast.makeText(this, "你点击了停止按钮", 1000).show();
                Intent startIntent = new Intent(this, BackGroundService.class);
                startIntent.putExtra("distance", distance);
                startIntent.putExtra("x", x);
                startIntent.putExtra("y", y);
                startIntent.putExtra("command", data);
                startService(startIntent);
                break;
            case R.id.end:
                //Toast.makeText(this, "你点击了开始按钮", 1000).show();
                Intent stopIntent = new Intent(this, BackGroundService.class);
                stopService(stopIntent);
                //unbindService(stopIntent);
                break;
            case R.id.scrollview:
                Intent scrollactivity = new Intent(this, myScrollView.class);
                startActivity(scrollactivity);
            case R.id.txt_setting:
                distance = Integer.parseInt(txt_edit.getText().toString()) * 1000;
                txt.setText("设置刷新间隔为:" + distance / 1000 + "秒");
                break;
            case R.id.location_btn:
                Toast.makeText(this, "设置成功", Toast.LENGTH_LONG).show();
                break;
            case R.id.backhome:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
                intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
                startActivity(intent);

                break;
            case R.id.addC_tv:

                String commandString = commandBtn.getText().toString();
                parseCommand(commandString);

                break;

            case R.id.script01:
                ArrayList<String> scriptOneData = new ArrayList<>();
                scriptOneData.add("t:1000");
                scriptOneData.add("x:920*y:1420");
                scriptOneData.add("t:2000");
                scriptOneData.add("s:520 1500 520 600");
                scriptOneData.add("t:1000");
                scriptOneData.add("x:166*y:1036");
                scriptOneData.add("t:1000");
                scriptOneData.add("t:1000");
                scriptOneData.add("x:166*y:1036");
                scriptOneData.add("t:2000");
                scriptOneData.add("x:166*y:1036");
                scriptOneData.add("t:1000");
                scriptOneData.add("x:166*y:1036");
                scriptOneData.add("t:1000");
                data.addAll(scriptOneData);
                adaper.notifyDataSetChanged();
                break;

            case R.id.cleanScript:
                data.clear();
                adaper.notifyDataSetChanged();

                // 测试vpn是否连接
                boolean vpnConnected = VpnUtils.isVpnConnected(mContext);
                Toast.makeText(mContext,"Vpn Connect:"+vpnConnected,Toast.LENGTH_LONG).show();

                break;

            default:
                break;
        }
    }

    private void parseCommand(String str) {
        data.add(str);
        adaper.notifyDataSetChanged();
    }
}
