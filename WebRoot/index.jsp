<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 	     4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		 var ws;
		var target = "ws://localhost:8081/xiangmu/echo";
		f();
		for (var int = 1; int <=0; int++) {
			name(int);
		}
		function f() {
			if("WebSocket"in window){
				alert(1)
				ws = new WebSocket(target);
				
			}else if("MozWebSocket" in window){
				alert(2)
				ws = new MozWebSocket(target);
				
			}else{
				alert("不支持您使用的浏览器");
				return
			}
			ws.onmessage = function(event) {
				var a = event.data.split(":");
				document.getElementById(a[0]).innerHTML=a[1];
				
			};
			
		}
		function name(id) {
			if(ws.readyState == 1) {
				ws.send(id);
			}
			
			
			setTimeout(function(){ 
			name(id);
   			}, 1500);
		}
		
		
		function test() {
			var date = document.getElementById("date").value;
			var date2 = document.getElementById("date2").value;
			var da = date.split("T");
			var da2 = date2.split("T");
			date = da[0]+" "+da[1]+":00";
			date2 = da2[0]+" "+da2[1]+":00";
			var userAgent = navigator.userAgent;  // 取得浏览器的 userAgent 字符串
			if (!!window.ActiveXObject || "ActiveXObject" in window) {
 				 alert("IE");
 			 // do IE something
			} else {
			  // do not IE something
			}
			location.href="test.do?date="+date+"&date2="+date2;
		}
		
	</script>
	
  </head>
  
  <body>
    	<button onclick="test();">点击</button>
    	从<input id="date" type="datetime-local" />到<input id="date2" type="datetime-local" />
    	<text>< 例：2019-04-26 16:05:00></text>
  </body>
</html>
