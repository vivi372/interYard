-- 1-1. 테이블 삭제
DROP TABLE reply CASCADE CONSTRAINTS PURGE;
DROP TABLE usedgoods CASCADE CONSTRAINTS PURGE;
DROP TABLE review CASCADE CONSTRAINTS PURGE;
DROP TABLE qna CASCADE CONSTRAINTS PURGE;
DROP TABLE message CASCADE CONSTRAINTS PURGE;
DROP TABLE event CASCADE CONSTRAINTS PURGE;
DROP TABLE notice CASCADE CONSTRAINTS PURGE;
DROP TABLE basketOpt CASCADE CONSTRAINTS PURGE;
DROP TABLE basket CASCADE CONSTRAINTS PURGE;
DROP TABLE ordersOpt CASCADE CONSTRAINTS PURGE;
DROP TABLE goodsOption CASCADE CONSTRAINTS PURGE;
DROP TABLE orders CASCADE CONSTRAINTS PURGE;
DROP TABLE Goods CASCADE CONSTRAINTS PURGE;
DROP TABLE Category CASCADE CONSTRAINTS PURGE;
DROP TABLE dlvyAddr CASCADE CONSTRAINTS PURGE;
DROP TABLE member CASCADE CONSTRAINTS PURGE;
DROP TABLE grade CASCADE CONSTRAINTS PURGE;
-- 1-2. 시퀀스 삭제
DROP SEQUENCE basket_seq;
DROP SEQUENCE basketOpt_seq;
DROP SEQUENCE dlvyAddr_seq;
DROP SEQUENCE goods_seq;
DROP SEQUENCE goodsOption_seq;
DROP SEQUENCE msg_seq;
DROP SEQUENCE notice_seq;
DROP SEQUENCE orders_seq;
DROP SEQUENCE ordersOpt_seq;
DROP SEQUENCE qna_seq;
DROP SEQUENCE reply_seq;
DROP SEQUENCE review_seq;
DROP SEQUENCE usedgoods_seq;

