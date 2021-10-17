<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>${movie.title}</title>
    </head>
    <body onload="changeHashOnLoad();">
        <jsp:include page="../parts/navbar.jsp"/>

        <div class="container">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="edit_movie">
                <input type="hidden" name="movie_id" value="${movie.movieId}">
                <div class=" mb-3">
                    <label for="floatingTitle"><fmt:message key="edit_movie.label.title"/></label>
                    <input type="text" class="form-control" id="floatingTitle" placeholder="title" name="movie_title" value="${movie.title}"/>
                </div>

                <div class="mb-3">
                    <label for="floatingLogline" class="form-label"><fmt:message key="edit_movie.label.logline"/></label>
                    <textarea type="text" class="form-control" id="floatingLogline" style="height: 150px" name="movie_logline">${movie.logline}</textarea>
                </div>

                <div class="mb-3">
                    <label for="floatingReleaseYear"><fmt:message key="edit_movie.label.release.year"/></label>
                    <input type="text" class="form-control" id="floatingReleaseYear" placeholder="title" name="movie_release_year" value="${movie.releaseYear}"/>
                </div>
                <button class="btn btn-primary" type="submit">Save</button>
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
