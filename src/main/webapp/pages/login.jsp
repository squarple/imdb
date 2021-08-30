<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Login</title>
</head>
<body>
<jsp:include page="parts/navbar.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="sign_in"> <!-- COMMAND -->
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput" placeholder="login" name="login">
        <label for="floatingInput">login</label>
    </div>
    <div class="form-floating mb-3">
        <input type="password" class="form-control" id="floatingPassword" placeholder="password" name="password"/>
        <label for="floatingPassword">password</label>
    </div>
    <button class="btn btn-primary" type="submit">Sign In</button>
</form>
</body>
</html>
