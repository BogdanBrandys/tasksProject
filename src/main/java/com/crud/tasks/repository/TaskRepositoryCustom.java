package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.stereotype.Repository;

public interface TaskRepositoryCustom {
    Task findTaskById(Long id);
}
