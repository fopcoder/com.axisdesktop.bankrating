CREATE TABLE `fetch_data_status` (
  `id` tinyint NOT NULL AUTO_INCREMENT,
  `name` char(36) NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `name` UNIQUE (`name`)
) /*!40101 ENGINE=InnoDB DEFAULT CHARSET=utf8 */;

CREATE TABLE `fetch_data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(500) NOT NULL,
  `fetch_status_id` tinyint NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `fetch_data_status_ibfk_1` FOREIGN KEY (`fetch_status_id`) REFERENCES `fetch_data_status` (`id`),
  CONSTRAINT `url` UNIQUE(`url`),
  PRIMARY KEY (`id`)
)  /*!40101 ENGINE=InnoDB DEFAULT CHARSET=utf8 */;

CREATE TABLE `bank` (
	`id` int NOT NULL AUTO_INCREMENT,
	`created` datetime NOT NULL,
	`modified` datetime NOT NULL,
	`name` varchar(50) NOT NULL,
	`title` varchar(200) NOT NULL,
	`url` varchar(200) NOT NULL,
	PRIMARY KEY (`id`)
) /*!40101 ENGINE=InnoDB DEFAULT CHARSET=utf8 */;

CREATE TABLE `rating` (
	`id` int NOT NULL AUTO_INCREMENT,
	`created` datetime NOT NULL,
	`modified` datetime NOT NULL,
	`date` date NOT NULL,
	`bank_id` int NOT NULL,
	`rating` decimal(3,2) NOT NULL,
	`score` tinyint NOT NULL,
	`stress_tolerance` decimal(3,2) NOT NULL,
	`investor_loyalty` decimal(3,2) NOT NULL,
	`analyst_correction` decimal(3,2) NOT NULL,
	`nbu_asset_size_score` int NOT NULL,
	CONSTRAINT `date_bank` UNIQUE(`bank_id`,`date`),
	CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`),
	PRIMARY KEY (`id`)
) /*!40101 ENGINE=InnoDB DEFAULT CHARSET=utf8 */;


	


