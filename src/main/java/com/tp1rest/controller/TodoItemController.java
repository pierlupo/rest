package com.tp1rest.controller;



import com.tp1rest.entity.TodoItem;
import com.tp1rest.entity.TodoItemList;
import com.tp1rest.service.TodoItemListService;
import com.tp1rest.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todoitemList/{id}")
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;

    @Autowired
    private TodoItemListService todoItemListService;

    @PostMapping("/created")
    public ResponseEntity<String> post(@PathVariable int id, @RequestBody TodoItem todoItem) {
        TodoItemList todoItemList = todoItemListService.getTodoItemListById(id).get();
        if (todoItemList == null) {
            String message = "Invalid request, TodoItemList does not exist";
            return ResponseEntity.badRequest().body(message);
        }
        todoItemList.getTodoItems().add(todoItem);
        todoItem.setTodoItemList(todoItemList);
        todoItemService.createTodoItem(todoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body("todoItem successfully created");
    }


    @PutMapping("/put")
    public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody TodoItem todoItem){

        if(!todoItemService.getTodoItemById(id).isPresent()){
            String message = "TodoItem does not exist";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        if(todoItem == null || todoItem.getTitle() == null){
            String message = "Invalid request, title required";
            return ResponseEntity.badRequest().body(message);

        }
        todoItemService.updateTodoItem(id, todoItem);
        String message = "TodoItem with the ID : " + id + "has been updated";
        return ResponseEntity.ok(message);
    }


    @GetMapping("/get")
    public ResponseEntity<?> getTodoItemById(@PathVariable("id") Integer id){

        if(!todoItemService.getTodoItemById(id).isPresent()){
            String message = "No todo with this id";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(todoItemService.getTodoItemById(id).get());
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllTodoItems() {
        List<TodoItem> todoItems = todoItemService.getAllTodoItems();
        String message = "Empty list";
        if (todoItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
        } else {
            return ResponseEntity.ok(todoItems);
        }
    }

}
