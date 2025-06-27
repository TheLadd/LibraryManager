package com.learn.library.domain.ErrorMessages;

public class BookErrorMessage {
    public static final String TITLE_EMPTY = "Book title should not be empty";
    public static final String AUTHOR_EMPTY  = "Book author should not be empty";
    public static final String GENRE_EMPTY = "Book genre should not be empty";

    public static String ID_NOT_FOUND(int id) { return "Could not find book with ID: " + id; }
}
