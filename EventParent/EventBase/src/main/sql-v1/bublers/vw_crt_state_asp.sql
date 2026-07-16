create or replace view vw_state_asp as
   select 
      sa.id as asp_id, sa.name as asp_name, sa.description as asp_d,
      sr.id as rat_id, sr.rating as rat_rating,
      sreg.id as reg_id, sreg.state_registration_ts as reg_ts
   from 
      state_aspect sa 
         left outer join state_rating sr on sr.state_aspect_id = sa.id
         left outer join state_registration sreg on sreg.id = sr.state_registration_id
   order by 
      sa.name;