package com.zova0.rinjindoudeshouserver.service;

import com.zova0.rinjindoudeshouserver.dto.LikeDto;
import com.zova0.rinjindoudeshouserver.exception.AppException;
import com.zova0.rinjindoudeshouserver.exception.PostNotFoundException;
import com.zova0.rinjindoudeshouserver.model.Like;
import com.zova0.rinjindoudeshouserver.model.Post;
import com.zova0.rinjindoudeshouserver.repository.LikeRepository;
import com.zova0.rinjindoudeshouserver.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final AuthenticationService authenticationService;

    public void like(LikeDto likeDto) {
        Post post = postRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found with id: " + likeDto.getPostId()));
        Optional<Like> likeByPostAndUser = likeRepository.findTopByPostAndUserOrderByLikeIdDesc(post, authenticationService.getCurrentUser());
        if(likeByPostAndUser.isPresent()) {
            throw new AppException("既にいいね済みです");
        }
        Like save = Like.builder().post(post).user(authenticationService.getCurrentUser()).build();
        likeRepository.save(save);
        postRepository.save(post);
    }
}
