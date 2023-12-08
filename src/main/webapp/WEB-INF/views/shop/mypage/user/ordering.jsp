<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>order</h1>


order -> order date, ship date, address id, user id(<=), order id
address id -> address name
order id -> order_detail( product_id, quantity, unit_cost)
product_id -> productList

order list <foreach></foreach>
address는 주문 당 하나니까 status.index?
list 안에서 또 order 반복, List<<List<OrderDetail>>
