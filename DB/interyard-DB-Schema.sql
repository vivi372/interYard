-- 1-1. ���̺� ����
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
-- 1-2. ������ ����
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

-- 2-1. ���̺� ����
-- ȸ����� ���� ���̺�
CREATE TABLE grade(
	gradeNo     NUMBER NOT NULL ,
	gradeName   VARCHAR2(20) NOT NULL ,
 PRIMARY KEY (gradeNo)
);
-- ȸ�� ���� ���̺�
CREATE TABLE member(
	id          VARCHAR2(30) NOT NULL ,
	pw          VARCHAR2(30) NOT NULL ,
	name        VARCHAR2(30) NOT NULL ,
	gender      VARCHAR2(10) NOT NULL  CHECK (gender in('����','����')),
	birth       DATE NOT NULL ,
	tel         VARCHAR2(30) UNIQUE NOT NULL ,
	email       VARCHAR2(100) UNIQUE NOT NULL ,
	WriteDate   DATE DEFAULT sysdate NULL ,
	conDate     DATE DEFAULT sysdate NULL ,
	photo       VARCHAR2(300) NULL ,
	status      VARCHAR2(10) DEFAULT '����' NULL CHECK (status in('����','Ż��','�޸�','����')),
	gradeNo     NUMBER DEFAULT 1 NULL ,
	newMsgCnt   NUMBER DEFAULT 0 NULL ,
 PRIMARY KEY (id),
 FOREIGN KEY (gradeNo) REFERENCES grade(gradeNo) ON DELETE SET NULL
);
-- ����� ���̺�
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
-- ī�װ� ���̺�
CREATE TABLE Category(
	CategoryNo      NUMBER DEFAULT 1 NOT NULL ,
	CategoryName    VARCHAR2(300) NULL ,
 PRIMARY KEY (CategoryNo)
);
-- ��ǰ ���� ���̺�
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
	goodsRating     VARCHAR2(30) DEFAULT '��ü����' NOT NULL ,
	goodsStartDate  DATE NULL ,
	goodsEndDate    DATE NULL ,
	goodsTrans      VARCHAR2(30) NULL ,
	categoryNo      NUMBER DEFAULT 1 NULL ,
 PRIMARY KEY (goodsNo),
 FOREIGN KEY (categoryNo) REFERENCES Category(CategoryNo) ON DELETE SET NULL
);
-- �ֹ� ���� ���̺�
CREATE TABLE orders(
	orderNo         NUMBER NOT NULL ,
	dlvyAddrNo      NUMBER NULL ,
	dlvyMemo        VARCHAR2(300) NULL ,
	orderPrice      NUMBER NOT NULL ,
	dlvyCharge      NUMBER NOT NULL ,
	payWay          VARCHAR2(30) NOT NULL CHECK (payWay in('����','�ڵ���','ī��')),
	payDetail       VARCHAR2(100) NULL ,
	orderState      VARCHAR2(30) DEFAULT '�ֹ� Ȯ��' NOT NULL CHECK (orderState in('�ֹ� Ȯ��','��� ���','�����','��� �Ϸ�','���� Ȯ��','���� Ȯ��(�����ۼ�)','��� ��û')),
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
-- ��ǰ �ɼ� ���̺�
CREATE TABLE goodsOption(
	optNo           NUMBER(13) NOT NULL ,
	optName         VARCHAR2(30) NOT NULL ,
	optPrice        NUMBER(8) DEFAULT 0 NOT NULL ,
	goodsNo         NUMBER(13) NULL ,
 PRIMARY KEY (optNo),
 FOREIGN KEY (goodsNo) REFERENCES Goods(goodsNo) ON DELETE SET NULL
);
-- �ֹ� �ɼ� ���̺�
CREATE TABLE ordersOpt(
	orderOptNo      NUMBER ,
	optNo           NUMBER(13) ,
	amount          NUMBER ,
	orderNo         NUMBER ,
 PRIMARY KEY (orderOptNo) ,
 FOREIGN KEY (orderNo) REFERENCES orders(orderNo) ON DELETE CASCADE ,
 FOREIGN KEY (optNo) REFERENCES goodsOption(optNo) ON DELETE SET NULL
);
-- ��ٱ��� ���̺�
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
-- ��ٱ��� �ɼ� ���̺�
CREATE TABLE basketOpt(
	basketOptNo    NUMBER  NOT NULL ,
	optNo          NUMBER(13)  NOT NULL ,
	amount         NUMBER  NOT NULL ,
	basketNo       NUMBER  NOT NULL ,
 PRIMARY KEY (basketOptNo),
 FOREIGN KEY (basketNo) REFERENCES basket(basketNo) ON DELETE CASCADE,
 FOREIGN KEY (optNo)    REFERENCES goodsOption(optNo) ON DELETE CASCADE
);
-- �������� ���̺�
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
-- �̺�Ʈ ���̺�
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
-- �޽��� ���̺�
CREATE TABLE message(
	msgNo         NUMBER NOT NULL ,
	title         VARCHAR2(150) NOT NULL ,
	content       VARCHAR2(900) NOT NULL ,
	sendDate      DATE DEFAULT sysdate NOT NULL ,
	acceptDate    DATE NULL ,
	uploadFile    VARCHAR2(200) NULL ,
	msgStatus     VARCHAR2(15) DEFAULT '����' NOT NULL CHECK (msgStatus in ('����', '����')),
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
-- �������� ���̺�
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
-- �ı� ���̺�
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
-- �߰��ǰ ���̺�
CREATE TABLE usedgoods(
	ugno          NUMBER NOT NULL ,
	title         VARCHAR2(300) NOT NULL ,
	content       VARCHAR2(300) NOT NULL ,
	price         NUMBER NOT NULL ,
	image         VARCHAR2(100) NOT NULL ,
	wrtieDate     DATE DEFAULT sysdate NULL ,
	ugstatus      VARCHAR2(20) DEFAULT '�Ǹ���' NULL CHECK (ugstatus in('�Ǹ���', '�ǸſϷ�')),
	id            VARCHAR2(30) NOT NULL ,
 PRIMARY KEY (ugno),
 FOREIGN KEY (id) REFERENCES member(id) ON DELETE SET NULL
);
-- ��� ���̺�
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
-- 2-2. ������ ����
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

-- 3. ���õ�����
-- ȸ�� ��� ����
insert into grade values(1,'�Ϲ�ȸ��'); 
insert into grade VALUES(9,'������');

-- ȸ�� ����
-- a) �Ϲ�ȸ��
insert into member(id, pw, name, gender, birth, tel, email, photo)
values('test', '1111', 'ȫ�浿', '����', '1990-01-11', '010-1336-5628', 'hong@naver.com', '/upload/member/hong.jpg');
-- b) ������
insert into member(id, pw, name, gender, birth, tel, email, photo, gradeNo)
values('admin', '1111', '������', '����', '1922-01-11', '010-2133-2131', 'admin131@naver.com', '/upload/member/adimn.jpg', 9);

