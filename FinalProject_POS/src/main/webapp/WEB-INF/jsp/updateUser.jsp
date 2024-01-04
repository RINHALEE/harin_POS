<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>계정 수정</title>
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
            background-color: #ffffff;
            border-radius: 5px;
            padding: 20px;
            max-width: 400px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"],
        input[type="password"] {
            width: 90%;
            padding: 10px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        input[type="radio"] {
            margin-bottom: 20px;
        }
        input[type="submit"] {
            background-color: #4caf50;
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
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
    </style>

<script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var position = document.querySelector('input[name="position"]:checked');
            var name = document.getElementById("name").value;

            if (password === "" || !position || name === "") {
                alert("필수 필드를 모두 입력해주세요.");
                return false;
            }

        }
    </script>
</head>
<body>
<div class="header">
    <h1>계정 수정</h1>
</div>


    <form method="post" action="/updateUser" onsubmit="return validateForm()">
        <p>
            <label for="id">계정 아이디: ${id}</label>
            <input type="hidden" name="id" value="${id}">
        </p>
        <p>
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password">
        </p>
        <p>
            <label for="position">직급: <br>
            <input type="radio" id="employee" name="position" value="직원"> 직원
            <input type="radio" id="manager" name="position" value="매니저"> 매니저
	</label>
        </p>
        <p>
            <label for="name">이름:</label>
            <input type="text" id="name" name="name">
        </p>
        <input type="submit" value="수정">
    </form>


<div class="footer">
<script>
    function goBack() {
        history.back();
    }
</script>
        <a href="javascript:goBack()" style="display: inline-block; padding: 10px 20px; background-color: #f2f2f2; color: #333; text-decoration: none; border-radius: 4px; border: 1px solid #ccc;">뒤로가기</a>
    </div>
</body>
</html>
