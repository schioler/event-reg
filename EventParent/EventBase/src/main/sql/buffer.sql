select * 
from event 
where
   event_date between
   to_timestamp('01012022 00:00', 'DDMMYYYY HH24:MI') and to_timestamp('31122022 23:59', 'DDMMYYYY HH24:MI');