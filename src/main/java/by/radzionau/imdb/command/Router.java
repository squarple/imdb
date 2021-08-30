package by.radzionau.imdb.command;

public class Router {
    public enum RouterType {
        REDIRECT, FORWARD
    }

    private final PagePath pagePath;
    private final RouterType routerType;

    public Router(PagePath pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    public PagePath getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }
}
