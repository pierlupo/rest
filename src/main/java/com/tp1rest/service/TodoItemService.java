package com.tp1rest.service;


import com.tp1rest.entity.TodoItem;
import com.tp1rest.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;


    public TodoItem createTodoItem(TodoItem todoItem){

        return todoItemRepository.save(todoItem);

    }

    public void deleteTodoItem(Integer id){

        todoItemRepository.deleteById(id);

    }

    public TodoItem updateTodoItem(Integer id, TodoItem todoItem){
        Optional<TodoItem> todoItem1 = todoItemRepository.findById(id);
        if(todoItem1.isPresent()){
            TodoItem todoItem2 = todoItem1.get();
            todoItem2.setTitle(todoItem.getTitle());
            return todoItemRepository.save(todoItem2);
        }
        return null;
    }

    public List<TodoItem> getAllTodoItems(){

        return (List<TodoItem>) todoItemRepository.findAll();

    }

    public Optional<TodoItem> getTodoItemById(Integer id){

        return todoItemRepository.findById(id);

    }

    public boolean getTodoItemByTitle(String title) {

        TodoItem todoItem = todoItemRepository.findTodoItemByTitle(title);
        return todoItem != null ? true : false;

    }
}
