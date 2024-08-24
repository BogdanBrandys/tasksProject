package com.crud.tasks.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortingTask {
    public List<Integer> sortIntegers(List<Integer> list) {
        List<Integer> sortedNumbers = list.stream()
                .sorted()
                .collect(Collectors.toList());
        return sortedNumbers;
    }
}