-- 2-1. 테이블 생성
-- 회원등급 관리 테이블
CREATE TABLE grade(
	gradeNo     NUMBER NOT NULL ,
	gradeName   VARCHAR2(20) NOT NULL ,
 PRIMARY KEY (gradeNo)
);
-- 회원 관리 테이블
CREATE TABLE member(
	id          VARCHAR2(30) NOT NULL ,
	pw          VARCHAR2(30) NOT NULL ,
	name        VARCHAR2(30) NOT NULL ,
	gender      VARCHAR2(10) NOT NULL  CHECK (gender in('남자','여자')),
	birth       DATE NOT NULL ,
	tel         VARCHAR2(30) UNIQUE NOT NULL ,
	email       VARCHAR2(100) UNIQUE NOT NULL ,
	WriteDate   DATE DEFAULT sysdate NULL ,
	conDate     DATE DEFAULT sysdate NULL ,
	photo       VARCHAR2(300) NULL ,
	status      VARCHAR2(10) DEFAULT '정상' NULL CHECK (status in('정상','탈퇴','휴면','상퇴')),
	gradeNo     NUMBER DEFAULT 1 NULL ,
	newMsgCnt   NUMBER DEFAULT 0 NULL ,
 PRIMARY KEY (id),
 FOREIGN KEY (gradeNo) REFERENCES grade(gradeNo) ON DELETE SET NULL
);
-- 배송지 테이블
CREATE TABLE dlvyAddr(
	dlvyAddrNo  NUMBER NOT NULL ,
	dlvyName    VARCHAR2(30) NOT NULL ,
	recipient   VARCHAR2(30) NOT NULL ,
	tel         VARCHAR2(20) NOT NULL ,
	addr        VARCHAR2(300) NOT NULL ,
	postNo      number NOT NULL ,
	basic       NUMBER(1) DEFAULT  0 NULL ,
	id          VARCHAR2(30) NULL ,
 PRIMARY KEY (dlvyAddrNo),
 FOREIGN KEY (id) REFERENCES member(id) ON DELETE CASCADE
);
-- 카테고리 테이블
CREATE TABLE Category(
	CategoryNo      NUMBER DEFAULT 1 NOT NULL ,
	CategoryName    VARCHAR2(300) NULL ,
 PRIMARY KEY (CategoryNo)
);
-- 상품 관리 테이블
CREATE TABLE Goods(
	goodsNo         NUMBER(13) NOT NULL ,
	goodsTitle      VARCHAR2(300) NOT NULL ,
	goodsSubTitle   VARCHAR2(300) NULL ,
	goodsMaker      VARCHAR2(60) NOT NULL ,
	goodsPublicher  VARCHAR2(60) NOT NULL ,
	goodsRunTime    VARCHAR2(5)  NOT NULL ,
	goodsImage      VARCHAR2(3000) NULL ,
	goodsPrice      NUMBER(8) DEFAULT 0 NOT NULL ,
	goodsQuantity   NUMBER(3) DEFAULT 1 NOT NULL ,
	goodsCost       NUMBER(5) DEFAULT 0 NOT NULL ,
	goodsStatus     VARCHAR2(21) NOT NULL ,
	goodsContent    VARCHAR2(3000) NOT NULL ,
	goodsWriteDate  DATE DEFAULT sysdate NULL ,
	goodsRating     VARCHAR2(30) DEFAULT '전체연령' NOT NULL ,
	goodsStartDate  DATE NULL ,
	goodsEndDate    DATE NULL ,
	goodsTrans      VARCHAR2(30) NULL ,
	categoryNo      NUMBER DEFAULT 1 NULL ,
 PRIMARY KEY (goodsNo),
 FOREIGN KEY (categoryNo) REFERENCES Category(CategoryNo) ON DELETE SET NULL
);
-- 주문 관리 테이블
CREATE TABLE orders(
	orderNo         NUMBER NOT NULL ,
	dlvyAddrNo      NUMBER NULL ,
	dlvyMemo        VARCHAR2(300) NULL ,
	orderPrice      NUMBER NOT NULL ,
	dlvyCharge      NUMBER NOT NULL ,
	payWay          VARCHAR2(30) NOT NULL CHECK (payWay in('계좌','핸드폰','카드')),
	payDetail       VARCHAR2(100) NULL ,
	orderState      VARCHAR2(30) DEFAULT '주문 확인' NOT NULL CHECK (orderState in('주문 확인','배송 대기','배송중','배송 완료','구매 확인','구매 확인(리뷰작성)','취소 요청')),
	cancleReason    VARCHAR2(300) NULL ,
	id              VARCHAR2(30) NULL ,
	goodsNo         NUMBER(13) NULL ,
    orderDate       date default sysdate null,
    confirmDate     date null,
    dlvyName    VARCHAR2(30) NOT NULL ,
	recipient   VARCHAR2(30) NOT NULL ,
	tel         VARCHAR2(20) NOT NULL ,
	addr        VARCHAR2(300) NOT NULL ,
	postNo      number NOT NULL ,
    hopeDate       date,
 PRIMARY KEY (orderNo),
 FOREIGN KEY (id)         REFERENCES member(id) ON DELETE CASCADE,
 FOREIGN KEY (goodsNo)    REFERENCES Goods(goodsNo)
);
-- 상품 옵션 테이블
CREATE TABLE goodsOption(
	optNo           NUMBER(13) NOT NULL ,
	optName         VARCHAR2(30) NOT NULL ,
	optPrice        NUMBER(8) DEFAULT 0 NOT NULL ,
	goodsNo         NUMBER(13) NULL ,
 PRIMARY KEY (optNo),
 FOREIGN KEY (goodsNo) REFERENCES Goods(goodsNo) ON DELETE SET NULL
);
-- 주문 옵션 테이블
CREATE TABLE ordersOpt(
	orderOptNo      NUMBER ,
	optNo           NUMBER(13) ,
	amount          NUMBER ,
	orderNo         NUMBER ,
 PRIMARY KEY (orderOptNo) ,
 FOREIGN KEY (orderNo) REFERENCES orders(orderNo) ON DELETE CASCADE ,
 FOREIGN KEY (optNo) REFERENCES goodsOption(optNo) ON DELETE SET NULL
);
-- 장바구니 테이블
CREATE TABLE basket(
	basketNo       NUMBER NOT NULL ,
	goodsNo        NUMBER(13) NOT NULL ,
	orderPrice     NUMBER NOT NULL ,
	dlvyCharge     NUMBER NOT NULL ,
	id             VARCHAR2(30) NULL ,
    hopeDate       date,
 PRIMARY KEY (basketNo),
 FOREIGN KEY (id)      REFERENCES member(id) ON DELETE CASCADE ,
 FOREIGN KEY (goodsNo) REFERENCES Goods(goodsNo) ON DELETE CASCADE
);
-- 장바구니 옵션 테이블
CREATE TABLE basketOpt(
	basketOptNo    NUMBER  NOT NULL ,
	optNo          NUMBER(13)  NOT NULL ,
	amount         NUMBER  NOT NULL ,
	basketNo       NUMBER  NOT NULL ,
 PRIMARY KEY (basketOptNo),
 FOREIGN KEY (basketNo) REFERENCES basket(basketNo) ON DELETE CASCADE,
 FOREIGN KEY (optNo)    REFERENCES goodsOption(optNo) ON DELETE CASCADE
);
-- 공지사항 테이블
CREATE TABLE notice(
	no             NUMBER NOT NULL ,
	title          VARCHAR2(300) NOT NULL ,
	content        VARCHAR2(2000) NULL ,
	startDate      DATE DEFAULT sysdate NULL ,
	endDate        DATE DEFAULT '9999-12-30' NULL ,
	writeDate      DATE DEFAULT sysdate NULL ,
	updateDate     DATE DEFAULT sysdate NULL ,
	image          VARCHAR2(2000) NULL ,
 PRIMARY KEY (no)
);
-- 이벤트 테이블
CREATE TABLE event(
	no            NUMBER NOT NULL ,
	title         VARCHAR2(300) NOT NULL ,
	content       VARCHAR2(2000) NULL ,
	startDate     DATE DEFAULT sysdate NULL ,
	endDate       DATE DEFAULT '9999-12-30' NULL ,
	writeDate     DATE DEFAULT sysdate NULL ,
	updateDate    DATE DEFAULT sysdate NULL ,
	hit           NUMBER DEFAULT 0 NULL ,
	image         VARCHAR2(2000) NULL ,
	categoryNo    NUMBER NOT NULL ,
 PRIMARY KEY (no),
 FOREIGN KEY (categoryNo) REFERENCES Category(CategoryNo) ON DELETE SET NULL
);
-- 메시지 테이블
CREATE TABLE message(
	msgNo         NUMBER NOT NULL ,
	title         VARCHAR2(150) NOT NULL ,
	content       VARCHAR2(900) NOT NULL ,
	sendDate      DATE DEFAULT sysdate NOT NULL ,
	acceptDate    DATE NULL ,
	uploadFile    VARCHAR2(200) NULL ,
	msgStatus     VARCHAR2(15) DEFAULT '전송' NOT NULL CHECK (msgStatus in ('전송', '삭제')),
	senderId      VARCHAR2(30) NOT NULL ,
	accepterId    VARCHAR2(30) NOT NULL ,
    refNo         NUMBER NULL ,  
    ordNo         NUMBER NULL ,
    parentNo      NUMBER NULL ,
    levNo         NUMBER NULL ,
 PRIMARY KEY (msgNo),
 FOREIGN KEY (senderId)   REFERENCES member(id) ON DELETE SET NULL ,
 FOREIGN KEY (accepterId) REFERENCES member(id) ON DELETE SET NULL ,
 FOREIGN KEY (refNo)      REFERENCES message(msgNo) ON DELETE CASCADE , 
 FOREIGN KEY (parentNo)   REFERENCES message(msgNo) ON DELETE CASCADE
);
-- 질의응답 테이블
CREATE TABLE qna(
	qnaNo         NUMBER NOT NULL ,
	ctg           VARCHAR2(100) NOT NULL ,
	semiCtg       VARCHAR2(100) NOT NULL ,
	title         VARCHAR2(300) NOT NULL ,
	content       VARCHAR2(2000) NOT NULL ,
	id            VARCHAR2(30) NOT NULL ,
	writeDate     DATE DEFAULT sysdate NULL ,
	hit           NUMBER DEFAULT  0 NULL ,
	refNo         NUMBER NULL ,
	ordNo         NUMBER NULL ,
	levNo         NUMBER NULL ,
	parentNo      NUMBER NULL ,
 PRIMARY KEY (qnaNo),
 FOREIGN KEY (refNo)    REFERENCES qna(qnaNo) ON DELETE CASCADE,
 FOREIGN KEY (parentNo) REFERENCES qna(qnaNo) ON DELETE CASCADE,
 FOREIGN KEY (id)       REFERENCES member(id)
);
-- 후기 테이블
CREATE TABLE review(
	revNo         NUMBER NOT NULL ,
	title         VARCHAR2(300) NOT NULL ,
	content       VARCHAR2(2000) NOT NULL ,
	imgFile       VARCHAR2(3000 CHAR) NULL ,
	grade         NUMBER NOT NULL ,
	writeDate     DATE DEFAULT sysdate NULL ,
	id            VARCHAR2(30) NOT NULL ,
	likeCnt       NUMBER DEFAULT 0 NULL ,
	ordNo         NUMBER DEFAULT 0 NULL ,
	levNo         NUMBER DEFAULT 0 NULL ,
	goodsNo       NUMBER(13) NULL ,
	orderNo       NUMBER NULL ,
	parentNo      NUMBER DEFAULT 0 NULL ,
 PRIMARY KEY (revNo),
 FOREIGN KEY (parentNo) REFERENCES review(revNo) ON DELETE CASCADE ,
 FOREIGN KEY (id)       REFERENCES member(id),
 FOREIGN KEY (goodsNo)  REFERENCES Goods(goodsNo) ON DELETE SET NULL ,
 FOREIGN KEY (orderNo)  REFERENCES orders(orderNo) ON DELETE SET NULL
);
-- 중고상품 테이블
CREATE TABLE usedgoods(
	ugno          NUMBER NOT NULL ,
	title         VARCHAR2(300) NOT NULL ,
	content       VARCHAR2(300) NOT NULL ,
	price         NUMBER NOT NULL ,
	image         VARCHAR2(100) NOT NULL ,
	wrtieDate     DATE DEFAULT sysdate NULL ,
	ugstatus      VARCHAR2(20) DEFAULT '판매중' NULL CHECK (ugstatus in('판매중', '판매완료')),
	id            VARCHAR2(30) NOT NULL ,
 PRIMARY KEY (ugno),
 FOREIGN KEY (id) REFERENCES member(id) ON DELETE SET NULL
);
-- 댓글 테이블
CREATE TABLE reply(
	rno           NUMBER NOT NULL ,
	ugno          NUMBER NULL ,
	content       VARCHAR2(500) not NULL ,
	writeDate     DATE DEFAULT sysdate NULL ,
	id            VARCHAR2(30) NULL ,
 PRIMARY KEY (rno),
 FOREIGN KEY (ugno) REFERENCES usedgoods(ugno) ON DELETE SET NULL,
 FOREIGN KEY (id)   REFERENCES member(id) ON DELETE SET NULL
);
-- 2-2. 시퀀스 생성
CREATE SEQUENCE basket_seq;
CREATE SEQUENCE basketOpt_seq;
CREATE SEQUENCE dlvyAddr_seq;
CREATE SEQUENCE goods_seq;
CREATE SEQUENCE goodsOption_seq;
CREATE SEQUENCE msg_seq;
CREATE SEQUENCE review_seq;
CREATE SEQUENCE usedgoods_seq;
CREATE SEQUENCE reply_seq;
CREATE SEQUENCE qna_seq;
CREATE SEQUENCE ordersOpt_seq;
CREATE SEQUENCE orders_seq;
CREATE SEQUENCE notice_seq;

