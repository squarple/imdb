package by.radzionau.imdb.controller;

import by.radzionau.imdb.controller.command.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
                response.sendRedirect(router.getPagePath().getAddress());
                break;
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath().getAddress());
                dispatcher.forward(request, response);
                break;
            default:
                response.sendError(500);
        }
    }
}