package com.victorp.service;

public interface ChoiceService {
    int choiceYes_NO(int choice) throws Exception;
    int choiceEdit_Add(int choice) throws Exception;
    int choiceNumberToReplace(int positionOfPhoneNumber, int count) throws Exception;
    int choiceRoleToReplace(int positionOfRole, int count) throws Exception;
}
