package com.zova0.rinjindoudeshouserver.service;

import com.zova0.rinjindoudeshouserver.dto.MansionForumDto;
import com.zova0.rinjindoudeshouserver.exception.AppException;
import com.zova0.rinjindoudeshouserver.model.MansionForum;
import com.zova0.rinjindoudeshouserver.repository.MansionForumRepository;
import lombok.AllArgsConstructor;
import com.zova0.rinjindoudeshouserver.mapper.MansionForumMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MansionForumService {
    private final MansionForumRepository mansionForumRepository;
    private final MansionForumMapper mansionForumMapper;

    @Transactional
    public MansionForumDto save(MansionForumDto mansionForumDto) {
        MansionForum save = mansionForumRepository.save(mansionForumMapper.mapDtoToMansionForum(mansionForumDto));
        mansionForumDto.setId(save.getId());
        return mansionForumDto;
    }

    @Transactional(readOnly = true)
    public List<MansionForumDto> getAll() {
        return mansionForumRepository.findAll()
                .stream()
                .map(mansionForumMapper::mapMansionForumToDto)
                .collect(Collectors.toList());
    }

    public MansionForumDto getMansionForum(Long id) {
        MansionForum mansionForum = mansionForumRepository.findById(id)
                .orElseThrow(() -> new AppException("No mansion forum found with id: " + id));
        return mansionForumMapper.mapMansionForumToDto(mansionForum);
    }
}
