package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Repository
public class TaskRepositoryCustomImpl implements TaskRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Task findTaskById(Long id) {
        Task task = entityManager.find(Task.class, id);
        if (task == null) {
            throw new EntityNotFoundException("Task not found with id " + id);
        }
        return task;
    }
}
