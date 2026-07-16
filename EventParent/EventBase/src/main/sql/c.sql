select
   ty.id as type_id
   , tp.id as tmpl_id
   , ty.name as type_name
   , tp.name as tmpl_name
   , tp.dose as dose
   , tp.unit as unit
   , is_favorite
   , sort_order
from
   EVENT_TYPE TY, EVENT_TEMPLATE TP
WHERE
   ty.id = tp.event_type_id
order by 
   ty.id, tp.sort_order;
   
   
select p.id, p.first_name, last_name, primary_email, l.id, l.parent_id, l.role, l.token from login l left join user_profile p on  p.id = l.user_profile_id;