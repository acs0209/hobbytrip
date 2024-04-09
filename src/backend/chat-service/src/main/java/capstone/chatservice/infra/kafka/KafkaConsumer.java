package capstone.chatservice.infra.kafka;

import capstone.chatservice.domain.emoji.dto.EmojiDto;
import capstone.chatservice.domain.emoji.dto.response.EmojiCreateResponse;
import capstone.chatservice.domain.server.dto.ServerMessageDto;
import capstone.chatservice.domain.server.dto.response.ServerMessageCreateResponse;
import capstone.chatservice.domain.server.dto.response.ServerMessageDeleteResponse;
import capstone.chatservice.domain.server.dto.response.ServerMessageModifyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SimpMessageSendingOperations messagingTemplate;

    @KafkaListener(topics = "${spring.kafka.topic.server-chat}", groupId = "${spring.kafka.consumer.group-id.server-chat}", containerFactory = "serverChatListenerContainerFactory")
    public void serverChatListener(ServerMessageDto messageDto) {
        String messageType = messageDto.getType();
        Long serverId = messageDto.getServerId();
        switch (messageType) {
            case "send" -> {
                ServerMessageCreateResponse createResponse = ServerMessageCreateResponse.from(messageDto);
                messagingTemplate.convertAndSend("/topic/server/" + serverId, createResponse);
            }
            case "modify" -> {
                ServerMessageModifyResponse modifyResponse = ServerMessageModifyResponse.from(messageDto);
                messagingTemplate.convertAndSend("/topic/server/" + serverId, modifyResponse);
            }
            case "delete" -> {
                ServerMessageDeleteResponse deleteResponse = ServerMessageDeleteResponse.from(messageDto);
                messagingTemplate.convertAndSend("/topic/server/" + serverId, deleteResponse);
            }
        }
    }

    @KafkaListener(topics = "${spring.kafka.topic.emoji-chat}", groupId = "${spring.kafka.consumer.group-id.emoji-chat}", containerFactory = "emojiChatListenerContainerFactory")
    public void emojiChatListener(EmojiDto emojiDto) {
        String emojiType = emojiDto.getType();
        Long serverId = emojiDto.getServerId();
        switch (emojiType) {
            case "save" -> {
                EmojiCreateResponse createResponse = EmojiCreateResponse.from(emojiDto);
                messagingTemplate.convertAndSend("/topic/server/" + serverId, createResponse);
            }
        }
    }
}