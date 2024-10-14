package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class TrelloValidatorTest {
    @Autowired
    TrelloValidator trelloValidator;
    private LogCaptor logCaptor;

    @BeforeEach
    void setUp() {
        trelloValidator = new TrelloValidator();
        logCaptor = LogCaptor.forClass(TrelloValidator.class);
    }

    @Test
    public void testFirstScenarioCardValidator() {
        // Given
        TrelloCard trelloCard = new TrelloCard("test", "Description", "pos", "1");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        assertTrue(logCaptor.getInfoLogs().contains("Someone is testing my application!"));
    }
    @Test
    public void testSecondScenarioCardValidator() {
        // Given
        TrelloCard trelloCard = new TrelloCard("card", "Description", "pos", "1");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        assertTrue(logCaptor.getInfoLogs().contains("Seems that my application is used in proper way."));
    }
    @Test
    public void shouldReturnBoardsListWithValue() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        List<TrelloList> list = List.of(new TrelloList("1", "List", false));
        TrelloBoard board1 = new TrelloBoard("1", "Board", list);
        trelloBoardList.add(board1);
        TrelloBoard board2 = new TrelloBoard("2", "Board", list);
        trelloBoardList.add(board2);
        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoardList);
        //Then
        assertEquals(2, resultList.size());
        assertEquals("2", resultList.get(1).getId());
    }
    @Test
    public void shouldReturnEmptyBoardsList() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        List<TrelloList> list = List.of(new TrelloList("1", "List", false));
        TrelloBoard board1 = new TrelloBoard("1", "Test", list);
        trelloBoardList.add(board1);
        TrelloBoard board2 = new TrelloBoard("2", "teSt", list);
        trelloBoardList.add(board2);
        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoardList);
        //Then
        assertEquals(0, resultList.size());
    }
}