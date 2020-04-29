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
			function modify(attendid) {
				window.confirm(attendid + '更新してよろしいですか？');
				window.location.href='attendupdateconfirmation?attendid=' + attendid;
			};
			function remove(attendid) {
				window.confirm(attendid + '削除してよろしいですか？');
				window.location.href='attenddeleteconfirmation?attendid=' + attendid;
			};
		</script>
	</head>
	<body>
		<span id="menu">
			<a href="userupdateconfirmation">アカウント情報修正</a>
			<a href="logout">ログアウト</a>
		</span>
		<div class="base">
			<table id="management">
				<tr><td>出勤日</td><td>出勤時間</td><td>退勤時間</td><td>休憩時間(h)</td><td>稼働時間(h)</td></tr>
				<% for (Attend v : attendList) { %>
				<tr>
					<td><%=Utility.getDate(v.getAttendanceTime()) %></td>
					<td><%=Utility.getTime(v.getAttendanceTime()) %></td>
					<% if (v.getLeavingTime() == null) { %>
						<td>-</td>
					<% } else { %>
						<td><%=Utility.getTime(v.getLeavingTime()) %></td>
					<% } %>
					<% if (v.getActualRestTime() == null || v.getActualRestTime().isEmpty()) { %>
						<td>-</td>
					<% } else { %>
						<td><%=v.getActualRestTime() %></td>
					<% } %>
					<% if (v.getOperatingHours() == null || v.getOperatingHours().isEmpty()) { %>
						<td>-</td>
					<% } else { %>
						<td><%=v.getOperatingHours() %></td>
					<% } %>
					<% if (v.getLeavingTime() == null) { %>
						<td><input type="submit" class="button" value="修正" onclick="modify(<%=v.getAttendId() %>)" disabled></td>
						<td><input type="button" class="button" value="削除" onclick="remove(<%=v.getAttendId() %>)" disabled></td>
					<% } else { %>
						<td><input type="submit" class="button" value="修正" onclick="modify(<%=v.getAttendId() %>)"></td>
						<td><input type="button" class="button" value="削除" onclick="remove(<%=v.getAttendId() %>)"></td>
					<% } %>
				</tr>
				<% } %>
			</table>
		</div>
	</body>
</html>