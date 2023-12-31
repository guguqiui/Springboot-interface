package cn.edu.bupt.springsecurity.userdetails.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.bupt.**.demo.mapper")
public class SpringSecurityUserdetailsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityUserdetailsDemoApplication.class, args);
    }

}
