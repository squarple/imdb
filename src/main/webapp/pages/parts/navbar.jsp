<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<div class="container mb-3">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <form>
                <input type="hidden" name="command" value="move_to_main_page">
                <button type="submit" class="btn btn-link">
                    <img src="https://images-na.ssl-images-amazon.com/images/G/01/imdb/authportal/images/www_imdb_logo._CB667618033_.png">
                </button>
            </form>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation" name="command" value="">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <form id="films" action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="movie_type" value="film">
                            <input type="hidden" name="command" value="get_movie_list">
                            <a class="nav-link active" href=# onclick="document.getElementById('films').submit()" tabindex="-1" aria-disabled="true"><fmt:message key="navbar.common.films"/></a>
                        </form>
                    </li>
                    <li class="nav-item">
                        <form id="serials" action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="movie_type" value="serial">
                            <input type="hidden" name="command" value="get_movie_list">
                            <a class="nav-link active" href=# onclick="document.getElementById('serials').submit()" tabindex="-1" aria-disabled="true"><fmt:message key="navbar.common.serials"/></a>
                        </form>
                    </li>
                    <li class="nav-item">
                        <form id="top" action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="move_to_top_methods">
                            <a class="nav-link active" href=# onclick="document.getElementById('top').submit()" tabindex="-1" aria-disabled="true">Top methods</a>
                        </form>
                    </li>
                    <c:if test="${role == 'ADMIN'}">
                        <li class="nav-item">
                            <form id="users_list" action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="get_users">
                                <a class="nav-link active" href=# onclick="document.getElementById('users_list').submit()" tabindex="-1" aria-disabled="true"><fmt:message key="navbar.admin.users.list"/></a>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form id="feedback_list" action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="get_feedbacks">
                                <a class="nav-link active" href=# onclick="document.getElementById('feedback_list').submit()" tabindex="-1" aria-disabled="true"><fmt:message key="navbar.admin.feedbacks.list"/></a>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form id="new_movie" action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="move_to_add_movie_page">
                                <a class="nav-link active" href=# onclick="document.getElementById('new_movie').submit()" tabindex="-1" aria-disabled="true"><fmt:message key="navbar.admin.add.movie"/></a>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form id="new_genre" action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="move_to_add_genre_page">
                                <a class="nav-link active" href=# onclick="document.getElementById('new_genre').submit()" tabindex="-1" aria-disabled="true"><fmt:message key="navbar.admin.add.genre"/></a>
                            </form>
                        </li>
                    </c:if>
                </ul>
                <div>
                    <form class="d-flex" action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="search_movies">
                        <input class="form-control me-2" type="search" name="search_query" placeholder="<fmt:message key="navbar.common.search"/>" aria-label="Search" required minlength="1">
                        <button class="btn btn-outline-success" type="submit">
                            <fmt:message key="navbar.common.search"/>
                        </button>
                    </form>
                </div>

                <c:if test="${role != 'ADMIN' && role != 'USER'}">
                    <div>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="move_to_login_page">
                            <button class="btn btn-primary ms-1" type="submit">
                                <fmt:message key="navbar.common.sign.in"/>
                            </button>
                        </form>
                    </div>
                </c:if>
                <c:if test="${role == 'ADMIN' || role == 'USER'}">
                    <div>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="sign_out">
                            <button class="btn btn-primary ms-1" type="submit">
                                <fmt:message key="navbar.common.logout"/>
                            </button>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </nav>
</div>