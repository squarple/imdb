<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Results of paired comparisons</title>
</head>
<body onload="changeHashOnLoad();">
    <div class="container">
        <jsp:include page="../../parts/navbar.jsp"/>
        <br/>
        <div>Matrix of paired comparisons</div>
        <br/>
        <div>
            <c:forEach items="${matrix}" var="steps">
                <div>
                    <c:forEach items="${steps}" var="step">
                        <c:out value="${step}"/>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
        <br/>
        <br/>
        <div>Movie rating</div>
        <br/>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">weight</th>
                    <th scope="col">Title</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${norm_prices}" var="price_movie_pair">
                    <tr>
                        <th scope="row">${price_movie_pair.weight}</th>
                        <td>${price_movie_pair.movie.title}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


<jsp:include page="../../parts/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script type="text/javascript">
    <%@include file="../../../script/returnScript.js"%>
</script>
</body>
</html>
