package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	public String callURL(String myURL) {

        System.out.println("Requeted URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        //error : Caused by: javax.net.ssl.SSLPeerUnverifiedException: Hostname not verified:
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                //특정 hostname만 승인을 해주는 형태             
                    return true;
            }
        };
        
        //
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
                //charset 문자 집합의 인코딩을 사용해 urlConn.getInputStream을 문자스트림으로 변환 객체를 생성.
                BufferedReader bufferedReader = new BufferedReader(in);
                //주어진 문자 입력 스트림 inputStream에 대해 기본 크기의 버퍼를 갖는 객체를 생성.
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception URL:"+ myURL, e);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    public Map<String , Object> string2Map(String json){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;

        try {
            map = mapper.readValue(json, Map.class);
            System.out.println(map);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
    
}
