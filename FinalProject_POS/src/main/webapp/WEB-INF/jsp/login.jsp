<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        form {
            margin-top: 20px;
            max-width: 300px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"],
        input[type="password"] {
            padding: 10px;
            width: 90%;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #333;
            color: #fff;
            padding: 10px 20px;
            border: 2;
            cursor: pointer;
            width: 100%;
            border-radius: 3px;
        }

        input[type="submit"]:hover {
            background-color: #555;
        }
    </style>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Harin POS</h1>
	<p style="color: red; text-align: center; font-weight: bold;">${message}</p>
    <form method="POST">
        <label for="userId">Id:</label>
        <input type="text" id="userId" name="userId"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>
        <input type="submit" formaction="/join" value="Member JOIN"> <br><br>
        <input type="submit" formaction="/main" value="Login">
    </form>

</body>
</html>