-- 3. 샘플데이터
-- 회원 등급 관리
insert into grade values(1,'일반회원'); 
insert into grade VALUES(9,'관리자');

-- 회원 관리
-- a) 일반회원
insert into member(id, pw, name, gender, birth, tel, email, photo)
values('test', '1111', '홍길동', '남자', '1990-01-11', '010-1336-5628', 'hong@naver.com', '/upload/member/hong.jpg');
-- b) 관리자
insert into member(id, pw, name, gender, birth, tel, email, photo, gradeNo)
values('admin', '1111', '관리자', '남자', '1922-01-11', '010-2133-2131', 'admin131@naver.com', '/upload/member/adimn.jpg', 9);

-- 배송지
Insert into dlvyAddr(dlvyAddrNo, dlvyName, recipient, tel, addr, postNo, basic, id) 
values (1,'집','나','010-3456-4567','경기 양주시 고덕로 160 (덕계동, 양주덕계현진에버빌1단지) /103동',11460,1,'test');

-- 카테고리
insert into category(categoryNo, categoryname)
values (2000, '쇼핑');

-- 상품 관리
insert into goods(categoryNo, goodsNo, goodsTitle, goodsMaker, goodsPublicher, goodsRunTime, goodsPrice, goodsQuantity, goodsCost, goodsStatus, goodsContent, goodsRating)
VALUES (1, goods_seq.nextval, '테스트', 'test', 'tester', 80, 10000, 1, 3000, '테스트', '테스트용 샘플상품입니다.', '테스트');

