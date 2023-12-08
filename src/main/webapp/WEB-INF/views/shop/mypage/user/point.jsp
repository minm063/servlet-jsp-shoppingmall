<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>no</th>
            <th>포인트 사용 내역</th>
            <th>포인트 사용 날짜</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="point" items="${requestScope.pointList.getContent()}" varStatus="status">
            <tr>
                <td>
                        ${status.index+1+(requestScope.page-1)*10}
                </td>
                <td>
                        ${point.pointChanged}
                </td>
                <td>
                        ${point.pointDate}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:forEach var="index" begin="${requestScope.startPage}" end="${requestScope.endPage}" step="1">
            <li class="page-item">
                <a class="page-link" href="/mypage/point/usage.do?page=${index}">
                        ${index}
                </a>
            </li>
        </c:forEach>
    </ul>
</nav>