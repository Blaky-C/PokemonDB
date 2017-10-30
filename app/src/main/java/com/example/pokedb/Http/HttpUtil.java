package com.example.pokedb.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jack on 2017/10/29.
 */

public class HttpUtil {

    public interface PicCallbackListener{
        void onFinish(Bitmap bitmap);
        void onError(Exception e);
    }

/*
    public static void getHTMLData(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!=null){
                        sb.append(line);
                    }
                    reader.close();
                    if (listener != null){
                        //回调onFinish方法
                        listener.onFinish(sb.toString());
                    }
                }catch (Exception e){
                    listener.onError(e);
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
*/

    public static void getServerPic(final String a, final PicCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    Bitmap bitmap = null;
                    URL url = new URL(a);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    if (connection.getResponseCode()==200){
                        bitmap = BitmapFactory.decodeStream(in);
                    }
                    listener.onFinish(bitmap);
                }catch (Exception e){
                    e.printStackTrace();
                    listener.onError(e);
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

}
