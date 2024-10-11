package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
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
        trelloBoardsDto.add(new TrelloBoardDto("1", "list", trelloListsDto));
        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "list", false));
        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "list", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDto);
        when(trelloMapper.mapToBoards(trelloBoardsDto)).thenReturn(mappedTrelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(mappedTrelloBoards)).thenReturn(trelloBoardsDto);

        // When
        List<TrelloBoardDto> resultList = trelloFacade.fetchTrelloBoards();

        InOrder inOrder = inOrder(trelloService, trelloMapper, trelloValidator);

        inOrder.verify(trelloService).fetchTrelloBoards();
        inOrder.verify(trelloMapper).mapToBoards(trelloBoardsDto);
        inOrder.verify(trelloValidator).validateTrelloBoards(mappedTrelloBoards);
        inOrder.verify(trelloMapper).mapToBoardsDto(mappedTrelloBoards);

        // Then
        assertNotNull(resultList);
        assertEquals(0, resultList.size());

        resultList.forEach(trelloBoardDto -> {

            assertEquals("1", trelloBoardDto.getId());
            assertEquals("list", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("list", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });
    }
}
