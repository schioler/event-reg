
CREATE TABLE LOGIN_EVENT (
   ID SERIAL PRIMARY KEY,
   START_TS TIMESTAMP NOT NULL DEFAULT NOW(),
   END_TS TIMESTAMP,
   LOGIN_ID INTEGER NOT NULL REFERENCES LOGIN, 
   EVENT_ID INTEGER NOT NULL REFERENCES EVENT
);

CREATE TABLE LOGIN_EVENT_TEMPLATE (
   ID SERIAL PRIMARY KEY,
   START_TS TIMESTAMP NOT NULL DEFAULT NOW(),
   END_TS TIMESTAMP,
   LOGIN_ID INTEGER NOT NULL REFERENCES LOGIN, 
   EVENT_TEMPLATE_ID INTEGER NOT NULL REFERENCES EVENT_TEMPLATE
);

CREATE TABLE LOGIN_EVENT_TYPE (
   ID SERIAL PRIMARY KEY,
   START_TS TIMESTAMP NOT NULL DEFAULT NOW(),
   END_TS TIMESTAMP,
   LOGIN_ID INTEGER NOT NULL REFERENCES LOGIN, 
   EVENT_TTPE_ID INTEGER NOT NULL REFERENCES EVENT_TYPE
);