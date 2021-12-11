<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>${movie.title}</title>
    </head>
    <body onload="changeHashOnLoad();">
        <jsp:include page="parts/navbar.jsp"/>
    
        <div class="container">
            <c:if test="${role == 'ADMIN'}">
                <div>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="movie_id" value="${movie.movieId}">
                        <input type="hidden" name="command" value="move_to_edit_movie_page">
                        <button type="submit" class="btn btn-link">
                            <fmt:message key="get_movie.button.edit"/>
                        </button>
                    </form>
                </div>
                <div>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="movie_id" value="${movie.movieId}">
                        <input type="hidden" name="command" value="move_to_add_movie_cover_page">
                        <button type="submit" class="btn btn-link">
                            <fmt:message key="get_movie.button.edit.movie.cover"/>
                        </button>
                    </form>
                </div>
                <div>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="delete_movie">
                        <input type="hidden" name="movie_id" value="${movie.movieId}">
                        <button type="submit" class="btn btn-link">
                            <fmt:message key="get_movie.button.delete"/>
                        </button>
                    </form>
                </div>
            </c:if>
            <br>
        
            <img src="${movie_cover}" width="220" height="330" alt="..."/>
            <br>
            <h5><b><fmt:message key="get_movie.text.movie.title"/></b> ${movie.title}</h5>
            <h5><b><fmt:message key="get_movie.text.movie.logline"/></b> ${movie.logline}</h5>
            <h5><b><fmt:message key="get_movie.text.movie.release.year"/></b> ${movie.releaseYear}</h5>
            <h5>
                <c:choose>
                    <c:when test="${role == 'ADMIN' || role == 'USER'}">
                        <b><fmt:message key="get_movie.text.movie.score"/></b> ${movie_score}
                    </c:when>
                    <c:otherwise>
                        <b><fmt:message key="get_movie_list.sign.in.to.see.rating"/></b>
                    </c:otherwise>
                </c:choose>
            </h5>
            <b><fmt:message key="get_movie.text.movie.genre"/></b></h5>
            <c:forEach items="${genres_list}" var="genre">
                <h5>${genre.name}</h5>
                <br>
            </c:forEach>
        
            <br>
            <c:if test="${role == 'USER'}">
                <c:choose>
                    <c:when test="${is_user_already_made_review == true}">
                        <fmt:message key="get_movie.message.you.have.already.left.review"/>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="movie_id" value="${movie.movieId}">
                                <input type="hidden" name="command" value="move_to_add_feedback_page">
                                <button type="submit" class="btn btn-link">
                                    <fmt:message key="get_movie.button.add.feedback"/>
                                </button>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <br>
        
            <c:choose>
                <c:when test="${role == 'ADMIN' || role == 'USER'}">
                    <div>
                        <hr>
                        <c:forEach items="${feedback_list}" var="feedback">
                            <h5><fmt:message key="get_movie.text.movie.score"/> ${feedback.score}</h5>
                            <h5>${feedback.content}</h5>
                            <hr>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <fmt:message key="get_movie.message.sign.in.to.see.reviews"/>
                    </div>
                </c:otherwise>
            </c:choose>
        
        
        </div>
    
        <jsp:include page="parts/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script type="text/javascript">
            <%@include file="../script/returnScript.js"%>
        </script>
    </body>
</html>
