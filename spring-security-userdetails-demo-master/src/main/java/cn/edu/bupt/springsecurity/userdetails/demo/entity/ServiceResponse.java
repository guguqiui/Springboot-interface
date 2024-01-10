package cn.edu.bupt.springsecurity.userdetails.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class ServiceResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    @JsonProperty("system_fingerprint")
    private String systemFingerprint;
    private List<Choice> choices;
    private Usage usage;

    public Message getLatestMessage() {
        return choices.get(0).getLatestMessage();
    }

}

@Data
class Choice {
    private int index;
    private Message message;
    @JsonProperty("finish_reason")
    private String finishReason;

    public Message getLatestMessage() {
        return message;
    }
}

@Data
class Usage {
    @JsonProperty("prompt_tokens")
    private int promptTokens;
    @JsonProperty("completion_tokens")
    private int completionTokens;
    @JsonProperty("total_tokens")
    private int totalTokens;

}
