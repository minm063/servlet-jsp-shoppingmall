<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main class="form-signin w-100 m-auto">
    <form method="post" action="/mypage/user/address/updateAction.do">
        <h1 class="h3 mb-3 fw-normal">type address</h1>

        <div class="form-floating">
            <input type="hidden" name="addressId" value="${requestScope.address.addressId}">
            <input type="text" name="address" class="form-control" id="floatingInput" value="${requestScope.address.address}">
        </div>

        <button class="btn btn-primary w-100 py-2" type="submit">수정하기</button>
        <button class="btn btn-primary" type="button" onclick="location.href='/mypage/menu.do'">취소</button>

        <p class="mt-5 mb-3 text-body-secondary">© 2017–2023</p>
    </form>
</main>