package com.victorp.service;

import com.victorp.entity.User;

import java.util.List;

public interface UserService {

    void create() throws Exception;
    void edit(String userLastName) throws Exception;
    User view(String userLastName) throws Exception;
    List<User> viewAll() throws Exception;
    void delete(String userLastName) throws Exception;
    List<User> findAll() throws Exception;
}
