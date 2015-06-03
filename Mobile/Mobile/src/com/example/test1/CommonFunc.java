package com.example.test1;

import java.net.*;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class CommonFunc {
  
	
	 public String getLocalHostIp()
	    {
	        String ipaddress = "";
	        try
	        {
	            Enumeration<NetworkInterface> en = NetworkInterface
	                    .getNetworkInterfaces();
	            // 遍历所用的网络接口
	            while (en.hasMoreElements())
	            {
	                NetworkInterface nif = en.nextElement();// 得到每一个网络接口绑定的所有ip
	                Enumeration<InetAddress> inet = nif.getInetAddresses();
	                // 遍历每一个接口绑定的所有ip
	                while (inet.hasMoreElements())
	                {
	                    InetAddress ip = inet.nextElement();
	                    if (!ip.isLoopbackAddress()
	                            && InetAddressUtils.isIPv4Address(ip
	                                    .getHostAddress()))
	                    {
	                        return ipaddress = ip.getHostAddress();
	                    }
	                }

	            }
	        }
	        catch (SocketException e)
	        {
	            Log.e("feige", "获取本地ip地址失败");
	            e.printStackTrace();
	        }
	        return ipaddress;
	    }
	 /*public String getLocalMac()
	    {
	        String mac = "";
	        WifiManager wifiMng = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	        WifiInfo wifiInfor = wifiMng.getConnectionInfo();
	        mac = "本机的mac地址是：" + wifiInfor.getMacAddress();
	        return mac;
	    }
	    */
}
