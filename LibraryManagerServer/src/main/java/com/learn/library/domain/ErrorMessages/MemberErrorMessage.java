package com.learn.library.domain.ErrorMessages;

public class MemberErrorMessage extends UserErrorMessage {
    public static final String JOINED_ON_FUTURE_DATED = "Member joined on date cannot be in the future";
    public static final String JOINED_ON_NULL = "Member joined on date cannot be null";
}
