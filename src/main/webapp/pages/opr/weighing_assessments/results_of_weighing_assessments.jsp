<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Results of weighing assessments</title>
    </head>
    <body onload="changeHashOnLoad();">
        <jsp:include page="../../parts/navbar.jsp"/>
        <div class="container">
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">User</th>
                        <th scope="col">Competency</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${user_competency}" var="one_user_competency">
                            <tr>
                                <th scope="col">${one_user_competency.key.name}</th>
                                <td>${one_user_competency.value}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">user</th>
                        <c:forEach items="${movie_list}" var="movie">
                            <th scope="col">${movie.title}</th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${user_scores}" var="one_user_scores">
                            <tr>
                                <th scope="row">${one_user_scores.key.name}</th>
                                <c:forEach items="${one_user_scores.value}" var="score">
                                    <td>${score.value}</td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">weight</th>
                            <th scope="col">Title</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${movie_weight_map}" var="one_movie_weight">
                            <tr>
                                <td>${one_movie_weight.key}</td>
                                <td>${one_movie_weight.value.title}</td>
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
