package com.codesmell.domain.dto;

import com.codesmell.domain.eum.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PostDto extends BaseDto<Long> {

    private Status status;

    private String title;

    private String slug;

    private String content;

    private CategoryDto category;

    private Collection<TagDto> tags;

    private Collection<CommentDto> comments;

}
