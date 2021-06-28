package com.zova0.rinjindoudeshouserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String url;
    private String content;
    private String username;
    private String mansionForumName;
    private Integer likeCount;
    private Integer commentCount;
    private String duration;
}
