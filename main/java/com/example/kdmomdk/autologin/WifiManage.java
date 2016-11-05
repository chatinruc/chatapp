package com.example.kdmomdk.autologin;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by kdmomdk on 2016/9/1.
 */
public class WifiManage {
    private WifiManager manager = null;
    private List<WifiConfiguration> con = null;
    private List<ScanResult> scan = null;
    public WifiManage(Context cur){
        try{
            manager = (WifiManager)cur.getSystemService(Context.WIFI_SERVICE);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //判断wifi是否开启，否则打开wifi,成功打开返回true，否则返回false
    public boolean OpenWifi(){
        if(!manager.isWifiEnabled())
            return manager.setWifiEnabled(true);
        else
            return true;
    }
    //获得已经保存的连接
    public boolean getConfiguration(){
        this.con=manager.getConfiguredNetworks();
        if(this.con==null) return false;
        return true;
    }
    //扫描wifi信息,储存在scan中
    public boolean scanWifi(){
        manager.startScan();
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        scan = manager.getScanResults();
        if(scan==null) return false;
        return true;
    }

    //连接wifi
    public boolean getConnection(String name){
        boolean res;
        this.OpenWifi();
        this.getConfiguration();
        //在已保存列表中尝试
        for(int i=0;i<con.size();i++) {
            if (con.get(i).SSID.matches(name)) {
                res = manager.enableNetwork(con.get(i).networkId, true);
                if (res == true) return true;
            }
        }
        //尝试重新构造连接
        this.scanWifi();
        for(int i=0;i<scan.size();i++){
            if(scan.get(i).SSID.matches(name)){
                WifiConfiguration wifiConfig = new WifiConfiguration();
                int Id = -1;
                wifiConfig.SSID = scan.get(i).SSID;
                wifiConfig.hiddenSSID = false;
                wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                wifiConfig.allowedAuthAlgorithms.clear();
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                Id = manager.addNetwork(wifiConfig);
                res = manager.enableNetwork(Id,true);
                if(res==true) return true;
            }
        }
        return false;
    }
}
