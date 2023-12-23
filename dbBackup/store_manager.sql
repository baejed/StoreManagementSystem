-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2023 at 10:54 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `store_manager`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_login`
--

CREATE TABLE `admin_login` (
  `username` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin_login`
--

INSERT INTO `admin_login` (`username`, `password`) VALUES
('hello', '486ea46224d1bb4fb680f34f7c9ad96a8f24ec88be73ea8e5a6c65260e9cb8a7'),
('jed', '4938a08a8e81a8a9e45b4ee887d4a2b3431a9d38ddb03ff1705c1b056bbf9be1');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `item_name` varchar(50) NOT NULL,
  `price` double DEFAULT NULL,
  `stocks` int(11) DEFAULT NULL,
  `cost_to_buy` double DEFAULT NULL,
  `items_sold` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`item_name`, `price`, `stocks`, `cost_to_buy`, `items_sold`) VALUES
('144hz Monitor', 12000, 0, 8500, 0),
('Cam', 33, 351, 23, 0),
('Cat', 24, 232301248, 12, 23296),
('Chevy', 50000, 1, 42000, 0),
('DIP Switch', 45, 0, 30, 0),
('Gaming Mouse', 20, 0, 12, 0),
('GTX 1080 Ti', 20000, 0, 18000, 0),
('iPhone 15', 45000, 0, 35000, 0),
('Jordan 1', 11500, 0, 7800, 0),
('Mac', 20, 1906, 14, 17),
('Macbook', 25000, 12, 43000, 14),
('PlayStation 5', 29900, 20, 20000, 0),
('Potato Chips', 49, 200, 24, 1000),
('Red Horse', 120, 0, 98, 0),
('Rice Cooker', 293, 231, 200, 0),
('Tanduay Select', 140, 100, 125, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_login`
--
ALTER TABLE `admin_login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`item_name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
