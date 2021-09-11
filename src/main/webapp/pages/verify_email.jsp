<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="title.verify_email"/></title>
</head>
<body>
<jsp:include page="parts/navbar.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


<b>Verify email address</b>
<b>To verify your email, we've sent a One Time Password (6 знаков) (OTP) to radionoff.antony@gmail.com</b>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="email_verification">
    <input type="hidden" name="page_from" value="verify_email_page">
    <input type="hidden" name="page_to" value="main_page">

    <div><b>Enter OTP</b></div>
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingPassword" placeholder="password" name="password">
        <label for="floatingPassword">OTP Password</label>
    </div>

    <div>
        <button class="btn btn-primary" type="submit">Create your IMDb account</button>
    </div>
</form>
</body>
</html>
