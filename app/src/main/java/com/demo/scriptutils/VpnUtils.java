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
        boolean flag = false;
        Log.d(TAG, "Network count: " + networks.length);
        for(int i = 0; i < networks.length; i++) {

            NetworkCapabilities caps = cm.getNetworkCapabilities(networks[i]);
            flag = flag || caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
            Log.i(TAG, "Network " + i + ": " + networks[i].toString());
            Log.i(TAG, "VPN transport is: " + caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN));
            Log.i(TAG, "NOT_VPN capability is: " + caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN));

        }
        // 根据具体参数 重写返回逻辑
        return flag;
    }
    public static boolean isVpnAlive(Context mContext){
        return isVpnConnected(mContext);
    }

    public static Object getVpnInfo(){
        return null;
    }
}
