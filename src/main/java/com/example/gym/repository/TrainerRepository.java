package com.example.gym.repository;

import com.example.gym.repository.entity.Event;
import com.example.gym.repository.entity.Trainer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
//    @Query("select t from Trainer t left join fetch t.events") // określamy co dociągamy, ale problem jest, gdy mamy Pageable,
        // bo gdy jest join, to nie wiadomo ile będzie rekordów, dlatego tego nie możemy użyć. rozwiązaniem jest
        // stworzenie metody która pobiera trenerów, a potem wszystkie eventy
//    List<Trainer> findAllTrainers();

    @Query("select t from Trainer t order by :sort")
    List<Trainer> findAllTrainers(Pageable page); //

    Long countAllByInGymIsTrue();

}
