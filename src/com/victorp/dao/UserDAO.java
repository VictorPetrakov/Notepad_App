package com.victorp.dao;

import com.victorp.entity.User;

import java.util.List;

public interface UserDAO {
    void write(List<User> users) throws Exception;
    List<User> read() throws Exception;
    void edit(String userLastName) throws Exception;
    User findByLastName(String userLastName) throws Exception;
    User view(String userLastName) throws Exception;
    List<User> viewAll() throws Exception;
    void delete(String userLastName) throws Exception;
    List<User> findAll() throws Exception;
}
