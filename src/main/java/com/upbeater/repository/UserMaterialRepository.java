package com.upbeater.repository;

import com.upbeater.model.dom.UserMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserMaterialRepository extends JpaRepository<UserMaterial, Integer> {

    List<UserMaterial> findAllByUserIdAndStatusNot(int id, int status);


}
