package com.chat.socket.Repositories;

import com.chat.socket.dto.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository <ChatMessage,String> {
}
