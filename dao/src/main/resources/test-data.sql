INSERT INTO ROLE (TYPE)
VALUES ('USER');

INSERT INTO ROLE (TYPE)
VALUES ('ADMIN');

INSERT INTO ROLE (TYPE)
VALUES ('VIP');

INSERT INTO CONTACT (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, DATE_BIRTH, PHONE, STATE)
VALUES ('max', '$2a$10$sWZ6p2zfazz6v5iOtF/A/.c9WZYvaDzBO/UKhIrYVjzPfJnuV0uhq', 'Max', 'Lysenko', '1987-03-10',
        '380970790879', 'Active');

INSERT INTO CONTACT_ROLE (CONTACT_ID, ROLE_ID)
  VALUES (1,2);

INSERT INTO CONTACT (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, DATE_BIRTH, PHONE, STATE)
VALUES ('nika', '$2a$10$sWZ6p2zfazz6v5iOtF/A/.c9WZYvaDzBO/UKhIrYVjzPfJnuV0uhq', 'Nika', 'Lysenko', '2012-09-18',
        '123456789', 'Active');

INSERT INTO CONTACT_ROLE (CONTACT_ID, ROLE_ID)
VALUES (2,1);

INSERT INTO CONTACT (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, DATE_BIRTH, PHONE, STATE)
VALUES ('kate', '$2a$10$sWZ6p2zfazz6v5iOtF/A/.c9WZYvaDzBO/UKhIrYVjzPfJnuV0uhq', 'Kate', 'Lysenko', '1987-05-27',
        '987654321', 'Active');

INSERT INTO CONTACT_ROLE (CONTACT_ID, ROLE_ID)
VALUES (3,1);

INSERT INTO HOBBY (TITLE, DESCRIPTION)
VALUES ('running', 'just do it');

INSERT INTO HOBBY (TITLE, DESCRIPTION)
VALUES ('Volunteer', 'To a lot of charities, this is more valuable than your money');

INSERT INTO HOBBY (TITLE, DESCRIPTION)
VALUES ('reading', 'it expands your horizons');

INSERT INTO PLACE (TITLE, DESCRIPTION, LATITUDE, LONGITUDE)
VALUES ('Stockholm', 'World’s Smallest Big City', 59.33, 18.06);

INSERT INTO PLACE (TITLE, DESCRIPTION, LATITUDE, LONGITUDE)
VALUES ('Malmo', 'sure to visit Oresund bridge', 55.34, 13.11);

INSERT INTO PLACE (TITLE, DESCRIPTION, LATITUDE, LONGITUDE)
VALUES ('Copenhagen', 'has more Michelin-starred restaurants than any other city in Scandinavia', 55.68, 12.57);

INSERT INTO PLACE (TITLE, DESCRIPTION, LATITUDE, LONGITUDE)
VALUES ('Lund', 'nice Sweden town,has cool cycling infrastructure,5,000 bike parking spaces', 55.42, 13.11);

INSERT INTO PLACE (TITLE, DESCRIPTION, LATITUDE, LONGITUDE)
VALUES ('Lviv', 'unique city, mentality of two worlds the East and the West  are tolerantly merged', 49.49, 24.1);

INSERT INTO PLACE (TITLE, DESCRIPTION, LATITUDE, LONGITUDE)
VALUES ('Kiev', 'the capital of Ukraine - the geographical center of Europe', 50.27, 30.3);

INSERT INTO PLACE (TITLE, DESCRIPTION, LATITUDE, LONGITUDE)
VALUES ('Tokio', 'too many people and very little space', 35.4, 139.35);

INSERT INTO PLACE (TITLE, DESCRIPTION, LATITUDE, LONGITUDE)
VALUES ('Zurich', 'Europe''s largest clock face is located in Zurich', 47.22, 8.32);



