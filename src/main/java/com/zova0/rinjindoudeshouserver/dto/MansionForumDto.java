package com.zova0.rinjindoudeshouserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MansionForumDto {
    private Long id;
    private String name;
    private String address;
    private Integer numberOfPosts;
}
