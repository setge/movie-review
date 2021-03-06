package com.setge.talkingtoday.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;
    private String title;
    private String content;
    private Long memberMid;
    private String memberEmail;
    private String memberNickname;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int replyCnt;
    private int viewCnt;
}
