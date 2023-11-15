package com.example.homeassignment.controllers;

import com.example.homeassignment.requests.TaskRequest;
import com.example.homeassignment.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
//@RequestMapping("api/v1/")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("tasks")
    public ResponseEntity<?> getTasks() {
        return new ResponseEntity<>(taskService.getTasks(), HttpStatus.OK);
    }

    @PostMapping("tasks")
    public ResponseEntity<?> insertTask(@RequestBody @Valid TaskRequest shiftRequest) {
        taskService.insertTask(shiftRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable(required = false) Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(required = false) Long id,
                                         @RequestBody @Valid TaskRequest taskRequest) {
        taskService.updateTaskById(id, taskRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(required = false) Long id) throws MissingPathVariableException {
        if (id == null) {
            throw new MissingPathVariableException("id", null);
        }
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
