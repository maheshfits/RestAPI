package com.upbeater.service.serviceimpl;

import com.upbeater.model.dom.Chapter;
import com.upbeater.model.dom.Material;
import com.upbeater.model.request.ChapterRequest;
import com.upbeater.model.response.ChapterResponse;
import com.upbeater.repository.ChapterRepository;
import com.upbeater.repository.MaterialRepository;
import com.upbeater.service.ChapterService;
import com.upbeater.utility.ResponseObject;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final MaterialRepository materialRepository;

    @Autowired
    public ChapterServiceImpl(ChapterRepository chapterRepository, MaterialRepository materialRepository) {
        this.chapterRepository = chapterRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    public ResponseEntity<ResponseObject> createChapter(ChapterRequest chapterRequest) {
        ResponseObject responseObject;
        if (chapterRequest != null) {
            Chapter chapter = new Chapter();
            chapter.setMaterialId(chapterRequest.getMaterialId());
            chapter.setChapterName(chapterRequest.getChapterName());
            chapter.setChapterDescription(chapterRequest.getChapterDescription());
            chapter.setStatus(chapterRequest.getStatus());
            chapter.setCreatedDate(new Date());
            Chapter savedChapter = this.chapterRepository.save(chapter);
            responseObject = new ResponseObject("Chapter Successfully Saved!", true, savedChapter);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("Chapter Saving Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ChapterResponse> getChaptersByMaterialId(int materialId) {
        List<Chapter> chapterList;
        List<ChapterResponse> chapterResponseList = new ArrayList<>();
        if (materialId > 0) {
            Material findMaterial = materialRepository.findMaterialByIdAndStatusNot(materialId, StatusTypes.DEACTIVATE.getStatusId());
            if (findMaterial != null) {
                chapterList = chapterRepository.findChapterByMaterialIdAndStatusNot(findMaterial.getId(), StatusTypes.DEACTIVATE.getStatusId());
                for (Chapter chapter : chapterList) {
                    ChapterResponse chapterResponse = new ChapterResponse();
                    chapterResponse.setChapterId(chapter.getChapterId());
                    chapterResponse.setChapterName(chapter.getChapterName());
                    chapterResponse.setChapterDescription(chapter.getChapterDescription());
                    chapterResponseList.add(chapterResponse);
                }
                return chapterResponseList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
