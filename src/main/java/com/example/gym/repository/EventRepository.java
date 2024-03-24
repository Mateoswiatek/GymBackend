package com.example.gym.repository;

import com.example.gym.repository.entity.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
//    @Query("select e from Event e where e.date between :date1 and :date2")
//    List<Event> findAllByDateBetween(@Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);
    List<Event> findAllByStartDateBetween(LocalDateTime from, LocalDateTime to);

    List<Event> findAllByTitle(String title);
    List<Event> findAllByTrainerId(Long id, Pageable pageable);
}
