package by.radzionau.imdb.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);

    default void setPageFromAttribute(HttpServletRequest request) {
        String pageFrom = request.getParameter(RequestParameter.PAGE_FROM);
        request.setAttribute(RequestAttribute.PAGE_FROM, pageFrom);
    }

    default void setPageToAttribute(HttpServletRequest request, PagePath pageTo) {
        request.setAttribute(RequestAttribute.PAGE_TO, pageTo.toString().toLowerCase());
    }
}
