DELETE FROM MEDICINE_FORM ;
INSERT INTO MEDICINE_FORM (CREATED_TS, OWNER_ID, NAME, FORM) values (NOW(), 5, 'The medicine is taken as a pill, to be swallowed with wath water', 
   'pill');
INSERT INTO MEDICINE_FORM (CREATED_TS, OWNER_ID, NAME, FORM) values (NOW(), 5, 'tbd', 'fluent');
INSERT INTO MEDICINE_FORM (CREATED_TS, OWNER_ID, NAME, FORM) values (NOW(), 5, 'tbd', 'gel');
INSERT INTO MEDICINE_FORM (CREATED_TS, OWNER_ID, NAME, FORM) values (NOW(), 5, 'tbd', 'iv');
INSERT INTO MEDICINE_FORM (CREATED_TS, OWNER_ID, NAME, FORM) values (NOW(), 5, 'tbd', 'mix-with-water');
INSERT INTO MEDICINE_FORM (CREATED_TS, OWNER_ID, NAME, FORM) values (NOW(), 5, 'tbd', 'steam');
SELECT * FROM MEDICINE_FORM;
