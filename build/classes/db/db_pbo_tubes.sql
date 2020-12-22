-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2020 at 05:55 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_pbo_tubes`
--

-- --------------------------------------------------------

--
-- Table structure for table `m_product`
--

CREATE TABLE `m_product` (
  `id` int(11) NOT NULL,
  `product_name` varchar(150) NOT NULL,
  `product_desc` varchar(200) NOT NULL,
  `product_qty` int(11) NOT NULL,
  `product_price` int(11) NOT NULL,
  `is_available` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `m_product`
--

INSERT INTO `m_product` (`id`, `product_name`, `product_desc`, `product_qty`, `product_price`, `is_available`) VALUES
(1, 'Ayam Bakar Goreng Panggang', 'Enak, lezat, bermanfaat bagi tulang dan gigi', 2, 100000, 1),
(2, 'Mac n cheez', 'Enak bangeeet, mo meninggalzzz', 5, 12000, 1),
(3, 'Nasi Goreng Bakso', 'Enak, lezat dan nikmat', 1, 9500, 2),
(4, 'Nasi Goreng Ati Ampela', 'Enak, digoreng dengan hati yang tulus', 5, 15000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `m_transaction`
--

CREATE TABLE `m_transaction` (
  `id` int(11) NOT NULL,
  `id_m_user` int(11) NOT NULL,
  `id_m_product` int(11) NOT NULL,
  `order_code` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `m_transaction`
--

INSERT INTO `m_transaction` (`id`, `id_m_user`, `id_m_product`, `order_code`) VALUES
(1, 2, 2, 'ORDER4817'),
(2, 2, 2, 'ORDER994'),
(3, 2, 4, 'ORDER3412'),
(4, 3, 3, 'ORDER3991'),
(5, 3, 1, 'ORDER8565'),
(6, 3, 3, 'ORDER7159'),
(7, 3, 4, 'ORDER8912');

-- --------------------------------------------------------

--
-- Table structure for table `m_user`
--

CREATE TABLE `m_user` (
  `id` int(11) NOT NULL,
  `user_name` varchar(200) NOT NULL,
  `user_pass` varchar(100) NOT NULL,
  `user_address` varchar(150) NOT NULL,
  `user_phone` varchar(20) NOT NULL,
  `user_type` int(11) NOT NULL,
  `auth_token` int(11) NOT NULL DEFAULT 0,
  `status` int(1) NOT NULL,
  `user_saldo` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `m_user`
--

INSERT INTO `m_user` (`id`, `user_name`, `user_pass`, `user_address`, `user_phone`, `user_type`, `auth_token`, `status`, `user_saldo`) VALUES
(1, 'andresaftari', 'Prasidya1', 'Bandung', '083870992897', 1, 7936798, 0, 0),
(2, 'billzzzz', 'Billa123', 'Jakarta', '083870992897', 2, 0, 0, 875000),
(3, 'andresaft', 'andre2011', 'Jakarta', '083870992897', 2, 0, 0, 87000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `m_product`
--
ALTER TABLE `m_product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `m_transaction`
--
ALTER TABLE `m_transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_m_user` (`id_m_user`),
  ADD KEY `id_m_product` (`id_m_product`);

--
-- Indexes for table `m_user`
--
ALTER TABLE `m_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `m_product`
--
ALTER TABLE `m_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `m_transaction`
--
ALTER TABLE `m_transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `m_user`
--
ALTER TABLE `m_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `m_transaction`
--
ALTER TABLE `m_transaction`
  ADD CONSTRAINT `m_transaction_ibfk_1` FOREIGN KEY (`id_m_user`) REFERENCES `m_user` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `m_transaction_ibfk_2` FOREIGN KEY (`id_m_product`) REFERENCES `m_product` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
