-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 04, 2025 at 05:30 AM
-- Server version: 8.0.30
-- PHP Version: 8.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pp2_tubes`
--

-- --------------------------------------------------------

--
-- Table structure for table `kurir`
--

CREATE TABLE `kurir` (
  `id` int NOT NULL,
  `nama_kurir` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kurir`
--

INSERT INTO `kurir` (`id`, `nama_kurir`) VALUES
(1, 'Budi'),
(2, 'Rudi'),
(3, 'Andi'),
(4, 'Siti'),
(5, 'Dedi'),
(7, '323'),
(8, 'wa'),
(10, 'BHADRIKASSS'),
(11, 'ASA');

-- --------------------------------------------------------

--
-- Table structure for table `penjemputan`
--

CREATE TABLE `penjemputan` (
  `id` int NOT NULL,
  `id_kurir` int DEFAULT NULL,
  `id_permintaan` int DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `point` int DEFAULT NULL,
  `tanggal_penjemputan` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `penjemputan`
--

INSERT INTO `penjemputan` (`id`, `id_kurir`, `id_permintaan`, `status`, `point`, `tanggal_penjemputan`) VALUES
(1, 1, 1, 'Dalam Perjalanan', 5, '2024-12-21'),
(2, 2, 2, 'Belum Dimulai', 102, '2024-12-22'),
(3, 3, 3, 'Dalam Perjalanan', 7, '2024-12-23'),
(4, 1, 1, 'Dalam Perjalanan', 3, '2024-12-24'),
(5, 5, 5, 'Belum Dimulai', 0, '2024-12-25'),
(9, 1, 1, 'Belum Dimulai', 2, '2025-01-03'),
(10, 1, 1, 'Belum Dimulai', 2, '2025-01-03'),
(11, 1, 1, 'Belum Dimulai', 2, '2025-01-03'),
(12, 1, 1, 'Belum Dimulai', 2, '2025-01-03'),
(15, 1, 1, 'Belum Dimulai', 2, '2025-01-03'),
(16, 1, 1, 'Belum Dimulai', 2, '2025-01-03'),
(17, 1, 1, 'Selesai', 12, '2025-01-03'),
(18, 1, 1, 'Dalam Perjalanan', 21, '2025-01-03'),
(20, 1, 1, 'Dalam Perjalanan', 21, '2025-01-03'),
(21, 1, 1, 'Dalam Perjalanan', 12, '2025-01-03'),
(23, 1, 1, 'Belum Dimulai', 2, '2025-01-04'),
(24, 1, 1, 'Belum Dimulai', 21, '2025-01-04'),
(25, 1, 1, 'Belum Dimulai', 2, '2025-01-04'),
(32, 1, 1, 'Belum Dimulai', 2, '2025-01-04'),
(33, 1, 1, 'Belum Dimulai', 221212, '2025-01-04'),
(34, 1, 1, 'Belum Dimulai', 12, '2025-01-04'),
(38, 5, 5, 'Belum Dimulai', 21, '2025-01-04'),
(39, 8, 3, 'Selesai', 2, '2025-01-04'),
(43, 1, 1, 'Belum Dimulai', 112, '2025-01-04'),
(46, 10, 5, 'Belum Dimulai', 12, '2025-01-04'),
(47, 10, 1, 'Dalam Perjalanan', 121, '2025-01-04'),
(48, 11, 1, 'Belum Dimulai', 1112, '2025-01-04'),
(49, 1, 1, 'Selesai', 12, '2025-01-04'),
(50, 1, 1, 'Dalam Perjalanan', 12, '2025-01-04'),
(51, 1, 1, 'Belum Dimulai', 12, '2025-01-04'),
(52, 1, 1, 'Dalam Perjalanan', 12, '2025-01-04'),
(53, 7, 5, 'Belum Dimulai', 12, '2025-01-04');

-- --------------------------------------------------------

--
-- Table structure for table `permintaan`
--

CREATE TABLE `permintaan` (
  `id` int NOT NULL,
  `nama_pelanggan` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `jenis_sampah` varchar(255) NOT NULL,
  `berat_sampah` int NOT NULL,
  `tanggal_penjemputan` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `permintaan`
--

INSERT INTO `permintaan` (`id`, `nama_pelanggan`, `alamat`, `jenis_sampah`, `berat_sampah`, `tanggal_penjemputan`) VALUES
(1, 'Ahmad', 'Jl. Merdeka No. 123', 'HP', 15, '2024-12-21'),
(2, 'Sari', 'Jl. Pahlawan No. 45', 'Battery', 25, '2024-12-22'),
(3, 'Joko', 'Jl. Kenanga No. 67', 'Laptop', 30, '2024-12-23'),
(4, 'Lia', 'Jl. Surya No. 89', 'Computer', 10, '2024-12-24'),
(5, 'Tono', 'Jl. Bintang No. 56', 'HP', 20, '2024-12-25');

-- --------------------------------------------------------

--
-- Table structure for table `tracking`
--

CREATE TABLE `tracking` (
  `id` int NOT NULL,
  `id_penjemputan` int DEFAULT NULL,
  `lokasi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tracking`
--

INSERT INTO `tracking` (`id`, `id_penjemputan`, `lokasi`) VALUES
(1, 1, 'Jl. Merdeka No. 123'),
(2, 1, 'Jl. Merdeka No. 100'),
(3, 2, 'Jl. Pahlawan No. 45'),
(4, 2, 'Jl. Pahlawan No. 10'),
(5, 3, 'Jl. Kenanga No. 67'),
(6, 3, 'Jl. Kenanga No. 50'),
(7, 4, 'Jl. Surya No. 89'),
(8, 4, 'Jl. Surya No. 70'),
(9, 5, 'Jl. Bintang No. 56');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kurir`
--
ALTER TABLE `kurir`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `penjemputan`
--
ALTER TABLE `penjemputan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kurir` (`id_kurir`),
  ADD KEY `id_permintaan` (`id_permintaan`);

--
-- Indexes for table `permintaan`
--
ALTER TABLE `permintaan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tracking`
--
ALTER TABLE `tracking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_penjemputan` (`id_penjemputan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kurir`
--
ALTER TABLE `kurir`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `penjemputan`
--
ALTER TABLE `penjemputan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `permintaan`
--
ALTER TABLE `permintaan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tracking`
--
ALTER TABLE `tracking`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `penjemputan`
--
ALTER TABLE `penjemputan`
  ADD CONSTRAINT `penjemputan_ibfk_1` FOREIGN KEY (`id_kurir`) REFERENCES `kurir` (`id`),
  ADD CONSTRAINT `penjemputan_ibfk_2` FOREIGN KEY (`id_permintaan`) REFERENCES `permintaan` (`id`);

--
-- Constraints for table `tracking`
--
ALTER TABLE `tracking`
  ADD CONSTRAINT `tracking_ibfk_1` FOREIGN KEY (`id_penjemputan`) REFERENCES `penjemputan` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
