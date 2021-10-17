<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title><fmt:message key="title.verify_email"/></title>
    </head>
    <body onload="changeHashOnLoad();">
        <jsp:include page="parts/navbar.jsp"/>
        
        <div class="container">
            <b><fmt:message key="verify_email.label.verify.email.address"/></b>
            <b><fmt:message key="verify_email.label.verify.email.address.definition"/> ${email_address}</b>
        
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="email_verification">
                <div>
                    <b><fmt:message key="verify_email.label.enter.password.from.email"/></b>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingPassword" placeholder="password" name="password">
                    <label for="floatingPassword">
                        <fmt:message key="verify_email.label.password"/>
                    </label>
                </div>
                <div>
                    <button class="btn btn-primary" type="submit">
                        <fmt:message key="verify_email.label.create.account"/>
                    </button>
                </div>
            </form>
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
