package capstone.chatservice.domain.forum.dto;

import capstone.chatservice.domain.emoji.dto.EmojiDto;
import capstone.chatservice.domain.forum.domain.ForumMessage;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumMessageDto {

    private Long messageId;
    private Long forumId;
    private Long serverId;
    private Long channelId;
    private Long userId;
    private Long parentId;
    private Long count;
    private String profileImage;
    private String type;
    private String writer;
    private String content;
    private boolean isDeleted;
    private List<String> files;
    private List<EmojiDto> emojis;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ForumMessageDto from(ForumMessage message) {
        return ForumMessageDto.builder()
                .messageId(message.getMessageId())
                .forumId(message.getForumId())
                .serverId(message.getServerId())
                .channelId(message.getChannelId())
                .userId(message.getUserId())
                .parentId(message.getParentId())
                .profileImage(message.getProfileImage())
                .type(message.getType())
                .writer(message.getWriter())
                .content(message.getContent())
                .isDeleted(message.isDeleted())
                .files(message.getFiles())
                .createdAt(message.getCreatedAt())
                .modifiedAt(message.getModifiedAt())
                .build();
    }
}
