package com.chat.socket.controller;

import com.chat.socket.Repositories.ChatMessageRepository;
import com.chat.socket.dto.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    ChatMessageRepository chatMessageRepository;
    @Autowired
    public WebSocketController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) {
        System.out.println(message);
        chatMessageRepository.save(message);
        return new ChatMessage(message.getMessage(), message.getUser(), message.getEmojis(), message.getReactions());
    }
}
