package cn.edu.bupt.springsecurity.userdetails.demo.mapper;

import cn.edu.bupt.springsecurity.userdetails.demo.entity.Conversation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface ConversationMapper extends BaseMapper<Conversation> {
    @Insert("INSERT INTO conversation (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Conversation conversation);
}
