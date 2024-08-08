package com.crud.tasks;

import com.crud.tasks.domain.AverageW;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Average Weighted Test Suite")
public class AverageWTestSuite {


    @Test
    void testGradeOverLimit(){
        //Given
        List<Integer> grades = List.of(3,7,1,5,6,4);
        List<Integer> wages = List.of(4,6,8,4,4,10);
        //When
        AverageW averageW = new AverageW();
        double result = averageW.calculateAverageW(grades,wages);
        //Then
        assertEquals(3.466666666666667,result);
    }
    @Test
    void testWageOverLimit(){
        //Given
        List<Integer> grades = List.of(3,1,1,5,6,4);
        List<Integer> wages = List.of(4,11,8,4,4,10);
        //When
        AverageW averageW = new AverageW();
        double result = averageW.calculateAverageW(grades,wages);
        //Then
        assertEquals(3.466666666666667,result);
    }
    @Test
    void testDifferentCollectionSizes(){
        //Given
        List<Integer> grades = List.of(3,1,1,5,6,4);
        List<Integer> wages = List.of(4,11,8,4,4);
        //When
        AverageW averageW = new AverageW();
        double result = averageW.calculateAverageW(grades,wages);
        //Then
        assertEquals(0.0,result);
    }
    @Test
    void testCollectionSizeZero(){
        //Given
        List<Integer> grades = List.of();
        List<Integer> wages = List.of(4,11,8,4,4);
        //When
        AverageW averageW = new AverageW();
        double result = averageW.calculateAverageW(grades,wages);
        //Then
        assertEquals(0.0,result);
    }
}
