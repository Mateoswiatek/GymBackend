package com.example.gym.repository;

import com.example.gym.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

// coś w tym stylu na te logi ile osób na siłowni jest.
// @Query("SELECT u FROM User u WHERE u.state = true")
//    List<User> findAllByStateTrue();

// TODO: 13.03.2024 Zrobić zapytania na wejście i wyjście z siłowni, gdzie przekazujemy nie wiem, jakiś hash czy coś i w tedy odpowiedni user wychodzi / wchodzi