-- �����
Insert into dlvyAddr(dlvyAddrNo, dlvyName, recipient, tel, addr, postNo, basic, id) 
values (1,'��','��','010-3456-4567','��� ���ֽ� ����� 160 (���赿, ���ִ�������������1����) /103��',11460,1,'test');

-- ī�װ�
insert into category(categoryNo, categoryname)
values (2000, '����');

-- ��ǰ ����
insert into goods(categoryNo, goodsNo, goodsTitle, goodsMaker, goodsPublicher, goodsRunTime, goodsPrice, goodsQuantity, goodsCost, goodsStatus, goodsContent, goodsRating)
VALUES (1, goods_seq.nextval, '�׽�Ʈ', 'test', 'tester', 80, 10000, 1, 3000, '�׽�Ʈ', '�׽�Ʈ�� ���û�ǰ�Դϴ�.', '�׽�Ʈ');

-- �ֹ� ����
insert into orders(orderNo,dlvyAddrNo,orderPrice,dlvyCharge,payWay,payDetail,id,goodsNo,orderState)
values(1,null,0,0,'����','','admin',1,'�ֹ� Ȯ��');

-- ��ǰ �ɼ�
insert into goodsoption (optNo, optName, optPrice, goodsno)
VALUES (goodsoption_seq.nextval, '/', 0, 1);

