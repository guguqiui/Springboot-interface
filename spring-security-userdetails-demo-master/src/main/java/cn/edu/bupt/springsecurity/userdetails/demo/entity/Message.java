package cn.edu.bupt.springsecurity.userdetails.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
@Data
public class Message {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String content;
    private String role;
    private Long conversationId;

    // getters and setters
}