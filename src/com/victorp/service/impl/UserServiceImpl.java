package com.victorp.service.impl;

import com.victorp.dao.UserDAO;
import com.victorp.dao.impl.UserDAOImpl;
import com.victorp.entity.User;
import com.victorp.service.ChoiceService;
import com.victorp.service.UserService;
import com.victorp.validator.Validator;
import com.victorp.validator.impl.ValidatorImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    UserDAO userDAO = new UserDAOImpl();
    ChoiceService choiceService = new ChoiceServiceImpl();

    @Override
    public void create() throws Exception {
        User user = new User();
        List<User> users = new ArrayList<>();
        Validator valid = new ValidatorImpl();
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter your user information");
        System.out.println("Enter FirstName: ");
        user.setFirst_name(in.nextLine());
        System.out.println("Enter LastName: ");
        user.setLast_name(in.nextLine());
        System.out.println("Enter email: ");
        boolean correctEmail = false;
        while (correctEmail == false) {
            String str = in.nextLine();
            if (valid.checkEmail(str)) {
                user.setEmail(str);
                correctEmail = true;
            } else {
                System.out.println("incorrect email format, try again!");
                System.out.println("Enter email: ");
            }
        }
        System.out.println("Enter PhoneNumber: ");
        boolean correctPhone = false;
        while (correctPhone == false) {
            String str = in.nextLine();
            if (str.trim().isEmpty()) {
                System.out.println("You must enter at least 1 phone number! Try again!");
                System.out.println("Enter PhoneNumber: ");
            } else {
                if (valid.checkPhoneNumber(str)) {
                    if (user.getPhone_numbers().size() < 3) {
                        user.setPhone_numbers(str);
                        System.out.println("Enter another phone number? ");
                        System.out.println("Yes-1/No-2");
                        int choice = 0;
                        choice = choiceService.choiceYes_NO(choice);
                        switch (choice) {
                            case 1:
                                System.out.println("Enter PhoneNumber: ");
                                correctPhone = false;
                                break;
                            case 2:
                                correctPhone = true;
                                break;
                        }
                    } else {
                        System.out.println("There should be no more than 3 phone numbers. If you want to change existing phone numbers, use the Edit User function in the main menu");
                        correctPhone = true;
                    }
                } else {
                    System.out.println("incorrect phone number format, try again!");
                    System.out.println("Enter PhoneNumber: ");
                }
            }
        }
        System.out.println("Enter role: ");
        boolean role = false;
        String strRole = in.nextLine();
        while (role == false) {
            if (user.getRoles().size() < 3) {
                user.setRoles(strRole);
                System.out.println("Enter another role? ");
                System.out.println("Yes-1/No-2");
                int choice = 0;
                choice = choiceService.choiceYes_NO(choice);
                switch (choice) {
                    case 1:
                        System.out.println("Enter role: ");
                        role = false;
                        break;
                    case 2:
                        role = true;
                        break;
                }
            } else {
                System.out.println("There should be no more than 3 roles. If you want to change existing roles, use the Edit User function in the main menu");
                role = true;
            }
        }
        if (userDAO.read().isEmpty()) {
            users.add(user);
            userDAO.write(users);
        } else {
            users = userDAO.read();
            users.add(user);
            userDAO.write(users);
        }
        System.out.println("the user is stored");
    }

    @Override
    public void edit(String userLastName) throws Exception {
        User userForEdit = view(userLastName);
        if (userForEdit != null) {
            List<User> users;
            Validator valid = new ValidatorImpl();
            Scanner in = new Scanner(System.in);

            System.out.println("Please enter new user information");
            System.out.println("Edit FirstName: " + userForEdit.getFirst_name());
            String firstName = in.nextLine();
            if (firstName.isEmpty()) {
                userForEdit.setFirst_name(userForEdit.getFirst_name());
            } else {
                userForEdit.setFirst_name(firstName);
            }
            System.out.println("Edit LastName: " + userForEdit.getLast_name());
            String lastName = in.nextLine();
            if (lastName.isEmpty()) {
                userForEdit.setLast_name(userForEdit.getLast_name());
            } else {
                userForEdit.setLast_name(lastName);
            }
            System.out.println("Edit email: " + userForEdit.getEmail());
            boolean correctEmail = false;
            while (correctEmail == false) {
                String str = in.nextLine();
                if (str.isEmpty()) {
                    userForEdit.setEmail(userForEdit.getEmail());
                    correctEmail = true;
                } else {
                    if (valid.checkEmail(str)) {
                        userForEdit.setEmail(str);
                        correctEmail = true;
                    } else {
                        System.out.println("incorrect email format, try again!");
                        System.out.println("Enter email: ");
                    }
                }
            }
            System.out.println("Edit or enter PhoneNumber: " + userForEdit.getPhone_numbers());
            boolean correctPhone = false;
            while (correctPhone == false) {
                String strPhone = in.nextLine();
                if (valid.checkPhoneNumber(strPhone) || strPhone.isEmpty()) {
                    if (strPhone.isEmpty()) {
                        userForEdit.setPhone_numbers(userForEdit.getPhone_numbers());
                        correctPhone = true;
                    } else {
                        System.out.println("You want to edit or add phone number? ");
                        System.out.println("1-edit/2-add");
                        int ch = 0;
                        ch = choiceService.choiceEdit_Add(ch);
                        switch (ch) {
                            case 1:
                                int count = 0;
                                List<String> phone_number = userForEdit.getPhone_numbers();
                                for (String s : phone_number) {
                                    count++;
                                    System.out.println(count + " . " + s);
                                }
                                System.out.println("select the phone number to replace");
                                int positionOfPhoneNumber = -1;
                                positionOfPhoneNumber = choiceService.choiceNumberToReplace(positionOfPhoneNumber, count);
                                phone_number.remove(positionOfPhoneNumber - 1);
                                phone_number.add(strPhone);
                                userForEdit.setPhone_numbers(phone_number);
                                correctPhone = true;

                                break;
                            case 2:
                                if (userForEdit.getPhone_numbers().size() < 3) {
                                    userForEdit.setPhone_numbers(strPhone);
                                    System.out.println("Enter another phone number? ");
                                    System.out.println("Yes-1/No-2");
                                    int choice = 0;
                                    choice = choiceService.choiceYes_NO(choice);
                                    switch (choice) {
                                        case 1:
                                            System.out.println("Enter PhoneNumber: ");
                                            correctPhone = false;
                                            break;
                                        case 2:
                                            correctPhone = true;
                                            break;
                                    }
                                } else {
                                    System.out.println("There should be no more than 3 phone numbers. If you want to change existing phone numbers, use the Edit function");
                                    System.out.println("Enter PhoneNumber: ");
                                    correctPhone = false;
                                }
                                break;
                        }
                    }
                } else {
                    System.out.println("incorrect phone number format, try again!");
                    System.out.println("Enter PhoneNumber: ");
                }
            }

            System.out.println("Edit or enter role: " + userForEdit.getRoles());
            boolean role = false;
            String strRole = in.nextLine();
            while (role == false) {
                if (strRole.isEmpty()) {
                    userForEdit.setRoles(userForEdit.getRoles());
                    role = true;
                } else {
                    System.out.println("You want to edit or add role? ");
                    System.out.println("1-edit/2-add");
                    int ch = 0;
                    ch = choiceService.choiceEdit_Add(ch);
                    switch (ch) {
                        case 1:
                            int count = 0;
                            List<String> roles = userForEdit.getRoles();
                            for (String s : roles) {
                                count++;
                                System.out.println(count + " . " + s);
                            }
                            System.out.println("select the role to replace");
                            int positionOfRole = -1;
                            positionOfRole = choiceService.choiceRoleToReplace(positionOfRole, count);
                            roles.remove(positionOfRole - 1);
                            roles.add(strRole);
                            userForEdit.setRoles(roles);
                            role = true;
                            break;
                        case 2:
                            if (userForEdit.getRoles().size() < 3) {
                                userForEdit.setRoles(strRole);
                                System.out.println("Enter another role? ");
                                System.out.println("Yes-1/No-2");
                                int choice = 0;
                                choice = choiceService.choiceYes_NO(choice);
                                switch (choice) {
                                    case 1:
                                        System.out.println("Enter role: ");
                                        role = false;
                                        break;
                                    case 2:
                                        role = true;
                                        break;
                                }
                            } else {
                                System.out.println("There should be no more than 3 roles. If you want to change existing roles, use the Edit function");
                            }
                            break;
                    }
                }
            }
            delete(userLastName);
            users = findAll();
            users.add(userForEdit);
            userDAO.write(users);
        }
    }

    @Override
    public User view(String userLastName) throws Exception {
        User user = userDAO.view(userLastName);
        return user;
    }

    @Override
    public List<User> viewAll() throws Exception {
        List<User> users = userDAO.viewAll();
        return users;
    }

    @Override
    public void delete(String userLastName) throws Exception {
        userDAO.delete(userLastName);
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> users = userDAO.findAll();
        return users;
    }
}
