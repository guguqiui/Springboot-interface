package cn.edu.bupt.springsecurity.userdetails.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

//用户权限表
@Data
public class Authorities {
    //主键注解,数据库 ID 自增
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
    private String authority;
}
