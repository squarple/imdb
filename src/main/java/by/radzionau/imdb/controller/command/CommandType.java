package by.radzionau.imdb.controller.command;

public enum CommandType {
    //USER
    SIGN_IN, SIGN_OUT, SIGN_UP, EMAIL_VERIFICATION,

    //MOVIE
    //ADD_MOVIE, UPDATE_MOVIE, DELETE_MOVIE,

    //GENRE
    //ADD_GENRE,

    //FEEDBACK
    //ADD_FEEDBACK, UPDATE_FEEDBACK, DELETE_FEEDBACK,

    REDIRECT,

    DEFAULT
}
