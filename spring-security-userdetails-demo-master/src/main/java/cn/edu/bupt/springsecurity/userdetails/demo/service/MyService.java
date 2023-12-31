package cn.edu.bupt.springsecurity.userdetails.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final RestTemplate restTemplate;

    @Autowired
    public MyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getResponseFromApi() {
        String url = "https://api.ai2u.link/ai/";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        // 获取状态码
        HttpStatus statusCode = response.getStatusCode();

        // 获取响应体
        String responseBody = response.getBody();

        // 在这里，你可以处理状态码和响应体，例如打印它们或者保存到数据库
        System.out.println("Status code: " + statusCode);
        System.out.println("Response body: " + responseBody);

        return responseBody;
    }
}
