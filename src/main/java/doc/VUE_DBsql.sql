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
	"USER_LOCATION_DONG" VARCHAR2(100 BYTE), 
	"USER_LIKE" VARCHAR2(100 BYTE), 
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