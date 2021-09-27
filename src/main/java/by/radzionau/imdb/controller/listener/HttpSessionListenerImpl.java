package by.radzionau.imdb.controller.listener;

import by.radzionau.imdb.controller.command.RequestAttribute;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(RequestAttribute.LOCALE, "ru_RU");
    }
}
