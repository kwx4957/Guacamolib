package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
public class TestController {
    String clientId = "864b7106c176be294a68dbf5c9b8b95e";
    String redirectURL = "http://127.0.0.1:8080/redirect";
    String response = "code";
    @GetMapping("/")
    public String helloworld() throws MalformedURLException {
        return "https://kauth.kakao.com/oauth/authorize" + "?"
                + URLEncoder.encode("response_type", StandardCharsets.UTF_8) + "=code"
                + "&" + "client_id=" + clientId + "&redirect_uri=" + redirectURL;
    }
    @GetMapping("/redirect")
    public void getURL(@RequestParam String code) throws IOException {
        String access = getAccessCode(code);

        // 3. 사용자 정보 가져오기
        URL getInfo = new URL("https://kapi.kakao.com/v2/user/me");
        HttpURLConnection getInfoConn = (HttpURLConnection) getInfo.openConnection();
        getInfoConn.setRequestMethod("GET");
        getInfoConn.setRequestProperty("Authorization", "Bearer " + access);
        //getInfoConn.setRequestProperty("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        log.info(getInfoConn.getHeaderFields().toString());

        BufferedReader br = new BufferedReader(new InputStreamReader(getInfoConn.getInputStream()));
        String rsss = "";
        StringBuffer sb1 = new StringBuffer();
        while ((rsss = br.readLine()) !=null){
            sb1.append(rsss);
        }
        System.out.println(sb1.toString());

        br.close();
        getInfoConn.disconnect();
    }

    public String getAccessCode(String code) throws IOException {
        BufferedReader br = null;
        StringBuffer sb = null;
        String response = "";
        String returnData ="";

        URL url = new URL("https://kauth.kakao.com/oauth/token");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true); //POST BODY 넘기기

        String request = "grant_type=authorization_code&client_id="
                +clientId+"&code="
                +code+"&redirect_uri="+redirectURL;
        OutputStream os = conn.getOutputStream();
        os.write(request.getBytes(StandardCharsets.UTF_8));
        os.close();

        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        sb = new StringBuffer();
        while ((response = br.readLine()) !=null){
            sb.append(response);
        }

        returnData = sb.toString();
        String access = returnData.split(":")[1].split(",")[0].replaceAll("\"","");
        br.close();
       // conn.disconnect();
        log.info(returnData);
        log.info(access);

        return access;
    }
}
