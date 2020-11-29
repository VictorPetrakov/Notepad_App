package com.victorp.validator;

public interface Validator {
    boolean checkEmail(String email) throws Exception;
    boolean checkPhoneNumber(String phoneNumber) throws Exception;
}
