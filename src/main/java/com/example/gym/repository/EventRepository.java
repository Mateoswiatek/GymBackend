package com.example.gym.repository;

import com.example.gym.repository.entity.Event;

import com.example.gym.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
//    @Query("select e from Event e where e.date between :date1 and :date2")
//    List<Event> findAllByDateBetween(@Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);
    List<Event> findAllByStartDateBetween(LocalDateTime from, LocalDateTime to);

    @Query("select e from Event e left join fetch e.trainer where e.id = :id")
    Optional<Event> findByIdWithTrainer(Long id);
    List<Event> findAllByTitle(String title);
    List<Event> findAllByTrainerId(Long id, Pageable pageable);

    // https://vladmihalcea.com/join-fetch-pagination-spring/
    // Takie obejście, bo w tym eventIds jest juz pageable.
    // bo N+1 naturalnie rozwiazujemy poprzez left join fetch, ale to sie gryzie z pageable. takie rozwiazanie
    // ale musimy pobierac bezposrednio liste id.
    @Query("select e from Event e left join fetch e.trainer where e.id in :eventIds")
    List<Event> findAllByIdWithTrainer(List<Long> eventIds);

    // do pobierania razem z trenerem.
    @Query("select e.id from Event e")
    List<Long> findAllEventIds(Pageable pageable);

    @Query("select e.participants from Event e where e.id = :eventId")
    List<User> findParticipantsByEventId(Long eventId);

}
