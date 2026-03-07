package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.TodoRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.TodoResponseDTO;
import com.kalyan.CoreDesk.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/{userId}")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/create")
    public TodoResponseDTO createTodo(@PathVariable Long userId, @RequestBody TodoRequestDTO request){
        return todoService.createTodo(userId, request);
    }

    @GetMapping("/list")
    public List<TodoResponseDTO> getUserTodos(@PathVariable Long userId){
        return todoService.getUserTodos(userId);
    }

    @DeleteMapping("/delete/{todoId}")
    public String deleteTodo(@PathVariable Long todoId, @PathVariable Long userId){
        return todoService.deleteTodo(todoId, userId);
    }

    @PutMapping("/update/{todoId}")
    public TodoResponseDTO updateTodo(@PathVariable Long todoId, @PathVariable Long userId, @RequestBody TodoRequestDTO request){
        return todoService.updateTodo(todoId, request);
    }

    @PatchMapping("/toggleComplete/{todoId}")
    public TodoResponseDTO toggleComplete(@PathVariable Long todoId, @PathVariable Long userId){
        return todoService.toggleComplete(todoId, userId);
    }

}
