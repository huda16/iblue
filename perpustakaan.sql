-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2021 at 05:00 AM
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
declare stokBuku int;
SELECT buku.stok INTO stokBuku FROM buku WHERE buku.kodeBuku = idBuku;
IF(stokBuku > 0) THEN
INSERT INTO transaksi (idPeminjam, idBuku, tanggalPinjam, batasTanggal) VALUES (idPeminjam, idBuku, now(), (now()+INTERVAL 7 DAY));
UPDATE buku SET stok = stok - 1 WHERE kodeBuku = idBuku;
END IF;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update_transaction` (IN `idTransaksi` INT(11))  begin
DECLARE kodeBukuTransaksi varchar(255);
SELECT buku.kodeBuku INTO kodeBukuTransaksi FROM buku INNER JOIN transaksi ON buku.kodeBuku = transaksi.idBuku WHERE transaksi.id = idTransaksi;
UPDATE transaksi SET tanggalKembali=now() WHERE id=idTransaksi;
UPDATE buku SET stok = stok + 1 WHERE kodeBuku = kodeBukuTransaksi;
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

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `nama`, `password`, `telepon`, `alamat`, `status`) VALUES
(1905375, 'admin', 'admin', '081242337968', 'Bandung', 1);

-- --------------------------------------------------------

--
-- Table structure for table `artikel`
--

CREATE TABLE `artikel` (
  `id` int(11) NOT NULL,
  `idJurnal` varchar(255) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `pengarang` varchar(255) NOT NULL,
  `nomor` int(11) NOT NULL,
  `halamanAwal` int(11) NOT NULL,
  `halamanAkhir` int(11) NOT NULL,
  `doi` varchar(255) DEFAULT NULL,
  `tanggalDidaftarkan` date DEFAULT NULL,
  `tanggalDireview` date DEFAULT NULL,
  `tanggalDipublikasikan` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `artikel`
--

INSERT INTO `artikel` (`id`, `idJurnal`, `judul`, `pengarang`, `nomor`, `halamanAwal`, `halamanAkhir`, `doi`, `tanggalDidaftarkan`, `tanggalDireview`, `tanggalDipublikasikan`) VALUES
(1, 'JS001', 'Artikel 1', 'Daffa', 125, 9, 10, 'jdyeh', '2021-06-07', '2021-06-08', '2021-06-09'),
(2, 'JS001', 'Sosial 1', 'Ari Ganteng', 124, 9, 10, 'jdyeh', '2021-06-07', '2021-06-08', '2021-06-09'),
(3, 'JS002', 'How To Define Life', 'Fityan', 123, 1, 10, '1', '2021-06-07', '2021-06-08', '2021-06-09'),
(4, 'JS004', 'Iblue', 'Daffa', 1, 1, 20, '12', '2021-06-07', '2021-06-08', '2021-06-09');

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
  `stok` int(11) NOT NULL,
  `kodeRak` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`kodeBuku`, `judulBuku`, `pengarang`, `penerbit`, `kota`, `edisi`, `tanggalPublikasi`, `isbn`, `stok`, `kodeRak`) VALUES
('B001', 'Doraemon', 'Fujiko F. Fujio', 'Elex Media Komputindo', 'Tokyo', 1, '2021-06-08', 0, 1, 'N001'),
('C001', 'Laskar Pelangii', 'Andrea Hirata', 'Mizan Media Utama', 'Bandung', 1, '2008-06-07', 979306279, 10, 'N001'),
('D001', 'Harry Potter Harry Potter dan batu bertuah', 'J.K. Rowling', 'Gramedia Pustaka Utama', 'Jakarta', 1, '2021-06-18', 747532699, 10, 'N001'),
('F001', 'Habibie & Ainun', 'Bacharuddin Jusuf Habibie', 'PT THC Mandiri', 'Jakarta', 1, '2021-06-07', 97897912, 10, 'N001'),
('G001', 'C++: a Dialogue', 'Steve Heller', 'Prentice Hall', 'London', 1, '2003-06-07', 2131, 10, 'SE001'),
('H001', 'Milea, suara dari Dilan', 'Pidi Baiq', 'PT Mizan Pustaka', 'Bandung', 1, '2016-06-07', 0, 10, 'N001'),
('I001', 'Pemrograman Berbasis Objek Dengan Bahasa Java', 'Indrajani', 'Elex Media', 'Jakarta', 1, '2021-06-07', 979273333, 11, 'CS001'),
('J001', 'Atomic Habits', 'James Clear', 'Penguin Random House', 'Lupa', 0, '2018-03-29', 0, 1, 'S001');

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
(4, 3, 35000),
(5, 4, 0),
(6, 2, 5000),
(7, 5, 0),
(8, 6, 0),
(9, 4, 0),
(10, 7, 0),
(11, 8, 0),
(12, 12, 0),
(13, 9, 0),
(14, 10, 0),
(15, 13, 0);

-- --------------------------------------------------------

--
-- Table structure for table `jurnal`
--

CREATE TABLE `jurnal` (
  `kode` varchar(255) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `tahun` int(11) NOT NULL,
  `volume` int(11) NOT NULL,
  `kodeRak` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jurnal`
