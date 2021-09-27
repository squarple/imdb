<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title><fmt:message key="title.get_users"/></title>
    </head>
    <body>
        <jsp:include page="../parts/navbar.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>    
        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Login</th>
                    <th scope="col">Role</th>
                    <th scope="col"></th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users_list}">
                        <tr>
                            <th scope="row">1</th>
                            <td>${user.login}</td>
                            <td>${user.role}</td>
                            <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="page_from" value="get_users">
                                <input type="hidden" name="command" value="change_user_role">
                                <input type="hidden" name="login" value="${user.login}">
                                <button type="submit" class="btn btn-link">
                                <c:choose>
                                    <c:when test="${user.role == 'USER'}">
                                        enable admin rights
                                    </c:when>
                                    <c:when test="${user.role == 'ADMIN'}">
                                        disable admin rights
                                    </c:when>
                                </c:choose>
                                </button>
                            </form>
                            </td>
                            <td>${user.status}</td>
                            <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="page_from" value="get_users">
                                <input type="hidden" name="command" value="change_user_status">
                                <input type="hidden" name="login" value="${user.login}">
                                <button type="submit" class="btn btn-link">
                                <c:choose>
                                    <c:when test="${user.status == 'ACTIVATED'}">
                                        bane
                                    </c:when>
                                    <c:when test="${user.status == 'BANNED'}">
                                        activate
                                    </c:when>
                                    <c:when test="${user.status == 'NON_ACTIVATED'}">
                                        activate
                                    </c:when>
                                </c:choose>
                                </button>
                            </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="../parts/footer.jsp"/>
    </body>
</html>
