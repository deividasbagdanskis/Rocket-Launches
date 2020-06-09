-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 09, 2020 at 03:20 PM
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
-- Database: `final_project_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `countryCode` varchar(5) DEFAULT NULL,
  `wikiURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `launch`
--

DROP TABLE IF EXISTS `launch`;
CREATE TABLE IF NOT EXISTS `launch` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `windowStart` timestamp NULL DEFAULT NULL,
  `windowEnd` timestamp NULL DEFAULT NULL,
  `rocket_Id` int(10) UNSIGNED DEFAULT NULL,
  `launchPad_id` int(10) UNSIGNED NOT NULL,
  `launchServiceProvider` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`launchPad_id`),
  KEY `fk_launch_launchPad1_idx` (`launchPad_id`),
  KEY `fk_launch_rocket1_idx` (`rocket_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `launchpad`
--

DROP TABLE IF EXISTS `launchpad`;
CREATE TABLE IF NOT EXISTS `launchpad` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `locationName` varchar(255) DEFAULT NULL,
  `latitude` decimal(11,8) DEFAULT NULL,
  `longtitude` decimal(11,8) DEFAULT NULL,
  `wikiURL` varchar(255) DEFAULT NULL,
  `mapsURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `mission`
--

DROP TABLE IF EXISTS `mission`;
CREATE TABLE IF NOT EXISTS `mission` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `launch_id` int(10) UNSIGNED DEFAULT NULL,
  `customer_id` int(10) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mission_customer1_idx` (`customer_id`),
  KEY `fk_mission_launch1_idx` (`launch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `payload`
--

DROP TABLE IF EXISTS `payload`;
CREATE TABLE IF NOT EXISTS `payload` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` text,
  `weight` int(11) DEFAULT NULL,
  `totalAmount` int(11) DEFAULT NULL,
  `mission_id` int(10) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_payload_mission1_idx` (`mission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `rocket`
--

DROP TABLE IF EXISTS `rocket`;
CREATE TABLE IF NOT EXISTS `rocket` (
  `Id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `countryOfOrigin` varchar(255) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `diameter` double DEFAULT NULL,
  `mass` int(10) UNSIGNED DEFAULT NULL,
  `numberOfStages` tinyint(3) UNSIGNED DEFAULT NULL,
  `payloadToLEO` int(10) UNSIGNED DEFAULT NULL,
  `payloadToGTO` int(10) UNSIGNED DEFAULT NULL,
  `wikiURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rocket`
--

INSERT INTO `rocket` (`Id`, `name`, `manufacturer`, `type`, `countryOfOrigin`, `height`, `diameter`, `mass`, `numberOfStages`, `payloadToLEO`, `payloadToGTO`, `wikiURL`) VALUES
(1, 'Falcon 9', 'SpaceX', 'Orbital', 'USA', 70, 3.7, 549054, 2, 22800, 8300, 'https://en.wikipedia.org/wiki/Falcon_9_Block_5'),
(2, 'Delta IV Heavy', 'United Launch Alliance', 'Orbital', 'USA', 72, 5, 733000, 2, 28790, 14220, 'https://en.wikipedia.org/wiki/Delta_IV_Heavy'),
(3, 'Atlas V', 'United Launch Alliance', 'Orbital', 'USA', 58.3, 3.81, 590000, 2, 20520, 8900, 'https://en.wikipedia.org/wiki/Atlas_V');

-- --------------------------------------------------------

--
-- Table structure for table `stage`
--

DROP TABLE IF EXISTS `stage`;
CREATE TABLE IF NOT EXISTS `stage` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `rocket_Id` int(10) UNSIGNED NOT NULL,
  `type` varchar(45) NOT NULL,
  `numberOfEngines` tinyint(4) NOT NULL,
  `engine` varchar(255) NOT NULL,
  `thrust` int(11) NOT NULL,
  `fuel` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`rocket_Id`),
  KEY `fk_stage_rocket1_idx` (`rocket_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stage`
--

INSERT INTO `stage` (`id`, `rocket_Id`, `type`, `numberOfEngines`, `engine`, `thrust`, `fuel`) VALUES
(1, 1, 'First', 9, 'Merlin 1D+', 7607, 'LOX / RP-1'),
(2, 1, 'Second', 1, 'Merlin 1D Vacuum+', 934, 'LOX / RP-1'),
(3, 2, 'First', 1, 'RS-68A', 3140, 'LH2 / LOX'),
(4, 2, 'Second', 1, 'RL10', 110, 'LH2 / LOX'),
(5, 3, 'First', 1, 'RD-180', 3827, 'RP-1 / LOX'),
(6, 3, 'Second', 1, 'RL10A', 99, 'LH2 / LOX');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `launch`
--
ALTER TABLE `launch`
  ADD CONSTRAINT `fk_launch_launchPad1` FOREIGN KEY (`launchPad_id`) REFERENCES `launchpad` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_launch_rocket1` FOREIGN KEY (`rocket_Id`) REFERENCES `rocket` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `fk_mission_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_mission_launch1` FOREIGN KEY (`launch_id`) REFERENCES `launch` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `payload`
--
ALTER TABLE `payload`
  ADD CONSTRAINT `fk_payload_mission1` FOREIGN KEY (`mission_id`) REFERENCES `mission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `stage`
--
ALTER TABLE `stage`
  ADD CONSTRAINT `fk_stage_rocket1` FOREIGN KEY (`rocket_Id`) REFERENCES `rocket` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