-- �ֹ� �ɼ�
insert into ordersOpt(orderOptNo,optNo,amount,orderNo) values(1 ,1 , 1 , 1);
-- ��ٱ���
insert into basket(basketNo,goodsNo,orderPrice,dlvyCharge,id)
values(basket_seq.nextval,1,24000,3000,'test');

-- ��ٱ��� �ɼ�
insert into basketOpt(basketOptNo,optNo,amount,basketNo)
values(basketOpt_seq.nextval,1,1,2);

-- ��������
-- a) ���� ����
insert into notice(no, title, content, startDate)
values(notice_seq.nextval, 'Interyard � ��å', 'sample', '2024-07-29');

-- b) ���� ����
insert into notice(no, title, content, startDate, endDate)
values(notice_seq.nextval, 'Interyard ���� �Խ���', 'sample', '2024-07-11', '2024-07-18');

-- c) ���� ����
insert into notice(no, title, content, startDate)
values(notice_seq.nextval, 'Interyard ���� ������', 'sample', '2024-08-06');

-- �̺�Ʈ
INSERT INTO event (no, title, content, startDate, endDate, image, categoryno)
values(event_seq.NEXTVAL, '���� �̺�Ʈ', '����', '2024-07-19', '2024-10-10', 'upload/event/book.jpg', 1000);

INSERT INTO event (no, title, content, startDate, endDate, image, categoryno)
values(event_seq.NEXTVAL, ������ �̺�Ʈ', '����', '2024-07-19', '2024-10-10', 'upload/event/shop.jpg', 2000);

INSERT INTO event (no, title, content, startDate, endDate, image, categoryno)
values(event_seq.NEXTVAL, 'Ƽ�� �̺�Ʈ', 'Ƽ��', '2024-07-19', '2024-10-10', 'upload/event/ticket.jpg', 3000);

-- �޽���
insert into message (msgNo, title, content, sendDate, senderId, accepterId, refNo, parentNo, ordNo)
values (1, '������ ��Ʈ10 �Ǹ�', '�ȳ��ϼ���. �߰����� �����ϰ� �;� ������Ƚ��ϴ�.', '2024-07-15',
'admin', 'test', 1, null, 1);

insert into message (msgNo, title, content, sendDate, senderId, accepterId, refNo, parentNo, ordNo)
values (2, '[Re:]������ ��Ʈ10 �Ǹ�', '�ȳ��ϼ���~!! �׷� ���Ͻ� �ð� ���ؼ� �˷��ּ���.', '2024-07-17',
'test', 'admin', 1, 1, 2);

-- ��������
insert into qna(qnaNo, ctg, semiCtg, title, content, id, refNo, ordNo, levNo, ParentNo)
values(qna_seq.nextval, '��ǰ����', '��۹���', '�ֹ��� ��ǰ�� ����� �ȿɴϴ�.', '���� �����ϳ���?', 'test', qna_seq.nextval, 1, 0, null)

-- �ı�
insert into review (revNo, title, content, grade, id, goodsNo, orderNo, parentNo)
values(review_seq.nextval, '�̰� �� ���ݿ�?', '���ݴ�� �ʹ� �����������ϴ�.', '4.5', 'test', 1, 2, null);

-- �߰��ǰ
INSERT INTO usedgoods (ugno, title, content, price, writeDate, ugstatus, image, id)
VALUES (usedgoods_seq.NEXTVAL, '������24', '������S24 256GB', 950000, '2024-07-19', '�Ǹ���', '/upload/image/������24.jpg', 'test');

-- ���
insert into reply(rno, ugno, content, id)
values(reply_seq.nextval, 149, '�����Ź�帳�ϴ�', 'test1');
