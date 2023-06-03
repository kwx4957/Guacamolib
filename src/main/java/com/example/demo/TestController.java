package com.example.demo;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    String clientId = "864b7106c176be294a68dbf5c9b8b95e";
    String redirectURL = "http://127.0.0.1:8080/redirect";
    String response = "code";
    @GetMapping("/")
    public String helloworld() throws MalformedURLException {
        String urlBuilder = "https://kauth.kakao.com/oauth/authorize" + "?"
                + URLEncoder.encode("response_type", StandardCharsets.UTF_8) + "=code"
                + "&" + "client_id=" + clientId + "&redirect_uri=" + redirectURL ;
        URL url = new URL(urlBuilder);
        System.out.println(url.toString());
        return "hello world";
    }
    @GetMapping("/redirect")
    public void getURL(@RequestParam String code) throws IOException {
        BufferedReader br = null;
        StringBuffer sb = null;
        String response = "";
        String returnData ="";

        URL url = new URL("https://kauth.kakao.com/oauth/token");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true); //POST BODY 넘기기
        conn.setDoInput(true);

        List<String> rs = new ArrayList<>();
        rs.add("grant_type=authorization_code");
        rs.add("client_id="+clientId);
        rs.add("code=" + code);
        rs.add("redirect_uri="+ redirectURL);

        System.out.println();
        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.writeBytes(String.valueOf(rs));

        conn.connect();
        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        sb = new StringBuffer();
        while ((response = br.readLine()) !=null){
            sb.append(response);
        }

        returnData = sb.toString();
        System.out.println(returnData);
        br.close();
    }
}

// 1. GET https://kapi.kakao.com/oauth/authorize
// 2. redirect url로 인가 코드 전달

// 3. POST /oauth/token