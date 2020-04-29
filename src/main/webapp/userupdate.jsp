<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="jp.co.sysral.bean.Employee" %>

<% Employee employee=(Employee)request.getAttribute("employee"); %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF-8">
		<title>勤怠管理SYSTEM</title>
		<link rel="stylesheet" href="./css/base.css">
	</head>
	<body>
		<div class="base">
			<h1>ユーザー情報修正</h1>
			<form action="userupdate" method="post">
				<table>
					<tr><td>社員名</td><td><input type="text" class="filed" name="empname" value="<%=employee.getEmpName() %>" required></td></tr>
					<tr><td>パスワード</td><td><input type="text" class="filed" name="emppass" value="<%=employee.getEmpPass() %>" required></td></tr>
					<tr><td>メールアドレス</td><td><input type="text" class="filed" name="mail" value="<%=employee.getMailaddress() %>" required></td></tr>
					<tr><td>出勤時刻</td><td><input type="time" class="filed" name="openingtime" value="<%=employee.getOpeningTime() %>" required></td></tr>
					<tr><td>退勤時刻</td><td><input type="time" class="filed" name="closingtime" value="<%=employee.getClosingTime() %>" required></td></tr>
					<tr><td>単位時間</td><td><input type="number" class="filed" name="credittime" value="<%=employee.getCreditTime() %>" max="99" required></td></tr>
					<tr><td>休憩時間</td><td><input type="number" class="filed" name="resttime" value="<%=employee.getRestTime() %>" max="99" required></td></tr>
					<tr><td></td><td><input type="submit" class="button" value="更新"></td></tr>
				</table>
			</form>
		</div>
	</body>
</html>