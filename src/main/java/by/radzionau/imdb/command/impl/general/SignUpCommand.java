package by.radzionau.imdb.command.impl.general;

import by.radzionau.imdb.command.Command;
import by.radzionau.imdb.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
