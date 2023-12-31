package cn.edu.bupt.springsecurity.userdetails.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyService {

    private final RestTemplate restTemplate;

    @Autowired
    public MyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getResponseFromApi() {
        String url = "https://api.ai2u.link/ai/";

        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // 创建请求体
        String requestBody = "{"
                + "\"service\": \"OpenAI\","
                + "\"interface_name\": \"chatGPT_1\","
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "{"
                + "\"role\": \"user\","
                + "\"content\": \"Your knowledge cutoff is until when?\""
                + "}"
                + "]"
                + "}";

        // 创建 HttpEntity 对象
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 发送 POST 请求
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

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
