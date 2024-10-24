
create or replace view VW_LOGIN as (
   SELECT   
      LOGIN.USER_PROFILE_ID AS UP_ID,
      LOGIN.ID AS L_ID,
      LOGIN.LOGIN AS LOGIN,
      LOGIN.ROLE AS LOGIN_ROLE, 
      to_char(LOGIN.START_TS ,'YYYYMMDD HH24:MI') as L_STA,
      to_char(LOGIN.END_TS ,'YYYYMMDD HH24:MI') AS L_END,
      PASSWORD.ID AS PASSWORD_ID,
      left(PASSWORD.PWD,10) AS P_PWD, 
      to_char(PASSWORD.START_TS ,'YYYYMMDD HH24:MI') as P_STA,
      to_char(PASSWORD.END_TS ,'YYYYMMDD HH24:MI') AS P_END
   FROM
      LOGIN LEFT JOIN PASSWORD ON LOGIN.ID = PASSWORD.LOGIN_ID
   WHERE 
      (PASSWORD.END_TS IS NULL or  PASSWORD.END_TS > now())
); 
       