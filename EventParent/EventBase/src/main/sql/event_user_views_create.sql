
create or replace view VW_LOGIN as (
   SELECT   
      LOGIN.ID AS LOGIN_ID,
      LOGIN.USER_PROFILE_ID AS USER_PROFILE_ID,
      LOGIN.LOGIN AS LOGIN,
      LOGIN.ROLE AS LOGIN_ROLE, 
      LOGIN.START_TS AS LOGIN_START, 
      LOGIN.END_TS AS LOGIN_END,
      PASSWORD.ID AS PASSWORD_ID,
      PASSWORD.PWD AS PASSWORD, 
      PASSWORD.START_TS AS PWD_START, 
      PASSWORD.END_TS AS PWD_END 
   FROM
      LOGIN LEFT JOIN PASSWORD ON LOGIN.ID = PASSWORD.LOGIN_ID
   WHERE 
      (PASSWORD.END_TS IS NULL or  PASSWORD.END_TS > now())
); 
       