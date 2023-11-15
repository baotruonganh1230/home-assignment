package com.example.homeassignment.repositories;

import com.example.homeassignment.entities.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Transactional
    @Modifying
    @Query(value = "update task set title = ?2, description = ?3, completed = ?4 where id = ?1", nativeQuery = true)
    void setTaskById(Long id, String title, String description, Boolean completed);

    @Transactional
    @Modifying
    @Query(value = "delete from task where id = ?1", nativeQuery = true)
    void deleteById(Long id);
}
