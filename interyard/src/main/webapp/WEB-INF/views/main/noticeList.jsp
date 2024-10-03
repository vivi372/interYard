<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>

<title>Notice List</title>

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	margin: 0;
	padding: 0;
}

.event-container {
	margin: 20px auto;
	padding: 15px;
	background: #ffffff;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	/* border-collapse: collapse; */
}

th, td {
	padding: 12px;
	text-align: left;
}

th {
	background-color: #007bff;
	color: white;
}


.dataRow {
	transition: background-color 0.3s;
}


</style>
<!-- Data rows -->
<div class="event-container">
<table>
	<c:forEach items="${noticelist }" var="vo">

		<tr class="dataRow notice">
		<input name="no" value="${vo.no}" type="hidden">
			<!-- td : table data - 테이블 데이터 텍스트 -->
			<td>${vo.title}</td>
			<td>${vo.startDate }</td>
			<td>${vo.endDate}</td>
			<td>${vo.updateDate}</td>
		</tr>
	</c:forEach>
</table>
</div>