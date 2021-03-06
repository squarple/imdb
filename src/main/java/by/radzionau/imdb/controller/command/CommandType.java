package by.radzionau.imdb.controller.command;

/**
 * The enum CommandType.
 */
public enum CommandType {
    //COMMON
    SIGN_IN, SIGN_OUT, SIGN_UP, EMAIL_VERIFICATION,
    MOVE_TO_LOGIN_PAGE, MOVE_TO_MAIN_PAGE, MOVE_TO_SIGNUP_PAGE,
    GET_MOVIE_LIST, GET_MOVIE, SEARCH_MOVIES, CHANGE_LOCALE,

    //USER
    ADD_FEEDBACK, MOVE_TO_ADD_FEEDBACK_PAGE,

    //ADMIN
    GET_USERS, CHANGE_USER_ROLE, CHANGE_USER_STATUS,
    MOVE_TO_ADD_MOVIE_PAGE, ADD_MOVIE_COVER,
    MOVE_TO_EDIT_MOVIE_PAGE, EDIT_MOVIE, DELETE_MOVIE,
    GET_FEEDBACKS, CHANGE_FEEDBACK_STATUS, ADD_MOVIE,
    MOVE_TO_ADD_MOVIE_COVER_PAGE, MOVE_TO_ADD_GENRE_PAGE,
    ADD_GENRE,

    DEFAULT
}
