package cn.edu.bupt.springsecurity.userdetails.demo.controller;


import cn.edu.bupt.springsecurity.userdetails.demo.entity.Conversation;
import cn.edu.bupt.springsecurity.userdetails.demo.entity.Message;
import cn.edu.bupt.springsecurity.userdetails.demo.mapper.ConversationMapper;
import cn.edu.bupt.springsecurity.userdetails.demo.mapper.MessageMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    @Autowired
    private ConversationMapper conversationMapper;
    @Autowired
    private MessageMapper messageMapper;

    // 创建对话
    @PostMapping
    public Conversation createConversation(@RequestBody Conversation conversation) {
        conversationMapper.insert(conversation);
        return conversation;
    }

    // 获取所有对话
    @GetMapping
    public List<Conversation> getAllConversations() {
        return conversationMapper.selectList(null);
    }

    // 获取特定对话
    @GetMapping("/{id}")
    public Conversation getConversationById(@PathVariable Long id) {
        return conversationMapper.selectById(id);
    }

    // 修改对话
    @PutMapping("/{id}")
    public Conversation updateConversation(@PathVariable Long id, @RequestBody Conversation conversationDetails) {
        conversationDetails.setId(id);
        conversationMapper.updateById(conversationDetails);
        return conversationDetails;
    }

    // 删除对话
    @DeleteMapping("/{id}")
    public String deleteConversation(@PathVariable Long id) {
        conversationMapper.deleteById(id);
        return "Deleted Conversation id = " + id;
    }

    // 发送消息
    @PostMapping("/{id}/messages")
    public Message createMessage(@PathVariable Long id, @RequestBody Message message) {
        message.setConversationId(id);
        messageMapper.insert(message);
        return message;
    }

    // 获取对话的所有消息
    @GetMapping("/{id}/messages")
    public List<Message> getAllMessagesByConversationId(@PathVariable Long id) {
        return messageMapper.selectList(new QueryWrapper<Message>().eq("conversation_id", id));
    }
}

