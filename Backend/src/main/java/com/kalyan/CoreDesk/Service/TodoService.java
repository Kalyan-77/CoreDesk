package com.kalyan.CoreDesk.Service;

import com.kalyan.CoreDesk.DTO.Request.TodoRequestDTO;
import com.kalyan.CoreDesk.DTO.Response.TodoResponseDTO;
import com.kalyan.CoreDesk.Model.Todo;
import com.kalyan.CoreDesk.Model.User;
import com.kalyan.CoreDesk.Repository.TodoRepository;
import com.kalyan.CoreDesk.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoResponseDTO createTodo(Long userId, TodoRequestDTO request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not found"));

        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCreatedAt(LocalDateTime.now());
        todo.setDueDate(request.getDueDate());
        todo.setCompleted(false);
        todo.setUser(user);

        Todo saved = todoRepository.save(todo);

        return mapToDTO(saved);
    }

    public List<TodoResponseDTO> getUserTodos(Long userId){
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return todoRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public String deleteTodo(Long todoId, Long userId){
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo Not Found"));

        if (!todo.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized action");
        }

        if(!todoRepository.existsById(todoId)){
            throw new RuntimeException("Todo Not Found");
        }

        todoRepository.deleteById(todoId);

        return "Todo Successfully Deleted";
    }

    public TodoResponseDTO updateTodo(Long todoId, TodoRequestDTO request){
        Todo existingTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo Not Found"));

        existingTodo.setTitle(request.getTitle());
        existingTodo.setDescription(request.getDescription());
        existingTodo.setDueDate(request.getDueDate());

        Todo Updated = todoRepository.save(existingTodo);

        return mapToDTO(Updated);
    }

    public TodoResponseDTO toggleComplete(Long todoId, Long userId){
        System.out.println("toggleComplete accessed - UserID: " + userId + ", TodoID: " + todoId);
        
        Todo existingTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo Not Found"));

        if (!existingTodo.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized action");
        }

        existingTodo.setCompleted(!existingTodo.isCompleted());
        Todo updated = todoRepository.save(existingTodo);
        return mapToDTO(updated);
    }



    private TodoResponseDTO mapToDTO(Todo todo) {
        return TodoResponseDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .createdAt(todo.getCreatedAt())
                .dueDate(todo.getDueDate())
                .build();
    }
}
