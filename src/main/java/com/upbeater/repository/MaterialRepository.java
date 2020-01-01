package com.upbeater.repository;

import com.upbeater.model.dom.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findAllByStatusNot(int status);

    Material findMaterialByIdAndStatusNot(int id, int status);
}
