package com.upbeater.repository;

import com.upbeater.model.dom.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    List<UserType> findAllByStatusNot(int status);

    UserType findUserTypesById(int id);

    UserType findUserTypesByNameAndStatusNot(String name, int status);
}
