package com.zova0.rinjindoudeshouserver.controller;

import com.zova0.rinjindoudeshouserver.dto.PostRequest;
import com.zova0.rinjindoudeshouserver.dto.PostResponse;
import com.zova0.rinjindoudeshouserver.service.PostService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts/")
@AllArgsConstructor
public class PostController {
    @Autowired
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("by-mansionForum/{id}")
    public ResponseEntity<List<PostResponse>> getPostsByMansionForum(Long id) {
        return status(HttpStatus.OK).body(postService.getPostByMansionForum(id));
    }

    @GetMapping("by-user/{name}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String name)  {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(name));
    }
}
