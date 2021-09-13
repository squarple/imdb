<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title><fmt:message key="title.login"/></title>
    </head>
    <body>
        <jsp:include page="parts/navbar.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <div class="container">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="sign_in">
                <input type="hidden" name="page_from" value="login_page">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingInput" placeholder="login" name="login">
                    <label for="floatingInput">login</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="password" name="password"/>
                    <label for="floatingPassword">password</label>
                </div>
                <button class="btn btn-primary" type="submit">Sign in</button>
            </form>
            <div>
                <form action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="page_from" value="login_page">
                    <input type="hidden" name="page_to" value="signup_page">
                    <button class="btn btn-primary" type="submit" name="command" value="move_to">Sign up</button>
                </form>
            </div>
            <br>
            <hr>
            <h1>${page_to}</h1>
        </div>

        <jsp:include page="parts/footer.jsp"/>
    </body>
</html>
