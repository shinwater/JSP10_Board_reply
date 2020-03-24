--jsp_bbs 테이블 생성

--BBS(Bulletin Board System : 전자 게시판)
--게시판 필드(컬럼) 구성

create table jsp_bbs(
	board_no number(5) primary key,			--게시판 글번호
	board_writer varchar2(20) not null,		--게시판 글 작성자
	board_title varchar2(100) not null,		--게시판 글 제목
	board_cont varchar2(1000) not null,		--게시판 글 내용
	board_pwd varchar2(20) not null,		--게시판 글 비밀번호
	board_hit number(5) default 0,			--게시판 글 조회수
	board_date date,						--게시판 글 작성일
	board_group number(4),					--게시판 글 그룹
	board_step number(4),					--게시판 답변 단계
	board_indent number(4)					--게시판 답변 글 들여쓰기
);


create sequence bbs_seq
start with 1
increment by 1
nocache;


insert into jsp_bbs values(bbs_seq.nextval,'홍길동','제목1','내용1','1111',default,sysdate,bbs_seq.currval,0,0);
insert into jsp_bbs values(bbs_seq.nextval,'이순신','제목2','내용2순신님 글','2222',default,sysdate,bbs_seq.currval,0,0);
insert into jsp_bbs values(bbs_seq.nextval,'유관순','제목3','내용3입니당','3333',default,sysdate,bbs_seq.currval,0,0);
insert into jsp_bbs values(bbs_seq.nextval,'김유신','제목4','내용4김유신글이다','4444',default,sysdate,bbs_seq.currval,0,0);
insert into jsp_bbs values(bbs_seq.nextval,'김연아','제목5','내용5연아글이다','5555',default,sysdate,bbs_seq.currval,0,0);
