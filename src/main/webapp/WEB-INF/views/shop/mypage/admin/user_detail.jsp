<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="table-responsive">
    <table class="table table-striped table-sm">
        <tbody>
        <!-- todo view 구현 -->
        <c:set var="user" value="${requestScope.user}" scope="page"/>
        <tr>
            <th scope="row">아이디</th>
            <td>${user.userId}</td>
        </tr>
        <tr>
            <th scope="row">이름</th>
            <td>${user.userName}</td>
        </tr>
        <tr>
            <th scope="row">비밀번호</th>
            <td>${user.userPassword}</td>
        </tr>
        <tr>
            <th scope="row">생년월일</th>
            <td>${user.userBirth}</td>
        </tr>
        <tr>
            <th scope="row">권한</th>
            <td>${user.userAuth}</td>
        </tr>
        <tr>
            <th scope="row">포인트</th>
            <td>${user.userPoint}</td>
        </tr>
        <tr>
            <th scope="row">등록일</th>
            <td>${user.createdAt}</td>
        </tr>
        <tr>
            <th scope="row">최근 로그인 날짜</th>
            <td>${user.latestLoginAt}</td>
        </tr>
        </tbody>
    </table>
</div>
<button class="btn btn-primary" type="button" onclick="location.href='/admin/menu.do'">뒤로 가기</button>
