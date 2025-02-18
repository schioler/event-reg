create or replace view vw_state as
   select 
      sreg.id as sreg_id, sreg.state_registration_ts as sreg_ts,
      sa.name as sa_name, sa.description as sa_description,
      sr.rating as sr_rating
   from 
      state_registration sreg left join state_rating sr on sreg.id = sr.state_registration_id 
      left join state_aspect sa on sr.state_aspect_id = sa.id; 