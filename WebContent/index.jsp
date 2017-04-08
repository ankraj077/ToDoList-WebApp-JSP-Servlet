<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align: center">ToDo List Web-App</h1>
	<hr></hr>
	<center>
		<h2>Saved ToDo List:</h2>

		<c:choose>
			<c:when test="${counter > -1}">
			
				<c:forEach var="i" begin="0" end="${counter}">
				
				<c:out value="${tName.get(i)}" /> 
						<span> - </span>
					<c:out value="${tDate.get(i)}" /><br/><br/>
				</c:forEach>
				
			</c:when>

			<c:otherwise>
				
				<p>No task in the database.</p>
			</c:otherwise>
		</c:choose>



	
		<br /> <br />
		<h2>Add new task:</h2>
		<br />


		<form action="DBConnector" method="GET">
			<input type="text" name="n" placeholder="Enter task name" /><br />
			<br /> <input type="text" id="datepicker" placeholder="Select Date"
				name="d"><br /> <br /> <input type="submit" value="Add">

		</form>


		<script>
			var dateToday = new Date();
			$(function() {
				$("#datepicker").datepicker({
					numberOfMonths : 1,
					showButtonPanel : true,
					minDate : dateToday
				});
			});
		</script>

	</center>
</body>
</html>