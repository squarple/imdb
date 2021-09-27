package by.radzionau.imdb.controller.command;

public enum PagePath {
    MAIN_PAGE ("pages/main.jsp"),
    LOGIN_PAGE ("pages/login.jsp"),
    SIGNUP_PAGE("pages/signup.jsp"),
    VERIFY_EMAIL_PAGE("pages/verify_email.jsp"),
    GET_USERS_PAGE("pages/admin/get_users.jsp"),
    EDIT_MOVIE_PAGE("pages/admin/edit_movie.jsp"),
    GET_MOVIE_LIST_PAGE("pages/get_movie_list.jsp"),
    GET_MOVIE_PAGE("pages/get_movie.jsp"),
    SEARCH_MOVIES_PAGE("pages/search_movies.jsp"),
    ERROR_500_PAGE ("pages/error/error500.jsp"),
    ERROR_404_PAGE ("pages/error/error404.jsp");

    private String address;

    PagePath(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
