package com.zova0.neighvaluation.service;

import com.zova0.neighvaluation.dto.CommentsDto;
import com.zova0.neighvaluation.exception.PostNotFoundException;
import com.zova0.neighvaluation.mapper.CommentMapper;
import com.zova0.neighvaluation.model.Comment;
import com.zova0.neighvaluation.model.NotificationEmail;
import com.zova0.neighvaluation.model.Post;
import com.zova0.neighvaluation.model.User;
import com.zova0.neighvaluation.repository.CommentRepository;
import com.zova0.neighvaluation.repository.PostRepository;
import com.zova0.neighvaluation.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL ="";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authenticationService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(authenticationService.getCurrentUser() + " posted a comment on your post" + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " commented on your post", user.getEmail(), message));
    }

    //レポジトリを通して投稿についてる全コメントを取得
    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return  commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(Collectors.toList());
    }

    // ユーザが残した全コメントを取得
    public List<CommentsDto> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto).collect(Collectors.toList());
    }
}
