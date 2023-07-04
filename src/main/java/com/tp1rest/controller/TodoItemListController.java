package com.tp1rest.controller;


import com.tp1rest.entity.TodoItemList;
import com.tp1rest.repository.TodoItemRepository;
import com.tp1rest.service.TodoItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("todoitemlist")
public class TodoItemListController {

    @Autowired
    private TodoItemListService todoItemListService;


    @PostMapping("created")
    public ResponseEntity<String> post(@RequestBody TodoItemList todoItemList) {

        if (todoItemListService.getTodoItemListByTitle(todoItemList.getTitle())) {
            String errorMessage = "title already exists";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }

        if (todoItemList == null || todoItemList.getTitle() == null) {
            String message = "Invalid request, title required";
            return ResponseEntity.badRequest().body(message);
        }


        todoItemListService.createTodoItemList(todoItemList);
        return ResponseEntity.status(HttpStatus.CREATED).body("todoItemList successfully created");
    }

    @GetMapping("getList/{id}")
    public ResponseEntity<?> getTodoItemListById(@PathVariable("id") Integer id){

        if(!todoItemListService.getTodoItemListById(id).isPresent()){
            String message = "No list with this id";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(todoItemListService.getTodoItemListById(id).get());
    }

    @PutMapping("put/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id,@RequestBody TodoItemList todoItemList){

        if(!todoItemListService.getTodoItemListById(id).isPresent()){
            String message = "List does not exist";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        if(todoItemList == null || todoItemList.getTitle() == null){
            String message = "Invalid request : list required";
            return ResponseEntity.badRequest().body(message);

        }
        todoItemListService.updateTodoItemList(id, todoItemList);
        String message = "List with the id : " + id + "updated";
        return ResponseEntity.ok(message);
    }
    @GetMapping("all")
    public ResponseEntity<?> getAllLists() {
        List<TodoItemList> lists = todoItemListService.getAllTodoItemList();
        String message = "empty list";
        if (lists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
        } else {
            return ResponseEntity.ok(lists);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        if(todoItemListService.getTodoItemListById(id).isPresent()){
            String message = "No list with this id";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        todoItemListService.deleteTodoItemList(id);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/api/todoitemlist/all")).build();
    }
}
