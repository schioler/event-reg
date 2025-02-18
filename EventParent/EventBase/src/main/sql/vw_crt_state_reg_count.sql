create or replace view vw_state_reg_count as
   select
      sreg.login_id as login,
      extract(year from sreg.state_registration_ts) as year,
      extract(month from sreg.state_registration_ts) as month,
      extract(day from sreg.state_registration_ts) as day,
      extract(hour from sreg.state_registration_ts) as hour,
--      extract(minute from sreg.state_registration_ts) as minute,
      count(sreg.id) as count_registrations
--      srat.rating as rating,
  from 
      state_registration sreg 
   group by
      year, month, day, hour, sreg.login_id
   order by
      year, month, day, hour
    
         
            ;