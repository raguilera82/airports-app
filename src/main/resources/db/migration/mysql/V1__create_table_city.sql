CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO devops.city (id, name) VALUES(1, 'Nueva York');
INSERT INTO devops.city (id, name) VALUES(2, 'Madrid');
INSERT INTO devops.city (id, name) VALUES(3, 'Bilbao');
