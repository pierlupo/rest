package com.tp1rest.repository;


import com.tp1rest.entity.TodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends CrudRepository<TodoItem, Integer> {

    TodoItem findTodoItemById(Integer id);
    TodoItem findTodoItemByTitle(String title);

}
