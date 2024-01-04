<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>제품 등록</title>
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

        form {
            width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="text"], input[type="submit"] {
            width: 90%;
            margin-top: 5px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>제품 등록</h1>
     <h2>${existProduct}</h2>
    <form method="post" action="/insertProduct">
        <label for="code">제품 코드:</label>
        <input type="text" id="code" name="code"><br>
        <label for="price">가격:</label>
        <input type="text" id="price" name="price" required><br>
        <label for="quantity">수량:</label>
        <input type="text" id="quantity" name="quantity" required><br>
        <label for="name">제품명:</label>
        <input type="text" id="name" name="name" required><br>
        <input type="submit" value="등록">
    </form>
</body>
</html>
