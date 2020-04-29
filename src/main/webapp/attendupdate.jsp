<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="jp.co.sysral.util.Utility, jp.co.sysral.bean.Attend" %>

<% Attend attend=(Attend)request.getAttribute("attend"); %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF-8">
		<title>勤怠管理SYSTEM</title>
		<link rel="stylesheet" href="./css/base.css">
	</head>
	<body>
		<div class="base">
			<h1>勤怠修正</h1>
			<form action="attendupdate">
				<table>
					<tr>
						<td>出勤日時</td>
						<td><input type="datetime-local" class="filed" name="attendancetime" value="<%=Utility.conv(attend.getAttendanceTime()) %>" required></td>
					</tr>
					<tr>
						<td>退勤日時</td>
						<td><input type="datetime-local" class="filed" name="leavingtime" value="<%=Utility.conv(attend.getLeavingTime()) %>" required></td>
					</tr>
					<tr>
						<td>休憩時間</td>
						<td><input type="number" class="filed" name="actualresttime" value="<%=attend.getActualRestTime() %>" max="99999" required></td>
					</tr>
					<tr>
						<td></td>
						<td>
						<input type="hidden" name="attendid" value="<%=attend.getAttendId() %>">
						<input type="hidden" name="empid" value="<%=attend.getEmpId() %>">
						<input type="submit" class="button" value="更新">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>