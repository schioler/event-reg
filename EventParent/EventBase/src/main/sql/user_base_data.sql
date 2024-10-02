INSERT INTO USER_PROFILE (id, first_name, last_name) values (1, 'Lars', 'Schiøler');
INSERT INTO USER_PROFILE (id, first_name, last_name) values (2, 'Doe', 'John');

INSERT INTO LOGIN (ID, USER_PROFILE_ID, ROLE, LOGIN) VALUES (1, 1, 'ADMIN', 'lars@schioler.dk');
INSERT INTO PASSWORD (ID, LOGIN_ID, PWD) VALUES (1, 1, md5('AA43psxc'));