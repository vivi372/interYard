

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>

<title>Event Display</title>
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
	border-collapse: collapse;
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #dddddd;
}

th {
	background-color: #007bff;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

.badge {
	display: inline-block;
	padding: 0.5em 0.75em;
	font-size: 0.75em;
	font-weight: 700;
	color: #fff;
	background-color: #dc3545;
	border-radius: 0.25rem;
}

.dataRow {
	transition: background-color 0.3s;
}


.float-right {
	text-align: right;
}

.no {
	font-weight: bold;
}

.rnum {
	font-weight: normal;
}
</style>
	<div class="event-container">
		<table>
			<c:forEach items="${qnalist }" var="faq">
				<tr class="dataRow table-light faq">
					<td class="float-right no" data-data="${faq.qnaNo }">${faq.qnaNo}.</td>
					<td class="rnum" data-data="${faq.rnum }">
					<b>[${faq.ctg }] 
					[${faq.semiCtg}] ${faq.title} </b> 
				<c:if test="${faq.answerCnt != 0}">
						<span class="badge badge-danger ml-2">${faq.answerCnt }</span>
				</c:if>
				</td>

				</tr>
			</c:forEach>
		</table>
	</div>
