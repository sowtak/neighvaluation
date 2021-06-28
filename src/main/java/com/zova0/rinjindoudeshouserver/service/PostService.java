package com.zova0.rinjindoudeshouserver.service;

import com.zova0.rinjindoudeshouserver.dto.PostRequest;
import com.zova0.rinjindoudeshouserver.dto.PostResponse;
import com.zova0.rinjindoudeshouserver.exception.AppException;
import com.zova0.rinjindoudeshouserver.exception.MansionForumNotFoundException;
import com.zova0.rinjindoudeshouserver.exception.PostNotFoundException;
import com.zova0.rinjindoudeshouserver.mapper.PostMapper;
import com.zova0.rinjindoudeshouserver.model.MansionForum;
import com.zova0.rinjindoudeshouserver.model.Post;
import com.zova0.rinjindoudeshouserver.model.User;
import com.zova0.rinjindoudeshouserver.repository.MansionForumRepository;
import com.zova0.rinjindoudeshouserver.repository.PostRepository;
import com.zova0.rinjindoudeshouserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MansionForumRepository mansionForumRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        MansionForum mansionForum = mansionForumRepository.findByName(postRequest.getMansionForumName())
                .orElseThrow(() -> new MansionForumNotFoundException(postRequest.getMansionForumName()));
        postRepository.save(postMapper.map(postRequest, mansionForum, authenticationService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostByMansionForum(Long mansionForumId) {
        MansionForum mansionForum = mansionForumRepository.findById(mansionForumId)
                .orElseThrow(() -> new MansionForumNotFoundException(mansionForumId.toString()));
        List<Post> posts = postRepository.findAllByMansionForum(mansionForum);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
