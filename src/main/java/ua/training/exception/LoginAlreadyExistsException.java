package ua.training.exception;

public class LoginAlreadyExistsException extends Exception {
    private String login;

    public LoginAlreadyExistsException(String login, String message) {
        super(message);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
