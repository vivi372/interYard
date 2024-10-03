# interYard
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fvivi372%2FinterYard&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)


<div align="center">
  <img src="readmeAssets/interYardLogo.png" width="300" style="">
</div>



# 프로젝트 소개

이 프로젝트는 포트폴리오 목적으로 제작된 도서, 티켓, 각종 상품을 판매하는 가상 쇼핑몰입니다. 

실제 서비스를 염두에 두기보다는, 다양한 기능을 구현하고 풀스택 개발 역량을 보여주기 위해 개발되었습니다.

프로젝트는 Servlet과 JSP를 사용하여 프론트엔드와 백엔드를 전반적으로 개발하였으며, 

2024년 7월 19일부터 2024년 8월 5일까지 18일간 진행되었습니다.

# 팀원 소개

+ 팀장(황문성) : 상품 관리 개발
+ PM(원필재) : 후기 / QNA / FAQ 개발
+ PM(이이섭) : 중고장터 / 댓글 개발
+ PL(윤해연) : 메인 페이지 / 공지사항 / 이벤트 개발
+ __PL(박근태) : 장바구니 / 주문관리 개발__
+ DBA(전희원) : 회원 관리 개발
+ 서기(지유빈) : 메세지 개발

# 개발 환경

| 자원분류 | 자원 이름 |
| :---: | :---: |
| OS | window 10/11 |
| IDE | Eclipse |
| DBSM | Oracle 11g |
| WAS | Tomcat9 |
| JAVA Version | JAVA17 |

# 기술 스택

<div align=center>
  <img src="https://img.shields.io/badge/java-ff1a1a?style=for-the-badge&logo=JAVA&logoColor=white">
  <img src="https://img.shields.io/badge/servlet-ff6666?style=for-the-badge&logo=servlet&logoColor=white">
  <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white">  
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">  
</div>

# 화면 구성

<details>
  
<summary>장바구니</summary>

+ 장바구니 리스트
<img src="readmeAssets/장바구니 리스트.png" width="600" style="">

+ 장바구니 등록
<img src="readmeAssets/장바구니 등록.png" width="600" style="">

</details>

<details>
  
<summary>주문관리</summary>

+ 주문 리스트
<img src="readmeAssets/주문 리스트.png" width="600" style="">

+ 주문 등록
<img src="readmeAssets/주문 등록.png" width="600" style="">

+ 배송지 리스트
<img src="readmeAssets/배송지 리스트.png" width="600" style="">

+ 주문 관리 페이지
<img src="readmeAssets/주문 관리 페이지.png" width="600" style="">

</details>

# 주요 기능

+ ajax를 사용해 비동기 방식으로 배송지 관련 CRUD를 구현해 결제시 입력한 데이터를 유지시킨 상태로 배송지 등록,수정,삭제가 가능
+ 책 같은 경우 ISBN이 존재하고 티켓은 관람일이 필요하기 때문에 주문 데이터 출력시 다른 정보를 출력이 가능
+ 상품의 옵션이 존재할 경우 여러개의 옵션이 선택이 가능
