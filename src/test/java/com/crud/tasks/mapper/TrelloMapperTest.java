package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;
    @Test
    public void mapToBoardsTest(){
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "List1", false);
        trelloListDtos.add(trelloListDto);
        List<TrelloBoardDto> listTrelloBoardDto = new ArrayList<>();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1","Board1", trelloListDtos);
        listTrelloBoardDto.add(trelloBoardDto1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2","Board2",trelloListDtos);
        listTrelloBoardDto.add(trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(listTrelloBoardDto);
        //Then
        assertNotNull(trelloBoards);
        assertEquals(listTrelloBoardDto.size(),trelloBoards.size());
        assertEquals("Board2",trelloBoards.get(1).getName());
    }
    @Test
    public void mapToBoardsDtoTest(){
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "List1", false);
        trelloList.add(trelloList1);
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "List1", trelloList);
        trelloBoard.add(trelloBoard1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "List2", trelloList);
        trelloBoard.add(trelloBoard2);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoard);
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(trelloBoardDtos.size(),trelloBoard.size());
        assertEquals("List1",trelloBoardDtos.get(0).getName());
    }
    @Test
    public void mapToListTest(){
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "List1", false);
        trelloListDtos.add(trelloListDto);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertNotNull(trelloLists);
        assertEquals(trelloListDtos.size(),trelloLists.size());
        assertEquals("List1",trelloListDtos.get(0).getName());
    }
    @Test
    public void mapToListDtoTest(){
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "List1", false);
        trelloList.add(trelloList1);
        TrelloList trelloList2 = new TrelloList("2", "List2", false);
        trelloList.add(trelloList2);
        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloList);
        //Then
        assertNotNull(trelloList2);
        assertEquals(trelloListDtoList.size(),trelloList.size());
        assertEquals("List2",trelloListDtoList.get(1).getName());
    }
    @Test
    public void mapToCardDtoTest(){
        //Given
        TrelloCard trelloCard = new TrelloCard("1", "Card1", "top", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertNotNull(trelloCardDto);
        assertEquals("Card1",trelloCardDto.getDescription());
    }
    @Test
    public void mapToCardTest(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("2", "Card2", "top", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertNotNull(trelloCard);
        assertEquals("2",trelloCard.getName());
    }

}