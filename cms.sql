-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 16, 2021 at 12:58 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
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
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `course_name`) VALUES
(1, 'BIT'),
(2, 'BBA'),
(3, 'BBS');

-- --------------------------------------------------------

--
-- Table structure for table `courses_cancelled`
--

CREATE TABLE `courses_cancelled` (
  `course_cncl_id` int(10) NOT NULL,
  `course_cncl_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `courses_cancelled`
--

INSERT INTO `courses_cancelled` (`course_cncl_id`, `course_cncl_name`) VALUES
(4, 'MCS'),
(5, 'MBIT'),
(6, 'MBBS');

-- --------------------------------------------------------

--
-- Table structure for table `instructors`
--

CREATE TABLE `instructors` (
  `instructor_id` int(11) NOT NULL,
  `instructor_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `instructors`
--

INSERT INTO `instructors` (`instructor_id`, `instructor_name`) VALUES
(1, 'Ram Kumar'),
(2, 'Shiva Rai'),
(3, 'Hari Bahadur'),
(4, 'Ramesh Acharya'),
(5, 'Mark Brown'),
(6, 'Amy Holmes'),
(7, 'Subash Malla'),
(8, 'Dev Poudel'),
(9, 'Anthony Martial'),
(10, 'Aaron Paul'),
(11, 'Walter Henderson'),
(12, 'Steve Rogers');

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE `marks` (
  `student_id` int(20) NOT NULL,
  `module_id` int(20) NOT NULL,
  `marks` int(20) NOT NULL,
  `remarks` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`student_id`, `module_id`, `marks`, `remarks`) VALUES
(1, 112, 95, 'Pass'),
(1, 115, 98, 'Pass'),
(3, 102, 95, 'Pass'),
(3, 109, 86, 'Pass'),
(3, 119, 95, 'Pass'),
(2, 102, 95, 'Pass'),
(2, 109, 96, 'Pass'),
(2, 119, 98, 'Pass'),
(4, 118, 86, 'Pass'),
(4, 123, 84, 'Pass'),
(4, 126, 48, 'Pass'),
(5, 124, 65, 'Pass'),
(5, 129, 96, 'Pass'),
(6, 110, 85, 'Fail'),
(6, 114, 75, 'Fail'),
(6, 117, 45, 'Fail'),
(6, 112, 28, 'Fail'),
(6, 115, 69, 'Fail'),
(13, 122, 95, 'Pass'),
(13, 128, 98, 'Pass'),
(7, 120, 95, 'Pass'),
(5, 124, 56, 'Pass'),
(5, 129, 72, 'Pass');

-- --------------------------------------------------------

--
-- Table structure for table `modules`
--

CREATE TABLE `modules` (
  `module_id` int(11) NOT NULL,
  `module_name` varchar(50) DEFAULT NULL,
  `course_id` int(10) NOT NULL,
  `level` int(5) NOT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  `choice` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `modules`
--

INSERT INTO `modules` (`module_id`, `module_name`, `course_id`, `level`, `instructor_id`, `choice`) VALUES
(101, 'Human Coumputer Interaction', 1, 4, 1, 0),
(102, 'Object Oriented Programming', 1, 6, 2, 0),
(103, 'Numerical Methods and Cuncurrency', 1, 5, 3, 0),
(104, 'Personal Development', 1, 6, 1, 1),
(106, 'Big Data', 1, 6, 3, 0),
(107, 'Computer Servers', 1, 6, 1, 1),
(108, 'Cloud Computing', 1, 6, 7, 0),
(109, 'Artificial Intelligence', 1, 6, 2, 1),
(110, 'Introductory Programming and Problem Solving', 1, 6, 4, 1),
(111, 'Complex System', 1, 6, 7, 1),
(112, 'High Performance Computing', 1, 6, 5, 1),
(113, 'Project-Based Learning', 1, 5, 7, 0),
(114, 'Fundamentals of Computing', 1, 5, 4, 0),
(115, 'Internet Software Architecture', 1, 5, 5, 0),
(116, 'Computational Mathematics', 1, 4, 3, 0),
(117, 'Database Management', 1, 4, 4, 0),
(118, 'FrontEnd Development', 1, 4, 6, 0),
(119, 'Concepts of Web Applications', 1, 4, 2, 0),
(120, 'Graphic Designing', 1, 4, 11, 0),
(121, 'Computer Networking', 1, 4, 10, 0),
(122, 'Cyber Security', 1, 4, 9, 0),
(123, 'Ethics of AI', 1, 5, 6, 0),
(124, 'Computer Hardware', 1, 5, 8, 0),
(125, 'Computer History', 1, 5, 12, 0),
(126, 'Penetration Testing', 1, 6, 6, 0),
(127, 'UI/UX Concepts and Design', 1, 6, 10, 1),
(128, 'Embedded Systems Programming', 1, 5, 9, 0),
(129, 'Quantum Computers', 1, 6, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `student_name` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `level` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `student_name`, `address`, `course_id`, `level`) VALUES
(1, 'Sushant Pokharel', 'Thimi', 1, 4),
(2, 'Apekshya Prasai', 'Chabahil', 1, 0),
(3, 'Hari Bahadur', 'Gorkha', 2, 5),
(4, 'Shyam Acharya', 'Naxal', 1, 6),
(5, 'Ally Salort', 'Bishalnagar', 2, 4),
(6, 'John Cena', 'California', 1, 5),
(7, 'Rahul Shrestha', 'Baneshwor', 1, 0),
(8, 'Rabi Sharma', 'Baglung', 1, 0),
(13, 'Nirav', 'Koteshwor', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `student_module`
--

CREATE TABLE `student_module` (
  `student_id` int(20) NOT NULL,
  `module1` int(20) NOT NULL,
  `module2` int(20) NOT NULL,
  `module3` int(20) NOT NULL,
  `module4` int(20) NOT NULL,
  `module5` int(20) NOT NULL,
  `module6` int(20) NOT NULL,
  `module7` int(20) NOT NULL,
  `module8` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_module`
--

INSERT INTO `student_module` (`student_id`, `module1`, `module2`, `module3`, `module4`, `module5`, `module6`, `module7`, `module8`) VALUES
(1, 101, 116, 117, 118, 119, 120, 121, 122),
(3, 103, 113, 114, 115, 123, 124, 125, 128),
(13, 101, 116, 117, 118, 119, 120, 121, 122),
(2, 103, 113, 114, 115, 123, 124, 125, 128),
(7, 102, 106, 108, 126, 109, 111, 112, 127),
(8, 102, 106, 108, 126, 107, 109, 111, 127);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `courses_cancelled`
--
ALTER TABLE `courses_cancelled`
  ADD PRIMARY KEY (`course_cncl_id`);

--
-- Indexes for table `instructors`
--
ALTER TABLE `instructors`
  ADD PRIMARY KEY (`instructor_id`);

--
-- Indexes for table `modules`
--
ALTER TABLE `modules`
  ADD PRIMARY KEY (`module_id`),
  ADD KEY `instructor_id` (`instructor_id`),
  ADD KEY `course_id` (`course_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `student_module`
--
ALTER TABLE `student_module`
  ADD KEY `student_id` (`student_id`),
  ADD KEY `module1` (`module1`),
  ADD KEY `module2` (`module2`),
  ADD KEY `module3` (`module3`),
  ADD KEY `module4` (`module4`),
  ADD KEY `module5` (`module5`),
  ADD KEY `module6` (`module6`),
  ADD KEY `module7` (`module7`),
  ADD KEY `module8` (`module8`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `modules`
--
ALTER TABLE `modules`
  ADD CONSTRAINT `modules_ibfk_1` FOREIGN KEY (`instructor_id`) REFERENCES `instructors` (`instructor_id`),
  ADD CONSTRAINT `modules_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`);

--
-- Constraints for table `student_module`
--
ALTER TABLE `student_module`
  ADD CONSTRAINT `student_module_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
  ADD CONSTRAINT `student_module_ibfk_10` FOREIGN KEY (`module8`) REFERENCES `modules` (`module_id`),
  ADD CONSTRAINT `student_module_ibfk_3` FOREIGN KEY (`module1`) REFERENCES `modules` (`module_id`),
  ADD CONSTRAINT `student_module_ibfk_4` FOREIGN KEY (`module2`) REFERENCES `modules` (`module_id`),
  ADD CONSTRAINT `student_module_ibfk_5` FOREIGN KEY (`module3`) REFERENCES `modules` (`module_id`),
  ADD CONSTRAINT `student_module_ibfk_6` FOREIGN KEY (`module4`) REFERENCES `modules` (`module_id`),
  ADD CONSTRAINT `student_module_ibfk_7` FOREIGN KEY (`module5`) REFERENCES `modules` (`module_id`),
  ADD CONSTRAINT `student_module_ibfk_8` FOREIGN KEY (`module6`) REFERENCES `modules` (`module_id`),
  ADD CONSTRAINT `student_module_ibfk_9` FOREIGN KEY (`module7`) REFERENCES `modules` (`module_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
