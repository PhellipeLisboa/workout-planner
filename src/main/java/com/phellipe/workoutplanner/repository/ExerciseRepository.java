package com.phellipe.workoutplanner.repository;

import com.phellipe.workoutplanner.entity.Equipment;
import com.phellipe.workoutplanner.entity.Exercise;
import com.phellipe.workoutplanner.entity.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByMuscleGroupsContaining(MuscleGroup muscleGroup);

    List<Exercise> findByNameContainingIgnoreCase(String keyword);

    List<Exercise> findByMuscleGroupsContainingAndEquipment(MuscleGroup muscleGroup, Equipment equipment);

}
