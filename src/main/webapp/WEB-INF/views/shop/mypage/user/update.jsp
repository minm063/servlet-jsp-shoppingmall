<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/mypage/updateAction.do" method="post">
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <tbody>
            <!-- todo view 구현 -->
            <c:set var="user" value="${requestScope.user}" scope="page"/>
            <tr>
                <th scope="row">아이디</th>
                <td><input type="text" value="${user.userId}" name="userId" readonly></td>
            </tr>
            <tr>
                <th scope="row">이름</th>
                <td><input type="text" value="${user.userName}" name="userName"></td>
            </tr>
            <tr>
                <th scope="row">비밀번호</th>
                <td><input type="text" value="${user.userPassword}" name="userPassword"></td>
            </tr>
            <tr>
                <th scope="row">생년월일</th>
                <td><input type="date" value="${requestScope.birth}" name="userBirth"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <button class="btn btn-primary" type="button" onclick="location.href='/admin.do'">취소</button>
    <button class="btn btn-primary" type="submit">수정하기</button>

</form>