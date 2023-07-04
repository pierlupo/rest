package com.tp1rest.repository;

import com.tp1rest.entity.TodoItemList;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemListRepository extends CrudRepository<TodoItemList, Integer> {

    TodoItemList findTodoItemListByTitle(String title);
}
