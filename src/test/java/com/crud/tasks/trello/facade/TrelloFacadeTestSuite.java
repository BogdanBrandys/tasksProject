package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloValidator trelloValidator;

    @Test
    public void shouldFetchTrelloBoards(){
        //Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[2];
        trelloBoards[0] = new TrelloBoardDto("test_id", "Kodilla", new ArrayList<>());
        trelloBoards[1] = new TrelloBoardDto("test_id2", "Kodilla2", new ArrayList<>());

        when(trelloFacade.fetchTrelloBoards()).thenReturn(List.of(trelloBoards));
        //When
        List<TrelloBoardDto> resultTrelloBoards = trelloFacade.fetchTrelloBoards();
        //Then
        assertEquals(2, resultTrelloBoards.size());
        verify(trelloService, times(2)).fetchTrelloBoards();
        verify(trelloMapper, times(2)).mapToBoards(anyList());
        verify(trelloValidator, times(2)).validateTrelloBoards(anyList());
    }
    @Test
    public void shouldCreateTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Fasade test task",
                "Test Description",
                "top",
                "test_id");
        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto(
                "1",
                "Facade test task",
                "http://test.com",
                null);
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCard);
        //When
        CreatedTrelloCardDto createdCard = trelloFacade.createCard(trelloCardDto);
        //Then
        assertEquals("1", createdCard.getId());
        assertEquals("Facade test task", createdCard.getName());
        assertEquals("http://test.com", createdCard.getShortUrl());
    }
}
