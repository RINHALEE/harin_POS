<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>결제</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #cccccc;
        }

        th {
            background-color: #f2f2f2;
            color: #333333;
        }

        form {
            margin-top: 20px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"], input[type="submit"] {
            margin-top: 5px;
            padding: 5px;
        }

        input[type="submit"] {
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }

        .button {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
        }

        .button-update {
            background-color: #c8e6c9;
            color: #333;
        }

        .button-delete {
            background-color: #ffcdd2;
            color: #333;
        }

        .button-add {
            background-color: #bbdefb;
            color: #333;
        }

        .button:hover {
            opacity: 0.8;
        }

        .header {
            background-color: #333;
            padding: 10px;
            color: #fff;
        }

        .footer {
            background-color: #333;
            padding: 10px;
            color: #fff;
            text-align: center;
            margin-top: 30px;
        }

	.button-ex {
		background-color: 	#B0C4DE;
		color: #333;
	}
    </style>
</head>
<body>
    <div class="header">
        <h1>결제</h1>
    </div>
<div style="text-align: right;">
        <a href="/goSale" class="button button-ex">결제</a>
    </div>
    <h2>제품 검색</h2>
    <form method="post" action="/searchProduct">
        <label for="productName">제품명:</label>
        <input type="text" id="name" name="name">
        <input type="submit" value="검색">
        <a href="/sell" class="button button-ex">제품 전체</a>
    </form>

    <h2>제품 목록</h2>
    <table>
        <tr>
            <th>제품 코드</th>
            <th>가격</th>
            <th>수량</th>
            <th>제품명</th>
            <th>구매</th>
        </tr>
        <c:forEach var="product" items="${products}" varStatus="status">
            <tr>
                <td>${product.code}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.name}</td>
<td>
		<form method="post" action="/purchaseAdd?name=${product.name}">
                        	<button type="submit">구매</button>
                        </form>
	</td>
            </tr>
        </c:forEach>
    </table>
<script>
    function goBack() {
        history.back();
    }
</script>
    <div class="footer">
        <a href="javascript:goBack()" style="display: inline-block; padding: 10px 20px; background-color: #f2f2f2; color: #333; text-decoration: none; border-radius: 4px; border: 1px solid #ccc;">뒤로가기</a>
    </div>
</body>
</html>
