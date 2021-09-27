<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Error Page</title>
    </head>
    <body>
    500
        Request from ${pageContext.errorData.requestURI} is failed <br/>
        Servlet name: ${pageContext.errorData.servletName} <br/>
        Status code: ${pageContext.errorData.statusCode} <br/>
        Exception: ${pageContext.exception} <br/>
        Message from exception: ${pageContext.exception.message}<br/>
        stack trace : <br/>
        <c:forEach items="${exception.stackTrace}" var="element">
            element =   ${element}
        </c:forEach>
        <br/>
        <c:forEach var = "i" begin = "1" end = "5">
            Item <c:out value = "${i}"/><p>
        </c:forEach>
        <a href="index.jsp">Back to index</a>
    </body>
</html>