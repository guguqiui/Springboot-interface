package cn.edu.bupt.springsecurity.userdetails.demo.mapper;

import cn.edu.bupt.springsecurity.userdetails.demo.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface MessageMapper extends BaseMapper<Message> {
    @Insert("INSERT INTO message (content, role, conversation_id) VALUES (#{content}, #{role}, #{conversationId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Message message);
}