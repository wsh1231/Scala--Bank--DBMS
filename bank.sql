-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2018 at 05:24 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `deposit`
--

CREATE TABLE `deposit` (
  `ACNO` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deposit`
--

INSERT INTO `deposit` (`ACNO`, `Amount`, `Date`) VALUES
(1, 700, '2018-11-21'),
(2, 600, '2018-11-21'),
(3, 200, '2018-11-21'),
(3, 300, '2018-11-21'),
(3, 600, '2018-11-21'),
(1, 5000, '2018-11-21'),
(1, 3000, '2018-11-21'),
(1, 5000, '2018-11-21'),
(5, 5000, '2018-11-21'),
(2, 700, '2018-11-21'),
(6, 500, '2018-11-21'),
(10, 200, '2018-11-22'),
(1, 200, '2018-11-22'),
(1, 400, '2018-11-22'),
(13, 200, '2018-11-22'),
(2, 300, '2018-11-22'),
(2, 500, '2018-11-22');

-- --------------------------------------------------------

--
-- Table structure for table `personal`
--

CREATE TABLE `personal` (
  `ACNO` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Address` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personal`
--

INSERT INTO `personal` (`ACNO`, `Name`, `Address`) VALUES
(1, 'ABC', 'London'),
(2, 'Peter', 'London'),
(3, 'Stephen', 'London'),
(4, 'William', 'London'),
(5, 'William', 'London'),
(6, 'Jenny', 'Manchester'),
(7, 'William', 'Wanstead'),
(8, 'Harry', 'Leicester'),
(10, 'James', 'Sheffield'),
(11, 'George', 'Ormskirk'),
(12, 'Carl', 'Hounslow'),
(13, 'John', 'Exeter');

-- --------------------------------------------------------

--
-- Table structure for table `withdraw`
--

CREATE TABLE `withdraw` (
  `ACNO` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `withdraw`
--

INSERT INTO `withdraw` (`ACNO`, `Amount`, `Date`) VALUES
(2, 40, '2018-11-21'),
(1, 5000, '2018-11-22'),
(3, 1000, '2018-11-22');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`ACNO`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `personal`
--
ALTER TABLE `personal`
  MODIFY `ACNO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `deposit`
--
ALTER TABLE `deposit`
  ADD CONSTRAINT `deposit_ibfk_1` FOREIGN KEY (`ACNO`) REFERENCES `personal` (`ACNO`);

--
-- Constraints for table `withdraw`
--
ALTER TABLE `withdraw`
  ADD CONSTRAINT `withdraw_ibfk_1` FOREIGN KEY (`ACNO`) REFERENCES `personal` (`ACNO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
