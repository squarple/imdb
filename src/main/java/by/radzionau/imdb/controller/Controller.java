package by.radzionau.imdb.controller;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.CommandProvider;
import by.radzionau.imdb.controller.command.RequestParameter;
import by.radzionau.imdb.controller.command.Router;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * The class Controller extends {@link HttpServlet}. Handle requests from website.
 */
@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {
    private final CommandProvider COMMAND_PROVIDER = CommandProvider.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command;

        try {
            command = COMMAND_PROVIDER.getCommand(commandName);
        } catch (NullPointerException e) {
            response.sendError(404);
            return;
        }
        Router router = command.execute(request);

        switch (router.getRouterType()) {
            case REDIRECT:
                response.sendRedirect(router.getPagePath());
                break;
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
                break;
            default:
                response.sendError(500);
                break;
        }
    }
}