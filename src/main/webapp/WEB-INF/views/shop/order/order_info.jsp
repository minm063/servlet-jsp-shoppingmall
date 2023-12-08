<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty productList}">
    <h4>상품을 추가해주세요.</h4>
</c:if>

<form action="/orderAction.do" method="post">
    <h3 class="card-title">배송 정보</h3>
    <hr/>
    <div class="card mb-3" style="display: inline-block;">
        <c:forEach var="address" items="${addressList}" varStatus="status">
            <label for="addressRadio${status.index}" style="margin: 10px;">
                <input id="addressRadio${status.index}" type="radio" name="addressRadio" value="${address.addressId}">
                <input type="text" value="${address.address}">
            </label>
        </c:forEach>
    </div>
    <h5 style="">주문 상품</h5>
    <hr/>
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
                        <p class="card-text">${cartList.get(status.index).quantity}개</p>
                        <h3>${product.unitCost*cartList.get(status.index).quantity}원</h3>
                        <c:set var="sum" value="${product.unitCost*cartList.get(status.index).quantity}"/>
                        <c:set var="totalSum" value="${totalSum + sum}" />
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    <hr />
    <h4>결제 예정 금액</h4>
    <p>보유 포인트</p>
    <p>${userPoint}원</p>
    <p>상품 금액</p>
    <p>${totalSum}원</p>
    <p>잔액</p>
    <p><c:out value="${userPoint-totalSum}" />원</p>
    <button type="submit" class="btn btn-primary" >${totalSum}원 결제하기</button>
</form>