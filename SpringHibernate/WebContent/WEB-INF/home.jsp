<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<link href="/css/bootstrap.css" rel="stylesheet" />
<script src="/js/bootstrap.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Home</title>
</head>
<body>
	<form id="indexForm" action="search">
		<div class="input-group">
			<div style="margin-left: 400px; margin-top: 100px;">
				<div style="float: left;">
					<input type="text" name="searchVal" class="form-control"
						placeholder="Search...">
				</div>
				<div class="input-group-btn" style="float: left;">
					<button class="btn btn-default" type="submit">Search</button>
				</div>
			</div>
		</div>
		<div id="searchResult" style="float:left;clear:left;margin-left:400px;margin-top:10px;">
			<c:forEach items="${list}" var="map">
				<div class="panel panel-default">
					<div class="panel-body">
						<span class="glyhicon glyphicon-cloud"></span>
						<c:set var="country" value="${map['country']}"></c:set>
						<div style="float:left">
							<strong><c:out value="${map['country']}" /></strong><br>
							<c:out value="${map['date']}" />
						</div>
						<div style="float:left;clear:left">
							<button type="button" class="btn">${map['main_temp']} &#8451;</button>
							&nbsp;${map['weather_main']}<br>
							${map['wind_speed']} m/s. &nbsp; ${map['main_humidity']} &#37; &nbsp; ${map['main_pressure']} hpa
						</div>
						<div style="float:left;">
							<button type="button" class="btn btn-danger" onclick='removeCountry();'>Delete</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</form>
	<script type="text/javascript">
	function removeCountry(){
		alert('remove country');
		document.getElementById("indexForm").action = "remove";
		document.getElementById("indexForm").method = "GET";
		document.getElementById("indexForm").submit();
	}
	</script>
</body>
</html>