CREATE SEQUENCE DASHBOARD_IDX_SEQ;

CREATE TABLE DASHBOARD
(
	DASHBOARD_IDX      CHAR(20) NOT NULL ,
	TITLE              VARCHAR2(150) NOT NULL ,
	MARGIN_X           NUMBER(3) NULL ,
	MARGIN_Y           NUMBER(3) NULL ,
	PADDING_X          NUMBER(3) NULL ,
	PADDING_Y          NUMBER(3) NULL ,
	HEIGHT             NUMBER(5) NULL ,
	LAYOUT_JSON_FILE   VARCHAR2(250) NOT NULL ,
	LAYOUTS_JSON_FILE   VARCHAR2(250) NOT NULL
);

ALTER TABLE DASHBOARD
	ADD CONSTRAINT  DASHBOARD_PK PRIMARY KEY (DASHBOARD_IDX);

COMMENT ON TABLE DASHBOARD IS '대시보드';

COMMENT ON COLUMN DASHBOARD.DASHBOARD_IDX IS '대시보드번호';
COMMENT ON COLUMN DASHBOARD.TITLE IS '타이틀';
COMMENT ON COLUMN DASHBOARD.MARGIN_X IS '간격X';
COMMENT ON COLUMN DASHBOARD.MARGIN_Y IS '간격Y';
COMMENT ON COLUMN DASHBOARD.PADDING_X IS '여백X';
COMMENT ON COLUMN DASHBOARD.PADDING_Y IS '여백Y';
COMMENT ON COLUMN DASHBOARD.HEIGHT IS '메모';
COMMENT ON COLUMN DASHBOARD.LAYOUT_JSON_FILE IS 'layout파일';
COMMENT ON COLUMN DASHBOARD.LAYOUTS_JSON_FILE IS 'layouts파일';