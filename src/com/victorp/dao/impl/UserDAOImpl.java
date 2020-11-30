package com.victorp.dao.impl;

import com.victorp.dao.UserDAO;
import com.victorp.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static UserDAO userDAO;

    public UserDAOImpl() {
    }

    public static UserDAO getInstance() {
        if (userDAO == null) {
            synchronized (UserDAOImpl.class) {
                if (userDAO == null) {
                    userDAO = new UserDAOImpl();
                }
            }
        }
        return userDAO;
    }


    @Override
    public void write(List<User> users) throws Exception {

        try {
            FileOutputStream f = new FileOutputStream("./resources/users.txt");
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(users);

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

    }

    @Override
    public List<User> read() throws Exception {
        List<User> users = new ArrayList<>();
        File file = new File("./resources/users.txt");

        boolean empty = file.exists() && file.length() == 0;
        if (empty) {
            return users;
        } else {
            try {
                FileInputStream fi = new FileInputStream("./resources/users.txt");
                ObjectInputStream oi = new ObjectInputStream(fi);

                users = (List<User>) oi.readObject();

                oi.close();
                fi.close();

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return users;
        }
    }

    @Override
    public User findByLastName(String userLastName) throws Exception {
        List<User> userList;
        userList = read();
        User findUser = userList.stream().filter(user -> userLastName.equals(user.getLast_name())).findAny().orElse(null);
        if (findUser == null) {
            System.out.println("There is no such user! Check that the entered data is correct and try again");
        }
        return findUser;
    }

    @Override
    public User view(String userLastName) throws Exception {
        User user = findByLastName(userLastName);
        System.out.println(user);
        return user;
    }

    @Override
    public List<User> viewAll() throws Exception {
        List<User> userList = new ArrayList<>();
        userList = read();

        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    @Override
    public void delete(String userLastName) throws Exception {
        List<User> userList;
        userList = read();
        userList.removeIf(user -> user.getLast_name().equals(userLastName));
        write(userList);

    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> userList = new ArrayList<>();
        userList = read();
        return userList;
    }
}
