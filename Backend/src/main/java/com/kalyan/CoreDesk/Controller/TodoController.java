package com.kalyan.CoreDesk.Controller;

import com.kalyan.CoreDesk.DTO.Request.TodoRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.TodoResponseDTO;
import com.kalyan.CoreDesk.Service.TodoService;
import com.kalyan.CoreDesk.Utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/todo/{userId}")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/create")
    public TodoResponseDTO createTodo(@PathVariable Long userId, @RequestBody TodoRequestDTO request, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return todoService.createTodo(userId, request);
    }

    @GetMapping("/list")
    public List<TodoResponseDTO> getUserTodos(@PathVariable Long userId, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return todoService.getUserTodos(userId);
    }

    @DeleteMapping("/delete/{todoId}")
    public String deleteTodo(@PathVariable("todoId") Long todoId, @PathVariable("userId") Long userId, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return todoService.deleteTodo(todoId, userId);
    }

    @PutMapping("/update/{todoId}")
    public TodoResponseDTO updateTodo(@PathVariable("todoId") Long todoId, @PathVariable("userId") Long userId, @RequestBody TodoRequestDTO request, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return todoService.updateTodo(todoId, request);
    }

    @PatchMapping("/toggleComplete/{todoId}")
    public TodoResponseDTO toggleComplete(@PathVariable("todoId") Long todoId, @PathVariable("userId") Long userId, HttpSession session){
        SessionUtil.verifyOwnership(userId, session);
        return todoService.toggleComplete(todoId, userId);
    }

}
