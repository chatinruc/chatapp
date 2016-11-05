package com.example.kdmomdk.autologin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

/**
 * Created by kdmomdk on 2016/8/22.
 */
public class Post {

    public Post(String http,String acc,String pas) {
        try{
            HttpsURLConnection post = null;
            URL url = new URL(http);
            post = (HttpsURLConnection) url.openConnection();
            post.setRequestMethod("POST");
            post.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; qdesk 2.4.1263.203; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727");
            post.setRequestProperty("Accept-Language","zh-cn");
            post.setUseCaches(false);
            post.setDoOutput(true);    // 可以发送数据
            post.setDoInput(true);
            post.setRequestProperty("Connection", "Keep-Alive");
            post.setConnectTimeout(15000);
            post.setReadTimeout(20000);
            post.setAllowUserInteraction(false);
            post.connect();
            OutputStreamWriter output = new OutputStreamWriter(post.getOutputStream(), "UTF-8");
            output.write("OMKKey=123456&DDDDD="+acc+"&hid1=&hid2=&R6=0&upass="+pas);
            output.flush();
            output.close();
            if(post.getResponseCode()!=-1){
                //connect successfully
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
