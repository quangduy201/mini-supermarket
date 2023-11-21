-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 23, 2023 at 10:00 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mini-supermarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `last_signed_in` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `role_id`, `staff_id`, `last_signed_in`) VALUES
(1, 'admin', '$2a$12$s4TMkIcc10CTHaIwgfiQo.1VcQJt6dt0QnLfdlfspIpWJB8u.hkLu', 1, 1, '1000-01-01 00:00:00.000000'),
(2, 'dungboi', '$2a$12$rBbvwRj7q1dtvEvD0tJvCuqlhk/J1gSaBsabbv.dXd4c5G8ZFY3Ze', 2, 2, '1000-01-01 00:00:00.000000'),
(3, 'quangduy', '$2a$12$/odLRxEA.cA/TMTEejMlFeHTIIVw9YKocn4VQnt4H1oKVbDOXFEsa', 2, 3, '1000-01-01 00:00:00.000000'),
(4, 'longbott', '$2a$12$Ullzt8ORJVZ3gsBuO/T0OuAuqGZQZmCGea9qXwA/OVUFjACGUP24m', 2, 4, '1000-01-01 00:00:00.000000'),
(5, 'hoangminh', '$2a$12$JphZRAF58LOd/7K3jB.TM.3lCSweMY.JmPzB9nOF48UEhM4pUt.cy', 2, 5, '1000-01-01 00:00:00.000000'),
(6, 'phuocsang', '$2a$12$/XftwDJ4LBTxO03ZEhyUOeD7v90LkjfyHf7RfuLn0HH3zF9umdc5y', 2, 6, '1000-01-01 00:00:00.000000'),
(7, 'xuanmai', '$2a$12$rbUiS15k4wRYcH3DEzGg0uRf4KC/CdgRpammGZ6qKP0u8kXeLW6wO', 3, 7, '1000-01-01 00:00:00.000000'),
(8, 'legiang', '$2a$12$9lo.GzVz7m4PMv3EXp5Wle/3Ei0hFVytw.hjZkM5lnSNUJ2lqN.UW', 3, 8, '1000-01-01 00:00:00.000000'),
(9, 'vanlam', '$2a$12$/xxTbESg0iUyyGN9GNZCjOXXyZ0i.NjYSHdD0WvFJjycvGKRCJmz6', 4, 9, '1000-01-01 00:00:00.000000'),
(10, 'xuanphuc', '$2a$12$zWUvS25bGEvEFCPPzlrADekPNg.garXS1IxasgmPWxPV2tcezl4uG', 4, 10, '1000-01-01 00:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id`, `name`, `supplier_id`, `deleted`) VALUES
(1, 'Vfresh', 1, b'0'),
(2, 'Sure Prevent Gold', 1, b'0'),
(3, 'Vinamilk', 1, b'0'),
(4, 'TH true Milk', 2, b'0'),
(5, 'TH true Yogurt', 2, b'0'),
(6, 'Coca Cola', 3, b'0'),
(7, 'Fanta', 3, b'0'),
(8, 'Aquarius', 3, b'0'),
(9, 'Samurai', 3, b'0'),
(10, 'Nutriboost', 3, b'0'),
(11, 'Fuzetea', 3, b'0'),
(12, 'Modern', 4, b'0'),
(13, 'Hảo Hảo', 4, b'0'),
(14, 'Miến Phú Hương', 4, b'0'),
(15, 'Udon Sưki Sưki', 4, b'0'),
(16, 'Đệ nhất', 4, b'0'),
(17, 'TH true Juice Milk', 2, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `quantity`, `deleted`) VALUES
(1, 'Sữa nước', 16, b'0'),
(2, 'Mì ăn liền', 2, b'0'),
(3, 'Nước ngọt', 8, b'0'),
(4, 'Sữa chua', 6, b'0'),
(5, 'Sữa bột', 2, b'0'),
(6, 'Nước trái cây, nước trà', 6, b'0'),
(7, 'Miến ăn liền', 0, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `membership` bit(1) DEFAULT NULL,
  `signed_up_date` date DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `gender`, `birthdate`, `phone`, `membership`, `signed_up_date`, `point`, `deleted`) VALUES
(1, 'VÃNG LAI', b'0', '1000-01-01', '', b'0', '1000-01-01', 0, b'0'),
(2, 'NGUYỄN VĂN NAM', b'1', '2000-12-01', '0862994282', b'1', '2020-09-08', 1000, b'0'),
(3, 'HOÀNG XUÂN BẮC', b'1', '2001-09-03', '0967563268', b'1', '2021-02-07', 123, b'0'),
(4, 'NGUYỄN THỊ THU HIỀN', b'0', '2004-05-04', '0981485618', b'1', '2021-05-06', 500, b'0'),
(5, 'NGUYỄN VĂN THẮNG', b'1', '1999-08-10', '0861149539', b'1', '2021-08-03', 2300, b'0'),
(6, 'NGUYỄN THỊ YẾN NHI', b'0', '1000-01-01', '', b'0', '1000-01-01', 0, b'0'),
(7, 'ĐẶNG NGUYỄN GIA HUY', b'1', '1000-01-01', '', b'0', '1000-01-01', 0, b'0'),
(8, 'NGUYỄN THI DIỆU CHI', b'0', '2000-04-09', '0378367833', b'1', '2022-05-05', 450, b'0'),
(9, 'NGUYỄN THỊ THANH NHÀN', b'0', '2001-08-03', '0323373316', b'1', '2022-09-08', 3000, b'0'),
(10, 'NGUYỄN TRUNG TÍN', b'1', '1000-01-01', '', b'0', '1000-01-01', 0, b'0'),
(11, 'ĐINH XUÂN HOÀI', b'1', '2004-07-06', '0964745278', b'1', '2023-03-07', 200, b'0');

-- --------------------------------------------------------

--
-- Table structure for table `decentralization`
--

CREATE TABLE `decentralization` (
  `role_id` bigint(20) NOT NULL,
  `module_id` bigint(20) NOT NULL,
  `function_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `decentralization`
--

INSERT INTO `decentralization` (`role_id`, `module_id`, `function_id`) VALUES
(0, 1, 1),
(0, 1, 2),
(0, 1, 3),
(0, 1, 4),
(0, 2, 1),
(0, 3, 1),
(0, 3, 5),
(0, 4, 1),
(0, 5, 1),
(0, 5, 2),
(0, 5, 3),
(0, 6, 1),
(0, 6, 2),
(0, 6, 3),
(0, 7, 1),
(0, 7, 5),
(0, 7, 7),
(0, 8, 1),
(0, 8, 2),
(0, 8, 5),
(0, 8, 7),
(0, 9, 1),
(0, 9, 2),
(0, 9, 5),
(0, 9, 7),
(0, 10, 1),
(0, 10, 2),
(0, 10, 3),
(0, 10, 4),
(0, 10, 5),
(0, 10, 6),
(0, 10, 7),
(0, 11, 1),
(0, 11, 2),
(0, 11, 3),
(0, 11, 4),
(0, 11, 5),
(0, 11, 6),
(0, 11, 7),
(0, 12, 1),
(0, 12, 2),
(0, 12, 3),
(0, 12, 4),
(0, 12, 5),
(0, 12, 6),
(0, 12, 7),
(0, 13, 1),
(0, 13, 2),
(0, 13, 3),
(0, 13, 4),
(0, 13, 5),
(0, 13, 6),
(0, 13, 7),
(0, 14, 1),
(0, 14, 2),
(0, 14, 3),
(0, 14, 4),
(0, 14, 5),
(0, 14, 7),
(0, 15, 1),
(0, 15, 2),
(0, 15, 3),
(0, 15, 4),
(0, 15, 6),
(0, 15, 7),
(1, 14, 1),
(1, 14, 2),
(1, 14, 3),
(1, 14, 4),
(1, 14, 5),
(1, 14, 7),
(1, 15, 1),
(1, 15, 2),
(1, 15, 3),
(1, 15, 4),
(1, 15, 6),
(1, 15, 7),
(2, 1, 1),
(2, 1, 2),
(2, 1, 3),
(2, 1, 4),
(2, 2, 1),
(2, 3, 1),
(2, 3, 5),
(2, 4, 1),
(2, 5, 1),
(2, 5, 2),
(2, 5, 3),
(2, 6, 1),
(2, 6, 2),
(2, 6, 3),
(2, 7, 1),
(2, 7, 5),
(2, 7, 7),
(2, 8, 1),
(2, 8, 2),
(2, 8, 5),
(2, 8, 7),
(2, 9, 1),
(2, 9, 2),
(2, 9, 5),
(2, 9, 7),
(2, 10, 1),
(2, 10, 2),
(2, 10, 3),
(2, 10, 4),
(2, 10, 5),
(2, 10, 6),
(2, 10, 7),
(2, 11, 1),
(2, 11, 2),
(2, 11, 3),
(2, 11, 4),
(2, 11, 5),
(2, 11, 6),
(2, 11, 7),
(2, 12, 1),
(2, 12, 2),
(2, 12, 3),
(2, 12, 4),
(2, 12, 5),
(2, 12, 6),
(2, 12, 7),
(2, 13, 1),
(2, 13, 2),
(2, 13, 3),
(2, 13, 4),
(2, 13, 5),
(2, 13, 6),
(2, 13, 7),
(3, 1, 1),
(3, 2, 1),
(3, 5, 1),
(3, 6, 1),
(3, 7, 1),
(3, 7, 5),
(3, 10, 1),
(3, 10, 5),
(3, 12, 1),
(3, 12, 2),
(3, 12, 3),
(3, 12, 5),
(4, 1, 1),
(4, 3, 1),
(4, 3, 5),
(4, 5, 1),
(4, 6, 1),
(4, 8, 1),
(4, 8, 2),
(4, 8, 5),
(4, 8, 7),
(4, 9, 1),
(4, 9, 2),
(4, 9, 5),
(4, 9, 7),
(4, 10, 1),
(4, 10, 5),
(4, 11, 1),
(4, 11, 5);

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `id` bigint(20) NOT NULL,
  `percent` double DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `discount_detail`
--

CREATE TABLE `discount_detail` (
  `discount_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `export_detail`
--

CREATE TABLE `export_detail` (
  `export_note_id` bigint(20) NOT NULL,
  `shipment_id` bigint(20) NOT NULL,
  `quantity` double DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `export_note`
--

CREATE TABLE `export_note` (
  `id` bigint(20) NOT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `invoice_date` datetime(6) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `function`
--

CREATE TABLE `function` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `function`
--

INSERT INTO `function` (`id`, `name`) VALUES
(1, 'view'),
(2, 'add'),
(3, 'edit'),
(4, 'remove'),
(5, 'detail'),
(6, 'excel'),
(7, 'pdf');

-- --------------------------------------------------------

--
-- Table structure for table `import_note`
--

CREATE TABLE `import_note` (
  `id` bigint(20) NOT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `received_date` datetime(6) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`id`, `name`) VALUES
(1, 'homepage'),
(2, 'sale'),
(3, 'warehouse'),
(4, 'statistics'),
(5, 'discounts'),
(6, 'promotions'),
(7, 'receipts'),
(8, 'export_notes'),
(9, 'import_notes'),
(10, 'products'),
(11, 'suppliers'),
(12, 'customers'),
(13, 'staffs'),
(14, 'accounts'),
(15, 'decentralization');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `brand_id`, `category_id`, `unit`, `cost`, `quantity`, `image`, `barcode`, `deleted`) VALUES
(1, 'Lốc 4 hộp sữa tươi 110ml Vinamilk có đường', 3, 1, 'Lốc', 21500, 0, 'img/products/1.png', '', b'0'),
(2, 'Lốc 4 hộp sữa tươi 110ml Vinamilk ít đường', 3, 1, 'Lốc', 21500, 0, 'img/products/2.png', '', b'0'),
(3, 'Lốc 4 hộp sữa tươi 110ml Vinamilk không đường', 3, 1, 'Lốc', 21500, 0, 'img/products/3.png', '', b'0'),
(4, 'Lốc 4 hộp sữa tươi 180ml Vinamilk có đường', 3, 1, 'Lốc', 29900, 0, 'img/products/4.png', '', b'0'),
(5, 'Lốc 4 hộp sữa tươi 180ml Vinamilk ít đường', 3, 1, 'Lốc', 29900, 0, 'img/products/5.png', '', b'0'),
(6, 'Lốc 4 hộp sữa tươi 180ml Vinamilk không đường', 3, 1, 'Lốc', 29900, 0, 'img/products/6.png', '', b'0'),
(7, 'Sữa bột Vinamilk Sure Prevent Gold lon 900g', 2, 5, 'Lon', 625000, 0, 'img/products/7.png', '', b'0'),
(8, '6 chai sữa pha sẵn Sure Prevent Gold 200ml', 2, 5, 'Lốc', 168000, 0, 'img/products/8.png', '', b'0'),
(9, 'Nước ép cam Vfresh 1 lít', 1, 6, 'Hộp', 50000, 1, 'img/products/9.png', '', b'0'),
(10, 'Nước ép cam không đường Vfresh 1 lít', 1, 6, 'Hộp', 50000, 0, 'img/products/10.png', '', b'0'),
(11, 'Nước ép đào Vfresh 1 lít', 1, 6, 'Hộp', 42000, 0, 'img/products/11.png', '', b'0'),
(12, 'Nước ép đào không đường Vfresh 1 lít', 1, 6, 'Hộp', 42000, 0, 'img/products/12.png', '', b'0'),
(13, 'Trà Atiso Vfresh 1 lít', 1, 6, 'Hộp', 20000, 0, 'img/products/13.png', '', b'0'),
(14, 'Trà Atiso ít đường Vfresh 1 lít', 1, 6, 'Hộp', 20000, 0, 'img/products/14.png', '', b'0'),
(15, 'Lốc 4 hộp sữa tươi 110ml TH true Milk có đường ', 4, 1, 'Lốc', 24000, 0, 'img/products/15.png', '', b'0'),
(16, 'Lốc 4 hộp sữa tươi 110ml TH true Milk ít đường ', 4, 1, 'Lốc', 24000, 0, 'img/products/16.png', '', b'0'),
(17, 'Lốc 4 hộp sữa tươi 110ml TH true Milk không đường ', 4, 1, 'Lốc', 24000, 0, 'img/products/17.png', '', b'0'),
(18, 'Lốc 4 hộp sữa tươi 180ml TH true Milk có đường ', 4, 1, 'Lốc', 36000, 0, 'img/products/18.png', '', b'0'),
(19, 'Lốc 4 hộp sữa tươi 180ml TH true Milk ít đường ', 4, 1, 'Lốc', 36000, 0, 'img/products/19.png', '', b'0'),
(20, 'Lốc 4 hộp sữa tươi 180ml TH true Milk không đường ', 4, 1, 'Lốc', 36000, 0, 'img/products/20.png', '', b'0'),
(21, 'Lốc 4 hộp sữa tươi 180ml TH true Milk dâu', 4, 1, 'Lốc', 36000, 0, 'img/products/21.png', '', b'0'),
(22, 'Lốc 4 hộp sữa tươi 180ml TH true Milk socola', 4, 1, 'Lốc', 36000, 0, 'img/products/22.png', '', b'0'),
(23, 'Sữa tươi TH true Milk có đường hộp 1 lít', 4, 1, 'Hộp', 37000, 0, 'img/products/23.png', '', b'0'),
(24, 'Sữa tười TH true Milk ít đường hộp 1 lít ', 4, 1, 'Hộp', 37000, 0, 'img/products/24.png', '', b'0'),
(25, 'Lốc 4 hộp sữa chua 180ml TH true Yogurt việt quất', 5, 4, 'Lốc', 32500, 0, 'img/products/25.png', '', b'0'),
(26, 'Lốc 4 hộp sữa chua 180ml TH true Yogurt cam', 5, 4, 'Lốc', 32500, 0, 'img/products/26.png', '', b'0'),
(27, 'Lốc 4 hộp sữa chua 180ml TH true Yogurt dâu', 5, 4, 'Lốc', 32500, 0, 'img/products/27.png', '', b'0'),
(28, 'Lốc 4 chai sữa chua 180ml TH true Yogourt việt quất', 5, 4, 'Lốc', 31000, 0, 'img/products/28.png', '', b'0'),
(29, 'Lốc 4 chai sữa chua 180ml TH true Yogurt cam', 5, 4, 'Lốc', 31000, 0, 'img/products/29.png', '', b'0'),
(30, 'Lốc 4 chai sữa chua 180ml TH true Yogurt dâu', 5, 4, 'Lốc', 31000, 0, 'img/products/30.png', '', b'0'),
(31, 'Nước ngọt Coca Cola lon 320ml', 6, 3, 'Lon', 10600, 0, 'img/products/31.png', '', b'0'),
(32, 'Nước ngọt Coca Cola chai 390ml', 6, 3, 'Chai', 7800, 0, 'img/products/32.png', '', b'0'),
(33, 'Nước ngọt Coca Cola chai 1.5 lít', 6, 3, 'Chai', 20200, 0, 'img/products/33.png', '', b'0'),
(34, 'Nước ngọt Coca Cola Zero lon 320ml', 6, 3, 'Lon', 10600, 0, 'img/products/34.png', '', b'0'),
(35, 'Nước ngọt Coca Cola Zero chai 390ml', 6, 3, 'Chai', 7800, 0, 'img/products/35.png', '', b'0'),
(36, 'Nước ngọt Coca Cola Zero chai 1.5 lít', 6, 3, 'Chai', 20200, 0, 'img/products/36.png', '', b'0'),
(37, 'Nước ngọt Fanta cam chai 390ml', 7, 3, 'Chai', 7800, 0, 'img/products/37.png', '', b'0'),
(38, 'Nước ngọt Fanta xá xị chai 390ml', 7, 3, 'Chai', 7800, 0, 'img/products/38.png', '', b'0'),
(39, 'Mì Hảo Hảo tôm chua cay gói 75g', 13, 2, 'Gói', 4400, 0, 'img/products/39.png', '', b'0'),
(40, 'Mì Handy Hảo Hảo tôm chua cay ly 67g', 13, 2, 'Ly', 9500, 0, 'img/products/40.png', '', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `id` bigint(20) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotion_gift`
--

CREATE TABLE `promotion_gift` (
  `promotion_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotion_item`
--

CREATE TABLE `promotion_item` (
  `promotion_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id` bigint(20) NOT NULL,
  `staff_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `invoice_date` datetime(6) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `received` double DEFAULT NULL,
  `excess` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `receipt_detail`
--

CREATE TABLE `receipt_detail` (
  `receipt_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `percent` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`, `deleted`) VALUES
(0, 'Super Admin', b'1'),
(1, 'Admin', b'0'),
(2, 'Quản lý cửa hàng', b'0'),
(3, 'Nhân viên bán hàng', b'0'),
(4, 'Nhân viên kho', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `shipment`
--

CREATE TABLE `shipment` (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `remain` double DEFAULT NULL,
  `mfg` date DEFAULT NULL,
  `exp` date DEFAULT NULL,
  `sku` varchar(255) DEFAULT NULL,
  `import_note_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `entry_date` date DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `name`, `gender`, `birthdate`, `phone`, `address`, `email`, `entry_date`, `deleted`) VALUES
(1, 'ADMIN', b'0', '1000-01-01', '', '', '', '1000-01-01', b'0'),
(2, 'NGUYỄN TIẾN DŨNG', b'1', '2003-12-19', '0834527892', '531 Nguyễn Oanh, Phường 17, Gò Vấp, Thành phố Hồ Chí Minh', 'dungboi@gmail.com', '1000-01-01', b'0'),
(3, 'ĐINH QUANG DUY', b'1', '2003-01-20', '0359872569', '1A Lê Đức Thọ, Phường 17, Gò Vấp, Thành phố Hồ Chí Minh', 'quangduy@gmail.com', '1000-01-01', b'0'),
(4, 'NGUYỄN HOÀNG LONG', b'1', '2003-08-30', '0970352875', '514/26 Lê Đức Thọ, Phường 17, Gò Vấp, Thành phố Hồ Chí Minh', 'longbot@gmail.com', '1000-01-01', b'0'),
(5, 'NGUYỄN HOÀNG MINH', b'1', '2003-03-06', '0367834257', '153 Lê Hoàng Phái, Phường 17, Gò Vấp, Thành phố Hồ Chí Minh', 'hoangminh@gmail.com', '1000-01-01', b'0'),
(6, 'PHẠM ĐẶNG PHƯỚC SANG', b'1', '2002-09-20', '0898881394', '242 Nguyễn Văn Lượng, Phường 10, Gò Vấp, Thành phố Hồ Chí Minh', 'psg.exorcist@gmail.com', '1000-01-01', b'0'),
(7, 'NGUYỄN THỊ XUÂN MAI', b'0', '2002-06-19', '0825367498', '168 Lê Đức Thọ, Phường 15, Gò Vấp, Thành phố Hồ Chí Minh', 'xuanmai@gmail.com', '2023-09-15', b'0'),
(8, 'NGUYỄN THỊ LỆ GIANG', b'0', '2000-05-27', '0963527895', '190 Quang Trung, Phường 10, Gò Vấp, Thành phố Hồ Chí Minh', 'legiang@gmail.com', '2023-09-28', b'0'),
(9, 'ĐẶNG VĂN LÂM', b'1', '2001-02-18', '0340734629', '7 Phan Văn Trị, Phường 10, Gò Vấp, Thành phố Hồ Chí Minh', 'vanlam@gmail.com', '2023-06-27', b'0'),
(10, 'HOÀNG XUÂN PHÚC', b'1', '2001-04-11', '0812535278', '526 Lê Quang Định, Phường 1, Gò Vấp, Thành phố Hồ Chí Minh', 'xuanphuc@gmail.com', '2023-08-17', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `statistic`
--

CREATE TABLE `statistic` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `expenses` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `name`, `phone`, `address`, `email`, `deleted`) VALUES
(1, 'Vinamilk', '02854155555', 'Số 10, Đường Tân Trào, phường Tân Phú, quận 7, Tp. HCM', 'vinamilk@vinamilk.com.vn', b'0'),
(2, 'TH true Milk', '02862918888', 'Tháp B, Tầng 6 - Tòa nhà Viettel, Số 285 Cách Mạng Tháng 8, P.12, Q.10, TP.HCM', 'chamsockhachhang@thmilk.vn', b'0'),
(3, 'Coca-Cola', '1900555584', 'Xa lộ Hà Nội, Phường Linh Trung, Quận Thủ Đức, Thành phố Hồ Chí Minh', 'trucle@coca-cola.com', b'0'),
(4, 'Acecook', '02838154064', 'Lô số II-3,Đường số 11,Nhóm CN II, Khu Công nghiệp Tân Bình, Phường Tây Thạnh, Quận Tân Phú, Thành phố Hồ Chí Minh', 'info@acecookvietnam.com', b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_STAFF` (`staff_id`),
  ADD KEY `FK_ROLE` (`role_id`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_SUPPLIER` (`supplier_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `decentralization`
--
ALTER TABLE `decentralization`
  ADD PRIMARY KEY (`role_id`,`module_id`,`function_id`),
  ADD KEY `FK_MODULE` (`module_id`),
  ADD KEY `FK_FUNCTION` (`function_id`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `discount_detail`
--
ALTER TABLE `discount_detail`
  ADD PRIMARY KEY (`discount_id`,`product_id`),
  ADD KEY `FK_PRODUCT` (`product_id`);

--
-- Indexes for table `export_detail`
--
ALTER TABLE `export_detail`
  ADD PRIMARY KEY (`export_note_id`,`shipment_id`),
  ADD KEY `FK_SHIPMENT` (`shipment_id`);

--
-- Indexes for table `export_note`
--
ALTER TABLE `export_note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_STAFF1` (`staff_id`);

--
-- Indexes for table `function`
--
ALTER TABLE `function`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `import_note`
--
ALTER TABLE `import_note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_STAFF2` (`staff_id`),
  ADD KEY `FK_SUPPLIER1` (`supplier_id`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_BRAND` (`brand_id`),
  ADD KEY `FK_CATEGORY` (`category_id`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `promotion_gift`
--
ALTER TABLE `promotion_gift`
  ADD PRIMARY KEY (`promotion_id`,`product_id`),
  ADD KEY `FK_PRODUCT1` (`product_id`);

--
-- Indexes for table `promotion_item`
--
ALTER TABLE `promotion_item`
  ADD PRIMARY KEY (`promotion_id`,`product_id`),
  ADD KEY `FK_PRODUCT2` (`product_id`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_STAFF3` (`staff_id`),
  ADD KEY `FK_CUSTOMER` (`customer_id`);

--
-- Indexes for table `receipt_detail`
--
ALTER TABLE `receipt_detail`
  ADD PRIMARY KEY (`receipt_id`,`product_id`),
  ADD KEY `FK_PRODUCT3` (`product_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `shipment`
--
ALTER TABLE `shipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_IMPORT_NOTE` (`import_note_id`),
  ADD KEY `FK_PRODUCT4` (`product_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `statistic`
--
ALTER TABLE `statistic`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `discount`
--
ALTER TABLE `discount`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `export_note`
--
ALTER TABLE `export_note`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `function`
--
ALTER TABLE `function`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `import_note`
--
ALTER TABLE `import_note`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `receipt`
--
ALTER TABLE `receipt`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `shipment`
--
ALTER TABLE `shipment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `statistic`
--
ALTER TABLE `statistic`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FK_STAFF` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `brand`
--
ALTER TABLE `brand`
  ADD CONSTRAINT `FK_SUPPLIER` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`);

--
-- Constraints for table `decentralization`
--
ALTER TABLE `decentralization`
  ADD CONSTRAINT `FK_FUNCTION` FOREIGN KEY (`function_id`) REFERENCES `function` (`id`),
  ADD CONSTRAINT `FK_MODULE` FOREIGN KEY (`module_id`) REFERENCES `module` (`id`),
  ADD CONSTRAINT `FK_ROLE1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints for table `discount_detail`
--
ALTER TABLE `discount_detail`
  ADD CONSTRAINT `FK_DISCOUNT` FOREIGN KEY (`discount_id`) REFERENCES `discount` (`id`),
  ADD CONSTRAINT `FK_PRODUCT` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `export_detail`
--
ALTER TABLE `export_detail`
  ADD CONSTRAINT `FK_EXPORT_NOTE` FOREIGN KEY (`export_note_id`) REFERENCES `export_note` (`id`),
  ADD CONSTRAINT `FK_SHIPMENT` FOREIGN KEY (`shipment_id`) REFERENCES `shipment` (`id`);

--
-- Constraints for table `export_note`
--
ALTER TABLE `export_note`
  ADD CONSTRAINT `FK_STAFF1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `import_note`
--
ALTER TABLE `import_note`
  ADD CONSTRAINT `FK_STAFF2` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`),
  ADD CONSTRAINT `FK_SUPPLIER1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_BRAND` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  ADD CONSTRAINT `FK_CATEGORY` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `promotion_gift`
--
ALTER TABLE `promotion_gift`
  ADD CONSTRAINT `FK_PRODUCT1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK_PROMOTION` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`);

--
-- Constraints for table `promotion_item`
--
ALTER TABLE `promotion_item`
  ADD CONSTRAINT `FK_PRODUCT2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK_PROMOTION1` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`id`);

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `FK_CUSTOMER` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `FK_STAFF3` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `receipt_detail`
--
ALTER TABLE `receipt_detail`
  ADD CONSTRAINT `FK_PRODUCT3` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK_RECEIPT` FOREIGN KEY (`receipt_id`) REFERENCES `receipt` (`id`);

--
-- Constraints for table `shipment`
--
ALTER TABLE `shipment`
  ADD CONSTRAINT `FK_IMPORT_NOTE` FOREIGN KEY (`import_note_id`) REFERENCES `import_note` (`id`),
  ADD CONSTRAINT `FK_PRODUCT4` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
