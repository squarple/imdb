<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title><fmt:message key="title.add_movie"/></title>
    </head>
    <body onload="changeHashOnLoad();">
        <jsp:include page="../parts/navbar.jsp"/>

        <div class="container">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="add_movie">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingTitle" name="movie_title" minlength="1"/>
                    <label for="floatingTitle">
                        <fmt:message key="add_movie.label.title"/>
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingLogline" name="movie_logline" minlength="1"/>
                    <label for="floatingLogline">
                        <fmt:message key="add_movie.label.logline"/>
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingReleaseYear" name="movie_release_year" minlength="4" maxlength="4"/>
                    <label for="floatingReleaseYear">
                        <fmt:message key="add_movie.label.release.year"/>
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <select class="form-select" aria-label="Default select example" name="movie_genre" id="floatingGenre">
                        <c:forEach items="${genres_list}" var="genre">
                            <option value="${genre.name}">${genre.name}</option>
                        </c:forEach>
                    </select>
                    <label for="floatingGenre">
                        <fmt:message key="add_movie.label.genre"/>
                    </label>
                </div>

                <div class="form-floating mb-3">
                    <select class="form-select" aria-label="Default select example" name="movie_type" id="floatingMovieType">
                        <c:forEach items="${movie_types_list}" var="movie_type">
                            <option value="${movie_type}">${movie_type}</option>
                        </c:forEach>
                    </select>
                    <label for="floatingMovieType">
                        <fmt:message key="add_movie.label.movie.type"/>
                    </label>
                </div>

                <button class="btn btn-primary" type="submit">
                    <fmt:message key="add_movie.button.add.movie"/>
                </button>
            </form>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script type="text/javascript">
            var storedHash = window.location.hash;
            function changeHashOnLoad() {
                window.location.hash = "1";
            }
            window.onhashchange = function () {
                window.location.hash = storedHash;
            }
        </script>
    </body>
</html>