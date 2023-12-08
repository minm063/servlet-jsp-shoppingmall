<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${not empty product}">
    <c:set var="product" value="${product}"/>
</c:if>

<form action="${empty product?'/admin/product/create.do':'/admin/product/update.do'}" method="post"
      enctype="multipart/form-data">

    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <tbody>
            <tr>
                <th scope="row">product id</th>
                <td><input type="text" value="${product.productId}" name="productId" readonly></td>
            </tr>
            <c:forEach var="defaultValue" items="${requestScope.defaultCategory}" varStatus="status">
                <tr>
                    <th scope="row">category ${status.index+1}</th>
                    <td>
                        <select name="category${status.index+1}" id="category${status.index+1}">
                            <option value="0">선택하지 않음</option>
                            <c:forEach var="category" items="${requestScope.categoryList}">
                                <option value="${category.categoryId}"
                                        <c:if test="${category.categoryId eq defaultValue}">selected</c:if>
                                >
                                        ${category.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <th scope="row">product number</th>
                <td><input type="text" value="${product.productNumber}" name="productNumber" readonly></td>
            </tr>
            <tr>
                <th scope="row">product name</th>
                <td><input type="text" value="${product.productName}" name="productName" required></td>
            </tr>
            <tr>
                <th scope="row">cost</th>
                <td><input type="number" value="${product.unitCost}" name="unitCost" required></td>
            </tr>
            <tr>
                <th scope="row">재고</th>
                <td><input type="number" value="${product.stock}" name="stock"></td>
            </tr>
            <tr>
                <th scope="row">description</th>
                <td><input type="text" value="${product.description}" name="description"></td>
            </tr>
            <c:if test="${not empty product}">
                <tr>
                    <th scope="row">기존 이미지</th>
                    <td>
                        <img src="${product.productImage}" alt="기존 이미지">
                    </td>
                </tr>
                <tr>
                    <th scope="row">기존 이미지 삭제</th>
                    <td>
                        <input type="checkbox" id="deletePreviousProductImage" name="deletePreviousProductImage" value="delete">
                    </td>
                </tr>
            </c:if>
            <tr>
                <th scope="row">product image</th>
                <td>
                    <input type="file" name="productImage" accept="image/*">
                    <input type="hidden" name="previousProductImage" value="${product.productImage}">
                </td>
            </tr>
            <c:if test="${not empty product}">
                <tr>
                    <th scope="row">기존 썸네일</th>
                    <td>
                        <img src="${product.thumbnail}" alt="기존 썸네일">
                    </td>
                </tr>
                <tr>
                    <th scope="row">기존 이미지 삭제</th>
                    <td>
                        <input type="checkbox" id="deletePreviousThumbnail" name="deletePreviousThumbnail" value="delete">
                    </td>
                </tr>
            </c:if>

            <tr>
                <th scope="row">thumbnail</th>
                <td>
                    <input type="file" name="thumbnail" accept="image/*">
                    <input type="hidden" name="previousThumbnail" value="${product.thumbnail}">
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <button class="btn btn-primary" type="button" onclick="location.href='/admin/menu.do'">뒤로 가기</button>
    <button class="btn btn-primary" type="submit">
        <c:choose>
            <c:when test="${empty product}">
                등록
            </c:when>
            <c:otherwise>
                수정
            </c:otherwise>
        </c:choose>
    </button>

</form>