<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>
            <fmt:message key="title.get_feedbacks"/>
        </title>
    </head>
    <body>
        <jsp:include page="../parts/navbar.jsp"/>

        <div class="container">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col"><fmt:message key="get_feedbacks.table.number"/></th>
                        <th scope="col"><fmt:message key="get_feedbacks.table.login"/></th>
                        <th scope="col"><fmt:message key="get_feedbacks.table.content"/></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="feedback" items="${feedback_list}">
                        <tr>
                            <th scope="row">1</th>
                            <td>${feedback.userId}</td>
                            <td>${feedback.content}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="change_feedback_status">
                                    <input type="hidden" name="feedback_status" value="approved">
                                    <input type="hidden" name="feedback_id" value="${feedback.feedbackId}">
                                    <button type="submit" class="btn btn-link">
                                        <fmt:message key="get_feedbacks.button.approve"/>
                                    </button>
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <input type="hidden" name="command" value="change_feedback_status">
                                    <input type="hidden" name="feedback_status" value="blocked">
                                    <input type="hidden" name="feedback_id" value="${feedback.feedbackId}">
                                    <button type="submit" class="btn btn-link">
                                        <fmt:message key="get_feedbacks.button.block"/>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>
