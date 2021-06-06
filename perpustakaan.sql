-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2021 at 04:43 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_transaction` (IN `idPeminjam` INT(11), IN `idBuku` VARCHAR(255))  begin
declare exit handler for sqlexception
begin
rollback;
end;
START TRANSACTION;
INSERT INTO transaksi (idPeminjam, idBuku, tanggalPinjam, batasTanggal) VALUES (idPeminjam, idBuku, now(), (now()+INTERVAL 7 DAY));
COMMIT;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update_transaction` (IN `idTransaksi` INT(11))  begin
UPDATE transaksi SET tanggalKembali=now() WHERE id=idTransaksi;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `telepon` varchar(100) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `artikel`
--

CREATE TABLE `artikel` (
  `id` int(11) NOT NULL,
  `idJurnal` varchar(255) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `nomor` int(11) NOT NULL,
  `halamanAwal` int(11) NOT NULL,
  `halamanAkhir` int(11) NOT NULL,
  `doi` varchar(255) DEFAULT NULL,
  `tanggalDidaftarkan` date DEFAULT NULL,
  `tanggalDireview` date DEFAULT NULL,
  `tanggalDipublikasikan` date DEFAULT NULL,
  `tglInput` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `kodeBuku` varchar(255) NOT NULL,
  `judulBuku` varchar(100) NOT NULL,
  `pengarang` varchar(100) NOT NULL,
  `penerbit` varchar(100) NOT NULL,
  `kota` varchar(100) NOT NULL,
  `edisi` int(11) DEFAULT NULL,
  `tanggalPublikasi` date NOT NULL,
  `isbn` int(11) DEFAULT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`kodeBuku`, `judulBuku`, `pengarang`, `penerbit`, `kota`, `edisi`, `tanggalPublikasi`, `isbn`, `stok`) VALUES
('B001', 'Doraemon', 'Fujiko F. Fujio', '', '', NULL, '2021-06-08', NULL, 0),
('C001', 'Laskar Pelangi', 'Andrea Hirata', '', '', NULL, '2021-06-10', NULL, 0),
('D001', 'Harry Potter', 'J.K. Rowling', '', '', NULL, '2021-06-18', NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `denda_transaksi`
--

CREATE TABLE `denda_transaksi` (
  `id` int(11) NOT NULL,
  `idTransaksi` int(11) NOT NULL,
  `denda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `denda_transaksi`
--

INSERT INTO `denda_transaksi` (`id`, `idTransaksi`, `denda`) VALUES
(2, 2, 0),
(4, 3, 35000);

-- --------------------------------------------------------

--
-- Table structure for table `jurnal`
--

CREATE TABLE `jurnal` (
  `id` varchar(255) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `tahun` year(4) NOT NULL,
  `volume` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prodi` varchar(100) DEFAULT NULL,
  `telepon` varchar(100) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `aktif` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `name`, `email`, `password`, `prodi`, `telepon`, `alamat`, `aktif`) VALUES
(1902748, 'Fityandhiya', 'fityandhiya@upi.edu', 'fityan', 'Rekayasa Perangkat Lunak', '081372527392', 'Bandung', 1);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `idPeminjam` int(11) DEFAULT NULL,
  `idBuku` varchar(255) NOT NULL,
  `tanggalPinjam` datetime DEFAULT NULL,
  `batasTanggal` datetime DEFAULT NULL,
  `tanggalKembali` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `idPeminjam`, `idBuku`, `tanggalPinjam`, `batasTanggal`, `tanggalKembali`) VALUES
(1, 1902748, 'B001', '2021-06-06 08:47:04', '2021-06-06 08:47:11', '2021-06-13 09:00:55'),
(2, 1902748, 'C001', '2021-06-06 08:48:29', '2021-06-06 08:48:36', '2021-06-05 09:01:35'),
(3, 1902748, 'D001', '2021-06-06 08:52:07', '2021-06-13 08:52:07', '2021-06-20 09:21:42');

--
-- Triggers `transaksi`
--
DELIMITER $$
CREATE TRIGGER `add_denda` AFTER UPDATE ON `transaksi` FOR EACH ROW begin
Declare denda int default 0;
if(datediff(new.tanggalKembali, new.batasTanggal) < 0) then
set denda = 0;
else
set denda = datediff(new.tanggalKembali, new.batasTanggal)*5000;
end if;
INSERT INTO denda_transaksi (idTransaksi, denda) VALUES (new.id, denda); 
end
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `artikel`
--
ALTER TABLE `artikel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_jurnal` (`idJurnal`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`kodeBuku`);

--
-- Indexes for table `denda_transaksi`
--
ALTER TABLE `denda_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_transaksi` (`idTransaksi`);

--
-- Indexes for table `jurnal`
--
ALTER TABLE `jurnal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_buku` (`idBuku`),
  ADD KEY `id_peminjam` (`idPeminjam`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `artikel`
--
ALTER TABLE `artikel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `denda_transaksi`
--
ALTER TABLE `denda_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `artikel`
--
ALTER TABLE `artikel`
  ADD CONSTRAINT `artikel_ibfk_1` FOREIGN KEY (`idJurnal`) REFERENCES `jurnal` (`id`);

--
-- Constraints for table `denda_transaksi`
--
ALTER TABLE `denda_transaksi`
  ADD CONSTRAINT `denda_transaksi_ibfk_1` FOREIGN KEY (`idTransaksi`) REFERENCES `transaksi` (`id`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`idPeminjam`) REFERENCES `mahasiswa` (`id`),
  ADD CONSTRAINT `transaksi_ibfk_4` FOREIGN KEY (`idBuku`) REFERENCES `buku` (`kodeBuku`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
