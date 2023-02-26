-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 26, 2023 at 11:18 PM
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
-- Database: `cms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(255) NOT NULL,
  `adminName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `adminName`, `password`) VALUES
(1, 'SYSTEM', 'SYS123');

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `courseCode` varchar(50) NOT NULL,
  `courseName` varchar(100) NOT NULL,
  `totalModules` int(20) NOT NULL,
  `totalSemester` int(20) NOT NULL,
  `courseLength(months)` int(20) NOT NULL,
  `availability` varchar(20) NOT NULL DEFAULT 'Available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`courseCode`, `courseName`, `totalModules`, `totalSemester`, `courseLength(months)`, `availability`) VALUES
('BS004', 'Business Art', 32, 8, 36, 'Available'),
('C700', 'Biochemistry', 32, 8, 48, 'Available'),
('CS40', 'Computer Science', 32, 8, 36, 'Available'),
('F100', 'Chemistry', 32, 8, 48, 'Available'),
('L700', 'Geography', 24, 6, 36, 'Available'),
('V100', 'History', 24, 6, 36, 'Not Available'),
('W100', 'Fine Art', 24, 6, 36, 'Not Available');

-- --------------------------------------------------------

--
-- Table structure for table `login_activity`
--

CREATE TABLE `login_activity` (
  `Activity_ID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  `loginTimestamp` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login_activity`
--

INSERT INTO `login_activity` (`Activity_ID`, `userID`, `type`, `loginTimestamp`) VALUES
(2, 1, 'Student', '2023-02-09 05:46:18'),
(3, 1, 'Student', '2023-02-09 06:09:27'),
(4, 1, 'Admin', '2023-02-09 06:11:19'),
(5, 3, 'Student', '2023-02-09 13:31:50'),
(6, 1, 'Student', '2023-02-09 19:09:47'),
(7, 11, 'Tutor', '2023-02-09 19:10:47'),
(8, 11, 'Tutor', '2023-02-09 19:23:00'),
(9, 11, 'Tutor', '2023-02-09 19:31:43'),
(10, 11, 'Tutor', '2023-02-09 19:34:06'),
(11, 11, 'Tutor', '2023-02-09 19:35:10'),
(12, 11, 'Tutor', '2023-02-09 19:37:52'),
(13, 11, 'Tutor', '2023-02-09 19:41:29'),
(14, 11, 'Tutor', '2023-02-09 19:42:27'),
(15, 11, 'Tutor', '2023-02-09 19:44:53'),
(16, 11, 'Tutor', '2023-02-09 19:47:26'),
(17, 11, 'Tutor', '2023-02-09 19:48:51'),
(18, 1, 'Admin', '2023-02-09 19:49:11'),
(19, 1, 'Admin', '2023-02-14 10:27:44'),
(20, 1, 'Student', '2023-02-14 10:28:27'),
(21, 5, 'Student', '2023-02-14 10:48:20'),
(22, 11, 'Tutor', '2023-02-14 10:49:58'),
(23, 1, 'Admin', '2023-02-14 10:51:35'),
(24, 2, 'Student', '2023-02-14 10:57:22'),
(25, 1, 'Admin', '2023-02-15 12:06:28'),
(26, 3, 'Student', '2023-02-15 12:07:35'),
(27, 1, 'Admin', '2023-02-15 12:18:45');

-- --------------------------------------------------------

--
-- Table structure for table `modules`
--

CREATE TABLE `modules` (
  `moduleCode` varchar(50) NOT NULL,
  `moduleName` varchar(100) NOT NULL,
  `moduleType` varchar(10) NOT NULL,
  `semester` int(11) NOT NULL,
  `courseField` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `modules`
--

INSERT INTO `modules` (`moduleCode`, `moduleName`, `moduleType`, `semester`, `courseField`) VALUES
('CHS', 'China since 1900', 'Optional', 6, 'V100'),
('CICWE', 'Culture, politics and identity in Cold War Europe, 1945–68', 'Optional', 5, 'V100'),
('DOH', 'Disciplines of history', 'Compulsory', 5, 'V100'),
('EARC', 'English architecture, 1660–1720', 'Compulsory', 6, 'V100'),
('EWH', 'European and world history', 'Compulsory', 1, 'V100'),
('HBI', 'History of the British Isles', 'Compulsory', 1, 'V100'),
('HM', 'Historical methods', 'Compulsory', 2, 'V100'),
('NCE', 'The Norman conquest of England', 'Compulsory', 5, 'V100'),
('NEJM', 'The Near East in the age of Justinian and Muhammad, c527–700', 'Compulsory', 1, 'V100'),
('PACIR', 'Politics, art and culture in the Italian Renaissance, Venice and Florence, c1475–1525', 'Compulsory', 6, 'V100'),
('RRAJC', 'Race, religion and resistance in the US, from Jim Crow to Civil Rights', 'Compulsory', 2, 'V100'),
('TFLSR', 'Terror and forced labour in Stalin’s Russia', 'Compulsory', 2, 'V100'),
('TOS', 'Theories of the state', 'Compulsory', 2, 'V100'),
('TSM', 'The Scientific Movement in the 17th century', 'Compulsory', 1, 'V100');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(255) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `courseEnrolled` varchar(10) NOT NULL,
  `dateEnrolled` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `firstName`, `lastName`, `dob`, `address`, `gender`, `courseEnrolled`, `dateEnrolled`) VALUES
(1, 'Dipen', 'Bantawa', '2000-11-26', 'Radhe Marg, Gyaneshwor', 'Male', 'BS004', '2023-02-06'),
(2, 'Ragoon', 'Rai', NULL, NULL, NULL, 'W100', '2023-02-09'),
(3, 'Dipen', 'Rai', '2000-11-26', 'Shreepur, Gaighat', 'Male', 'V100', '2023-02-09'),
(4, 'Niraj', 'Tamang', NULL, NULL, NULL, 'C700', '2023-02-09'),
(5, 'Test', 't', '2023-02-08', 'Hadigaun, kathmandu', 'Male', 'BS004', '2023-02-14');

-- --------------------------------------------------------

--
-- Table structure for table `studentcreds`
--

CREATE TABLE `studentcreds` (
  `id` int(255) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `studentcreds`
--

INSERT INTO `studentcreds` (`id`, `email`, `password`) VALUES
(1, 'dipen@gmail.com', 'Dipen@'),
(2, 'dipen99@gmail.com', 'Dipen@'),
(3, 'd.bantawa@gmail.com', 'Dipen@'),
(4, 'niraj@gmail.com', 'niraj@'),
(5, 'test@gmail.com', 'test@123');

-- --------------------------------------------------------

--
-- Table structure for table `student_enrollment`
--

CREATE TABLE `student_enrollment` (
  `enrollment_id` int(11) NOT NULL,
  `student_id` int(255) NOT NULL,
  `module_code` varchar(50) NOT NULL,
  `final_grade` float DEFAULT NULL,
  `enrollment_date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_enrollment`
--

INSERT INTO `student_enrollment` (`enrollment_id`, `student_id`, `module_code`, `final_grade`, `enrollment_date`) VALUES
(1, 2, 'CHS', 75, '2023-02-09'),
(6, 3, 'CHS', 90, '2023-02-09'),
(9, 3, 'HM', NULL, '2023-02-09'),
(10, 3, 'RRAJC', NULL, '2023-02-09'),
(11, 3, 'TFLSR', NULL, '2023-02-09');

-- --------------------------------------------------------

--
-- Table structure for table `student_year`
--

CREATE TABLE `student_year` (
  `studentId` int(255) NOT NULL,
  `semester` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_year`
--

INSERT INTO `student_year` (`studentId`, `semester`) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 1),
(5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tutor`
--

CREATE TABLE `tutor` (
  `id` int(255) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `date_joined` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tutor`
--

INSERT INTO `tutor` (`id`, `firstName`, `lastName`, `dob`, `address`, `gender`, `date_joined`) VALUES
(1, 'Dipen', 'Bantawa', NULL, NULL, NULL, '2023-02-06'),
(8, 'Ragoon', 'Bantawa', NULL, NULL, NULL, '2023-02-08'),
(11, 'Utsav', 'Shrestha', NULL, NULL, NULL, '2023-02-09');

-- --------------------------------------------------------

--
-- Table structure for table `tutorcreds`
--

CREATE TABLE `tutorcreds` (
  `id` int(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tutorcreds`
--

INSERT INTO `tutorcreds` (`id`, `email`, `password`) VALUES
(1, 'dipen99@gmail.com', 'Dipen@'),
(8, 'dipen@gmail.com', 'Dipen@'),
(11, 'utsav@gmail.com', 'utsav@');

-- --------------------------------------------------------

--
-- Table structure for table `tutor_enrollment`
--

CREATE TABLE `tutor_enrollment` (
  `enrollment_id` int(11) NOT NULL,
  `tutor_id` int(255) NOT NULL,
  `module_code` varchar(50) DEFAULT '',
  `enrollment_date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tutor_enrollment`
--

INSERT INTO `tutor_enrollment` (`enrollment_id`, `tutor_id`, `module_code`, `enrollment_date`) VALUES
(5, 11, 'CHS', '2023-02-09'),
(6, 11, 'CICWE', '2023-02-09'),
(7, 11, 'DOH', '2023-02-09'),
(8, 11, 'EARC', '2023-02-09'),
(9, 1, 'CHS', '2023-02-09'),
(10, 8, 'CHS', '2023-02-09'),
(11, 1, 'CICWE', '2023-02-14');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`courseCode`),
  ADD UNIQUE KEY `courseName` (`courseName`);

--
-- Indexes for table `login_activity`
--
ALTER TABLE `login_activity`
  ADD PRIMARY KEY (`Activity_ID`);

--
-- Indexes for table `modules`
--
ALTER TABLE `modules`
  ADD PRIMARY KEY (`moduleCode`),
  ADD UNIQUE KEY `moduleName` (`moduleName`),
  ADD KEY `module_fk` (`courseField`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `courseEnrolled_fk` (`courseEnrolled`);

--
-- Indexes for table `studentcreds`
--
ALTER TABLE `studentcreds`
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `student_enrollment`
--
ALTER TABLE `student_enrollment`
  ADD PRIMARY KEY (`enrollment_id`),
  ADD KEY `student_enrollment_ibfk_2` (`module_code`),
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `student_year`
--
ALTER TABLE `student_year`
  ADD UNIQUE KEY `studentId` (`studentId`);

--
-- Indexes for table `tutor`
--
ALTER TABLE `tutor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tutorcreds`
--
ALTER TABLE `tutorcreds`
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `tutor_enrollment`
--
ALTER TABLE `tutor_enrollment`
  ADD PRIMARY KEY (`enrollment_id`),
  ADD KEY `tutor_enrollment_ibfk_1` (`module_code`),
  ADD KEY `tutor_id` (`tutor_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `login_activity`
--
ALTER TABLE `login_activity`
  MODIFY `Activity_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `student_enrollment`
--
ALTER TABLE `student_enrollment`
  MODIFY `enrollment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tutor`
--
ALTER TABLE `tutor`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tutor_enrollment`
--
ALTER TABLE `tutor_enrollment`
  MODIFY `enrollment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `modules`
--
ALTER TABLE `modules`
  ADD CONSTRAINT `module_fk` FOREIGN KEY (`courseField`) REFERENCES `courses` (`courseCode`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `courseEnrolled_fk` FOREIGN KEY (`courseEnrolled`) REFERENCES `courses` (`courseCode`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `studentcreds`
--
ALTER TABLE `studentcreds`
  ADD CONSTRAINT `studentcreds_ibfk_1` FOREIGN KEY (`id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student_enrollment`
--
ALTER TABLE `student_enrollment`
  ADD CONSTRAINT `student_enrollment_ibfk_2` FOREIGN KEY (`module_code`) REFERENCES `modules` (`moduleCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_enrollment_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student_year`
--
ALTER TABLE `student_year`
  ADD CONSTRAINT `student_year_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tutorcreds`
--
ALTER TABLE `tutorcreds`
  ADD CONSTRAINT `tutorcreds_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tutor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tutor_enrollment`
--
ALTER TABLE `tutor_enrollment`
  ADD CONSTRAINT `tutor_enrollment_ibfk_1` FOREIGN KEY (`module_code`) REFERENCES `modules` (`moduleCode`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `tutor_enrollment_ibfk_5` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
