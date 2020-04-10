function clock()
{
    // 曜日を表す各文字列
    var weeks = new Array("Sun", "Mon", "Thu", "Wed", "Thr", "Fri", "Sat");
    // 現在日時を表すインスタンスを取得
    var now = new Date();
    // 年
    var y = now.getFullYear();
    // 月
    var mo = now.getMonth() + 1;
    // 日
    var d = now.getDate();
    // 曜日
    var w = weeks[now.getDay()];
    // 時
    var h = now.getHours();
    // 分
    var mi = now.getMinutes();
    // 秒
    var s = now.getSeconds();

    if(mo < 10) mo = "0" + mo;
    if(d < 10) d = "0" + d;
    if(mi < 10) mi = "0" + mi;
    if(s < 10) s = "0" + s;

	// HTML: <span id="clock_date">(ココの日付文字列を書き換え)</span>
    document.getElementById("clock_date").innerHTML = y + "/" + mo + "/" + d + "(" + w + ")";
    // HTML: <spna id="clock_time">(ココの時刻文字列を書き換え)</span>
    document.getElementById("clock_time").innerHTML = h + ":" + mi + ":" + s;
    // HTML: <div id="clock_frame">の内部要素のフォントサイズをウィンドウサイズの10分の1ピクセルに設定
    document.getElementById("clock_frame").style.fontSize = window.innerWidth / 10 + "px";
}

setInterval(clock, 1000);