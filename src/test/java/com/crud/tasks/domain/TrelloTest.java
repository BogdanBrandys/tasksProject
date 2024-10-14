package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloTest {

    @Test
    void shouldReturnBoard() {
        //Given
        Trello trello = new Trello(1, 1);
        //When
        int resultBoard = trello.getBoard();
        //Then
        assertEquals(1,resultBoard);
    }

    @Test
    void shouldReturnCard() {
        //Given
        Trello trello = new Trello(1, 1);
        //When
        int resultCard = trello.getCard();
        //Then
        assertEquals(1,resultCard);
    }
    @Test
    void shouldSetCard() {
        //Given
        Trello trello = new Trello(1,1);
        //When
        trello.setCard(2);
        int resultCard = trello.getCard();
        //Then
        assertEquals(2,resultCard);
    }
}