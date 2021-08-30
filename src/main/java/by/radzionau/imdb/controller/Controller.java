package by.radzionau.imdb.controller;

import by.radzionau.imdb.command.*;
import by.radzionau.imdb.model.pool.CustomConnectionPool;
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

//        response.setContentType("text/html");
//
//        String num = request.getParameter("input_n");
//        try {
//            int result_number = Integer.parseInt(num) * 5;
//            request.setAttribute("res", result_number);
//        } catch (NumberFormatException e) {
//            throw new RuntimeException(e);
//        }
//
//        List<String> lst = Stream.of("a", "b", "c").collect(Collectors.toList());
//        request.setAttribute("lst", lst);
//        request.getRequestDispatcher("/pages/result.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void destroy() {
        CustomConnectionPool pool = CustomConnectionPool.getInstance();
        pool.destroyPull();

        super.destroy();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND);

        Command command = COMMAND_PROVIDER.getCommand(commandName);
        Router router = command.execute(request, response);

        switch (router.getRouterType()) {
            case REDIRECT:
                response.sendRedirect(router.getPagePath().getAddress());
                break;
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath().getAddress());
                dispatcher.forward(request, response);
                break;
            default:
                response.sendRedirect(PagePath.ERROR_404_PAGE.getAddress());
        }
    }
}