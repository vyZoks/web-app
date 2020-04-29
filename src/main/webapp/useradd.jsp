<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF-8">
		<title>勤怠管理SYSTEM</title>
		<link rel="stylesheet" href="./css/base.css">
	</head>
	<body>
		<div class="base">
			<h1>ユーザー情報登録</h1>
			<form action="useradd" method="post">
				<table>
					<tr><td>社員名</td><td><input type="text" class="filed" name="empname" value="" required></td></tr>
					<tr><td>パスワード</td><td><input type="text" class="filed" name="emppass" value="" required></td></tr>
					<tr><td>メールアドレス</td><td><input type="text" class="filed" name="mail" value="" required></td></tr>
					<tr><td>出勤時刻</td><td><input type="time" class="filed" name="openingtime" value="" required></td></tr>
					<tr><td>退勤時刻</td><td><input type="time" class="filed" name="closingtime" value="" required></td></tr>
					<tr><td>単位時間</td><td><input type="number" class="filed" name="credittime" value="" max="99" required></td></tr>
					<tr><td>休憩時間</td><td><input type="number" class="filed" name="resttime" value="" max="99" required></td></tr>
					<tr><td></td><td><input type="submit" class="button" value="登録"></td></tr>
				</table>
			</form>
		</div>
	</body>
</html>