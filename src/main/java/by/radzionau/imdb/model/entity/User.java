package by.radzionau.imdb.model.entity;

public class User {
    private Long userId = -1L;
    private String login;
    private String email;
    private String name;
    private String surname;
    private UserRole role;
    private UserStatus status;

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                && email.equals(user.email)
                && name.equals(user.name)
                && surname.equals(user.surname)
                && role.equals(user.role)
                && status.equals(user.status);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = result * 31 + login.hashCode();
        result = result * 31 + email.hashCode();
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
                .append(", email=").append(email)
                .append(", name=").append(name)
                .append(", surname=").append(surname)
                .append(", role=").append(role)
                .append(", status=").append(status)
                .append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder setUserId(Long userId) {
            user.setUserId(userId);
            return this;
        }

        public Builder setLogin(String login) {
            user.setLogin(login);
            return this;
        }

        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder setName(String name) {
            user.setName(name);
            return this;
        }

        public Builder setSurname(String surname) {
            user.setSurname(surname);
            return this;
        }

        public Builder setRole(UserRole userRole) {
            user.setRole(userRole);
            return this;
        }

        public Builder setStatus(UserStatus userStatus) {
            user.setStatus(userStatus);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
