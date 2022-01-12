package com.arpangroup.inventory.repository;

import com.arpangroup.inventory.entity.CategoryEntity;
import com.arpangroup.inventory.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends RevisionRepository<TagEntity, Integer, Integer>, JpaRepository<TagEntity, Integer> {
    public Optional<TagEntity> findByTagNameIgnoreCase(String tagName);
    public List<TagEntity> findByTagNameIn(List<String> tagNames);
}
