package com.chat.socket.Services;

import com.chat.socket.Repositories.ChatMessageRepository;
import com.chat.socket.dto.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    public void reactToMessage(String messageId, String reaction) {
        // Récupérer le message associé à l'ID
        Optional<ChatMessage> optionalMessage = chatMessageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            ChatMessage message = optionalMessage.get();

            // Ajouter la réaction à la liste des réactions du message
            List<String> reactions = message.getReactions();
            if (reactions == null) {
                reactions = new ArrayList<>();
            }
            reactions.add(reaction);
            message.setReactions(reactions);

            // Enregistrer le message mis à jour dans la base de données
            chatMessageRepository.save(message);

            // Diffuser la réaction à tous les utilisateurs connectés
            messagingTemplate.convertAndSend("/topic/reactions/" + messageId, reaction);
        } else {
            // Gérer le cas où le message n'a pas été trouvé
        }
    }
}
