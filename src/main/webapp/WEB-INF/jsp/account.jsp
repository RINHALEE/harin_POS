<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>계정 관리 페이지</title>
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

        input[type="text"],
        input[type="submit"] {
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
padding: 20px 40px;
	}


    </style>
</head>
<body>
<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
    String currentTime = dateFormat.format(new java.util.Date());
%>
    <div class="header">
    <h1>계정 관리 페이지</h1>
</div>
	<div style="text-align: right;">
        <a href="/join" class="button button-ex">가입</a>
    </div>
 <div class="content">
    <table>
        <tr>
            <th class="index">번호</th>
            <th class="id">아이디</th>
            <th class="password">비밀번호</th>
            <th class="position">직급</th>
            <th class="name">이름</th>
<th>수정</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="user" items="${users}" varStatus="status">
            <tr>
                <td class="index">${status.index+1}</td>
                <td class="id">${user.id}</td>
                <td class="password">${user.password}</td>
                <td class="position">${user.position}</td>
                <td class="name">${user.name}</td>
	    <td><a href="/updateUserF?id=${user.id}" class="button button-update">수정</a></td>
                <td><a href="/deleteUser?id=${user.id}" class="button button-delete">삭제</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="footer">
        <button class="button" onclick="location.href='/main'">Main</button>
    </div>
</body>
</html>
