<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/mypage/address/create.do" method="post">
    <input type="text" name="address" required>
    <button class="btn btn-primary">주소 추가하기</button>
</form>
<form action="/mypage/address/delete.do" method="post">
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>no</th>
                <th>address</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="address" items="${requestScope.addressList}" varStatus="status">
                <tr>
                    <th scope="row">${status.index+1}
                    </th>
                    <th>${address.address}</th>
                    <th>
                        <a href="/mypage/address/update.do?id=${address.addressId}">수정</a>
                    </th>
                    <th>
                        <input type="hidden" name="id" value="${address.addressId}">
                        <button class="btn btn-primary" type="submit">삭제</button>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>
<button class="btn btn-primary" type="button" onclick="location.href='/mypage/menu.do'">뒤로 가기</button>
