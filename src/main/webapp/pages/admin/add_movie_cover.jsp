<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title><fmt:message key="title.add_movie_cover"/></title>
    </head>
    <body onload="changeHashOnLoad();">
        <jsp:include page="../parts/navbar.jsp"/>

        <div class="container">
            <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data" method="post">
                <input type="hidden" name="movie_id" value="${movie.movieId}">
                <input type="hidden" name="command" value="add_movie_cover">
                <div class="mb-3">
                    <label for="formFile" class="form-label">
                        <fmt:message key="add_movie_cover.label.cover.for"/> <fmt:message key="common.quotation.mark"/>${movie.title}<fmt:message key="common.quotation.mark"/>
                    </label>
                    <input class="form-control" type="file" id="formFile" name="movie_cover" accept="image/png,image/jpeg" required>
                </div>
                <button type="submit" class="btn btn-link">
                    <fmt:message key="add_movie_cover.button.submit"/>
                </button>
            </form>
        </div>

        <jsp:include page="../parts/footer.jsp"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script type="text/javascript">
            <%@include file="../../script/returnScript.js"%>
        </script>
    <script>
        var uploadField = document.getElementById("formFile");

        uploadField.onchange = function() {
            if(this.files[0].size > 5242880){
                alert("File is too big!");
                this.value = "";
            };
        };
    </script>
    </body>
</html>
