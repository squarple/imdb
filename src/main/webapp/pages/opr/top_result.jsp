<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${movies_list}" var="movie" varStatus="status">
        <div>
            ${movie.title} - ${norm_prices[status.index]}
        </div>
    </c:forEach>
</body>
</html>
