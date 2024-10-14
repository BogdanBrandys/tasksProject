package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloBoardDtoTest {
    @Test
    public void shouldGetValues() {
        //Given
        List<TrelloListDto> trelloListList = List.of(new TrelloListDto("1","List",false));
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","Board",trelloListList);
        //When
        String resultId = trelloBoardDto.getId();
        String resultName = trelloBoardDto.getName();
        List<TrelloListDto> resultList = trelloBoardDto.getLists();
        //Then
        assertEquals("1",resultId);
        assertEquals("Board",resultName);
        assertEquals(1,resultList.size());
    }
    @Test
    public void shouldSetValues() {
        //Given
        List<TrelloListDto> trelloListList = List.of(new TrelloListDto("1","List",false));
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","Board",trelloListList);
        //When
        //set new values
        trelloBoardDto.setId("2");
        trelloBoardDto.setName("new Board");
        trelloBoardDto.setLists(List.of(new TrelloListDto("2","new List",false)));
        //get new values
        String resultId = trelloBoardDto.getId();
        String resultName = trelloBoardDto.getName();
        List<TrelloListDto> resultList = trelloBoardDto.getLists();
        //Then
        assertEquals("2",resultId);
        assertEquals("new Board",resultName);
        assertEquals("new List",trelloBoardDto.getLists().get(0).getName());
    }

}