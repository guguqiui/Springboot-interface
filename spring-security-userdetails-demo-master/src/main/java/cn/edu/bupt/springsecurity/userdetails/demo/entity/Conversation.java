package cn.edu.bupt.springsecurity.userdetails.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Conversation {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String name;

    // getters and setters
}
