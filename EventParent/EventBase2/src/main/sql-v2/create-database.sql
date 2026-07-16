# To create a tablespace dbspace at file system location /data/dbs, first create the directory using operating system facilities and set the correct ownership:
#
# mkdir /data/dbs
# chown postgres:postgres /data/dbs
#
# Then issue the tablespace creation command inside PostgreSQL:
# 
# CREATE TABLESPACE dbspace LOCATION '/data/dbs';
# 
# To create a tablespace owned by a different database user, use a command like this:
# 
# CREATE TABLESPACE indexspace OWNER genevieve LOCATION '/data/indexes'


CREATE TABLESPACE event_dev2 LOCATION 'C:\\test-data\\event\\dev2';



CREATE DATABASE event_dev2 OWNER event_dev2 TABLESPACE event_dev2;

--CREATE USER edev2 WITH ENCRYPTED PASSWORD 'dev123';
--CREATE USER event_dev2 WITH ENCRYPTED PASSWORD 'dev123';

   
# sudo -u postgres psql
# postgres=# create database mydb;
# postgres=# create user myuser with encrypted password 'mypass';
# postgres=# grant all privileges on database mydb to myuser;

--CREATE DATABASE
