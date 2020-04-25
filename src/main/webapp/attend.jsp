<%@page contentType="text/html; charset=UTF-8" %>
<% boolean attendFlag = (Boolean)request.getAttribute("attendFlag"); %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF-8">
		<title>勤怠管理SYSTEM</title>
		<link rel="stylesheet" href="./css/base.css">
		<script src="./js/clock.js"></script>
		<script>
			function getTime(attendflag) {
				var date = document.getElementById('clock_date').innerHTML;
				var time = document.getElementById('clock_time').innerHTML;
				var punchtime = date.substring(0, date.length - 5) + ' ' + time;
				window.location.href='attendmanagement?punchtime=' + punchtime + '&attendflag=' + attendflag;
			}
		</script>
	</head>
	<body>
		<div class="base">
			<div id="clock_frame">
				<span id="clock_date"></span>
				<span id="clock_time"></span>
			</div>
			<table>
				<tr>
				<% if (attendFlag == true) { %>
				<td><input type="submit" class="button" value="出勤" onclick="getTime(false)" disabled></td>
				<td><input type="submit" class="button" value="退勤" onclick="getTime(true)"></td>
				<% } else { %>
				<td><input type="submit" class="button" value="出勤" onclick="getTime(false)"></td>
				<td><input type="submit" class="button" value="退勤" onclick="getTime(true)" disabled></td>
				<% } %>
				</tr>
			</table>
			<p><a href="attenddetails">勤怠管理ページ</a></p>
		</div>
	</body>
</html>