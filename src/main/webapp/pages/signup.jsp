<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title><fmt:message key="title.signup"/></title>
    </head>
    <body>
        <jsp:include page="parts/navbar.jsp"></jsp:include>
        
        <div class="container">
            
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="sign_up">
                <input type="hidden" name="page_from" value="signup_page">
                <input type="hidden" name="page_to" value="verify_email_page">
            
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingName" placeholder="name" name="first_name">
                    <label for="floatingName">Name</label>
                </div>
            
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingSurname" placeholder="surname" name="surname">
                    <label for="floatingSurname">Surname</label>
                </div>
                
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingEmail" placeholder="name@example.com" name="email">
                    <label for="floatingEmail">Email address</label>
                </div>
                
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingLogin" placeholder="login" name="login">
                    <label for="floatingLogin">Login</label>
                </div>
                
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="password" name="password">
                    <label for="floatingPassword">Password</label>
                </div>
                
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingRepeatPassword" placeholder="Password" name="repeated_password">
                    <label for="floatingRepeatPassword">Repeat password</label>
                </div>
                
                <div>
                    <button class="btn btn-primary" type="submit">Create your IMDb account</button>
                </div>
            </form>

            <br>
            <hr>
            <h1>${page_to}</h1>
        </div>
    
        <jsp:include page="parts/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
