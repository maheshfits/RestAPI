package com.upbeater.repository;

import com.upbeater.model.dom.UserExercises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserExerciseRepository extends JpaRepository<UserExercises, Integer> {

    UserExercises findUserExercisesByUserIdAndExerciseIdAndStatusNot(int userId, int exerciseId, int status);
}
