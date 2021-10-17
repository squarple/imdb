package by.radzionau.imdb.controller.command.util;

import by.radzionau.imdb.controller.command.RequestParameter;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.FeedbackStatus;
import by.radzionau.imdb.model.entity.MovieType;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.attribute.standard.Severity;
import java.util.Optional;
import java.util.Scanner;

public final class RequestUtil {
    private static final Logger logger = LogManager.getLogger(RequestUtil.class);
    private static final RequestUtil INSTANCE = new RequestUtil();

    private RequestUtil() {}

    public static RequestUtil getInstance() {
        return INSTANCE;
    }

    public Long getLong(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (parameter.isPresent()) {
            try (Scanner scanner = new Scanner(parameter.get())) {
                if (scanner.hasNextLong()) {
                    return Long.valueOf(parameter.get());
                } else {
                    logger.error("Invalid parameter");
                    throw new ServiceException("Invalid parameter");
                }
            }
        } else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    public int getInt(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (parameter.isPresent()) {
            try(Scanner scanner = new Scanner(parameter.get())) {
                if (scanner.hasNextInt()) {
                    return Integer.parseInt(parameter.get());
                } else {
                    logger.error("Invalid parameter");
                    throw new ServiceException("Invalid parameter");
                }
            }
        } else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    public MovieType getMovieType(HttpServletRequest request) throws ServiceException {
        Optional<String> parameter = getParameter(request, RequestParameter.MOVIE_TYPE);
        if (parameter.isPresent()) {
            try {
                return MovieType.valueOf(parameter.get().toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.error("Invalid parameter");
                throw new ServiceException("Invalid parameter");
            }
        } else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    public FeedbackStatus getFeedbackStatus(HttpServletRequest request) throws ServiceException {
        Optional<String> parameter = getParameter(request, RequestParameter.FEEDBACK_STATUS);
        if (parameter.isPresent()) {
            try {
                return FeedbackStatus.valueOf(parameter.get().toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.error("Invalid parameter");
                throw new ServiceException("Invalid parameter");
            }
        } else {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
    }

    public String getString(HttpServletRequest request, String parameterName) throws ServiceException {
        Optional<String> parameter = getParameter(request, parameterName);
        if (!parameter.isPresent()) {
            logger.error("Invalid parameter");
            throw new ServiceException("Invalid parameter");
        }
        return parameter.get();
    }

    private Optional<String> getParameter(HttpServletRequest request, String parameterName) {
        String parameter = request.getParameter(parameterName);
        if (parameter == null) {
            return Optional.empty();
        } else {
            return Optional.of(parameter);
        }
    }
}
