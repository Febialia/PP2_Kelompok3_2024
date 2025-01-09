-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 05, 2025 at 01:59 PM
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
(1, 'Budi Santoso'),
(2, 'Siti Aminah'),
(3, 'Joko Susilo'),
(4, 'Dewi Putri'),
(5, 'Agus Setiawan'),
(6, 'Rina Wijaya'),
(7, 'Herman Syah'),
(8, 'Lina Marlina'),
(9, 'Toni Iskandar'),
(10, 'Maya Sari');

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
(1, 1, 1, 'Belum Dimulai', 10, '2024-11-17'),
(2, 3, 2, 'Dalam Perjalanan', 15, '2024-11-18'),
(3, 5, 3, 'Selesai', 20, '2024-11-19'),
(4, 2, 4, 'Belum Dimulai', 12, '2024-11-20'),
(5, 7, 5, 'Dalam Perjalanan', 18, '2024-11-21');

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
  `tanggal_permintaan` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `permintaan`
--

INSERT INTO `permintaan` (`id`, `nama_pelanggan`, `alamat`, `jenis_sampah`, `berat_sampah`, `tanggal_permintaan`) VALUES
(1, 'Ibu Ani', 'Jl. Merdeka No. 17, Jakarta Pusat', 'Plastik', 5, '2024-11-15'),
(2, 'Bapak Budi', 'Gg. Mawar No. 3, Surabaya', 'Kertas', 10, '2024-11-16'),
(3, 'Saudari Citra', 'Perumahan Indah Blok C No. 5, Bandung', 'Kaca', 3, '2024-11-17'),
(4, 'Tuan David', 'Jl. Pahlawan No. 22, Medan', 'Organik', 7, '2024-11-18'),
(5, 'Nyonya Erna', 'Komplek Sejahtera No. 10, Yogyakarta', 'Campuran', 12, '2024-11-19'),
(6, 'Bapak Fandi', 'Jl. Diponegoro No. 45, Semarang', 'Plastik', 8, '2024-11-20'),
(7, 'Ibu Gita', 'Desa Sukamaju RT 03 RW 05, Malang', 'Kertas', 6, '2024-11-21'),
(8, 'Saudara Hasan', 'Jl. Kenangan No. 8, Denpasar', 'Logam', 2, '2024-11-22'),
(9, 'Saudari Intan', 'Perumahan Asri Blok B No. 12, Makassar', 'Organik', 9, '2024-11-23'),
(10, 'Tuan Jaka', 'Jl. Sudirman Kav. 78, Palembang', 'Campuran', 15, '2024-11-24'),
(11, 'Nyonya Kartika', 'Gg. Sejahtera No. 1, Bogor', 'Plastik', 4, '2024-11-25'),
(12, 'Bapak Lukman', 'Jl. Gatot Subroto No. 100, Bekasi', 'Kertas', 11, '2024-11-26');

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
(1, 1, 'Gudang Kurir'),
(2, 2, 'Jl. Raya Kalimalang'),
(3, 3, 'Lokasi Pelanggan - Jl. Pahlawan No. 22, Medan'),
(4, 4, 'Gudang Kurir'),
(5, 5, 'Jl. Solo - Yogyakarta');

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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `penjemputan`
--
ALTER TABLE `penjemputan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `permintaan`
--
ALTER TABLE `permintaan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tracking`
--
ALTER TABLE `tracking`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
