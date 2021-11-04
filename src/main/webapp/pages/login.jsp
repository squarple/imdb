<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title><fmt:message key="title.login"/></title>
    </head>
    <body onload="changeHashOnLoad();">
        <jsp:include page="parts/navbar.jsp"/>
        
        <div class="container">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="sign_in">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingInput" placeholder="<fmt:message key="login.label.login"/>" name="login" required minlength="1" maxlength="30"/>
                    <label for="floatingInput">
                        <fmt:message key="login.label.login"/>
                    </label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="<fmt:message key="login.label.password"/>" name="password"  required minlength="8" maxlength="30"/>
                    <label for="floatingPassword">
                        <fmt:message key="login.label.password"/>
                    </label>
                </div>
                <div>
                    <c:if test="${not empty error_message}">
                        <div class="badge bg-danger text-wrap" style="width: 12rem;">
                                ${error_message}
                        </div>
                    </c:if>
                </div>
                <button class="btn btn-primary mt-3" type="submit">
                    <fmt:message key="login.button.sign.in"/>
                </button>
            </form>

            <div class="mt-3">
                <form action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="move_to_signup_page">
                    <button class="btn btn-primary" type="submit">
                        <fmt:message key="login.button.sign.up"/>
                    </button>
                </form>
            </div>
            <br>
        </div>

        <jsp:include page="parts/footer.jsp"/>
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
