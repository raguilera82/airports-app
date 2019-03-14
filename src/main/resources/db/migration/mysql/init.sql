CREATE DATABASE devops;
CREATE USER 'devops'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON devops.* to 'devops'@'%';