package com.example.homeassignment.services;

import com.example.homeassignment.entities.Task;
import com.example.homeassignment.repositories.TaskRepository;
import com.example.homeassignment.requests.TaskRequest;
import com.example.homeassignment.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void insertTask(TaskRequest taskRequest) {
        taskRepository.save(
                new Task(
                        null,
                        taskRequest.getTitle(),
                        taskRequest.getDescription(),
                        taskRequest.getCompleted()
                )
        );
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Task.class, "id", id.toString()));
    }

    public void updateTaskById(Long id, TaskRequest taskRequest) {

        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException(Task.class, "id", id.toString());
        }

        taskRepository.setTaskById(id,
                taskRequest.getTitle(),
                taskRequest.getDescription(),
                taskRequest.getCompleted());
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException(Task.class, "id", id.toString());
        }

        taskRepository.deleteById(id);
    }


}
