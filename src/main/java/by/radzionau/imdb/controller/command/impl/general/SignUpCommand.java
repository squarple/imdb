package by.radzionau.imdb.controller.command.impl.general;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
