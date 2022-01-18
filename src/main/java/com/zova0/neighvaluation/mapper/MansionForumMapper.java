package com.zova0.neighvaluation.mapper;

import com.zova0.neighvaluation.dto.MansionForumDto;
import com.zova0.neighvaluation.model.MansionForum;
import com.zova0.neighvaluation.model.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MansionForumMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(mansionForum.getPosts()))")
    MansionForumDto mapMansionForumToDto(MansionForum mansionForum);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    MansionForum mapDtoToMansionForum(MansionForumDto mansionForumDto);
}
