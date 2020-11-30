package com.victorp.service;

public interface MenuService {

    void printOptions() throws Exception;
    int makeChoice() throws Exception;
    boolean processingOperation(int choice);

}
