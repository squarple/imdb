<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Results of weighing assessments</title>
</head>
<body onload="changeHashOnLoad();">
<jsp:include page="../../parts/navbar.jsp"/>

    <c:forEach items="${movie_weight_map}" var="movie_weight">
        <div>
            ${movie_weight.key} - ${movie_weight.value.title}
        </div>
    </c:forEach>

<jsp:include page="../../parts/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script type="text/javascript">
    <%@include file="../../../script/returnScript.js"%>
</script>
</body>
</html>
