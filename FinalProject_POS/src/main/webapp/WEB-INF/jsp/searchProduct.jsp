<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>제품관리</title>
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
        <h1>제품 관리</h1>
    </div>

    <h2>제품 검색</h2>
    <form method="post" action="/searchProduct">
        <label for="productName">제품명:</label>
        <input type="text" id="name" name="name">
        <input type="submit" value="검색">
        <a href="/productControl" class="button button-ex">제품 전체</a>
    </form>

    <h2>제품 목록</h2>
    <table>
        <tr>
            <th>제품 코드</th>
            <th>가격</th>
            <th>수량</th>
            <th>제품명</th>
            <th>수정</th>
            <th>삭제</th>
<th>입고 수량</th>
            <th>입고</th>
        </tr>
        <c:forEach var="product" items="${products}" varStatus="status">
            <tr>
                <td>${product.code}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.name}</td>
                <td><a href="/updateProductF?code=${product.code}" class="button button-update">수정</a></td>
                <td><a href="/deleteProduct?code=${product.code}" class="button button-delete">삭제</a></td>
                <td>
                <form method="post" action="/addProduct/${product.code}">
                    <input type="text" id="addP" name="addP">
            </td>
            <td>
                <input type="submit" value="입고" class="button button-add">
                </form>
            </td>
            </tr>
        </c:forEach>
    </table>

    <div class="footer">
        <button class="button" onclick="location.href='/main'">Home</button>
<a href="/StockManagement" class="button button-add" style="float: right;">입고 관리</a>
        <button class="button" onclick="location.href='/logout'">로그아웃</button>
    </div>
</body>
</html>
