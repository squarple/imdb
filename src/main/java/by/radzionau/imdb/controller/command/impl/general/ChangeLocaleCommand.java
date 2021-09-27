package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {
    private static final String EN_LOCALE = "en_EN";
    private static final String RU_LOCALE = "ru_RU";

    @Override
    public Router execute(HttpServletRequest request) {
        setPageFromAttribute(request);
        String currentLocale = (String) request.getSession().getAttribute(RequestAttribute.LOCALE);

        if (currentLocale.equals(RU_LOCALE)) {
            setNewLocale(request, EN_LOCALE);
        } else {
            setNewLocale(request, RU_LOCALE);
        }

        PagePath pageTo;
        try {
            pageTo = PagePath.valueOf(request.getParameter(RequestParameter.PAGE_TO).toUpperCase());
        } catch (IllegalArgumentException e) {
            pageTo = PagePath.MAIN_PAGE;
        }
        setPageToAttribute(request, pageTo);

        return new Router(pageTo, Router.RouterType.FORWARD);
    }

    private void setNewLocale(HttpServletRequest request, String newLocale) {
        request.getSession().removeAttribute(RequestAttribute.LOCALE);
        request.getSession().setAttribute(RequestAttribute.LOCALE, newLocale);
    }
}
