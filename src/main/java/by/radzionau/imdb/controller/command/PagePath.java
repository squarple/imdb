package by.radzionau.imdb.controller.command;

public enum PagePath {
    MAIN_PAGE ("pages/main.jsp"),
    LOGIN_PAGE ("pages/login.jsp"),
    SIGNUP_PAGE("pages/signup.jsp"),
    VERIFY_EMAIL_PAGE("pages/verify_email.jsp"),
    ERROR_500_PAGE ("pages/error/error500.jsp"),
    ERROR_404_PAGE ("pages/error/error404.jsp");

    private String address;

    private PagePath(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
