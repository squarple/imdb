package by.radzionau.imdb.controller.command;

public enum CommandType {
    /*COMMON*/
    SIGN_IN, SIGN_OUT, SIGN_UP, EMAIL_VERIFICATION,
    GET_MOVIES,

    /*USER*/
    //ADD_FEEDBACK, UPDATE_FEEDBACK, DELETE_FEEDBACK,

    /*ADMIN*/
    GET_USERS, CHANGE_USER_ROLE, CHANGE_USER_STATUS,
    //ADD_MOVIE, UPDATE_MOVIE, DELETE_MOVIE,
    //ADD_GENRE,

    MOVE_TO,
    DEFAULT
}
