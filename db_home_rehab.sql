-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 14, 2015 at 12:33 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_home_rehab`
--

-- --------------------------------------------------------

--
-- Table structure for table `after_exer`
--

CREATE TABLE IF NOT EXISTS `after_exer` (
  `pid` char(10) NOT NULL,
  `discomfort` tinyint(4) NOT NULL,
  `dizziness` tinyint(4) NOT NULL,
  `RPE` int(11) NOT NULL,
  `other_uncomfortable` text NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `after_exer`
--

INSERT INTO `after_exer` (`pid`, `discomfort`, `dizziness`, `RPE`, `other_uncomfortable`, `time`) VALUES
('Jerry', 0, 0, 10, '2 2 2', '11:09:32'),
('Jerry', 0, 1, 17, 'Very hurt. Feel dizzy. Faint.', '11:47:43'),
('Jerry', 1, 1, 12, 'I am JiePeng. I feel dizzy. Yes', '12:09:06'),
('Jerry', 1, 0, 17, '  ', '14:06:21'),
('Jerry', 0, 0, 8, '  today', '17:07:50'),
('Jerry', 0, 1, 7, '  ', '17:25:21');

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE IF NOT EXISTS `answers` (
  `pid` char(10) NOT NULL,
  `qid` int(11) NOT NULL,
  `did` char(10) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`pid`, `qid`, `did`, `content`, `time`) VALUES
('John Smith', 1, 'Adam Bob', 'Do not worry', '2015-03-03 10:21:11'),
('John Smith', 4, 'Adam Bob', 'Do not worry', '2015-03-03 10:21:11'),
('Johny Walk', 2, 'vishal', 'So what should I do? hhhaaahhh !!!!', '0000-00-00 00:00:00'),
('John Smith', 11, 'vishal', 'hello', '0000-00-00 00:00:00'),
('Vishal Cha', 3, 'vishal', 'Be happy', '0000-00-00 00:00:00'),
('John Smith', 29, 'vishal', 'No at all', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `assessment`
--

CREATE TABLE IF NOT EXISTS `assessment` (
  `pid` char(10) NOT NULL,
  `selected_topic_id` varchar(20) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assessment`
--

INSERT INTO `assessment` (`pid`, `selected_topic_id`, `date`) VALUES
('John Smith', '1,3,4', '2015-03-01');

-- --------------------------------------------------------

--
-- Table structure for table `beforeexer`
--

