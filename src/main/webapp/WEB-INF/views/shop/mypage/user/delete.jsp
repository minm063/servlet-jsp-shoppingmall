<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<form action="/mypage/deleteAction.do" method="post">
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">회원 탈퇴</h1>
                <p class="lead text-body-secondary">
                    회원 탈퇴시 회원의 모든 정보가 삭제됩니다. 탈퇴를 위해 아래에 <strong>breakaway</strong>를 적어주세요.
                </p>
            </div>
        </div>
    </section>
    <div class="album py-5 bg-body-tertiary">
        <div class="container">
            <input type="text" placeholder="breakaway" name="deleteText">
            <button class="btn btn-primary" type="submit">완료하기</button>
        </div>
    </div>
</form>
