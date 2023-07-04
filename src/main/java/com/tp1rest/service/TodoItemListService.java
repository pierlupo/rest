package com.tp1rest.service;

import com.tp1rest.entity.TodoItem;
import com.tp1rest.entity.TodoItemList;
import com.tp1rest.repository.TodoItemListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("api/todoitemlist")
public class TodoItemListService {


    @Autowired
    private TodoItemListRepository todoItemListRepository;


    public TodoItemList createTodoItemList(TodoItemList todoItemList){

        return todoItemListRepository.save(todoItemList);

    }

    public void deleteTodoItemList(Integer id){

        todoItemListRepository.deleteById(id);

    }

    public List<TodoItemList> getAllTodoItemList(){

        return (List<TodoItemList>) todoItemListRepository.findAll();

    }

    public Optional<TodoItemList> getTodoItemListById(Integer id){

        return todoItemListRepository.findById(id);

    }

    public TodoItemList updateTodoItemList(Integer id, TodoItemList todoItemList){
        Optional<TodoItemList> todoItemList1 = todoItemListRepository.findById(id);
        if(todoItemList1.isPresent()){
            TodoItemList todoItemList2 = todoItemList1.get();
            todoItemList2.setTitle(todoItemList.getTitle());
            todoItemList2.setDate(todoItemList.getDate());
            todoItemList2.setComplete(todoItemList.getComplete());
            return todoItemListRepository.save(todoItemList2);
        }
        return null;
    }

    public boolean getTodoItemListByTitle(String title) {

        TodoItemList todoItemList = todoItemListRepository.findTodoItemListByTitle(title);
        return todoItemList != null ? true : false;

    }
}
