package com.victorp.validator.impl;

import com.victorp.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorImpl implements Validator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_PATTERN = "(375)+\\d{2}+\\s?\\d{7}";

    @Override
    public boolean checkEmail(String email) throws Exception {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) throws Exception {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
