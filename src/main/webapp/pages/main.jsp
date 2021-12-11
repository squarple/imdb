<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <meta charset="">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>
            <fmt:message key="title.main"/>
        </title>
    </head>
    <body onload="changeHashOnLoad();">
        <jsp:include page="parts/navbar.jsp"/>

        <div class="container">
            <div class="row row-cols-1 row-cols-md-5 g-4">
                <c:forEach items="${movies_list}" var="movie" varStatus="status">
                    <div class="col">
                        <div class="card h-100">
                            <img src="${movie_covers_list[status.index]}" width="220" height="330" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h6 class="card-title">
                                    <form id="${movie.movieId}" action="${pageContext.request.contextPath}/controller" method="get">
                                        <input type="hidden" name="movie_id" value="${movie.movieId}">
                                        <input type="hidden" name="command" value="get_movie">
                                        <a class="nav-link active" href=# onclick="document.getElementById('${movie.movieId}').submit()" tabindex="-1" aria-disabled="true">${movie.title}</a>
                                    </form>
                                </h6>
                                <p class="card-text">
                                    <c:choose>
                                        <c:when test="${role == 'ADMIN' || role == 'USER'}">
                                            <fmt:message key="get_movie_list.text.rating"/> ${movie_rating_list[status.index]}
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="get_movie_list.sign.in.to.see.rating"/>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">${movie.releaseYear}</small>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <jsp:include page="parts/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script type="text/javascript">
            <%@include file="../script/returnScript.js"%>
        </script>
    </body>
</html>
