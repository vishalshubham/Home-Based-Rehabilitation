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
	            // �������õ�����ӿ�
	            while (en.hasMoreElements())
	            {
	                NetworkInterface nif = en.nextElement();// �õ�ÿһ������ӿڰ󶨵�����ip
	                Enumeration<InetAddress> inet = nif.getInetAddresses();
	                // ����ÿһ���ӿڰ󶨵�����ip
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
	            Log.e("feige", "��ȡ����ip��ַʧ��");
	            e.printStackTrace();
	        }
	        return ipaddress;
	    }
	 /*public String getLocalMac()
	    {
	        String mac = "";
	        WifiManager wifiMng = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	        WifiInfo wifiInfor = wifiMng.getConnectionInfo();
	        mac = "������mac��ַ�ǣ�" + wifiInfor.getMacAddress();
	        return mac;
	    }
	    */
}
