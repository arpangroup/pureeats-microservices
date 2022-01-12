package com.arpangroup.inventory.service;

import com.arpangroup.inventory.dto.request.CategoryRequestDto;
import com.arpangroup.inventory.dto.request.TagRequest;
import com.arpangroup.inventory.entity.CategoryEntity;
import com.arpangroup.inventory.entity.TagEntity;

import java.util.List;

public interface TagService {
    public TagEntity insert(TagRequest request);
    public TagEntity findByTagName(String tagName);
    public List<TagEntity> findAll();
    public void deleteTag(String tagName);
}
