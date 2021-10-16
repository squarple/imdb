<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="title.add_feedback"/></title>
</head>
<body>
    <jsp:include page="../parts/navbar.jsp"/>


    <div class="container">
        <fmt:message key="add_feedback.review.of"/> <fmt:message key="common.quotation.mark"/>${movie.title}<fmt:message key="common.quotation.mark"/>

            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="movie_id" value="${movie.movieId}">
                <input type="hidden" name="command" value="add_feedback">
                <label for="scores"><fmt:message key="add_feedback.score"/></label>
                <select class="form-select" aria-label="Default select example" name="feedback_score" id="scores">
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                    <option value="4">Four</option>
                    <option value="5">Five</option>
                    <option value="6">Six</option>
                    <option value="7">Seven</option>
                    <option value="8">Eight</option>
                    <option value="9">Nine</option>
                    <option value="10">Ten</option>
                </select>

                <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label"><fmt:message key="add_feedback.feedback"/></label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="5" name="feedback_content"></textarea>
                </div>
                <button type="submit" class="btn btn-link">
                    <fmt:message key="add_feedback.button.post"/>
                </button>
            </form>
    </div>

    <jsp:include page="../parts/footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
