<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
    <jsp:include page="../parts/navbar.jsp"/>

    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="edit_movie">
        <input type="hidden" name="page_from" value="${page_to}">
        <input type="hidden" name="movie_id" value="${movie.movieId}">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="floatingTitle" placeholder="title" name="movie_title" value="${movie.title}"/>
            <label for="floatingTitle">title</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="floatingLogline" placeholder="logline" name="movie_logline" value="${movie.logline}"/>
            <label for="floatingLogline">logline</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="floatingReleaseYear" placeholder="title" name="movie_release_year" value="${movie.releaseYear}"/>
            <label for="floatingReleaseYear">release year</label>
        </div>
        <button class="btn btn-primary" type="submit">Save</button>
    </form>

    <jsp:include page="../parts/footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
