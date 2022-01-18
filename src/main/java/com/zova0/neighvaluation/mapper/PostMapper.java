package com.zova0.neighvaluation.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.zova0.neighvaluation.dto.PostRequest;
import com.zova0.neighvaluation.dto.PostResponse;
import com.zova0.neighvaluation.model.MansionForum;
import com.zova0.neighvaluation.model.Post;
import com.zova0.neighvaluation.model.User;
import com.zova0.neighvaluation.repository.CommentRepository;
import com.zova0.neighvaluation.repository.LikeRepository;
import com.zova0.neighvaluation.service.AuthenticationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "content", source = "postRequest.content")
    @Mapping(target = "mansionForum", source = "mansionForum")
    @Mapping(target = "likeCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, MansionForum mansionForum, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "mansionForumName", source = "mansionForum.name")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
}