-- 주문 관리
insert into orders(orderNo,dlvyAddrNo,orderPrice,dlvyCharge,payWay,payDetail,id,goodsNo,orderState)
values(1,null,0,0,'계좌','','admin',1,'주문 확인');

-- 상품 옵션
insert into goodsoption (optNo, optName, optPrice, goodsno)
VALUES (goodsoption_seq.nextval, '/', 0, 1);

-- 주문 옵션
insert into ordersOpt(orderOptNo,optNo,amount,orderNo) values(1 ,1 , 1 , 1);
-- 장바구니
insert into basket(basketNo,goodsNo,orderPrice,dlvyCharge,id)
values(basket_seq.nextval,1,24000,3000,'test');

-- 장바구니 옵션
insert into basketOpt(basketOptNo,optNo,amount,basketNo)
values(basketOpt_seq.nextval,1,1,2);

-- 공지사항
-- a) 현재 공지
insert into notice(no, title, content, startDate)
values(notice_seq.nextval, 'Interyard 운영 정책', 'sample', '2024-07-29');

-- b) 지난 공지
insert into notice(no, title, content, startDate, endDate)
values(notice_seq.nextval, 'Interyard 서버 게시일', 'sample', '2024-07-11', '2024-07-18');

-- c) 예약 공지
insert into notice(no, title, content, startDate)
values(notice_seq.nextval, 'Interyard 서버 종료일', 'sample', '2024-08-06');

