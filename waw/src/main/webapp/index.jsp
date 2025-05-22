<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つながりDiary</title>
</head>
<body>
	<link rel="stylesheet" href="CSS/style.css">
	<h1>つながりDiaryへようこそ</h1>

	<form action="Login" method="post">
		ユーザー名: <input type="text" name="name" maxlength="10"><br>
		パスワード: <input type="password" name="pass" maxlength="4"><br>
		<input type="submit" value="ログイン">
	</form>
</body>
</html>

<%-- トップ画面を出力するビュー --%>
