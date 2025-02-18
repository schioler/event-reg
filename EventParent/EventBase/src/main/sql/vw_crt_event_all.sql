CREATE OR REPLACE VIEW VW_EVENT_ALL AS
SELECT 
   TYP.ID AS TYP_ID, TYP.LOGIN_ID AS TYP_LOGIN_ID, substring(TYP.NAME,1,20) AS TYP_NAME, substring(TYP.SHORT_NAME,1,10) AS TYP_SHORT,
   TMPL.ID AS TMPL_ID, TMPL.LOGIN_ID AS TMPL_LOGIN, substring(TMPL.NAME, 1, 20) AS TMPL_NAME, substring(TMPL.SHORT_NAME, 1,10) AS TMPL_SHORT, TMPL.IS_FAVORITE AS FAV,
   EV.NAME, EV.LOGIN_ID, EV.DOSE, EV.UNIT
FROM 
   EVENT_TYPE TYP 
      LEFT JOIN EVENT_TEMPLATE TMPL ON TYP.ID = TMPL.EVENT_TYPE_ID 
      LEFT JOIN EVENT EV ON TMPL.ID = EV.EVENT_TEMPLATE_ID   
ORDER BY 
   TYP_ID, TYP_NAME;
   
   

--CREATE OR REPLACE VIEW VW_EVENTS AS
--SELECT 
--   ETY.ID AS TYPE_ID, ETY.NAME AS ETY_NAME, ETY.SHORT_NAME AS ETY_SHORT,
--   ETMP.ID AS TMPL_ID, ETMP.NAME AS ETMP_NAME, ETMP.SHORT_NAME AS ETMP_SHORT,
--   E.EVENT_DATE AS E_DATE, E.ID, E.NOTE
--FROM 
--   EVENT_TYPE ETY, EVENT_TEMPLATE ETMP, EVENT E
--WHERE 
--   ETY.ID = ETMP.EVENT_TYPE_ID AND ETMP.ID = E.EVENT_TEMPLATE_ID
--ORDER BY 
--   ETY_NAME;
--   