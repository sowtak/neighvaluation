package com.zova0.neighvaluation.controller;

import com.zova0.neighvaluation.dto.MansionForumDto;
import com.zova0.neighvaluation.service.MansionForumService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mansion-forum")
@AllArgsConstructor
public class MansionForumController {
    private final MansionForumService mansionForumService;

    @PostMapping
    public ResponseEntity<MansionForumDto> createMansionForum(@RequestBody MansionForumDto mansionForumDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mansionForumService.save(mansionForumDto));
    }

    @GetMapping
    public ResponseEntity<List<MansionForumDto>> getAllMansionForums() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mansionForumService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MansionForumDto> getMansionForum(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mansionForumService.getMansionForum(id));
    }
}
