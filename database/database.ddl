-- change this to your team id
use FTP01;

-- comment this line for the very first time
drop table if exists EMPLOYEE;

-- create the table
create table EMPLOYEE (EMP_ID int primary key);
 
 --Update the table
 UPDATE EMPLOYEE SET EMP_ID=1005 WHERE EMP_ID=1000;

--to SELECT the table
 SELECT * FROM EMPLOYEE;

--delete the table
 DELETE FROM EMPLOYEE WHERE EMP_ID=2000;



--examples of creating the new databases 
CREATE DATABASE FTP1;
CREATE USER 'FTP1'@'localhost' IDENTIFIED BY 'FTP1';
GRANT ALL ON FTP01.* TO 'FTP01'@'localhost';
