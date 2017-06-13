CREATE TABLE COURSE (
  CODE CHAR(10) NOT NULL PRIMARY KEY,
  TITLE VARCHAR(100) NOT NULL
);


CREATE TABLE LOCATION (
  ID BIGINT NOT NULL PRIMARY KEY,
  CITY VARCHAR(50) NOT NULL
);


CREATE TABLE COURSE_SESSION (
  ID BIGINT NOT NULL PRIMARY KEY,
  START_DATE DATE NOT NULL,
  END_DATE DATE NOT NULL,
  COURSE_CODE CHAR(10) NOT NULL,
  LOCATION_ID BIGINT NOT NULL,

  FOREIGN KEY (COURSE_CODE) REFERENCES COURSE (CODE),
  FOREIGN KEY (LOCATION_ID) REFERENCES LOCATION (ID)
);


CREATE TABLE CLIENT (
  ID BIGINT NOT NULL PRIMARY KEY,
  LASTNAME VARCHAR(50) NOT NULL,
  FIRSTNAME VARCHAR(50) NOT NULL,
  ADDRESS VARCHAR(100) NOT NULL,
  PHONE VARCHAR(20) NOT NULL,
  EMAIL VARCHAR(100),
  COURSE_SESSION_ID BIGINT NOT NULL,

  FOREIGN KEY (COURSE_SESSION_ID) REFERENCES COURSE_SESSION (ID)
);


INSERT INTO ADMIN.LOCATION (ID, CITY) VALUES (1, 'Paris');
INSERT INTO ADMIN.LOCATION (ID, CITY) VALUES (2, 'Londres');
INSERT INTO ADMIN.LOCATION (ID, CITY) VALUES (3, 'Belfort Ville lumiere');INSERT INTO ADMIN.COURSE (CODE, TITLE) VALUES ('MT11      ', 'Base des maths pour l''ingénieur');
INSERT INTO ADMIN.COURSE (CODE, TITLE) VALUES ('MT12      ', 'Algèbre');
INSERT INTO ADMIN.COURSE (CODE, TITLE) VALUES ('LO54      ', 'J2E');INSERT INTO ADMIN.COURSE_SESSION (ID, START_DATE, END_DATE, COURSE_CODE, LOCATION_ID) VALUES (1, '2017-06-09', '2017-06-10', 'MT11      ', 3);
INSERT INTO ADMIN.COURSE_SESSION (ID, START_DATE, END_DATE, COURSE_CODE, LOCATION_ID) VALUES (2, '2017-06-23', '2017-06-15', 'MT12      ', 1);INSERT INTO ADMIN.CLIENT (ID, LASTNAME, FIRSTNAME, ADDRESS, PHONE, EMAIL, COURSE_SESSION_ID) VALUES (1, 'Favry', 'Gerar', '4 rue de la vraie gauche 9000 Paris', '0123456789', 'fg@mail.com', 1);