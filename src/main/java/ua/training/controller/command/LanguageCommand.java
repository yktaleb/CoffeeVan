package ua.training.controller.command;

import ua.training.util.ExceptionMessage;
import ua.training.util.constant.general.Parameters;
import ua.training.util.Messages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocale(request);
        return request.getParameter(Parameters.PAGE);
    }

    private void setLocale(HttpServletRequest request) {
        String lang = request.getParameter(Parameters.LANGUAGE);
        if (lang.equals(Parameters.EN)) {
            request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.EN_US);
            Messages.setLocale(Messages.ENGLISH);
            ExceptionMessage.setLocale(ExceptionMessage.ENGLISH);
        } else {
            request.getSession().setAttribute(Parameters.LANGUAGE, Parameters.UK_UA);
            Messages.setLocale(Messages.UKRAINIAN);
            ExceptionMessage.setLocale(ExceptionMessage.UKRAINIAN);
        }
    }
}
