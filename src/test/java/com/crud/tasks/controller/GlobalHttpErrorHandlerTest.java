package com.crud.tasks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebMvcTest(GlobalHttpErrorHandler.class)
class GlobalHttpErrorHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnBadRequestWhenTaskNotFoundExceptionIsThrown() throws Exception {
        TaskNotFoundException exception = new TaskNotFoundException();
        ResponseEntity<Object> response = new GlobalHttpErrorHandler().handleTaskNotFoundException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Task with given id doesn't exist");

    }
}