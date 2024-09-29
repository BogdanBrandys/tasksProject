package com.crud.tasks.domain;

public class ChangingCharTaskPro {
    public int changeStringToInteger(String yourString){
        int numberOfCharacters = yourString.length();
        int multiplier = 1;
        int temporaryNumber = 0;
        int result = 0;
            for (int i = numberOfCharacters; i > 0; i--) {
                try {
                temporaryNumber = changeCharToInteger(yourString.charAt(i - 1));
                result += temporaryNumber * multiplier;
                multiplier *= 10;
                } catch (NumberFormatException e) {
                    System.out.println("Błąd: " + e.getMessage());
            }
        }
        return result;
    }
        private int changeCharToInteger(char ch) throws NumberFormatException{
                if (ch >= '0' && ch <= '9') {
                    return ch - '0';
                } else {
                    throw new NumberFormatException(ch + " is not a valid character");
                }
        }
    }
