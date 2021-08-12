package by.radzionau.imdb.domain;

public class User {
    private Long userId;
    private String login;
    private String mail;
    private String name;
    private String surname;
    private UserRole role;
    private UserStatus status;

    public User() {

    }

    public User(Long userId, String login, String mail, String name, String surname, UserRole role, UserStatus status) {
        this.userId = userId;
        this.login = login;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.status = status;
    }

    public User(String login, String mail, String name, String surname, UserRole role, UserStatus status) {
        this.userId = 0L; //fixme инициализация id, если его нету, как надо делать???
        this.login = login;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        return userId.equals(user.userId)
                && login.equals(user.login)
                && mail.equals(user.mail)
                && name.equals(user.name)
                && surname.equals(user.surname)
                && role.equals(user.role)
                && status.equals(user.status);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = result * 31 + login.hashCode();
        result = result * 31 + mail.hashCode();
        result = result * 31 + name.hashCode();
        result = result * 31 + surname.hashCode();
        result = result * 31 + role.hashCode();
        result = result * 31 + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{")
                .append("userId=").append(userId)
                .append(", login=").append(login)
                .append(", mail=").append(mail)
                .append(", name=").append(name)
                .append(", surname=").append(surname)
                .append(", role=").append(role)
                .append(", status=").append(status)
                .append('}');
        return sb.toString();
    }
}
