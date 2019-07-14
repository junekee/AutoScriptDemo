package com.demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class VpnConnectReceiver extends BroadcastReceiver
{
    private static final String TAG="[VpnConnectReceiver]";
    public void onReceive(Context c, Intent intent)
    {
        String state = intent.getSerializableExtra("connection_state").toString();

        Log.d(TAG, state.toString());

        if (state.equals("CONNECTING")) {
            // Do what needs to be done
            Log.d(TAG,"connecting");

        }
        else if (state.equals("CONNECTED")) {
            // Do what needs to be done
        }
        else if (state.equals("IDLE")) {
            int errorCode = intent.getIntExtra("err", 0);
            if (errorCode != 0) {
                // Do what needs to be done to report a failure
            }
            else {
                // Normal disconnect
            }
        }
        else if (state.equals("DISCONNECTING")) {
            Log.d(TAG,"disconnecting");
        }
    }
}
