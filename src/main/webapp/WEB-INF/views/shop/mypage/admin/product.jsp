<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="table-responsive">


    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th>id</th>
            <th colspan="3">category</th>
            <th>number</th>
            <th>name</th>
            <th>unit cost</th>
            <th>description</th>
            <th>image</th>
            <th>thumbnail</th>
            <th>delete</th>
        </tr>
        </thead>
        <tbody>


        <c:forEach var="product" items="${requestScope.products}" varStatus="status">
            <tr>
                <td>
                    <a href="/mypage/admin/product/detail.do?id=${product.productId}">${product.productId}</a>
                </td>
                <td colspan="3">
                    <c:forEach var="category" items="${requestScope.myCategory[status.index]}" varStatus="status2">
                        ${category}
                        <c:if test="${not status2.last}">, </c:if>
                    </c:forEach>
                </td>
                <td>${product.productNumber}</td>
                <td>${product.productName}</td>
                <td>${product.unitCost}</td>
                <td>${product.description}</td>
                <td>${product.productImage}</td>
                <td>${product.thumbnail}</td>
                <td>
                    <form action="/mypage/admin/product/delete.do?id=${product.productId}" method="post">
                        <button class="btn btn-primary">delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<button class="btn btn-primary" onclick="location.href='/mypage/admin/product/detail.do'">
    상품 추가하기
</button>
