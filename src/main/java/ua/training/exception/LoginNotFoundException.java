package ua.training.exception;

public class LoginNotFoundException extends Exception {
    private String login;

    public LoginNotFoundException(String login, String message) {
        super(message);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
