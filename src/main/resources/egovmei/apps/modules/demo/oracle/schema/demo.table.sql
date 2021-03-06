CREATE TABLE DEMO
(
	DEMO_IDX      NUMBER NOT NULL,
	USER_NAME     VARCHAR2(50) NOT NULL ,
	USER_ID       VARCHAR2(50) NULL ,
	MEMO          VARCHAR2 NULL ,
	SEQ           NUMBER NULL
);

ALTER TABLE DEMO
	ADD CONSTRAINT  DEMO_PK PRIMARY KEY (DEMO_IDX);

COMMENT ON TABLE DEMO IS '데모';

COMMENT ON COLUMN DEMO.DEMO_IDX IS '번호';
COMMENT ON COLUMN DEMO.USER_NAME IS '이름';
COMMENT ON COLUMN DEMO.USER_ID IS '아이디';
COMMENT ON COLUMN DEMO.MEMO IS '메모';
COMMENT ON COLUMN DEMO.SEQ IS '순번';