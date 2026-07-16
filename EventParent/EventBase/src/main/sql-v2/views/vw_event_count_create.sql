CREATE OR REPLACE VIEW VW_EVENT_COUNT AS
SELECT
   extract(year from event_ts) as year,
   extract(month from event_ts) as month,
   extract(day from event_ts) as day,
   E.NAME, E.UNIT, E.DOSE, COUNT(E.ID)´
FROM
   EVENT E
GROUP BY
   YEAR, MONTH, DAY, NAME, UNIT, DOSE
ORDER BY
   YEAR, MONTH, DAY, NAME;