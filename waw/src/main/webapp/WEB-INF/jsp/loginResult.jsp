<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 <%@ page import="model.User" %>

<%-- セッションスコープからユーザー情報を取得 --%>
<% 
User loginUser = (User)session.getAttribute("loginUser");
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つながりDiary</title>
</head>
<body>
<link rel="stylesheet" href="CSS/style.css">
<h1>つながりDiaryログイン</h1>
<% if (loginUser != null) { %>
	<p>ログインに成功しました</p>
	<p>ようこそ<%= loginUser.getName() %>さん</p>
	<a href="Main">日記投稿・閲覧へ</a>
<% } else { %>
	<p>ログインに失敗しました</p>
	<a href="index.jsp">TOPへ</a>
<% } %>
</body>
</html>

<%-- ログイン結果画面を出力するビュー --%>