--

INSERT INTO `jurnal` (`kode`, `judul`, `tahun`, `volume`, `kodeRak`) VALUES
('JS001', 'Jurnal Science', 2001, 10, 'CS001'),
('JS002', 'IJOST', 2020, 1, 'SE001'),
('JS003', 'IJOST', 2020, 2, 'SE001'),
('JS004', 'SEICT', 2021, 1, 'SE001'),
('JS005', 'Computer Science', 2020, 1, 'CS001');

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
(1902748, 'Fityandhiya', 'fityandhiya@upi.edu', 'fityan', 'Rekayasa Perangkat Lunak', '081372527392', 'Bandung', 1),
(1904207, 'Daffa Almer Fauzan', 'daffaalmer@gmail.com', 'daffa', 'Rekayasa Perangkat Lunak', '08283834325', 'Depok', 1),
(1904245, 'Ari Sandy Kurniawan', 'arisandy@gmail.com', 'ari', 'Rekayasa Perangkat Lunak', '08127383623', 'Tangerang', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rak`
--

CREATE TABLE `rak` (
  `kode` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `lokasi` varchar(255) NOT NULL,
  `keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rak`
--

INSERT INTO `rak` (`kode`, `nama`, `lokasi`, `keterangan`) VALUES
('CS001', 'Ilmu Komputer', 'Ruang 1 Lantai 1', 'Buku tentang ilmu komputer'),
('N001', 'Novel', 'Ruang 2 Lantai 1', 'Novel Random'),
('S001', 'Sastra', 'Ruang 2 Lantai 2', 'Buku tentang sastra'),
('SE001', 'Rekayasa Perangkat Lunak', 'Ruang 1 Lantai 2', 'Buku tentang rekayasa perangkat lunak');

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
(2, 1902748, 'C001', '2021-06-06 08:48:29', '2021-06-06 08:48:36', '2021-06-07 07:12:14'),
(3, 1902748, 'D001', '2021-06-06 08:52:07', '2021-06-13 08:52:07', '2021-06-20 09:21:42'),
(4, 1902748, 'B001', '2021-06-06 19:01:03', '2021-06-13 19:01:03', '2021-06-07 08:49:48'),
(5, 1902748, 'C001', '2021-06-06 19:53:51', '2021-06-13 19:53:51', '2021-06-07 07:33:06'),
(6, 1902748, 'D001', '2021-06-07 07:34:53', '2021-06-14 07:34:53', '2021-06-07 07:35:25'),
(7, 1902748, 'B001', '2021-06-07 09:09:07', '2021-06-14 09:09:07', '2021-06-07 09:09:17'),
(8, 1902748, 'D001', '2021-06-07 09:15:07', '2021-06-14 09:15:07', '2021-06-07 09:15:21'),
(9, 1904207, 'I001', '2021-06-07 10:48:29', '2021-06-14 10:48:29', '2021-06-09 07:13:46'),
(10, 1904245, 'B001', '2021-06-07 10:48:50', '2021-06-14 10:48:50', '2021-06-09 07:15:52'),
(12, 1904245, 'I001', '2021-06-07 11:10:24', '2021-06-14 11:10:24', '2021-06-07 11:10:38'),
(13, 1904207, 'B001', '2021-06-09 07:29:53', '2021-06-16 07:29:53', '2021-06-09 07:31:07');

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
  ADD PRIMARY KEY (`kodeBuku`),
  ADD KEY `kodeRak` (`kodeRak`);

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
  ADD PRIMARY KEY (`kode`),
  ADD KEY `kodeRak` (`kodeRak`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rak`
--
ALTER TABLE `rak`
  ADD PRIMARY KEY (`kode`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `denda_transaksi`
--
ALTER TABLE `denda_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `artikel`
--
ALTER TABLE `artikel`
  ADD CONSTRAINT `artikel_ibfk_1` FOREIGN KEY (`idJurnal`) REFERENCES `jurnal` (`kode`);

--
-- Constraints for table `buku`
--
ALTER TABLE `buku`
  ADD CONSTRAINT `buku_ibfk_1` FOREIGN KEY (`kodeRak`) REFERENCES `rak` (`kode`);

--
-- Constraints for table `jurnal`
--
ALTER TABLE `jurnal`
  ADD CONSTRAINT `jurnal_ibfk_1` FOREIGN KEY (`kodeRak`) REFERENCES `rak` (`kode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
