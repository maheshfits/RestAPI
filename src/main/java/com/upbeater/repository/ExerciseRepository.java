package com.upbeater.repository;

import com.upbeater.model.dom.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise> findExercisesByTaskIdAndStatusNot(int taskId, int satatus);

    Exercise findExercisesByExerciseIdAndStatusNot(int exerciseId, int satatus);
}
