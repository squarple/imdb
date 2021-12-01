<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Paired comparisons</title>
    </head>
    <body onload="changeHashOnLoad();">
        <jsp:include page="../parts/navbar.jsp"/>
        <div class="container">
            <form method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="get_top_paired_comparisons">
                <c:forEach items="${pair_list}" var="pair" varStatus="status">
                    <div>
                        ${pair.movie1.title} or ${pair.movie2.title}?
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${status.index}" id="${pair.movie1}" checked value="1">
                                <label class="form-check-label" for="${pair.movie1}">
                                    ${pair.movie1.title}
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="${status.index}" id="${pair.movie2}" value="0">
                                <label class="form-check-label" for="${pair.movie2}">
                                    ${pair.movie2.title}
                                </label>
                            </div>
                    </div>
                </c:forEach>
                <button type="submit" class="btn btn-link">
                    Poluchit' personalniy top
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
