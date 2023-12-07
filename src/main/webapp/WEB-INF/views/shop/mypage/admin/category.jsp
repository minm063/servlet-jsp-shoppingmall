<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>no</th>
            <th>name</th>
            <th>delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${requestScope.categoryList.getContent()}" varStatus="status">
            <tr>
                <td>
                    <a href="/admin/category/detail.do?id=${category.categoryId}">${status.index+1+(requestScope.page-1)*10}</a>
                </td>
                <td>
                        ${category.categoryName}
                </td>
                <td>
                    <form action="/admin/category/delete.do?id=${category.categoryId}" method="post">
                        <button class="btn btn-primary">delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<button class="btn btn-primary" onclick="location.href='/admin/category/detail.do'">카테고리 추가하기</button>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:forEach var="index" begin="${requestScope.startPage}" end="${requestScope.endPage}" step="1">
            <li class="page-item">
                <a class="page-link" href="/admin/category.do?page=${index}">
                        ${index}
                </a>
            </li>
        </c:forEach>
    </ul>
</nav>