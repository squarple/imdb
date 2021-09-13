<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <form>
            <input type="hidden" name="page_from" value="${page_to}">
            <input type="hidden" name="page_to" value="main_page">
            <input type="hidden" name="command" value="move_to">
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
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Dropdown
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <form id="films" action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="page_from" value="${page_to}">
                        <input type="hidden" name="page_to" value="get_movies_page">
                        <input type="hidden" name="movie_type" value="film">
                        <input type="hidden" name="command" value="get_movies">
                        <a class="nav-link active" href=# onclick="document.getElementById('films').submit()" tabindex="-1" aria-disabled="true">Films</a>
                    </form>
                </li>
                <li class="nav-item">
                    <form id="serials" action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="page_from" value="${page_to}">
                        <input type="hidden" name="page_to" value="get_movies_page">
                        <input type="hidden" name="movie_type" value="serial">
                        <input type="hidden" name="command" value="get_movies">
                        <a class="nav-link active" href=# onclick="document.getElementById('serials').submit()" tabindex="-1" aria-disabled="true">Serials</a>
                    </form>
                </li>
                <c:if test="${role == 'ADMIN'}">
                    <li class="nav-item">
                        <form id="users_list" action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="page_from" value="main_page">
                            <input type="hidden" name="page_to" value="get_users_page">
                            <input type="hidden" name="command" value="get_users">
                            <a class="nav-link active" href=# onclick="document.getElementById('users_list').submit()" tabindex="-1" aria-disabled="true">Users list</a>
                        </form>
                    </li>
                </c:if>
            </ul>
            <!--<form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form> -->
            <c:if test="${role != 'ADMIN' && role != 'USER'}">
            <div>
                <form action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="page_from" value="${page_to}">
                    <input type="hidden" name="page_to" value="login_page">
                    <button type="submit" name="command" value="move_to">Sign in</button>
                </form>
            </div>
            </c:if>
            <c:if test="${role == 'ADMIN' || role == 'USER'}">
                <div>
                    <form action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="page_from" value="${page_to}">
                        <input type="hidden" name="page_to" value="main_page">
                        <button type="submit" name="command" value="sign_out">Logout</button>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</nav>
</div>