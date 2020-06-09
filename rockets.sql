-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 08, 2020 at 06:35 PM
-- Server version: 5.7.26
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rockets`
--

-- --------------------------------------------------------

--
-- Table structure for table `rocket`
--

DROP TABLE IF EXISTS `rocket`;
CREATE TABLE IF NOT EXISTS `rocket` (
  `name` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `countryOfOrigin` varchar(255) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `diameter` double DEFAULT NULL,
  `mass` int(10) UNSIGNED DEFAULT NULL,
  `numberOfStages` tinyint(3) UNSIGNED DEFAULT NULL,
  `payloadToLEO` int(10) UNSIGNED DEFAULT NULL,
  `payloadToGTO` int(10) UNSIGNED DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rocket`
--

INSERT INTO `rocket` (`name`, `type`, `manufacturer`, `countryOfOrigin`, `height`, `diameter`, `mass`, `numberOfStages`, `payloadToLEO`, `payloadToGTO`, `status`) VALUES
('Atlas V', 'Orbital', 'United Launch Alliance', 'USA', 58.3, 3.81, 590000, 2, 20520, 0, 'S'),
('Delta IV Heavy', 'Orbital', 'United Launch Alliance', 'USA', 72, 5, 733000, 2, 28790, 14220, 'R'),
('Falcon 9', 'Orbital', 'SpaceX', 'USA', 70, 3.7, 549054, 2, 22800, 8300, 'S');

-- --------------------------------------------------------

--
-- Table structure for table `stage`
--

DROP TABLE IF EXISTS `stage`;
CREATE TABLE IF NOT EXISTS `stage` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `rocket` varchar(255) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `productionNumber` varchar(45) DEFAULT NULL,
  `numberOfEngines` tinyint(4) DEFAULT NULL,
  `engine` varchar(255) DEFAULT NULL,
  `thrust` int(11) DEFAULT NULL,
  `fuel` varchar(45) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `reusable` tinyint(1) DEFAULT NULL,
  `numberOfLaunches` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`rocket`),
  KEY `fk_stage_rocket_idx` (`rocket`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stage`
--

INSERT INTO `stage` (`id`, `rocket`, `type`, `productionNumber`, `numberOfEngines`, `engine`, `thrust`, `fuel`, `status`, `reusable`, `numberOfLaunches`) VALUES
(1, 'Falcon 9', 'First', 'B1048', 9, 'Merlin 1D+', 7607, 'LOX / RP-1', 'S', 1, 2),
(2, 'Falcon 9', 'Second', 'S0095', 1, 'Merlin 1D Vacuum+', 934, 'LOX / RP-1', 'S', 0, 0),
(3, 'Delta IV Heavy', 'First', 'DHF0164', 1, 'RS-68A', 3140, 'LH2 / LOX', 'S', 0, 0),
(4, 'Delta IV Heavy', 'Second', 'DHS0348', 1, 'RL10', 110, 'LH2 / LOX', 'S', 0, 0),
(5, 'Atlas V', 'First', 'CCB0135', 1, 'RD-180', 3827, 'RP-1 / LOX', 'S', 0, 0),
(6, 'Atlas V', 'Second', 'CE0154', 1, 'RL10A', 99, 'LH2 / LOX', 'S', 0, 0),
(7, 'Falcon 9', 'First', 'B1069', 9, 'Merlin 1D+', 7607, 'LOX / RP-1', 'S', 1, 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `stage`
--
ALTER TABLE `stage`
  ADD CONSTRAINT `fk_stage_rocket` FOREIGN KEY (`rocket`) REFERENCES `rocket` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
