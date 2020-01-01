package com.upbeater.service.serviceimpl;

import com.upbeater.model.dom.Material;
import com.upbeater.repository.MaterialRepository;
import com.upbeater.service.MaterialService;
import com.upbeater.utility.ResponseObject;
import com.upbeater.utility.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAllByStatusNot(StatusTypes.DEACTIVATE.getStatusId());
    }

    @Override
    public ResponseEntity<ResponseObject> createMaterial(Material material) {
        ResponseObject responseObject;
        if (material != null) {
            material.setCreatedDate(new Date());
            Material savedMaterial = this.materialRepository.save(material);
            responseObject = new ResponseObject("Materials Successfully saved!", true, savedMaterial);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("Materials Saving Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseObject> updateMaterial(Material material) {
        ResponseObject responseObject;
        if (material != null) {
            Material findMaterial = this.materialRepository.findMaterialByIdAndStatusNot(material.getId(), StatusTypes.DEACTIVATE.getStatusId());
            findMaterial.setMaterialTopic(material.getMaterialTopic());
            findMaterial.setMaterialDescription(material.getMaterialDescription());
            findMaterial.setStatus(material.getStatus());
            findMaterial.setCreatedDate(new Date());
            Material updatedMaterial = this.materialRepository.save(findMaterial);
            responseObject = new ResponseObject("Material Successfully Updated!", true, updatedMaterial);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("Material Updating Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseObject> deleteMaterial(int id) {
        ResponseObject responseObject;
        if (id != 0) {
            Material findMaterial = this.materialRepository.findMaterialByIdAndStatusNot(id, StatusTypes.DEACTIVATE.getStatusId());
            findMaterial.setStatus(StatusTypes.DEACTIVATE.getStatusId());
            findMaterial.setCreatedDate(new Date());
            Material deletedMaterial = this.materialRepository.save(findMaterial);
            responseObject = new ResponseObject("Material Successfully Deleted!", true, deletedMaterial);
            return new ResponseEntity<>(responseObject, HttpStatus.OK);
        } else {
            responseObject = new ResponseObject("Material Deleting Error!", false, null);
            return new ResponseEntity<>(responseObject, HttpStatus.BAD_REQUEST);
        }
    }
}
