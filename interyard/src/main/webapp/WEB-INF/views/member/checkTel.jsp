<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty tel }">
사용가능한 전화번호 입니다.
</c:if>
<c:if test="${!empty tel }">
중복된 전화번호 입니다. 다른 전화번호 를 입력해 주세요.
</c:if>