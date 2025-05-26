<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記詳細</title>
<link rel="stylesheet" href="CSS/style.css">
<link rel="stylesheet" href="CSS/style06.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    function confirmDeletion(deleteUrl) {
        Swal.fire({
            title: '本当に削除してもよろしいですか？',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'はい',
            cancelButtonText: 'いいえ',
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = deleteUrl;
            }
            // return false; // この行を削除またはコメントアウト
        });
        return false; // 関数自体は遷移しないように false を返す
    }
</script>
</head>
<body>
	<h1>日記詳細</h1>
	<p>
		タイトル：
		<c:out value="${diary.title}" />
	</p>
	<p>
		投稿者：
		<c:out value="${diary.userName}" />
	</p>
	<p>
		投稿日時：
		<c:out value="${diary.createdAt}" />
	</p>
	<p>本文：<pre><c:out value="${diary.text}" /></pre></p>
	<c:if test="${not empty diary.image}">
		<img src="ImageServlet?id=${diary.id}&type=main"
			style="max-width: 300px;">
	</c:if>
	<br>
	<c:if test="${diary.userName == loginUser.name}">
		<a href="EditFormServlet?id=${diary.id}">編集</a>
		<a href="#"
			onclick="return confirmDeletion('DeleteMutterServlet?id=${diary.id}');">削除</a>
	</c:if>
	<br>
	<a href="Main">一覧に戻る</a>
</body>
</html>