<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhn
  Date: 2023/11/08
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h2>최근 본 상품</h2>
            <div class="btn-toolbar mb-2 mb-md-0">
                <form action="/index.do" method="post">
                    <label for="category"></label>
                    <select name="category" id="category">
                        <option value="0">카테고리 선택</option>
                        <c:forEach var="category" items="${requestScope.category}">
                            <option value="${category.categoryId}"
                                    <c:if test="${category.categoryId eq defaultCategoryId}">selected</c:if>>
                                    ${category.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                    <button type="submit">정렬하기</button>
                </form>
            </div>
        </div>
    </div>
</header>

<div>
    <section>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <!-- 최근 본 상품 -->
            <c:forEach var="product" items="${requestScope.latestProduct}" varStatus="status1">
                <div class="col" style="width: 20%;">
                    <div class="card shadow-sm" onclick="location.href='/index/product.do?id=${product.productId}'">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                             preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">Image Not Found</text>
                            <image href="/resources/${product.thumbnail}" width="100%" height="100%"></image>
                        </svg>
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-center">
                                    ${product.productName}
                            </div>
                            <p class="card-text">${product.description}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</div>
<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
    <c:forEach var="product" items="${requestScope.products.getContent()}" varStatus="status1">
        <div class="col">
            <div class="card shadow-sm" onclick="location.href='/index/product.do?id=${product.productId}'">
                <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                     xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                     preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                    <text x="50%" y="50%" fill="#eceeef" dy=".3em">Image Not Found</text>
                    <image href="${product.thumbnail}" width="100%" height="100%"></image>
                </svg>
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                            ${product.productName}
                    </div>
                    <p class="card-text">${product.description}</p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:forEach var="index" begin="${requestScope.startPage}" end="${requestScope.endPage}" step="1">
            <li class="page-item">
                <a class="page-link" href="/index.do?page=${index}">
                        ${index}
                </a>
            </li>
        </c:forEach>
    </ul>
</nav>