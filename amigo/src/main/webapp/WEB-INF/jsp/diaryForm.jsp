<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日記投稿</title>
<link rel="stylesheet" href="CSS/style.css">
<script>
	function previewImage(event, previewId) {
		const input = event.target;
		const preview = document.getElementById(previewId);

		if (input.files && input.files[0]) {
			const reader = new FileReader();

			reader.onload = function(e) {
				preview.src = e.target.result;
				preview.style.display = 'block'; // プレビューを表示
			}

			reader.readAsDataURL(input.files[0]);
		} else {
			preview.src = '#';
			preview.style.display = 'none'; // プレビューを非表示
		}
	}
</script>
</head>
<body>
	<h1>新しい日記を投稿</h1>
	<form action="Main" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">タイトル：</label> <input type="text" id="title"
				name="title" size="40">
		</div>
		<div class="form-group">
			<label for="iconImage">アイコン画像：</label> <input type="file"
				id="iconImage" name="iconImage" accept="image/*"
				onchange="previewImage(event, 'iconPreview')"> <img
				id="iconPreview" src="#" alt="アイコンプレビュー"
				style="display: none; max-width: 100px; max-height: 100px;">
		</div>
		<div class="form-group">
			<label for="text">本文：</label>
			<textarea id="text" name="text" rows="4" cols="40"></textarea>
		</div>
		<div class="form-group">
			<label for="image">画像（任意）：</label> <input type="file" id="image"
				name="image" accept="image/*"
				onchange="previewImage(event, 'mainPreview')"> <img
				id="mainPreview" src="#" alt="メイン画像プレビュー"
				style="display: none; max-width: 200px; max-height: 200px;">
		</div>
		<button type="submit">投稿</button>
	</form>
	<br>
	<a href="Main">一覧に戻る</a>
</body>
</html>