package com.upbeater.controller;

import com.upbeater.model.request.ChapterRequest;
import com.upbeater.model.response.ChapterResponse;
import com.upbeater.service.ChapterService;
import com.upbeater.utility.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/chapterController")
public class ChapterController {
    private final String APPLICATION_JSON = "application/json";

    private final ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PostMapping(value = "/createChapter", produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> createChapter(@RequestBody ChapterRequest chapterRequest) {
        return chapterService.createChapter(chapterRequest);
    }

    @GetMapping(value = "/getChaptersByMaterialId/{materialId}", produces = {APPLICATION_JSON})
    public List<ChapterResponse> getChaptersByMaterialId(@PathVariable(value = "materialId") int materialId) {
        return chapterService.getChaptersByMaterialId(materialId);
    }
}
