<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title><fmt:message key="title.signup"/></title>
    </head>
    <body>
        <jsp:include page="parts/navbar.jsp"/>
        
        <div class="container">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="sign_up">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingName" placeholder="<fmt:message key="signup.label.name"/>" name="first_name" <c:if test="${not empty first_name}">value="${first_name}"</c:if> >
                    <label for="floatingName">
                        <fmt:message key="signup.label.name"/>
                    </label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingSurname" placeholder="<fmt:message key="signup.label.surname"/>" name="surname" <c:if test="${not empty surname}">value="${surname}"</c:if>>
                    <label for="floatingSurname">
                        <fmt:message key="signup.label.surname"/>
                    </label>
                </div>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingEmail" placeholder="name@example.com" name="email" <c:if test="${not empty email}">value="${email}"</c:if>>
                    <label for="floatingEmail">
                        <fmt:message key="signup.label.email.address"/>
                    </label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingLogin" placeholder="<fmt:message key="signup.label.login"/>" name="login" <c:if test="${not empty login}">value="${login}"</c:if>>
                    <label for="floatingLogin">
                        <fmt:message key="signup.label.login"/>
                    </label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="<fmt:message key="signup.label.password"/>" name="password">
                    <label for="floatingPassword">
                        <fmt:message key="signup.label.password"/>
                    </label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingRepeatPassword" placeholder="<fmt:message key="signup.label.password"/>" name="repeated_password">
                    <label for="floatingRepeatPassword">
                        <fmt:message key="signup.label.repeat.password"/>
                    </label>
                </div>
                <div>
                    <c:if test="${not empty error_message}">
                        <div class="badge bg-danger text-wrap" style="width: 6rem;">
                                ${error_message}
                        </div>
                    </c:if>
                </div>
                <div>
                    <button class="btn btn-primary mt-3" type="submit">
                        <fmt:message key="signup.label.create.account"/>
                    </button>
                </div>
            </form>
        </div>
    
        <jsp:include page="parts/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
