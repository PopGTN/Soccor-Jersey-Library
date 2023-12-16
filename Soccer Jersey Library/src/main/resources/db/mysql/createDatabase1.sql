DROP
DATABASE IF EXISTS cis2232_Soccer_Jersey_Library;
CREATE
DATABASE cis2232_Soccer_Jersey_Library;
use
cis2232_Soccer_Jersey_Library;

CREATE TABLE CodeType
(
    codeTypeId         int(3) COMMENT 'This is the primary key for code types',
    englishDescription varchar(100) NOT NULL COMMENT 'English description',
    frenchDescription  varchar(100) DEFAULT NULL COMMENT 'French description',
    createdDateTime    datetime     DEFAULT NULL,
    createdUserId      varchar(20)  DEFAULT NULL,
    updatedDateTime    datetime     DEFAULT NULL,
    updatedUserId      varchar(20)  DEFAULT NULL
) COMMENT 'This tables holds the code types that are available for the application';

INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (2, 'Ticket Types', 'Ticket Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');

CREATE TABLE CodeValue
(
    codeTypeId              int(3) NOT NULL COMMENT 'see code_type table',
    codeValueSequence       int(3) NOT NULL,
    englishDescription      varchar(100) NOT NULL COMMENT 'English description',
    englishDescriptionShort varchar(20)  NOT NULL COMMENT 'English abbreviation for description',
    frenchDescription       varchar(100) DEFAULT NULL COMMENT 'French description',
    frenchDescriptionShort  varchar(20)  DEFAULT NULL COMMENT 'French abbreviation for description',
    sortOrder               int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
    createdDateTime         datetime     DEFAULT NULL,
    createdUserId           varchar(20)  DEFAULT NULL,
    updatedDateTime         datetime     DEFAULT NULL,
    updatedUserId           varchar(20)  DEFAULT NULL
) COMMENT='This will hold code values for the application.';

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37',
        'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 3, 'Balcony', 'Balcony', 'BalconyFR', 'BalconyFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37',
        'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 4, 'Orchestra', 'Orchestra', 'OrchestraFR', 'OrchestraFR', '2015-10-25 18:44:37', 'admin',
        '2015-10-25 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 5, 'Main Floor', 'Main Floor', 'Main FloorFR', 'Main FloorFR', '2015-10-25 18:44:37', 'admin',
        '2015-10-25 18:44:37', 'admin');



CREATE TABLE UserAccess
(
    userAccessId         int(3) NOT NULL,
    username             varchar(100) NOT NULL COMMENT 'Unique user name for app',
    password             varchar(128) NOT NULL,
    name                 varchar(128),
    userAccessStatusCode int(3) NOT NULL DEFAULT '1' COMMENT 'Code type #2',
    userTypeCode         int(3) NOT NULL DEFAULT '1' COMMENT 'Code type #1',
    createdDateTime      datetime DEFAULT NULL COMMENT 'When user was created.'
);

ALTER TABLE CodeType
    ADD PRIMARY KEY (codeTypeId);

ALTER TABLE CodeValue
    ADD PRIMARY KEY (codeValueSequence);
--  ADD KEY codeTypeId (codeTypeId);

ALTER TABLE UserAccess
    ADD PRIMARY KEY (userAccessId),
  ADD KEY userTypeCode (userTypeCode);

ALTER TABLE CodeType
    MODIFY codeTypeId int (3) NOT NULL COMMENT 'This is the primary key for code types';

ALTER TABLE CodeValue
    MODIFY codeValueSequence int (3) NOT NULL;

ALTER TABLE UserAccess
    MODIFY userAccessId int (3) NOT NULL AUTO_INCREMENT;

CREATE TABLE SoccorJerseys
(
    id          int(5),
    jerseyCode  INT NOT NULL,
    nameCountry VARCHAR(255) NOT NULL,
    nameClub    VARCHAR(255) NOT NULL,
    nameType    VARCHAR(255) NOT NULL,
    namePlayer  VARCHAR(255) NOT NULL
) COMMENT 'This table holds ticket order data';

ALTER TABLE SoccorJerseys
    ADD PRIMARY KEY (id);
ALTER TABLE SoccorJerseys
    MODIFY id int (4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key', AUTO_INCREMENT=1;
INSERT INTO SoccorJerseys (jerseyCode, nameCountry, nameClub, nameType, namePlayer)
VALUES (1, 'Poland', 'Bayern Munich', 'Home', 'Robert Lewandowski'),
       (2, 'Egypt', 'Liverpool', 'Away', 'Mohamed Salah'),
       (3, 'Argentina', 'Paris Saint-Germain', 'Home', 'Lionel Messi'),
       (4, 'France', 'Paris Saint-Germain', 'Away', 'Kylian Mbappé'),
       (5, 'Portugal', 'Manchester United', 'Home', 'Cristiano Ronaldo'),
       (6, 'Spain', 'FC Barcelona', 'Home', 'Andres Iniesta'),
       (7, 'Germany', 'Bayern Munich', 'Away', 'Thomas Müller'),
       (8, 'Italy', 'AC Milan', 'Home', 'Gianluigi Donnarumma'),
       (9, 'Brazil', 'São Paulo FC', 'Home', 'Neymar'),
       (10, 'Argentina', 'Boca Juniors', 'Away', 'Carlos Tevez'),
       (11, 'England', 'Manchester City', 'Home', 'Raheem Sterling'),
       (12, 'Belgium', 'Manchester City', 'Away', 'Kevin De Bruyne'),
       (13, 'Brazil', 'Paris Saint-Germain', 'Home', 'Neymar'),
       (14, 'Spain', 'Real Madrid', 'Away', 'Sergio Ramos'),
       (15, 'Germany', 'Borussia Dortmund', 'Home', 'Erling Haaland'),
       (16, 'Argentina', 'Inter Milan', 'Away', 'Lautaro Martinez'),
       (17, 'France', 'Manchester United', 'Home', 'Paul Pogba'),
       (18, 'Portugal', 'Juventus', 'Away', 'Bruno Fernandes'),
       (19, 'England', 'Liverpool', 'Home', 'Trent Alexander-Arnold'),
       (20, 'Italy', 'AS Roma', 'Away', 'Lorenzo Pellegrini'),
       (21, 'Belgium', 'Chelsea', 'Home', 'Romelu Lukaku'),
       (22, 'Netherlands', 'FC Barcelona', 'Away', 'Frenkie de Jong');
