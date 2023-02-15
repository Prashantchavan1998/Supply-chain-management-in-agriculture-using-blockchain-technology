-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.19


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema supply_chain_bct1
--

CREATE DATABASE IF NOT EXISTS supply_chain_bct1;
USE supply_chain_bct1;

--
-- Definition of table `setpassword`
--

DROP TABLE IF EXISTS `setpassword`;
CREATE TABLE `setpassword` (
  `UserName` varchar(500) NOT NULL,
  `imageName` varchar(200) DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `setpassword`
--

/*!40000 ALTER TABLE `setpassword` DISABLE KEYS */;
INSERT INTO `setpassword` (`UserName`,`imageName`,`password`) VALUES 
 ('1','5ploFnsQ/LmtdRPVr85LRA==','8s+GLajYfgNrvioJBa/Dyg==');
/*!40000 ALTER TABLE `setpassword` ENABLE KEYS */;


--
-- Definition of table `tbl_bank_details`
--

DROP TABLE IF EXISTS `tbl_bank_details`;
CREATE TABLE `tbl_bank_details` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(200) NOT NULL,
  `bank_name` varchar(200) NOT NULL,
  `account_no` varchar(200) NOT NULL,
  `ifsc_code` varchar(200) NOT NULL,
  `bank_address` varchar(200) NOT NULL,
  `balance` double NOT NULL DEFAULT '1000',
  `bank_status` varchar(200) NOT NULL DEFAULT 'active',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_bank_details`
--

/*!40000 ALTER TABLE `tbl_bank_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_bank_details` ENABLE KEYS */;


--
-- Definition of table `tbl_crop_details`
--

DROP TABLE IF EXISTS `tbl_crop_details`;
CREATE TABLE `tbl_crop_details` (
  `crop_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `crop_name` varchar(200) NOT NULL,
  `crop_rate` double NOT NULL,
  PRIMARY KEY (`crop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_crop_details`
--

/*!40000 ALTER TABLE `tbl_crop_details` DISABLE KEYS */;
INSERT INTO `tbl_crop_details` (`crop_id`,`crop_name`,`crop_rate`) VALUES 
 (1,'Wheat',2),
 (2,'Bajara',2.5),
 (3,'Jowar',2.8),
 (4,'Rice',3),
 (5,'Soyabean',20);
/*!40000 ALTER TABLE `tbl_crop_details` ENABLE KEYS */;


--
-- Definition of table `tbl_inventory_request`
--

DROP TABLE IF EXISTS `tbl_inventory_request`;
CREATE TABLE `tbl_inventory_request` (
  `idtbl_inventory_request` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `requested_id` varchar(200) NOT NULL,
  `receiver_id` varchar(200) NOT NULL,
  `inventory_name` varchar(200) NOT NULL,
  `request_on` varchar(200) NOT NULL,
  `request_status` varchar(200) NOT NULL DEFAULT 'Not Viewed',
  `request_quantity` double NOT NULL,
  PRIMARY KEY (`idtbl_inventory_request`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_inventory_request`
--

/*!40000 ALTER TABLE `tbl_inventory_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_inventory_request` ENABLE KEYS */;


--
-- Definition of table `tbl_invertory_details`
--

DROP TABLE IF EXISTS `tbl_invertory_details`;
CREATE TABLE `tbl_invertory_details` (
  `inventory_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `agent_id` varchar(200) NOT NULL,
  `inventory_name` varchar(200) NOT NULL,
  `quantity` double NOT NULL,
  `inventory_status` varchar(200) NOT NULL DEFAULT 'Available',
  `inventory_rate` double NOT NULL,
  PRIMARY KEY (`inventory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_invertory_details`
--

/*!40000 ALTER TABLE `tbl_invertory_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_invertory_details` ENABLE KEYS */;


--
-- Definition of table `tbl_posts`
--

DROP TABLE IF EXISTS `tbl_posts`;
CREATE TABLE `tbl_posts` (
  `post_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `farmer_id` varchar(200) NOT NULL,
  `crop_name` varchar(200) NOT NULL,
  `crop_rate` double NOT NULL,
  `crop_quantity` double NOT NULL,
  `total_amount` double NOT NULL,
  `posted_on` varchar(200) NOT NULL,
  `post_status` varchar(200) NOT NULL DEFAULT 'Not Viewed',
  `agent_id` varchar(200) NOT NULL DEFAULT '',
  `accepted_on` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_posts`
--

/*!40000 ALTER TABLE `tbl_posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_posts` ENABLE KEYS */;


--
-- Definition of table `tbl_transactions`
--

DROP TABLE IF EXISTS `tbl_transactions`;
CREATE TABLE `tbl_transactions` (
  `transaction_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sender_id` varchar(200) NOT NULL,
  `receiver_id` varchar(200) NOT NULL,
  `crop_name` varchar(200) NOT NULL,
  `quantity` varchar(200) NOT NULL,
  `rate` varchar(200) NOT NULL,
  `amount` varchar(200) NOT NULL,
  `timestamp` varchar(200) NOT NULL,
  `prev_hash` varchar(200) NOT NULL,
  `current_hash` varchar(200) NOT NULL,
  `transaction_status` varchar(200) NOT NULL DEFAULT 'Success',
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_transactions`
--

/*!40000 ALTER TABLE `tbl_transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_transactions` ENABLE KEYS */;


--
-- Definition of table `tbl_users`
--

DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE `tbl_users` (
  `sr` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usertype` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `address` text NOT NULL,
  `mobile` varchar(200) NOT NULL,
  `name` text NOT NULL,
  PRIMARY KEY (`sr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_users`
--

/*!40000 ALTER TABLE `tbl_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_users` ENABLE KEYS */;


--
-- Definition of table `tbl_usertype`
--

DROP TABLE IF EXISTS `tbl_usertype`;
CREATE TABLE `tbl_usertype` (
  `sr` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_type` varchar(45) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`sr`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_usertype`
--

/*!40000 ALTER TABLE `tbl_usertype` DISABLE KEYS */;
INSERT INTO `tbl_usertype` (`sr`,`user_type`,`user_id`) VALUES 
 (1,'Agent',1),
 (2,'Customer',2);
/*!40000 ALTER TABLE `tbl_usertype` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
