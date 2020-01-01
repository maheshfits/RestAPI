package com.upbeater.service;

import com.upbeater.model.dom.Material;
import com.upbeater.utility.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MaterialService {
    List<Material> getAllMaterials();

    ResponseEntity<ResponseObject> createMaterial(Material material);

    ResponseEntity<ResponseObject> updateMaterial(Material material);

    ResponseEntity<ResponseObject> deleteMaterial(int id);
}
