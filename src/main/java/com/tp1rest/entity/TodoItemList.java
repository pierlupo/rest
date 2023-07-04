package com.tp1rest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "todo_item_list")
public class TodoItemList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_item_list_id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "complete", nullable = false)
    private Boolean complete = false;

    @OneToMany(mappedBy = "todoItemList")
    private Set<TodoItem> todoItems = new LinkedHashSet<>();

}