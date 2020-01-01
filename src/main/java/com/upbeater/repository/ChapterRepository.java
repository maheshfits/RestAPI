package com.upbeater.repository;

import com.upbeater.model.dom.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    List<Chapter> findChapterByMaterialIdAndStatusNot(int materialId, int status);
}
