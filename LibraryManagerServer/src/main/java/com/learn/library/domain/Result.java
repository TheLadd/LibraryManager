package com.learn.library.domain;

import jakarta.validation.ConstraintViolation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Result<T> {
    private T payload;
    private ResultType type = ResultType.SUCCESS;
    Set<String> messages = new HashSet<>();

    public Result() {}

    public Result(T payload) {
        this.payload = payload;

    }

    public Result(Set<ConstraintViolation<T>> violations) {
        for (ConstraintViolation<T> violation : violations) {
            messages.add(violation.getMessage());
        }
        this.type = ResultType.INVALID;
    }

    public void addMessage(String message) {
        messages.add(message);
        this.type = ResultType.INVALID;
    }

    public void addMessage(String message, ResultType type) {
        messages.add(message);
        this.type = type;
    }

    public void addMessages(List<String> messages) {
        for (String message : messages) {
            addMessage(message);
        }
    }

    public T getPayload() { return this.payload; }

    public Set<String> getMessages() { return this.messages; }

    public boolean isSuccess() { return this.messages.isEmpty(); }

    public void setPayload(T payload) { this.payload = payload; }

    public void setType(ResultType type) { this.type = type; }

    @Override
    public String toString() {
        return "Result{" +
                "payload=" + payload +
                ", type=" + type +
                ", messages=" + messages +
                '}';
    }
}
