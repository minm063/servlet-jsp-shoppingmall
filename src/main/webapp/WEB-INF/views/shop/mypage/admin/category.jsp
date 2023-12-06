<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-responsive">
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${requestScope.categories}">
            <tr>
                <td>
                    <a href="/mypage/admin/category/detail.do?id=${category.categoryId}">${category.categoryId}</a>
                </td>
                <td>
                        ${category.categoryName}
                </td>
                <td>
                    <form action="/mypage/admin/category/delete.do?id=${category.categoryId}" method="post">
                        <button class="btn btn-primary">delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<button class="btn btn-primary" onclick="location.href='/mypage/admin/category/detail.do'">카테고리 추가하기</button>
