CREATE TABLE `airport` (
  `code` varchar(3) NOT NULL,
  `name` varchar(255) NOT NULL,
  `city` int(11) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `idx_airport_name` (`name`),
  KEY `fk_airport_city` (`city`),
  CONSTRAINT `fk_airport_city` FOREIGN KEY (`city`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO devops.airport (code, name, city) VALUES('BIO', 'Sondika', 3);
INSERT INTO devops.airport (code, name, city) VALUES('MAD', 'Adolfo Suarez', 2);
