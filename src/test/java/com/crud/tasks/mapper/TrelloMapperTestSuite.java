package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("01", "list_01", false));
        trelloBoardDto.add(new TrelloBoardDto("01", "board_01", trelloListDto));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        assertEquals("board_01", trelloBoards.get(0).getName());
        assertEquals("01", trelloBoards.get(0).getId());
        assertEquals(1, trelloBoards.get(0).getLists().size());

    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> lists = new ArrayList<>();
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        lists.add(new TrelloList("01", "list_01", false));
        trelloBoards.add(new TrelloBoard("01", "board_01", lists));

        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals("01", trelloBoardDto.get(0).getId());
        assertEquals("board_01", trelloBoardDto.get(0).getName());
        assertEquals(1, trelloBoardDto.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("01", "list_01", false));

        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals("01", trelloList.get(0).getId());
        assertEquals("list_01", trelloList.get(0).getName());
        assertEquals(1, trelloList.size());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("01", "list_01", false));

        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals("01", trelloListDto.get(0).getId());
        assertEquals("list_01", trelloListDto.get(0).getName());
        assertEquals(1, trelloListDto.size());

    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name_01", "desc_01", "pos_01", "01");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("name_01", trelloCard.getName());
        assertEquals("desc_01", trelloCard.getDescription());
        assertEquals("pos_01", trelloCard.getPos());
        assertEquals("01", trelloCard.getListId());

    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name_01", "desc_01", "pos_01", "01");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("name_01", trelloCardDto.getName());
        assertEquals("desc_01", trelloCardDto.getDescription());
        assertEquals("pos_01", trelloCardDto.getPos());
        assertEquals("01", trelloCardDto.getListId());

    }
}
