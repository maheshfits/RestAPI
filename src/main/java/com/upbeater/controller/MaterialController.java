package com.upbeater.controller;

import com.upbeater.model.dom.Material;
import com.upbeater.service.MaterialService;
import com.upbeater.utility.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://3.124.216.255")
@RestController
@RequestMapping(value = "/rest/materialController")
public class MaterialController {
    private final String APPLICATION_JSON = "application/json";

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @RequestMapping(value = "/getAllMaterials", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @RequestMapping(value = "/createMaterial", method = RequestMethod.POST, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> createMaterial(@RequestBody Material material) {
        return materialService.createMaterial(material);
    }

    @RequestMapping(value = "/updateMaterial", method = RequestMethod.PUT, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> updateMaterial(@RequestBody Material material) {
        return materialService.updateMaterial(material);
    }

    @RequestMapping(value = "/deleteMaterial/{id}", method = RequestMethod.DELETE, produces = {APPLICATION_JSON})
    public ResponseEntity<ResponseObject> deleteMaterial(@PathVariable(value = "id") int id) {
        return materialService.deleteMaterial(id);
    }
}
