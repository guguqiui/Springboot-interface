package cn.edu.bupt.springsecurity.userdetails.demo.service;

import cn.edu.bupt.springsecurity.userdetails.demo.entity.Message;
import cn.edu.bupt.springsecurity.userdetails.demo.entity.ServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MyService {
    private final RestTemplate restTemplate;

    @Autowired
    public MyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Message getResponseFromApi(String service, String interfaceName, String model, List<Message> messages) {
        String url = "https://api.ai2u.link/ai/chat";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("service", service);
        requestBody.put("interface_name", interfaceName);
        requestBody.put("messages", messages);

        if (service.equals("OpenAI")) {
            requestBody.put("model", model);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // 返回null表示处理失败
        }

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        HttpStatus statusCode = response.getStatusCode();
        String responseBody = response.getBody();

        if (statusCode == HttpStatus.OK && responseBody != null) {
            try {
                ServiceResponse res = objectMapper.readValue(responseBody, ServiceResponse.class);
                System.out.println(res);
                return res.getLatestMessage();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null; // 返回null表示处理失败
            }
        }

        return null; // 如果无法解析响应或响应中没有消息，返回null
    }
}
