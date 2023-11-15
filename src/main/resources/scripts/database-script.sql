CREATE DATABASE IF NOT EXISTS homeassigndev;

CREATE USER IF NOT EXISTS 'homeassigndev'@'%' IDENTIFIED BY 'Abc1230g';

GRANT SELECT ON homeassigndev.* to 'homeassigndev'@'%';
GRANT INSERT ON homeassigndev.* to 'homeassigndev'@'%';
GRANT DELETE ON homeassigndev.* to 'homeassigndev'@'%';
GRANT UPDATE ON homeassigndev.* to 'homeassigndev'@'%';
GRANT CREATE ON homeassigndev.* to 'homeassigndev'@'%';