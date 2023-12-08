<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h5 style="">주문 내역</h5>
<hr/>
<c:forEach var="order" items="${orderList.getContent()}" varStatus="status1">
    주문 날짜 <h4>${order.orderDate}</h4>
    주소 <p class="card-title">${addressList}</p>
    <c:forEach var="orderDetail" items="${orderDetailList.get(status1.index)}" varStatus="status2">
        <div class="card mb-3">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="/resources/${product.get(status1.index).get(status2.index).productImage}" class="img-fluid rounded-start"
                         alt="image not found">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <p><small class="text-body-secondary">${product.get(status1.index).get(status2.index).productNumber}</small></p>
                        <h2 class="card-title">${product.get(status1.index).get(status2.index).productName}</h2>
                        <p class="card-text">${orderDetail.get(status2.index).quantity}개</p>
                        <h3>${orderDetail.get(status2.index).unitCost*orderDetail.get(status2.index).quantity}원</h3>
                        <c:set var="sum" value="${orderDetail.get(status2.index).unitCost*orderDetail.get(status2.index).quantity}"/>
                        <c:set var="totalSum" value="${totalSum + sum}"/>
                    </div>
                </div>
            </div>
            <p class="card-title">총 ${totalSum}원</p>
        </div>
    </c:forEach>
</c:forEach>