-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               10.0.17-MariaDB - mariadb.org binary distribution
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных oracleproject
CREATE DATABASE IF NOT EXISTS `oracleproject` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `oracleproject`;


-- Дамп структуры для таблица oracleproject.book
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы oracleproject.book: ~14 rows (приблизительно)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `author`, `title`, `count`) VALUES
	(1, 'Azimov', 'Old man and the sea', 10),
	(2, 'Pushkin', 'Tales', 11),
	(3, 'Gogol', 'Viy', 10),
	(4, 'Mitchel', 'Gone with the wind', 24),
	(8, 'Pushkin', 'tales', 0),
	(10, 'Zhelyazny', 'Amber', 10),
	(21, 'Pushkin', 'Tales', 11),
	(22, 'Gogol', 'Viy', 10),
	(23, 'Turgenev', 'Mumu', 8),
	(24, 'Tolstoy', 'Karenina', 9),
	(100, 'Wild', 'Portret of dorian gray', 3),
	(101, 'Arthur Conan-Doyle', 'Sherlock Holmes', 100),
	(102, 'Antuan de Saint-Exupery', ' The Little Prince', 99),
	(114, 'admin', 'root', 16);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


-- Дамп структуры для таблица oracleproject.report
CREATE TABLE IF NOT EXISTS `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `rent` date DEFAULT NULL,
  `return` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_report_book` (`book_id`),
  KEY `FK_report_user` (`user_id`),
  CONSTRAINT `FK_report_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_report_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы oracleproject.report: ~7 rows (приблизительно)
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` (`id`, `book_id`, `user_id`, `rent`, `return`) VALUES
	(8, 4, 1, '2016-01-10', NULL),
	(9, 23, 1, '2016-01-10', '2016-01-10'),
	(10, 24, 1, '2016-01-10', '2016-01-10'),
	(11, 23, 1, '2016-01-10', '2016-01-10'),
	(12, 8, 1, '2016-01-10', NULL),
	(13, 8, 1, '2016-01-10', NULL),
	(14, 101, 1, '2016-01-10', '2016-01-10');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;


-- Дамп структуры для таблица oracleproject.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `role` varchar(50) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы oracleproject.user: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `login`, `password`, `birthday`, `role`) VALUES
	(1, 'Jane', 'jane', 'jane777', '1993-12-24', 'user'),
	(2, 'Valeria', 'admin', 'root', '2016-01-07', 'admin'),
	(3, '1', '1', '1', '2016-01-10', 'user'),
	(4, 'Nimfadora', 'Tonks', 'nimfa20000', '1977-07-15', 'user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Дамп структуры для таблица oracleproject.usertobook
CREATE TABLE IF NOT EXISTS `usertobook` (
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  KEY `FK_usertobook_book` (`book_id`),
  KEY `FK_usertobook_user` (`user_id`),
  CONSTRAINT `FK_usertobook_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_usertobook_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы oracleproject.usertobook: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `usertobook` DISABLE KEYS */;
INSERT INTO `usertobook` (`user_id`, `book_id`) VALUES
	(1, 4),
	(1, 8),
	(1, 8);
/*!40000 ALTER TABLE `usertobook` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
