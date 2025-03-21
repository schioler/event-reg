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