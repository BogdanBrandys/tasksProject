package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    @Test
    public void shouldUpdateTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        CreatedTrelloCardDto updatedCard = new CreatedTrelloCardDto();
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(updatedCard);
        //When
        CreatedTrelloCardDto createdEmptyCard = trelloFacade.createCard(trelloCardDto);
        //Then
        assertNull(null, createdEmptyCard.getName());
    }
    @Test
    void shouldFetchEmptyList() {
        // Given

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        // Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }
    @Test
    public void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "list", false));
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("1", "Kodilla", trelloListsDto));
        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "list", false));
        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "Kodilla", mappedTrelloLists));

        // When
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDto);
        when(trelloMapper.mapToBoards(trelloBoardsDto)).thenReturn(mappedTrelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(mappedTrelloBoards)).thenReturn(trelloBoardsDto);

        List<TrelloBoardDto> resultList = trelloFacade.fetchTrelloBoards();
        // Then
        assertNotNull(resultList);
        assertEquals(1, resultList.size());

        resultList.forEach(trelloBoardDto -> {

            assertEquals("1", trelloBoardDto.getId());
            assertEquals("Kodilla", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("list", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });
    }
}
