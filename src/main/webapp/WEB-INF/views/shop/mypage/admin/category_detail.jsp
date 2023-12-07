<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty category}">
    <c:set var="category" value="${category}"/>
</c:if>

<form action="${empty category?'/admin/category/create.do':'/admin/category/update.do'}" method="post">
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <tbody>
            <!-- todo view 구현 -->
            <tr>
                <th scope="row">아이디</th>
                <td><input type="text" value="${category.categoryId}" name="categoryId" readonly></td>
            </tr>
            <tr>
                <th scope="row">이름</th>
                <td><input type="text" value="${category.categoryName}" name="categoryName"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <button class="btn btn-primary" type="button" onclick="location.href='/admin.do'">뒤로 가기</button>
    <button class="btn btn-primary" type="submit">
        <c:choose>
            <c:when test="${empty category}">
                등록
            </c:when>
            <c:otherwise>
                수정
            </c:otherwise>
        </c:choose>
    </button>
</form>