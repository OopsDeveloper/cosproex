create sequence seq_board;

DROP SEQUENCE seq_board;

create table tbl_board (
  bno number(10,0),
  title varchar2(200) not null,
  content varchar2(2000) not null,
  writer varchar2(50) not null,
  regdate date default sysdate, 
  updatedate date default sysdate
);

alter table tbl_board add constraint pk_board 
primary key (bno);

INSERT INTO TBL_BOARD (bno, title, content, writer) VALUES (seq_board.nextval, '테스트 제목1', '테스트 내용1', 'user01');

SELECT * FROM tbl_board ORDER BY bno desc;


CREATE TABLE tbl_reply (
   rno number(10,0),
   bno number(10,0) NOT NULL,
   reply varchar2(1000) NOT NULL,
   replyer varchar2(50) NOT NULL,
   replyDate date DEFAULT sysdate,
   updateDate date DEFAULT sysdate
);




DROP SEQUENCE seq_reply;


CREATE SEQUENCE seq_reply;

ALTER TABLE tbl_reply ADD CONSTRAINT pk_reply PRIMARY KEY (rno);

ALTER TABLE TBL_REPLY ADD CONSTRAINT fk_reply_board FOREIGN KEY (bno) references tbl_board(bno);