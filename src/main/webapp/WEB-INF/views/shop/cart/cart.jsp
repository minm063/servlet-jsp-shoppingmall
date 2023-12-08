<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${empty productList}">
    <h4>상품을 추가해주세요.</h4>
</c:if>
<form action="/order.do" method="post">
<%--<button type="button" class="btn btn-primary" onclick="location.href='/order.do'">구매하기</button>--%>
<button type="submit" class="btn btn-primary" >구매하기</button>
<c:forEach var="product" items="${productList}" varStatus="status">
    <div class="card mb-3">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="/resources/${product.productImage}" class="img-fluid rounded-start" alt="image not found">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <p><small class="text-body-secondary">${product.productNumber}</small></p>
                    <h2 class="card-title">${product.productName}</h2>
                    <input type="number" name="quantities" value="${cartList.get(status.index).quantity}" min="0">개
                    <h3>${product.unitCost*cartList.get(status.index).quantity}원</h3>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
</form>