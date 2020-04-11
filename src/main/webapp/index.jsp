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
			<h1>勤怠管理SYSTEM</h1>
			<form action="login" method="POST">
				<table>
					<tr>
					<td><input type="text" class="filed" name="mail" value="" placeholder="メールアドレス"></td>
					</tr>
					<tr>
					<td><input type="password" class="filed" name="pass" value="" placeholder="パスワード"></td>
					</tr>
					<tr>
					<td><input type="submit" class="button" value="ログイン"></td>
					</tr>
				</table>
			</form>
			<p><a href="02_adduser.html">新規ユーザー登録</a></p>
		</div>
	</body>
</html>