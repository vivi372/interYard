<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>InterYard<decorator:title /></title>

<!-- Bootstrap 4 + jQuery Libraries -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


<!-- Font Awesome & Material Icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- Custom Styles -->
<link rel="stylesheet" href="/css/defaultDeco.css">
<link rel="stylesheet" href="/css/page.css">



<decorator:head />
<script type="text/javascript">
    $(function() {
        $(".cancelBtn").click(function() {
            history.back();
        });
    });
</script>
<c:if test="${!empty login}">
    <script type="text/javascript">
        $(function() {
            setInterval(function() {
                $("#newMsgCnt").load("/ajax/getNewMsgCnt.do");
            }, 30000);
        });
    </script>
</c:if>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-light">
            <a href="/" class="logo">InterYard</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <div class="navbar-nav mr-auto links">
                    <div class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="noticeDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">공지사항</a>
                        <div class="dropdown-menu" aria-labelledby="noticeDropdown">
                            <a class="dropdown-item" href="/event/list.do">event</a>
                            <a class="dropdown-item" href="/notice/list.do">notice</a>
                        </div>
                    </div>
                    <div class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="productsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">상품</a>
                        <div class="dropdown-menu" aria-labelledby="productsDropdown">
                            <a class="dropdown-item" href="/goods/shopList.do?page=1&perPageNum=12&key=nt&word=2101">Shop</a>
                            <a class="dropdown-item" href="/goods/list.do?page=1&perPageNum=12&key=nt&word=1101">Book</a>
                            <a class="dropdown-item" href="/goods/ticketList.do?page=1&perPageNum=12&key=nt&word=3201">Ticket</a>
                        </div>
                    </div>
                    <a href="/usedgoods/list.do" class="nav-item nav-link">중고장터</a>
                    <a href="/qna/list.do" class="nav-item nav-link">질문답변</a>
                    
                </div>
                <div class="navbar-nav login">
                    <c:if test="${ empty login }">
                        <a href="/member/writeForm.do" class="nav-item nav-link">
                            <button class="signup">Get Started</button>
                        </a>
                        <a href="/member/loginForm.do" class="nav-item nav-link">
                            <button>Login</button>
                        </a>
                    </c:if>

                    <c:if test="${!empty login}">
                        <c:if test="${login.gradeNo == 9}">
                            <a href="/member/list.do" class="nav-item nav-link">회원관리</a>
                        </c:if>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <c:if test="${empty login.photo}">
                                    <i class="fa fa-user-circle-o"></i>
                                </c:if>
                                <c:if test="${!empty login.photo}">
                                    <img src="${login.photo}" class="rounded-circle" style="width: 30px; height: 30px;">
                                </c:if>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="profileDropdown">
                                 <a class="dropdown-item" href="/member/view.do">내정보보기</a>
                                 <a class="dropdown-item" href="/message/list.do">메세지</a>
                                 <a class="dropdown-item" href="/basket/list.do">장바구니</a>
                                  <a href="/order/list.do" class="dropdown-item">주문내역</a>
                            </div>
                        </div>
                        <span class="nav-item nav-link">
                            <span class="badge badge-pill badge-danger" id="newMsgCnt">${login.newMsgCnt}</span>
                        </span>
                        <a href="/member/logout.do" class="nav-item nav-link">
                            <i class="fa fa-sign-out"></i> Logout
                        </a>
                    </c:if>
                </div>
            </div>
        </nav>
    </header>

    <main role="main">
        <article>
            <decorator:body />
        </article>
    </main>

    <footer class="container-fluid text-center">
        <p>&copy; 2024 InterYard. All rights reserved.</p>
    </footer>

    <c:if test="${!empty msg}">
        <div class="modal fade" id="msgModal" tabindex="-1" role="dialog" aria-labelledby="msgModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="msgModalLabel">처리 결과 모달 창</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">${msg}</div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function() {
                $("#msgModal").modal("show");
            });
        </script>
    </c:if>
</body>
</html>

<%
session.removeAttribute("msg");
%>
