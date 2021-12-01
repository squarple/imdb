<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Choose method</title>
</head>
<body onload="changeHashOnLoad();">
    <jsp:include page="../parts/navbar.jsp"/>
    <div class="container">
        <div>
            <form method="get" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="method_of_paired_comparisons">
                <button type="submit" class="btn btn-link">
                    metod parnyh sravneniy
                </button>
            </form>
        </div>

        <div>
            <form method="get" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="method_of_weighing_assessments">
                <button type="submit" class="btn btn-link">
                    The method of weighing assessments / metod vzveshivaniya ocenok
                </button>
            </form>
        </div>
    </div>







    <jsp:include page="../parts/footer.jsp"/>
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
