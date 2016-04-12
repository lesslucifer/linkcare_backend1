USE cliniccore_db;
DROP function IF EXISTS GeoDistDiff;

DELIMITER $$
USE cliniccore_db$$
CREATE DEFINER=cliniccore@localhost FUNCTION GeoDistDiff(type ENUM('mi', 'km'), lat1 DECIMAL(30,15), lon1 DECIMAL(30,15), lat2 DECIMAL(30,15), lon2 DECIMAL(30,15) ) RETURNS decimal(30,15)
BEGIN
  RETURN ( IF(type = 'km', 6371, 3959) * acos( cos( radians(lat2) ) * cos( radians( lat1 ) ) * cos( radians( lon1 ) - radians(lon2) ) + sin( radians(lat2) ) * sin( radians( lat1 ) ) ) );
END$$
DELIMITER;