package by.radzionau.imdb.command.impl.general;

import by.radzionau.imdb.command.Command;
import by.radzionau.imdb.command.PagePath;
import by.radzionau.imdb.command.RequestAttribute;
import by.radzionau.imdb.command.Router;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        deleteSession(request);
        //clearCookies(request.getCookies(), response);
        //createNewSession(request);
        return new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
    }

    private void deleteSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }

//    private void clearCookies(Cookie[] cookies, HttpServletResponse response) {
//        if (cookies != null) {
//            removeCookie(cookies, CookieName.EMAIL.toString(), response);
//            removeCookie(cookies, CookieName.PASSWORD.toString(), response);
//        }
//    }

//    private void createNewSession(HttpServletRequest request) {
//        HttpSession newSession = request.getSession();
//        newSession.setAttribute(RequestAttribute.ROLE.toString(), Role.GUEST);
//    }

//    private void removeCookie(Cookie[] cookies, String name, HttpServletResponse response) {
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals(name)) {
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }
//        }
//    }
}
