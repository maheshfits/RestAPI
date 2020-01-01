package com.upbeater.repository;

import com.upbeater.model.dom.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByStatusNot(int status);

    User findUsersByIdAndStatusNot(int id, int status);

    User findUserById(int id);
}
