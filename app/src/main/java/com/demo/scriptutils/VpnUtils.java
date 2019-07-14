package com.demo.scriptutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;

public final class VpnUtils {
    private static final String TAG = "[VpnUtils]";


    /**
     * @Params
     * @Decription Utils 测试是否存在vpn连接
     * @Return boolean
     * */
    public static boolean isVpnConnected(Context mContext){
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network[] networks = cm.getAllNetworks();

        Log.d(TAG, "Network count: " + networks.length);
        for(int i = 0; i < networks.length; i++) {

            NetworkCapabilities caps = cm.getNetworkCapabilities(networks[i]);

            Log.d(TAG, "Network " + i + ": " + networks[i].toString());
            Log.d(TAG, "VPN transport is: " + caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN));
            Log.d(TAG, "NOT_VPN capability is: " + caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN));

        }

        // 根据具体参数 重写返回逻辑
        return false;
    }
    public static boolean isVpnAlive(){
        return false;
    }

    public static Object getVpnInfo(){
        return null;
    }
}
