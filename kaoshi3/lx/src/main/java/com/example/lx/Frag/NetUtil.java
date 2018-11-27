package com.example.lx.Frag;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class NetUtil {
    public static String getJson(String string) {

        try {
            URL url = new URL(string);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode==200){
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp=bufferedReader.readLine())!=null){
                    stringBuilder.append(temp);
                }
                return stringBuilder.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
