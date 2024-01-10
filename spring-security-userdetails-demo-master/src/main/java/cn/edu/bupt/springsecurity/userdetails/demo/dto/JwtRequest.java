package cn.edu.bupt.springsecurity.userdetails.demo.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    // 需要默认构造器以便于序列化
    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

