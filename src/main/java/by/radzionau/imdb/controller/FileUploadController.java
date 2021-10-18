package by.radzionau.imdb.controller;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.CommandProvider;
import by.radzionau.imdb.controller.command.RequestParameter;
import by.radzionau.imdb.controller.command.Router;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * The class File upload controller extends {@link HttpServlet}. Handle file uploading requests from website.
 */
@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 25 * 1024 * 1024)
public class FileUploadController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(FileUploadController.class);
    private final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        request.getRequestDispatcher("/index.jsp").forward(request, resp);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command;
        try {
            command = commandProvider.getCommand(commandName);
        } catch (NullPointerException e) {
            logger.error("Wild command name ={}", commandName);
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
