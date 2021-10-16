package by.radzionau.imdb.controller.command;

/**
 * The enum PagePath.
 */
public enum PagePath {
        MAIN_PAGE("pages/main.jsp"),
        LOGIN_PAGE("pages/login.jsp"),
        SIGNUP_PAGE("pages/signup.jsp"),
        GET_MOVIE_PAGE("pages/get_movie.jsp"),
        VERIFY_EMAIL_PAGE("pages/verify_email.jsp"),
        SEARCH_MOVIES_PAGE("pages/search_movies.jsp"),
        GET_MOVIE_LIST_PAGE("pages/get_movie_list.jsp"),

        ADD_MOVIE_PAGE("pages/admin/add_movie.jsp"),
        ADD_GENRE_PAGE("pages/admin/add_genre.jsp"),
        GET_USERS_PAGE("pages/admin/get_users.jsp"),
        EDIT_MOVIE_PAGE("pages/admin/edit_movie.jsp"),
        ADD_FEEDBACK_PAGE("pages/user/add_feedback.jsp"),
        GET_FEEDBACKS_PAGE("pages/admin/get_feedbacks.jsp"),
        ADD_MOVIE_COVER_PAGE("pages/admin/add_movie_cover.jsp"),

        ERROR_500_PAGE("pages/error/error500.jsp"),
        ERROR_404_PAGE("pages/error/error404.jsp");

        private String address;

        PagePath(String address) {
                this.address = address;
        }

        /**
         * Gets address.
         *
         * @return the address of page
         */
        public String getAddress() {
                return address;
        }
}
