<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>가입</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
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
        input[type="checkbox"] {
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
    </style>

<script>
        function validateForm() {
            var id = document.getElementById("id").value;
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var position = document.querySelector('input[name="position"]:checked');
            var name = document.getElementById("name").value;

            if (id === "" || password === "" || confirmPassword === "" || !position || name === "") {
                alert("필수 필드를 모두 입력해주세요.");
                return false;
            }

	if (password !== confirmPassword) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다. 다시 입력해주세요.");
            return false;
        }
        }
    </script>
</head>
<body>
<div class="header">
    <h1>가입</h1>
</div>
    <form action="FinishJoin" method="post" onsubmit="return validateForm()">
        <p> <label>아이디: <br><input type="text" name="id" id="id" value="${registerRequest.id}"></label> </p>
        <p> <label>비밀번호:<br><input type="password" name="password" id="password"></label> </p>
        <p> <label>비밀번호확인:<br><input type="password" name="confirmPassword" id="confirmPassword"></label> </p>
        <p> <label>직급:<br>
            <input type="radio" name="position" value="직원"> 직원
            <input type="radio" name="position" value="매니저"> 매니저
        </label> </p>
        <p> <label>이름:<br><input type="text" name="name" id="name" value="${registerRequest.name}"></label> </p>
        <input type="submit" value="가입완료">
    </form>
</body>
</html>
