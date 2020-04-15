<%@page contentType="text/html; charset=UTF-8" %>
<% boolean atendFlag = (Boolean)request.getAttribute("atendFlag"); %>

<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF-8">
		<title>勤怠管理SYSTEM</title>
		<link rel="stylesheet" href="./css/base.css">
		<script src="./js/clock.js"></script>
		<script>
			function getTime(atendflag) {
				var date = document.getElementById('clock_date').innerHTML;
				var time = document.getElementById('clock_time').innerHTML;
				var timestamp = date.substring(0, date.length - 5) + ' ' + time;
				window.location.href='attendmanagement?atendtime=' + timestamp + '&atendflag=' + atendflag;
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
				<% if (atendFlag == true) { %>
				<td><input type="submit" class="button" value="出勤" onclick="getTime(false)" disabled></td>
				<td><input type="submit" class="button" value="退勤" onclick="getTime(true)"></td>
				<% } else { %>
				<td><input type="submit" class="button" value="出勤" onclick="getTime(false)"></td>
				<td><input type="submit" class="button" value="退勤" onclick="getTime(true)" disabled></td>
				<% } %>
				</tr>
			</table>
			<p><a href="04_atendtable.html">勤怠管理ページ</a></p>
		</div>
	</body>
</html>