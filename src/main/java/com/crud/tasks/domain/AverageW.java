package com.crud.tasks.domain;

import java.util.List;

public class AverageW {
    public double calculateAverageW (List<Integer> grades, List<Integer> wages) {
        double sumOfGradesXWages = 0;
        double sumOfWages = 0;
        int numberOfGrades = grades.size();
        int numberOfWages = wages.size();
        if (numberOfGrades == 0 || numberOfWages == 0) {
            return 0.0;
        }
        if (numberOfGrades == numberOfWages) {
            for (int i = 0; i < grades.size(); i++) {
                if (grades.get(i) < 7 && wages.get(i) < 11) {
                    sumOfGradesXWages += grades.get(i) * wages.get(i);
                    sumOfWages += wages.get(i);
                } else {
                    System.out.println("skipped value: " + (i + 1));
                }
            }
        } else{
            System.out.println("Your lists have different sizes");
        return 0.0;
    }
        return sumOfGradesXWages/(double)sumOfWages;
    }
}
