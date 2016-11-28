package com.example.kdmomdk.autologin;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

/**
 * Created by kdmomdk on 2016/8/22.
 */
public class Post {
    public Post(String http,String acc,String pas) {
        HttpURLConnection post =null;
        URL url = null;
        String data = "DDDDD="+acc+"&upass="+pas+"&hid1=&hid2=&0MKKey=123456&R6=0";
        try{
            url = new URL(http);
            post = (HttpURLConnection) url.openConnection();

            post.setDoOutput(true);    // 可以发送数据

            post.setUseCaches(false);
            post.setInstanceFollowRedirects(true);

            post.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            post.setRequestProperty("Charset", "utf-8");

            post.setConnectTimeout(15000);
            post.setReadTimeout(20000);

            DataOutputStream output = new DataOutputStream(post.getOutputStream());
            output.writeBytes(data);
            output.flush();
            output.close();
            if(post.getResponseCode()==200){

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
