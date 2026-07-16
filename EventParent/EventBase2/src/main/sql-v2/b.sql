--create or replace view vw_user_login as 
select 
   p.id as up_id, p.first_name, p.last_name, p.primary_email, 
   l.id as login_id, l.parent_id, l.role, l.token,
   pwd.start_ts, pwd.end_ts
   , substring(pwd.pwd from '........$')   
from 
   login l left join user_profile p on  p.id = l.user_profile_id
   left join password pwd on l.id = pwd.login_id;