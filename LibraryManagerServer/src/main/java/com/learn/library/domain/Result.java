package com.learn.library.domain;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class Result<T> {
    private T payload;
    Set<String> messages;

    public Result(Set<ConstraintViolation<T>> violations) {

    }
}
