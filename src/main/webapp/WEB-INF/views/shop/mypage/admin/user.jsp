<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>no</th>
            <th>name</th>
            <th>password</th>
            <th>birth</th>
            <th>auth</th>
            <th>point</th>
            <th>created at</th>
            <th>latest login at</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="user" items="${requestScope.users.getContent()}" varStatus="status">
            <tr>
                <td>
                    <a href="/admin/userDetail.do?id=${user.userId}">${status.index+1+(requestScope.page-1)*10}</a>
                </td>
                <td>${user.userName}</td>
                <td>${user.userPassword}</td>
                <td>${user.userBirth}</td>
                <td>${user.userAuth}</td>
                <td>${user.userPoint}</td>
                <td>${user.createdAt}</td>
                <td>${user.latestLoginAt}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:forEach var="index" begin="${requestScope.startPage}" end="${requestScope.endPage}" step="1">
            <li class="page-item">
                <a class="page-link" href="/admin/user.do?page=${index}&id=${auth}">
                        ${index}
                </a>
            </li>
        </c:forEach>
    </ul>
</nav>