CREATE TABLE IF NOT EXISTS `beforeexer` (
  `pid` char(10) NOT NULL,
  `discomfort` tinyint(1) NOT NULL,
  `dizziness` tinyint(1) NOT NULL,
  `largemeal` tinyint(1) NOT NULL,
  `upset` tinyint(1) NOT NULL,
  `cold` tinyint(1) NOT NULL,
  `BP` int(11) NOT NULL,
  `take_medicines` tinyint(1) NOT NULL,
  `other_uncomfortable` text NOT NULL,
  `time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `beforeexer`
--

INSERT INTO `beforeexer` (`pid`, `discomfort`, `dizziness`, `largemeal`, `upset`, `cold`, `BP`, `take_medicines`, `other_uncomfortable`, `time`) VALUES
('Jerry', 0, 0, 0, 0, 0, 72, 0, 'hello', '11:20:39'),
('Jerry', 0, 0, 0, 0, 0, 152, 0, 'morning', '11:38:06'),
('Jerry', 0, 1, 0, 1, 0, 53, 0, '', '11:41:46'),
('Jerry', 1, 1, 0, 0, 1, 152, 1, 'stomachache', '11:46:43'),
('Jerry', 1, 0, 1, 0, 1, 135, 1, 'pj', '12:07:47'),
('Jerry', 1, 0, 1, 0, 1, 139, 1, 'pp', '14:06:05'),
('Jerry', 0, 0, 0, 0, 0, 100, 0, '', '15:46:08'),
('Jerry', 0, 1, 0, 1, 0, 110, 1, 'no', '17:06:48'),
('Jerry', 0, 0, 0, 1, 0, 121, 1, 'no', '17:21:24'),
('Jerry', 0, 0, 1, 0, 0, 87, 0, 'no', '14:11:32');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE IF NOT EXISTS `prescription` (
  `pid` char(10) NOT NULL,
  `did` char(10) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `frequency` int(11) NOT NULL,
  `targetHR` int(11) NOT NULL,
  `RPE` int(11) NOT NULL,
  `MET` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `Calorie` int(11) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`pid`, `did`, `subject`, `frequency`, `targetHR`, `RPE`, `MET`, `duration`, `Calorie`, `Date`) VALUES
('John Smtih', 'Adam Bob', 'Prescription for the week Mar 20th', 3, 100, 12, 5, 50, 5000, '2015-03-01'),
('Well Joe', 'Ann', 'Prescription for the week Mar 27th', 4, 110, 12, 5, 60, 5500, '2015-03-17');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
  `qid` int(11) NOT NULL,
  `pid` char(100) NOT NULL,
  `sub` varchar(200) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `attachment` varchar(200) NOT NULL,
  `time` datetime NOT NULL,
  `ifans` varchar(3) NOT NULL DEFAULT 'No'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`qid`, `pid`, `sub`, `description`, `attachment`, `time`, `ifans`) VALUES
(1, 'John Smith', '', 'I am feeling not comfortable after excercise', '', '2015-03-02 10:22:37', 'Yes'),
(2, 'Johny Walk', '', 'I''m having severe headache', '', '2015-04-06 07:06:19', 'Yes'),
(3, 'Vishal Cha', '', 'I''m still not able to solve problems', '', '2015-04-21 04:12:12', 'Yes'),
(4, 'Vishal Cha', '', 'I''m still not able to solve problems', '', '2015-04-21 04:12:12', 'Yes'),
(62, 'John Smith', 'Subject:pj', 'pj', '', '2015-04-10 22:32:12', 'No'),
(11, 'John Smith', 'Subject:pj', 'pj', '', '2015-04-10 22:32:48', 'Yes'),
(35, 'John Smith', 'Subject:pj', 'Basda', '', '2015-04-11 12:13:23', 'No'),
(60, 'John Smith', 'Subject:qqqqqqqqqqqqqqqqqqq', 'qqqqqqqqq', '', '2015-04-11 12:17:34', 'No'),
(16, 'John Smith', 'Subject:q', 'q', '', '2015-04-11 12:18:06', 'No'),
(56, 'John Smith', 'Subject:q q', 'q q', '', '2015-04-11 12:18:43', 'No'),
(89, 'John Smith', 'Subject:pre', 'good day', '', '2015-04-11 17:10:32', 'No'),
(29, 'John Smith', 'Subject:lamb', 'Can I eat one?How many can I eat?', '', '2015-04-12 17:28:49', 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `regular_report`
--

CREATE TABLE IF NOT EXISTS `regular_report` (
  `pid` char(10) NOT NULL,
  `resting_BP` int(11) NOT NULL,
  `medication_use` tinyint(1) NOT NULL,
  `cigarette_amount` int(11) NOT NULL COMMENT '(packages)',
  `body_weight` int(11) NOT NULL,
  `time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `regular_report`
--

INSERT INTO `regular_report` (`pid`, `resting_BP`, `medication_use`, `cigarette_amount`, `body_weight`, `time`) VALUES
('Jerry', 0, 0, 3, 66, '00:00:00'),
('Jerry', 0, 0, 0, 50, '23:54:27'),
('Jerry', 100, 0, 15, 57, '23:55:39'),
('Jerry', 155, 0, 6, 90, '00:14:46'),
('Jerry', 160, 1, 8, 202, '12:11:00'),
('Jerry', 95, 1, 2, 73, '14:06:36'),
('Jerry', 80, 1, 8, 95, '17:09:13'),
('Jerry', 125, 1, 0, 50, '17:18:36');

-- --------------------------------------------------------

--
-- Table structure for table `tb_doc_login`
--

CREATE TABLE IF NOT EXISTS `tb_doc_login` (
  `did` char(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `d_email` varchar(40) NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_doc_login`
--

INSERT INTO `tb_doc_login` (`did`, `password`, `d_email`) VALUES
('vishal', 'vishal', 'v3chaudh@uwaterloo.ca');

-- --------------------------------------------------------

--
-- Table structure for table `tb_doc_master`
--

CREATE TABLE IF NOT EXISTS `tb_doc_master` (
  `did` char(10) NOT NULL,
  `d_name` char(10) NOT NULL,
  `d_email` varchar(20) NOT NULL,
  `d_phone` int(11) NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_doc_pat_map`
--

CREATE TABLE IF NOT EXISTS `tb_doc_pat_map` (
  `pid` char(10) NOT NULL,
  `did` char(10) NOT NULL,
  PRIMARY KEY (`pid`,`did`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `td_pat_master`
--

CREATE TABLE IF NOT EXISTS `td_pat_master` (
  `pid` char(10) NOT NULL,
  `p_name` char(20) NOT NULL,
  `p_email` varchar(20) NOT NULL,
  `p_phone` int(11) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
