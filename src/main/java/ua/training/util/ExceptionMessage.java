package ua.training.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class ExceptionMessage {

    public static final String PASSWORD_PATTERN_ERROR = "password.pattern.error";
    public static final String PHONE_NUMBER_PATTERN_ERROR = "phone.pattern.error";
    public static final String EMAIL_PATTERN_ERROR = "email.pattern.error";
    public static final String EMAIL_EXIST_ERROR = "email.exist.error";
    public static final String WRONG_PASSWORD_ERROR = "wrong.password.error";
    public static final String EMAIL_NOT_FOUND_ERROR = "email.not.found.error";

    public static final Locale ENGLISH = new Locale("en", "US");
    public static final Locale UKRAINIAN = new Locale("uk", "UA");

    private static final String BUNDLE_NAME = "/i18n/exceptions";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, ENGLISH);

    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}
