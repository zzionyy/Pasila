package org.ssafy.pasila.domain.live.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatLogDto {

    @Schema(description = "라이브 방송 ID")
    private String liveId;
    
    @Schema(description = "시청자의 ID")
    private String memberId;
    
    @Schema(description = "채팅 내용")
    private String message;
    
    @Schema(description = "채팅 시간")
    private LocalDateTime createdAt;

    public void setCreatedAt() {

        this.createdAt = LocalDateTime.now();

    }

}