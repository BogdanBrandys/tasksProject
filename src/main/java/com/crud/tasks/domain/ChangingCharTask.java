package com.crud.tasks.domain;

public class ChangingCharTask {
    public int changeCharToInteger(char ch) throws NumberFormatException{
        try{
            if (ch >= '0' && ch <= '9') {
                return ch - '0';
            } else {
                throw new NumberFormatException(ch + " is not a valid character");
            }
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
