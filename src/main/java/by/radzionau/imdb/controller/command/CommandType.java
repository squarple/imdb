package by.radzionau.imdb.controller.command;

public enum CommandType {
    /*USER*/
    SIGN_IN, SIGN_OUT, SIGN_UP, EMAIL_VERIFICATION,
    //ADD_FEEDBACK, UPDATE_FEEDBACK, DELETE_FEEDBACK,

    /*ADMIN*/
    //ADD_MOVIE, UPDATE_MOVIE, DELETE_MOVIE,
    //CHANGE_USER_ROLE, CHANGE_USER_STATUS,
    //ADD_GENRE,

    MOVE_TO,
    DEFAULT
}
