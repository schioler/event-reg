create or replace view vw_state_reg as
   select
      sreg.login_id as login,
      extract(year from sreg.state_registration_ts) as year,
      extract(month from sreg.state_registration_ts) as month,
      extract(day from sreg.state_registration_ts) as day,
      extract(hour from sreg.state_registration_ts) as hour,
--      extract(minute from sreg.state_registration_ts) as minute,
      count(sreg.id) as count_registrations,
--      srat.rating as rating,
      sasp.id as asp_id, 
      sasp.name as name, 
      sasp.description as descr 
   from 
      state_registration sreg 
         left outer join 
            state_rating srat on sreg.id = srat.state_registration_id
         left outer join 
            state_aspect sasp on srat.state_aspect_id = sasp.id
   group by
      year, month, day, hour, sreg.login_id, sreg.state_registration_ts, sasp.id, sasp.name, sasp.description
   order by
      year, month, day, hour
    
         
            ;