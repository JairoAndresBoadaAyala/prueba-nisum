DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS phone;
DROP SEQUENCE IF EXISTS PHONE_SEQ;

CREATE SEQUENCE PHONE_SEQ;
ALTER SEQUENCE PHONE_SEQ INCREMENT by 1;

CREATE TABLE usuario (id VARCHAR(40) PRIMARY KEY ,
                  name VARCHAR(50),
                  email VARCHAR(50),
                  password VARCHAR(20),
                  created DATE,
                  modified DATE,
                  last_login DATE,
                  token VARCHAR(40),
                  isactive INTEGER
                   );

CREATE TABLE phone (id INTEGER AUTO_INCREMENT PRIMARY KEY ,
                  number VARCHAR(15),
                  citycode VARCHAR(10),
                  countrycode VARCHAR(20)
                   );