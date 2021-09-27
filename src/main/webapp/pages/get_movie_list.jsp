<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Title</title>
    </head>
    <body>
        <jsp:include page="parts/navbar.jsp"/>

        <div class="container">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <c:forEach items="${movies_list}" var="movie">
                    <div class="col">
                        <div class="card h-100">
                            <img src="..." class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <form id="${movie.movieId}" action="${pageContext.request.contextPath}/controller" method="get">
                                        <input type="hidden" name="page_from" value="${page_to}">
                                        <input type="hidden" name="page_to" value="get_movie_page">
                                        <input type="hidden" name="movie_id" value="${movie.movieId}">
                                        <input type="hidden" name="command" value="get_movie">
                                        <a class="nav-link active" href=# onclick="document.getElementById('${movie.movieId}').submit()" tabindex="-1" aria-disabled="true">${movie.title}</a>
                                    </form>
                                </h5>
                                <p class="card-text">${movie.logline}</p>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">${movie.releaseYear} год</small>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="parts/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
