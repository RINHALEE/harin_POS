<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session = "true" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Harin_POS</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        
        .header {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
        }
        
        .content {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
        }
        
       .button {
            margin-bottom: 10px;
            padding: 14px 28px;
            background-color: #c8e6c9;
            color: #333;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 18px;
        }

        .button:hover {
             opacity: 0.8;
        }
        .button-large {
            padding: 40px 60px;
            font-size: 20px;
background-color: #bbdefb;
            color: #333;
        }
        
	.button-logout {
            background-color: #ffcdd2;
            color: #333;
        }

.footer {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
        }
.pay-amount {
            text-align: center;
            font-weight: bold;
        }
    </style>
</head>
<body>
	<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
    String currentTime = dateFormat.format(new java.util.Date());
%>
    <div class="header">
    <div style="display: flex; justify-content: space-between;">
        <h1 style="margin-right: auto;">Harin_POS</h1>
        <p style="text-align: center;"><%= currentTime %></p>
    </div>
</div>
 <p class="pay-amount"> ${pay} </p>
    
    <div class="content">
            <button class="button button-large" onclick="location.href='/sell'">결제</button>
	<button class="button button-large" onclick="location.href='/dailySales'">통계</button>
    </div>
    
    <div class="footer">
        <button class="button" onclick="location.href='/productControl'">제품관리</button>
        <button class="button" onclick="location.href='/account'">계정관리</button>
	<button class="button button-logout" onclick="location.href='/logout'">로그아웃</button>
    </div>
</body>
</html>
