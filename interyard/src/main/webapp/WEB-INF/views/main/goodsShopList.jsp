<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags"%>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Event Display</title>
<style>
@charset "UTF-8";

@charset "UTF-8";

/* Container styling */
.goods {
	width: 100%;
    display: flex;
    flex-wrap: wrap; /* Allow wrapping to fit items in viewport */
    gap: 10px; /* Space between items */
    padding: 10px;
    justify-content: center; /* Center items */
    overflow-x: hidden; /* Remove horizontal scrolling */
}

/* Individual item styling */
.item {
    background-color: white;
    border-radius: 10px; /* Rounded corners */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Soft shadow */
    overflow: hidden;
    width: 210px; /* Set width */
    height: 150px; /* Set height to match width */
    transition: transform 0.3s, box-shadow 0.3s;
    position: relative; /* For positioning the image and text */
    flex-shrink: 0; /* Prevent items from shrinking */
    display: flex; /* Use flex for easier content alignment */
    align-items: flex-end; /* Align text to the bottom */
    justify-content: center; /* Center text horizontally */
}

/* Item hover effect */
.item:hover {
    transform: translateY(-3px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15); /* Pronounced shadow on hover */
}

/* Image styling */
.item img {
    width: 100%;
    height: 100%;
    object-fit: cover; /* Ensures the image covers the entire area */
    position: absolute;
    top: 0;
    left: 0;
}

/* Title styling */
.item h3 {
    background-color: white; /* Semi-transparent background */
    height: 30px;
    color: black; /* Text color for contrast */
    margin: 0;
    padding: 5px 10px; /* Padding */
    font-size: 0.9rem; /* Font size */
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden; /* Prevent overflow */
    position: absolute; /* Absolute positioning */
    bottom: 0; /* Stick to the bottom */
    left: 0; /* Align with the left edge */
    right: 0; /* Extend to the right edge */
    z-index: 1; /* Ensure text is above the image */
    border-radius: 0 0 10px 10px; /* Match item border radius */
    box-shadow: 0 -1px 3px rgba(0, 0, 0, 0.2); /* Optional shadow for better readability */
}

/* Responsive Design */
@media (max-width: 768px) {
    .item {
        width: 120px; /* Reduce size on smaller screens */
        height: 120px; /* Ensure square shape */
    }
}

@media (max-width: 480px) {
    .goods {
        justify-content: space-around; /* Distribute items with space around */
    }
    .item {
        width: calc(50% - 10px); /* Two items per row */
        height: auto; /* Allow flexible height for content */
        margin-bottom: 10px; /* Space between rows */
    }
    .item img {
        height: auto; /* Maintain aspect ratio */
    }
}

</style>

<div class="goods">
	<c:forEach items="${goodslist}" var="vo">
		<div class="item dataRow goods">
			<input name="no" value="${vo.goodsNo}" type="hidden">
			<img src="${vo.goodsImage}" alt="${vo.goodsTitle}">
			<h3>${vo.goodsTitle}</h3>
		</div>
	</c:forEach>
</div>

