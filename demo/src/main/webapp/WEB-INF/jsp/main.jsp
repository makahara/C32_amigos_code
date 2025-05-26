<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記一覧</title>
<link rel="stylesheet" href="CSS/style.css">
<link rel="stylesheet" href="CSS/style04.css">
<style>
table {
	border-collapse: collapse;
	width: 550px; /* 「どこつぶ」のテーブル幅に合わせる */
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: left;
}

.post-form {
	margin-bottom: 20px;
	padding: 15px;
	border: 1px solid #eee;
	background-color: #f9f9f9;
}

.form-group {
	margin-bottom: 10px;
}

.form-group label {
	display: inline-block;
	width: 80px;
	text-align: left;
}

.error-message {
	color: red;
	margin-top: 10px;
}
</style>
</head>
<body>
	<h1>日記一覧</h1>
	<p>
		<c:out value="${loginUser.name }" />
		さん、ログイン中 <a href="Logout">ログアウト</a> <a href="DiaryFormServlet">日記を追加</a>
	</p>
	<c:if test="${not empty errorMsg}">
		<p class="error-message">
			<c:out value="${errorMsg}" />
		</p>
	</c:if>
	<h2>投稿された日記</h2>
	<table>
		<thead>
			<tr>
				<th>アイコン</th>
				<th>タイトル</th>
				<th>投稿者</th>
				<th>日時</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="diary" items="${mutterList}">
				<tr>
					<td><c:if test="${not empty diary.iconImage}">
							<img src="ImageServlet?id=${diary.id}&type=icon" width="30"
								height="30">
						</c:if></td>
					<td><c:out value="${diary.title}" /></td>
					<td><c:out value="${diary.userName}" /></td>
					<td><c:out value="${diary.createdAt}" /></td>
					<td><a href="DiaryDetailServlet?id=${diary.id}">見る</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>