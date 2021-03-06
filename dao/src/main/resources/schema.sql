DROP TABLE IF EXISTS CONTACT;
DROP TABLE IF EXISTS HOBBY;
DROP TABLE IF EXISTS MESSAGE;
DROP TABLE IF EXISTS POST;
DROP TABLE IF EXISTS CONTACT_HOBBY;
DROP TABLE IF EXISTS CONTACT_PLACE;

CREATE TABLE CONTACT (
       ID INT NOT NULL AUTO_INCREMENT
     , USERNAME VARCHAR(50) NOT NULL
     , PASSWORD VARCHAR(100) NOT NULL
     , FIRST_NAME VARCHAR(50) NOT NULL
     , LAST_NAME VARCHAR(50) NOT NULL
     , DATE_BIRTH DATE
     , PHONE VARCHAR(50)
     , STATE VARCHAR(50) NOT NULL
     , UNIQUE UQ_USERNAME (USERNAME)
     , PRIMARY KEY (ID)
);

CREATE TABLE ROLE(
  ID INT NOT NULL AUTO_INCREMENT,
  TYPE VARCHAR(30) NOT NULL,
  PRIMARY KEY (ID),
  UNIQUE (TYPE)
);

CREATE TABLE CONTACT_ROLE (
  CONTACT_ID INT NOT NULL,
  ROLE_ID INT NOT NULL,
  PRIMARY KEY (CONTACT_ID, ROLE_ID),
  CONSTRAINT FK_CONTACT FOREIGN KEY (CONTACT_ID) REFERENCES CONTACT (ID),
  CONSTRAINT FK_ROLE FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ID)
);

CREATE TABLE HOBBY (
       ID INT NOT NULL AUTO_INCREMENT
     , TITLE VARCHAR(20) NOT NULL
     , DESCRIPTION VARCHAR(200)
     , PRIMARY KEY (ID)
);

CREATE TABLE MESSAGE (
       ID INT NOT NULL AUTO_INCREMENT
     , CONTACT_ID_FROM INT NOT NULL
     , CONTACT_ID_TO INT NOT NULL
     , MESSAGE_DATE DATETIME NOT NULL
     , CONTENT VARCHAR(160) NOT NULL
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_MESSAGE_CONTACT_FROM FOREIGN KEY (CONTACT_ID_FROM)
                  REFERENCES CONTACT (ID)
     , CONSTRAINT FK_MESSAGE_CONTACT_TO FOREIGN KEY (CONTACT_ID_TO)
                  REFERENCES CONTACT (ID)
);

CREATE TABLE PLACE (
       ID INT NOT NULL AUTO_INCREMENT
     , TITLE VARCHAR(30) NOT NULL
     , DESCRIPTION VARCHAR(200)
     , LATITUDE DECIMAL(10, 2)
     , LONGITUDE DECIMAL(10, 2)
     , PRIMARY KEY (ID)
);

CREATE TABLE POST (
       ID INT NOT NULL AUTO_INCREMENT
     , CONTACT_ID INT NOT NULL
     , POST_DATE DATETIME NOT NULL
     , CONTENT VARCHAR(160) NOT NULL
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_POST_CONTACT FOREIGN KEY (CONTACT_ID)
                  REFERENCES CONTACT (ID)
     , PRIMARY KEY (ID)
);

CREATE TABLE CONTACT_HOBBY (
       CONTACT_ID INT NOT NULL
     , HOBBY_ID INT NOT NULL
     , PRIMARY KEY (CONTACT_ID, HOBBY_ID)
     , CONSTRAINT FK_CONTACT_HOBBY_1 FOREIGN KEY (CONTACT_ID)
                  REFERENCES CONTACT (ID) ON DELETE CASCADE
     , CONSTRAINT FK_CONTACT_HOBBY_2 FOREIGN KEY (HOBBY_ID)
                  REFERENCES HOBBY (ID) ON DELETE CASCADE
);

CREATE TABLE CONTACT_PLACE (
       CONTACT_ID INT NOT NULL
     , PLACE_ID INT NOT NULL
     , PRIMARY KEY (CONTACT_ID, PLACE_ID)
     , CONSTRAINT FK_CONTACT_PLACE_1 FOREIGN KEY (CONTACT_ID)
                  REFERENCES CONTACT (ID) ON DELETE CASCADE
     , CONSTRAINT FK_CONTACT_PLACE_2 FOREIGN KEY (PLACE_ID)
                  REFERENCES PLACE (ID) ON DELETE CASCADE
);

CREATE TABLE FRIENDSHIP (
       CONTACT_ID_MAIN INT NOT NULL
     , CONTACT_ID_FRIEND INT NOT NULL
     , PRIMARY KEY (CONTACT_ID_MAIN, CONTACT_ID_FRIEND)
     , CONSTRAINT FK_CONTACT_ID_MAIN FOREIGN KEY (CONTACT_ID_MAIN)
                  REFERENCES CONTACT (ID) ON DELETE CASCADE
     , CONSTRAINT FK_CONTACT_ID_FRIEND FOREIGN KEY (CONTACT_ID_FRIEND)
                       REFERENCES CONTACT (ID) ON DELETE CASCADE
);