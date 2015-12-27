CREATE TABLE `fetch_data_status` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT,
  `name` char(36) NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `name` UNIQUE (`name`)
) /*!40101 ENGINE=InnoDB DEFAULT CHARSET=utf8 */;

CREATE TABLE `fetch_data` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `url` varchar(500) NOT NULL,
  `fetch_status_id` tinyint unsigned NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `fetch_data_status_ibfk_1` FOREIGN KEY (`fetch_status_id`) REFERENCES `fetch_data_status` (`id`),
  CONSTRAINT `url` UNIQUE(`url`),
  PRIMARY KEY (`id`)
)  /*!40101 ENGINE=InnoDB DEFAULT CHARSET=utf8 */;

CREATE TABLE `fetch_data_raw` (
  `id` bigint unsigned NOT NULL,
  `raw` LONGTEXT,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fetch_data_raw_id_ibfk_1` FOREIGN KEY (`id`) REFERENCES `fetch_data` (`id`)
) /*!40101 ENGINE=InnoDB DEFAULT CHARSET=utf8 */;

CREATE TABLE `rating_minfin` (
	`id` int UNSIGNED NOT NULL AUTO_INCREMENT,
	`created` datetime NOT NULL,
	`modified` datetime NOT NULL,
	`name` varchar(50) NOT NULL,
	`title` varchar(200) NOT NULL,
	`rating` decimal(3,2) NOT NULL,
	`score` tinyint NOT NULL,
	PRIMARY KEY (`id`)
) /*!40101 ENGINE=InnoDB DEFAULT CHARSET=utf8 */;

