package com.upbeater.service;

import java.util.List;
import com.upbeater.model.request.ChapterRequest;
import com.upbeater.model.response.ChapterResponse;
import com.upbeater.utility.ResponseObject;
import org.springframework.http.ResponseEntity;

public interface ChapterService {
    ResponseEntity<ResponseObject> createChapter(ChapterRequest chapterRequest);

    List<ChapterResponse> getChaptersByMaterialId(int materialId);
}
