CREATE TABLE STATE_REGISTRATION (
   ID SERIAL PRIMARY KEY,
   LOGIN_ID INTEGER NOT NULL REFERENCES LOGIN,
   CREATED TIMESTAMP DEFAULT NOW(),
   STATE_REGISTRATION_TS TIMESTAMP DEFAULT NOW()
   unique(login_id, state_registration_ts)
) ;


