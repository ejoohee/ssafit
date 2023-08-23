drop database if exists ssafy_board;
create database ssafy_board default charset utf8mb4;
use ssafy_board;

create table video (
video_seq int primary key auto_increment,
title varchar(100) not null,
part varchar(30) not null,
video_id varchar(30) not null,
creator varchar(30) not null,
view_cnt int default 0
);

create table users (
user_seq int primary key auto_increment,
id varchar(30) not null,
pw varchar(30) not null,
nickname varchar(30) not null,
email varchar(50) not null,
age int not null
);

create table comments (
comment_seq int primary key auto_increment,
video_id varchar(50) not null,
nickname varchar(50) not null,
content varchar(4096) not null,
reg_date TIMESTAMP DEFAULT NOW()
);

INSERT INTO comments (video_id, nickname, content) VALUES ("gMaB-fG4u4g", "익명1", "너무 재밌어요");
select * from comments;

update comments set content="비디오 댓글 수정" where comment_seq="1";
select * from comments;


ALTER TABLE users
ADD CONSTRAINT uc_nickname UNIQUE (nickname);

create table likes (
id varchar(50) not null,
video_id varchar(50) not null,
video_title varchar(200) not null
);
 ALTER TABLE `likes` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

insert into likes (id, video_id, video_title) VALUES ("ssafy", "gMaB-fG4u4g","전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]"),("test1","54tTYO-vU2E","상체 다이어트 최고의 운동 BEST"),
("test1","swRNeYw1JkY","하루 15분! 전신 칼로리 불태우는 다이어트 운동"),("ssafy","n8zGSCTd1bA","톱모델 한혜진 살뺄 때 하는 15분 근력유산소 (한번만 해보세요)");

CREATE TABLE boards (
	id INT AUTO_INCREMENT,
    writer VARCHAR(20) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT,
    view_cnt INT DEFAULT 0,
    reg_date TIMESTAMP DEFAULT now(),
    PRIMARY KEY(id)
);

CREATE TABLE boardcomments (
    id INT AUTO_INCREMENT,
    board_id INT,
    writer VARCHAR(30) NOT NULL,
    content TEXT,
    reg_date TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY(id),
    FOREIGN KEY(board_id) REFERENCES boards(id) on delete cascade,
    FOREIGN KEY(writer) REFERENCES users(nickname) on delete cascade
);

ALTER TABLE boardcomments DROP FOREIGN KEY boardcomments_ibfk_2;

ALTER TABLE users ADD INDEX idx_nickname (nickname);
ALTER TABLE boards
ADD COLUMN writer_nickname VARCHAR(30),
ADD FOREIGN KEY (writer_nickname) REFERENCES users(nickname);


insert into video(title,part,video_id,creator,view_cnt)
values ("전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]","전신","gMaB-fG4u4g","ThankyouBUBU",3456789),
("하루 15분! 전신 칼로리 불태우는 다이어트 운동","전신","swRNeYw1JkY","ThankyouBUBU",1234567),
("상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]","상체","54tTYO-vU2E","ThankyouBUBU",2578678),
("상체비만 다이어트 최고의 운동 [상체 핵매운맛]","상체","QqqZH3j_vH0","ThankyouBUBU",4547357),
("저는 하체 식주의자 입니다","하체","u5OgcZdNbMo","GYM종국",44564564),
("11자복근 복부 최고의 운동 [복근 핵매운맛]","복부","PjGcOP-TQPE","ThankyouBUBU",78945612),
("(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)","복부","7TLk7pscICk","SomiFit",98756421),
("하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]","하체","tzN6ypk6Sps","김강민",8788387);

INSERT INTO users (id, pw, nickname, email, age)
values ("euiseok", "0813", "의석", "euiseok@ssafy.com", 28),
("joohee","11", "주희", "joohee@ssafy.com", 26),
("ssafy", "1234", "김싸피", "ssafy.kim@ssafy.com", 20),
("test1","11", "익명1", "test1@ssafy.com", 22),
("test2","11", "익명2", "test2@ssafy.com", 23),
("test3","11", "익명3", "test3@ssafy.com", 24);

select * from users;

INSERT INTO comments (video_id, nickname, content)
VALUES ("54tTYO-vU2E", "익명1", "댓글1"),
	   ("54tTYO-vU2E", "의석", "댓글2"),
       ("54tTYO-vU2E", "주희", "댓글3"),
       ("54tTYO-vU2E", "익명2", "댓글4"),
       ("54tTYO-vU2E", "익명3", "댓글5"),
       ("54tTYO-vU2E", "익명1", "댓글6"),
       ("54tTYO-vU2E", "김싸피", "댓글7"),
       ("54tTYO-vU2E", "의석", "댓글8"),
       ("54tTYO-vU2E", "익명3", "댓글9"),
       ("54tTYO-vU2E", "주희", "댓글10"),
       ("54tTYO-vU2E", "김싸피", "댓글11");

select * from comments;

select * from video where video_seq="2";
select * FROM VIDEO;

INSERT INTO boards(title, writer, content) 
VALUES ("프로젝트", "의석", "어려워요."),
       ("싸피", "주희", "출근시간 10시로 해주세요."),
	   ("테스트","익명1","테스트입니다."),
	   ("안녕", "익명2", "안녕하세요"),
       ("백엔드","익명1","어려워요"),
       ("프론트엔드","익명3","어려워요");
       
SELECT * FROM boards;

insert into boardcomments(board_id,writer,content)
values (3, "익명2", "3번게시글의 댓글"),
       (1, "의석", "댓글1"),
       (1, "익명3", "댓글2"),
       (1, "주희", "댓글3"),
       (1, "익명2", "댓글4"),
       (1, "의석", "댓글5"),
       (1, "김싸피", "댓글6"),
       (1, "익명3", "댓글7"),
       (1, "김싸피", "댓글8"),
       (1, "익명2", "댓글9"),
       (1, "익명1", "댓글10"),
       (1, "익명1", "댓글11"),
       (1, "익명3", "댓글12");

select * from boardcomments;

delete from comments where comment_seq="2";

select * from likes;

