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
        <c:if test="${role == 'ADMIN'}">
            <div>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="page_from" value="${page_to}">
                    <input type="hidden" name="page_to" value="edit_movie_page">
                    <input type="hidden" name="movie_id" value="${movie.movieId}">
                    <input type="hidden" name="command" value="move_to_edit_movie_page">
                    <button type="submit" class="btn btn-link">Edit</button>
                </form>
            </div>
            <div>
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="delete_movie">
                    <input type="hidden" name="page_from" value="${page_to}">
                    <input type="hidden" name="movie_id" value="${movie.movieId}">
                    <button type="submit" class="btn btn-link">Delete</button>
                </form>
            </div>
        </c:if>
        <br>

        <h5>movie.cover</h5>
        <br>
        <h5>${movie.title}</h5>
        <br>
        <h5>${movie.logline}</h5>
        <br>
        <h5>${movie.releaseYear}</h5>
        <br>
        <h5>${movie_score}</h5>
        <br>
        <c:forEach items="${genres_list}" var="genre">
            <h5>${genre.name}</h5>
            <br>
        </c:forEach>

        <div>
            <c:if test="${role == 'USER'}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="page_from" value="${page_to}">
                    <input type="hidden" name="command" value="add_feedback">
                    <select class="form-select" aria-label="Default select example" name="feedback_score" id="scores">
                        <option selected>Ten</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                        <option value="3">Four</option>
                        <option value="3">Five</option>
                        <option value="3">Six</option>
                        <option value="3">Seven</option>
                        <option value="3">Eight</option>
                        <option value="3">Nine</option>
                        <option value="3">Ten</option>
                    </select>
                    <label for="scores">your score</label>

                    <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="5" name="feedback_content"></textarea>
                    </div>
                    <button type="submit" class="btn btn-link">Post</button>
                </form>
            </c:if>
        </div>
    </div>

    <jsp:include page="parts/footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
