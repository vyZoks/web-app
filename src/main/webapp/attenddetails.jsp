<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="jp.co.sysral.util.Utility, jp.co.sysral.bean.Attend, java.util.List" %>

<%
@SuppressWarnings("unchecked")
List<Attend> attendList=(List<Attend>)request.getAttribute("attendList");
%>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF8">
		<title>勤怠管理SYSTEM</title>
		<link rel="stylesheet" href="./css/base.css">
		<script>
			function modify() {
				window.confirm('更新してよろしいですか？');
			}
			function remove() {
				window.confirm('削除してよろしいですか？');
			};
		</script>
	</head>
	<body>
		<span id="menu">
			<a href="06_userfix.html">アカウント情報修正</a>
			<a href="01_login.html">ログアウト</a>
		</span>
		<div class="base">
			<table id="management">
				<tr><td>出勤日</td><td>出勤時間</td><td>退勤時間</td><td>休憩時間(h)</td><td>稼働時間(h)</td></tr>
				<% for (Attend v : attendList) { %>
				<tr>
					<td><%=Utility.getDate(v.getAttendanceTime()) %></td>
					<td><%=Utility.getTime(v.getAttendanceTime()) %></td>
					<td><%=Utility.getTime(v.getLeavingTime()) %></td>
					<td><%=v.getActualRestTime() %></td>
					<td><%=v.getOperatingHours() %></td>
					<td><input type="submit" class="button" value="修正" onclick="modify()"></td>
					<td><input type="button" class="button" value="削除" onclick="remove()"></td>
				</tr>
				<% } %>
			</table>
		</div>
	</body>
</html>