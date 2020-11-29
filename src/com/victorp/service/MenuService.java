package com.victorp.service;

import com.victorp.entity.User;

import java.util.List;

public interface MenuService {

    void printOptions() throws Exception;
    int makeChoice() throws Exception;
    boolean processingOperation(int choice);

}
