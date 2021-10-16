<%@ taglib prefix="ctg" uri="http://mycompany.com" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<div class="container">
    <footer class="footer">
        <div class="container mt-3">
            <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
                <p class="col-md-4 mb-0 text-muted">
                    <ctg:companyInfo/>
                </p>
            
                <a href="/" class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
                    <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
                </a>
            
                <ul class="nav col-md-4 justify-content-end">
                    <li class="nav-item">
                        <form id="footer_lang" action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="change_locale">
                            <a class="nav-link active px-2" href=# onclick="document.getElementById('footer_lang').submit()" tabindex="-1" aria-disabled="true">
                                <c:choose>
                                    <c:when test="${locale == 'ru_RU'}">
                                        <fmt:message key="footer.common.en"/>
                                    </c:when>
                                    <c:when test="${locale == 'en_EN'}">
                                        <fmt:message key="footer.common.ru"/>
                                    </c:when>
                                </c:choose>
                            </a>
                        </form>
                    </li>

                    <li class="nav-item">
                        <form id="footer_home" action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="page_to" value="main_page">
                            <input type="hidden" name="command" value="move_to">
                            <a class="nav-link active px-2" href=# onclick="document.getElementById('footer_home').submit()" tabindex="-1" aria-disabled="true"><fmt:message key="footer.common.home"/></a>
                        </form>
                    </li>
                    <li class="nav-item">
                        <form id="footer_films" action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="movie_type" value="film">
                            <input type="hidden" name="command" value="get_movie_list">
                            <a class="nav-link active px-2" href=# onclick="document.getElementById('footer_films').submit()" tabindex="-1" aria-disabled="true"><fmt:message key="footer.common.films"/></a>
                        </form>
                    </li>
                    <li class="nav-item">
                        <form id="footer_serials" action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="movie_type" value="serial">
                            <input type="hidden" name="command" value="get_movie_list">
                            <a class="nav-link active px-2" href=# onclick="document.getElementById('footer_serials').submit()" tabindex="-1" aria-disabled="true"><fmt:message key="footer.common.serials"/></a>
                        </form>
                    </li>
                </ul>
            </footer>
        </div>
    </footer>
</div>