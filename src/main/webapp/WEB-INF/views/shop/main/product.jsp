<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="/cartAction.do" method="post" id="productForm" name="productForm">
    <div class="card mb-3">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="${product.productImage}" class="img-fluid rounded-start" alt="image not found">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <input type="hidden" value="${product.productId}" name="productId">
                    <p><small class="text-body-secondary">${product.productNumber}</small></p>
                    <h2 class="card-title">${product.productName}</h2>
                    <h3>${product.unitCost}원</h3>
                    <p class="card-text"><input type="number" name="quantity" value="0">개</p>
                    <p><small class="text-body-secondary">description</small></p>
                    <p class="card-text">${product.description}</p>
                    <button type="submit">장바구니에 넣기</button>
                </div>
            </div>
        </div>
    </div>
</form>
<button class="btn btn-primary" onclick="location.href='/index.do'">뒤로 가기</button>