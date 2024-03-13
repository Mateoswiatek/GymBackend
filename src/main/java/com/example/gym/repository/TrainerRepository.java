package com.example.gym.repository;

import com.example.gym.repository.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    @Query("select t from Trainer t " +
            "left join fetch t.events")
    List<Trainer> findAllTrainers();
}
