package capstone.chatservice.infra.kafka;

import capstone.chatservice.domain.dm.dto.DirectMessageDto;
import capstone.chatservice.domain.emoji.dto.EmojiDto;
import capstone.chatservice.domain.forum.dto.ForumMessageDto;
import capstone.chatservice.domain.server.dto.ServerMessageDto;
import capstone.chatservice.infra.kafka.dto.ConnectionStateEventDto;
import capstone.chatservice.infra.kafka.dto.ConnectionStateInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${spring.kafka.topic.server-chat}")
    private String serverChatTopic;

    @Value("${spring.kafka.topic.direct-chat}")
    private String directChatTopic;

    @Value("${spring.kafka.topic.emoji-chat}")
    private String emojiChatTopic;

    @Value("${spring.kafka.topic.forum-chat}")
    private String forumChatTopic;

    @Value("${spring.kafka.topic.connection-state-event}")
    private String connectionStateEventTopic;

    @Value("${spring.kafka.topic.connection-state-info}")
    private String connectionStateInfoTopic;

    private final KafkaTemplate<String, ServerMessageDto> serverChatKafkaTemplate;
    private final KafkaTemplate<String, DirectMessageDto> direcetChatKafkaTemplate;
    private final KafkaTemplate<String, EmojiDto> emojiChatKafkaTemplate;
    private final KafkaTemplate<String, ForumMessageDto> forumChatKafkaTemplate;
    private final KafkaTemplate<String, ConnectionStateEventDto> connectionStateEventKafkaTemplate;
    private final KafkaTemplate<String, ConnectionStateInfo> connectionStateInfoKafkaTemplate;

    public void sendToServerChatTopic(ServerMessageDto messageDto) {
        serverChatKafkaTemplate.send(serverChatTopic, messageDto);
    }

    public void sendToDirectChatTopic(DirectMessageDto directMessageDto) {
        direcetChatKafkaTemplate.send(directChatTopic, directMessageDto);
    }

    public void sendToEmojiChatTopic(EmojiDto emojiDto) {
        emojiChatKafkaTemplate.send(emojiChatTopic, emojiDto);
    }

    public void sendToForumChatTopic(ForumMessageDto forumMessageDto) {
        forumChatKafkaTemplate.send(forumChatTopic, forumMessageDto);
    }

    public void sendToConnectionStateEventTopic(ConnectionStateEventDto connectionStateEventDto) {
        connectionStateEventKafkaTemplate.send(connectionStateEventTopic, connectionStateEventDto);
    }

    public void sendToConnectionStateInfoTopic(ConnectionStateInfo connectionStateInfo) {
        connectionStateInfoKafkaTemplate.send(connectionStateInfoTopic, connectionStateInfo);
    }
}