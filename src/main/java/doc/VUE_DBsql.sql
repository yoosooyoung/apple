create user VUE_DB identified by VUE_DB1234;
grant connect, resource, dba to VUE_DB;

--------------------------------------------------------
--  DDL for Table APPLE_MEMBER
--------------------------------------------------------

  CREATE TABLE "APPLE_MEMBER" 
   (	"USER_ID" VARCHAR2(100 BYTE), 
	"USER_PW" VARCHAR2(100 BYTE), 
	"NICK_NAME" VARCHAR2(100 BYTE), 
	"USER_LOCATION" VARCHAR2(100 BYTE), 
	"USER_IMAGE" VARCHAR2(100 BYTE), 
	"USER_JOIN" VARCHAR2(100 BYTE), 
	"USER_EMAIL" VARCHAR2(100 BYTE)
   ) 
   
   
   -----------------------------------------------------------
   -- DML 로그인 테스트용 id: admin, pw:12345 순서대로 명령어 칠것.
   -----------------------------------------------------------
   DELETE FROM APPLE_MEMBER;
   INSERT INTO APPLE_MEMBER
   (USER_ID, USER_PW, NICK_NAME)
   VALUES ('admin','$2a$10$WLYEXsKMeiOwqvY4fEAv0etZ5caCdMdGgyG1ZHZnEV3ICHgMeLpSm','apple');
   commit;   
   
   UPDATE APPLE_MEMBER SET USER_LOCATION = '중랑구 중화동',  USER_EMAIL='wndgkh202@naver.com' where user_id ='admin';
   commit;
   
   select * from apple_member;
   
   
   --------------------------------
   -- 게시판
   --------------------------------
  DROP TABLE APPLE_BOARD;
   CREATE TABLE "APPLE_BOARD" 
   (	
    "BOARD_SEQ" VARCHAR(25),
   "BOARD_TITLE" VARCHAR(100), 
	"BOARD_PRICE" VARCHAR(100), 
	"BOARD_CONTENT" VARCHAR(100), 
	"USER_ID" VARCHAR(100), 
	"BOARD_DATE" VARCHAR(100), 
	"BOARD_VIEWS" VARCHAR(100), 
	"BOARD_LIKE" VARCHAR(100)
   ) ;
   DROP TABLE APPLE_PICTURE;
    CREATE TABLE "APPLE_PICTURE" 
   (	"BOARD_SEQ" VARCHAR2(100 BYTE), 
	"PICTURE_URL" VARCHAR2(100 BYTE)
   ) ;
      
   CREATE SEQUENCE board_seq
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;

              
    SELECT * FROM APPLE_BOARD;
    SELECT * FROM APPLE_PICTURE;
    SELECT * FROM APPLE_MEMBER;
   

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