-- 이벤트
INSERT INTO event (no, title, content, startDate, endDate, image, categoryno)
values(event_seq.NEXTVAL, '도서 이벤트', '도서', '2024-07-19', '2024-10-10', 'upload/event/book.jpg', 1000);

INSERT INTO event (no, title, content, startDate, endDate, image, categoryno)
values(event_seq.NEXTVAL, ‘쇼핑 이벤트', '쇼핑', '2024-07-19', '2024-10-10', 'upload/event/shop.jpg', 2000);

INSERT INTO event (no, title, content, startDate, endDate, image, categoryno)
values(event_seq.NEXTVAL, '티켓 이벤트', '티켓', '2024-07-19', '2024-10-10', 'upload/event/ticket.jpg', 3000);

-- 메시지
insert into message (msgNo, title, content, sendDate, senderId, accepterId, refNo, parentNo, ordNo)
values (1, '우주폰 노트10 판매', '안녕하세요. 중고폰을 구매하고 싶어 연락드렸습니다.', '2024-07-15',
'admin', 'test', 1, null, 1);

insert into message (msgNo, title, content, sendDate, senderId, accepterId, refNo, parentNo, ordNo)
values (2, '[Re:]우주폰 노트10 판매', '안녕하세요~!! 그럼 편하신 시간 정해서 알려주세요.', '2024-07-17',
'test', 'admin', 1, 1, 2);

-- 질의응답
insert into qna(qnaNo, ctg, semiCtg, title, content, id, refNo, ordNo, levNo, ParentNo)
values(qna_seq.nextval, '상품문의', '배송문의', '주문한 상품이 배송이 안옵니다.', '언제 도착하나요?', 'test', qna_seq.nextval, 1, 0, null)

-- 후기
insert into review (revNo, title, content, grade, id, goodsNo, orderNo, parentNo)
values(review_seq.nextval, '이걸 이 가격에?', '가격대비 너무 만족스럽습니다.', '4.5', 'test', 1, 2, null);

-- 중고상품
INSERT INTO usedgoods (ugno, title, content, price, writeDate, ugstatus, image, id)
VALUES (usedgoods_seq.NEXTVAL, '갤럭시24', '갤럭시S24 256GB', 950000, '2024-07-19', '판매중', '/upload/image/갤럭시24.jpg', 'test');

-- 댓글
insert into reply(rno, ugno, content, id)
values(reply_seq.nextval, 149, '답장부탁드립니다', 'test1');
