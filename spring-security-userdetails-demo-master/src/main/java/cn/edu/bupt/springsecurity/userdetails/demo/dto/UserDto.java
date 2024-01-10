package cn.edu.bupt.springsecurity.userdetails.demo.dto;

public class UserDto {
    private String username;
    private String password;

    // 标准的构造器、getter和setter
    public UserDto() {}

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
