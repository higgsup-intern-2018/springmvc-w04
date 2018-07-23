-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 22, 2018 at 06:06 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ebshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE `author` (
  `id` bigint(20) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(225) NOT NULL,
  `year_of_birth` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `organization` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`id`, `firstname`, `lastname`, `year_of_birth`, `description`, `website`, `organization`) VALUES
(1, 'AI', 'Sweigart', 1970, 'Software developer. UI designer. Tech book author.', 'http://inventwithpython.com/', NULL),
(2, 'Ken', 'Kousen', 1968, 'Java Champion and Oracle Developer Champion', 'https://www.linkedin.com/in/kenkousen', '\"        \r\nKousen IT, Inc., No Fluff Just Stuff, Rensselaer at Hartford\"'),
(3, 'Anthony', ' Aragues', 1972, 'Data + Design + Security', 'https://www.linkedin.com/in/anthonyaragues/', 'Bluefin Payment Systems, SuddenDevelopment'),
(4, 'Craig', 'Walls', 1973, 'Software developer', 'https://www.linkedin.com/in/habuma', 'Pivotal'),
(5, 'Roy', 'Osherove', 1971, 'DevOps specialist', 'http://osherove.com/blog', NULL),
(6, 'Robert', 'Martin', 1952, 'Software Engineer', 'https://blog.cleancoder.com/', NULL),
(7, 'Robin ', 'Nixon', 1950, 'Technical writer and web developer', 'https://robinnixon.com', NULL),
(8, 'Sandro', 'Mancuso', 1979, 'Software Engineer', NULL, 'London Software Craftsmanship Community'),
(9, 'Nicholas', 'C. Zakas', 1978, 'Nicholas C. Zakas has been working on web applications since 2000, focusing on frontend development, and is known for writing and speaking about frontend best practices.', 'https://www.nczonline.net/', NULL),
(10, 'Mike', 'Cohn', 1962, 'Owner of Mountain Goat Software', 'https://www.mountaingoatsoftware.com/blog', 'Mountain Goat Software'),
(11, 'Matt', 'Neuburg', 1954, 'Matt Neuburg has a PhD in Classics and has taught at many universities and colleges. He has been programming computers since 1968. He has written applications for Mac OS X and iOS, is a former editor of MacTech Magazine, and is a long-standing contributin', 'https://www.linkedin.com/in/mattneuburg', ' freelancer'),
(12, 'Mario', 'Fusco', 1974, 'Principal Software Engineer', 'https://twitter.com/mariofusco', 'Jug Milano'),
(13, 'Jo', 'Guldi', 1984, 'Assistant Professor in the History of Britain and its Empire at Brown', 'https://www.joguldi.com/vita', 'Brown University'),
(14, 'Arlin', 'Crotts', 1980, 'Professor of Astronomy at Columbia University and has won numerous awards for his work. Having observed objects as distant as ten billion light years and as close as the Moon, he finds the problems of lunar science particularly intriguing.', 'www.astro.columbia.edu/~arlin/research.html', 'Columbia University'),
(15, 'Ralph', 'W. Fasold', 1976, 'Professor Emeritus and past Chair of the Department of Linguistics at Georgetown University. He is the author of four books and editor or co-editor of six others', 'http://www.cambridge.org/gb/academic/subjects/languages-linguistics/english-language-and-linguistics-general-interest/introduction-language-and-linguistics-2nd-edition?format=PB&isbn=9781107637993', 'Georgetown University'),
(16, 'Caitlin', 'Moran', 1995, 'An award-winning columnist and bestselling author of How to Be a Woman', 'https://www.penguin.co.uk/authors/caitlin-moran/1024791/', 'Freelancer'),
(17, 'Timothy', 'Donald Cook', 1960, 'Chief Executive Officer of Apple Inc.', 'https://en.wikipedia.org/wiki/Tim_Cook', 'Apple Inc'),
(18, 'Angela', 'Jean Ahrendts', 1960, 'American businesswoman and the Senior Vice President of Retail at Apple Inc', 'https://en.wikipedia.org/wiki/Angela_Ahrendts', 'Apple Inc'),
(19, 'Katherine', 'Leatherman Adams', 1964, 'General Counsel and Senior Vice President', 'https://www.apple.com/leadership/katherine-adams', 'Apple Inc'),
(20, 'Eddy', 'Cue', 1964, 'Senior Vice President Senior Vice President\r\nInternet software and services', 'https://www.apple.com/leadership/eddy-cue', 'Apple Inc');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `firstname`, `lastname`, `email`, `phone`, `address`) VALUES
(1, 'Anh', 'Pham', 'anhpth@gmail.com', '0987117234', 'Ha Noi'),
(2, 'Tu', 'Thieu', 'tutt@gmail.com', '0987127328', 'Thai Binh'),
(3, 'Thanh', 'Tong', 'thanhtong@gmail.com', '0983453948', 'Ha Noi'),
(4, 'Hiep', 'Tuan', 'tuanhiep@gmail.com', '0123874958', 'Ha Noi'),
(5, 'Thuy', 'Ngo', 'ngothuy@gmail.com', '01682233242', 'Thanh Hoa');

-- --------------------------------------------------------

--
-- Table structure for table `ebook`
--

CREATE TABLE `ebook` (
  `id` bigint(20) NOT NULL,
  `isbn` varchar(15) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `publisher_id` bigint(20) DEFAULT NULL,
  `publication_date` date NOT NULL,
  `pages` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) UNSIGNED NOT NULL,
  `deleted` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ebook`
--

INSERT INTO `ebook` (`id`, `isbn`, `title`, `description`, `author_id`, `publisher_id`, `publication_date`, `pages`, `price`, `quantity`, `deleted`) VALUES
(1, '1593278225', 'Cracking Codes with Python', 'Learn how to program in Python', 1, 1, '2018-05-02', 424, '19.33', 20, 0),
(2, '1593275994', 'Automate the Boring Stuff with Python', 'Learn how to use Python to write programs that do in minutes ', 1, 1, '2015-04-12', 504, '23.96', 30, 0),
(3, '1593278535', 'Coding with Minecraft', 'Step-by-step coding projects will show you how to write programs that automatically dig mines, collect materials, craft items, and build anything that you can imagine', 1, 1, '2018-06-13', 256, '26.95', 10, 0),
(4, '	149197317X', 'Modern Java Recipes', 'For developers comfortable with previous Java versions, this guide covers nearly all of Java SE 8, and includes a chapter focused on changes coming in Java 9', 2, 2, '2017-08-12', 322, '39.27', 25, 0),
(5, '1492031852', 'Visualizing Streaming Data', 'Author Anthony Aragues guides you through the concepts and tools you need to build visualizations for analyzing data as it arrives.', 3, 2, '2018-02-04', 200, '36.99', 37, 0),
(6, '1491978910', 'Learning PHP, MySQL & JavaScript, 5th Edition', 'Learning PHP, MySQL & JavaScript, 5th Edition', 7, 2, '2018-04-01', 832, '35.00', 45, 0),
(7, '596804849', 'Ubuntu: Up and Running', ' You\'ll learn how Ubuntu works, how to quickly configure and maintain Ubuntu 10.04, and how to use this unique operating system for networking, business, and home entertainment.', 7, 2, '2010-07-15', 464, '32.54', 40, 0),
(8, '1449327680', 'Maintainable JavaScript', 'With the JavaScript practices in this book - including code style, programming tips, and automation - you\'ll learn how to write maintainable code that other team members can easily understand, adapt, and extend.', 9, 2, '2012-09-15', 242, '32.66', 24, 0),
(9, '1593275404', 'The Principles of Object-Oriented JavaScript', '\nIn The Principles of Object-Oriented JavaScript, Nicholas C. Zakas thoroughly explores JavaScript\'s object-oriented nature, revealing the language\'s unique implementation of inheritance and other key characteristics.\n', 9, 1, '2014-08-20', 120, '19.96', 35, 0),
(10, '1491999225', 'Programming iOS 11', 'Through deep exploration and copious code examples, you\'ll learn how to create views, manipulate view controllers, and add features from iOS frameworks.', 11, 2, '2018-02-05', 1172, '62.13', 30, 0),
(11, '9781617291203', 'Spring in Action', 'a hands-on guide to the Spring \r Framework, updated for version 4', 4, 4, '2014-03-01', 624, '39.99', 20, 0),
(12, '9781617292545', 'Spring Boot in Action', 'A developer-focused guide to writing \r applications using Spring Boot', 4, 4, '2016-04-07', 264, '35.99', 45, 0),
(13, '1617290890', 'Clean Code', 'step by step from writing your first simple tests \rto developing robust test sets that are \r maintainable, readable, and trustworthy.', 5, 4, '2013-05-09', 292, '38.28', 35, 0),
(14, '9780132350884', 'Clean Code', 'This book is a must for any developer, software engineer, project manager, team lead, or systems analyst with an interest in producing better code.', 6, 3, '2009-09-12', 431, '42.17', 20, 0),
(15, '9780134494166', 'Clean Architecture', 'By applying universal rules of software architecture, you can dramatically improve developer productivity throughout the life of any software system', 6, 3, '2017-02-03', 432, '30.81', 25, 0),
(16, '9780137081073', 'The Clean Coder', 'This book is packed with practical advice–about everything from estimating and coding to refactoring and testing', 6, 3, '2011-02-13', 210, '16.88', 45, 0),
(17, '9780134052502', 'The Software Craftsman', 'If you want to develop software with pride and professionalism; love what you do and do it with excellence; and build a career with autonomy, mastery, and purpose.', 8, 3, '2014-04-17', 288, '32.20', 35, 0),
(18, '9780131479418', 'Agile Estimating and Planning', 'A view of planning that\'s balanced between theory and practice, and it is supported by enough concrete experiences to lend it credibility', 10, 3, '2005-04-04', 362, '39.80', 45, 0),
(19, '9781617291999', 'Java 8 in Action', 'A clearly written guide to the new features of Java 8', 12, 4, '2014-09-15', 424, '39.99', 30, 0),
(20, '9781617293566', 'Modern Java in Action', 'you\'ll build on your existing Java language skills with the newest features and techniques', 12, 4, '2018-09-18', 550, '43.99', 24, 0),
(21, '1617293865', 'JavaScript on Things', 'avaScript on Things introduces the exciting world of programming small electronics!', 6, 6, '2014-04-27', 448, '31.97', 16, 0),
(22, '9780672324536', 'Data Structures and Algorithms in Java', 'Besides clear and simple example programs, the author includes a workshop as a small demonstration program executable on a Web browser', 7, 5, '2002-05-18', 800, '72.08', 22, 0),
(23, '9780596009205', 'Head First Java', 'Head First Java combines puzzles, strong visuals, mysteries, and soul-searching interviews with famous Java objects to engage you in many different ways', 8, 6, '2005-08-22', 688, '44.31', 44, 0),
(24, '9780134685991', 'Effective Java', 'Updated techniques and best practices on classic topics, including objects, classes, methods, libraries, and generics', 9, 7, '2008-06-14', 416, '52.50', 19, 0),
(25, '9780984782857', 'Cracking the Coding Interview', 'help you through interview, teaching you what you need to know and enabling you to perform at your very best', 10, 8, '2015-07-01', 708, '53.69', 33, 0),
(26, '9781107432437', 'The History Manifesto', 'The History Manifesto is a call to arms to historians and everyone interested in the role of history in contemporary society', 13, 7, '2014-08-28', 175, '22.99', 26, 0),
(27, '9780521762243', 'The New Moon', 'How can exploring the Moon benefit development on Earth?', 13, 7, '2014-09-09', 552, '40.99', 45, 0),
(28, '9781107637993', 'An Introductuion to Language & Linguistics', 'This best-selling textbook addresses the full scope of language, from the traditional subjects of structural linguistics', 15, 9, '2014-10-14', 576, '42.99', 51, 0),
(29, '\r\n9780062433770', 'How to be Famous', 'The Sunday Times Number One bestseller about a young women making it in a world where men hold all the power', 16, 8, '2016-02-24', 312, '14.99', 19, 0),
(30, '9780091940744', 'How To Be a Woman', 'She wants women to stop seeing feminists as radical man-haters and to start seeing them as advocates for true equality', 16, 10, '2011-08-29', 328, '6.60', 62, 0),
(31, '1787121518', 'Machine Learning with Swift:Artificial Intelligence for IOS', 'This book will be your guide as you embark on an exciting journey in machine learning using the popular Swift language.', 11, 5, '2016-04-23', 378, '39.99', 55, 0),
(32, '321382838', 'Building Java Programs: A back to Basics Approach', 'This book will be your guide for Java language.', 2, 2, '2007-07-06', 896, '33.98', 40, 0),
(33, '9783319894904', 'Fundamentals of Java Programming', 'This textbook on Java programming teaches the fundamental skills for getting started in a command-line environmen', 3, 9, '2012-11-13', 515, '119.19', 72, 0),
(34, '9781788997454', 'Java Deep Learning Projects', 'you will have stepped up your expertise when it comes to deep learning in Java', 4, 6, '2013-03-07', 436, '49.99', 56, 0),
(35, '1484235932', 'Java EE 8 Recipes', 'provides you with effective and proven solutions that can be used to accomplish just about any task that you may encounter', 5, 7, '2016-12-25', 760, '59.99', 49, 0),
(36, '1593278055', 'Learn Java the Easy Way', 'Learn Java the Easy Way takes the chore out of learning Java with hands-on projects that will get you building real, functioning apps right away.', 9, 5, '2015-02-24', 312, '21.38', 56, 0),
(37, '1484231554', 'Learn Android Studio 3', 'Learn the latest and most productive tools in the Android tools ecosystem, ensuring quick Android app development and minimal effort on your part.', 12, 4, '2014-04-08', 260, '36.92', 11, 0),
(38, '1785285718', 'Building a RESTful Web Service with Sprin', 'This book takes you through the design of RESTful web services and leverages the Spring Framework to implement these services', 7, 1, '2015-12-06', 381, '29.99', 53, 0),
(39, '1509304525', 'Begin to Code with Python', 'it’s the first Python beginner’s guide that puts you in control of your own learning, and empowers you to build unique programs to solve problems', 1, 2, '2013-09-14', 528, '33.99', 26, 0),
(40, '1788398920', 'Predictive Analytics with TensorFlow', 'This book is for anyone who wants to build predictive models with the power of TensorFlow from scratch.', 6, 3, '2011-02-23', 558, '48.51', 33, 0),
(41, '1617293458', 'CSS in Depth', 'This instantly useful book is packed with creative examples and powerful best practices that will sharpen your technical skills and inspire your sense of design', 10, 10, '2016-01-15', 472, '37.45', 19, 0),
(42, '1430224150', 'Dive into Python 3', 'each chapter starts with a real, complete code sample, proceeds to pick it apart and explain the pieces', 11, 9, '2014-06-03', 360, '31.51', 62, 0),
(43, '1484228227', 'Python Machine Learning Case Studies', 'The book uses a hands-on case study-based approach to crack real-world applications to which machine learning concepts can be applied', 14, 6, '2012-12-21', 204, '40.81', 64, 0),
(44, '1784395412', 'Python Requests Essentials', 'This is book for python administrator or developer interested in interacting with web APIs', 15, 8, '2015-08-22', 422, '33.99', 93, 0),
(45, '150867387', 'A Smarter Way to Learn HTML & CSS', 'Research shows that you learn four times as effectively when you practice after you read. So each chapter is paired with free, interactive exercises–more than 1,000 of them in all', 8, 4, '2016-03-04', 322, '17.96', 85, 0),
(46, '1491906421', 'CSS Refactoring', 'This book is ideal for seasoned front-end developers cleaning up an existing project, as well as those starting a new project for the first time.', 16, 5, '2017-09-06', 360, '23.90', 77, 0),
(47, '1491999226', 'Programming iOS 11', 'this book provides a structured explanation of all essential real-world iOS app components.', 2, 7, '2013-05-18', 1172, '59.99', 20, 0),
(48, '1484226887', 'iOS Code Testing', 'This book uses a pragmatic approach to writing well-tested code and provides techniques that can be used to retrofit tests to legacy code bases', 13, 1, '2011-07-14', 440, '44.99', 46, 0),
(49, '159327601X', 'iOS Application Security', 'In this book, mobile security expert David Thiel reveals common iOS coding mistakes that create serious security problems and shows you how to find and fix them.', 5, 2, '2014-10-20', 296, '29.99', 35, 0),
(50, '149191100X', 'High Performance iOS Apps', 'You’ll learn how to design and optimize iOS apps that deliver a smooth experience even when the network is poor and memory is low.', 6, 3, '2016-04-20', 464, '36.91', 48, 0);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `created_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `customer_id`, `created_date`) VALUES
(1, 1, '2018-07-10'),
(2, 1, '2017-12-15'),
(3, 1, '2017-10-16'),
(4, 1, '2018-07-03'),
(5, 1, '2018-07-10'),
(6, 1, '2017-12-15'),
(7, 1, '2017-10-16'),
(8, 1, '2018-07-03'),
(9, 1, '2017-11-05'),
(10, 2, '2018-07-01'),
(13, 4, '2018-04-01'),
(14, 4, '2018-04-01'),
(15, 4, '2018-07-04'),
(16, 3, '2018-07-09'),
(17, 3, '2018-05-06'),
(18, 1, '2017-11-05'),
(22, 4, '2018-04-01'),
(24, 4, '2018-07-04'),
(25, 3, '2018-07-09'),
(26, 3, '2018-05-06'),
(27, 5, '2018-07-04'),
(28, 5, '2017-12-08'),
(29, 5, '2018-02-07'),
(30, 5, '2018-05-31'),
(31, 5, '2018-07-04'),
(32, 5, '2017-12-08'),
(33, 5, '2018-02-07'),
(34, 5, '2018-05-31');

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `ebook_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`id`, `order_id`, `ebook_id`, `quantity`) VALUES
(14, 14, 50, 2),
(15, 3, 4, 2),
(16, 5, 4, 4),
(17, 10, 32, 1),
(18, 32, 28, 30),
(19, 28, 3, 43),
(20, 5, 32, 4),
(21, 31, 43, 12),
(22, 27, 43, 23),
(23, 24, 34, 12),
(24, 7, 7, 21),
(25, 7, 48, 43),
(26, 27, 44, 12),
(27, 32, 18, 21),
(28, 22, 46, 9),
(29, 14, 50, 2),
(30, 3, 4, 2),
(31, 5, 4, 4),
(32, 10, 32, 1),
(33, 32, 28, 30),
(34, 28, 3, 43),
(35, 5, 32, 4),
(36, 31, 43, 12),
(37, 27, 43, 23),
(38, 24, 34, 12),
(39, 7, 7, 21),
(40, 7, 48, 43),
(41, 27, 44, 12),
(42, 32, 18, 21),
(43, 22, 46, 9);

-- --------------------------------------------------------

--
-- Table structure for table `publisher`
--

CREATE TABLE `publisher` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  `founder` varchar(255) DEFAULT NULL,
  `founded_year` int(11) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `publisher`
--

INSERT INTO `publisher` (`id`, `name`, `website`, `founder`, `founded_year`, `address`) VALUES
(1, 'No Starch Press', 'https://nostarch.com/', 'William Pollock', 1994, 'San Francisco, California, USA'),
(2, 'O\'Reilly Media', 'https://www.oreilly.com/', 'Tim O\'Reilly', 1978, 'Sebastopol, California, US'),
(3, 'Prentice Hall', 'http://prenticehall.com/', 'Charles Gerstenberg', 1913, 'New Jersey'),
(4, 'Manning Publications', 'https://www.manning.com/', 'Marjan Bace', 1990, 'New York'),
(5, 'Apress', 'https://www.apress.com/us', 'Gary Cornell and Dan Appleman', 1999, 'New York'),
(6, 'MIT Press\r\n', 'https://mitpress.mit.edu', 'James R. Killian, Jr.', 1932, 'Cambridge, Massachusetts'),
(7, 'Cambridge University Press', 'http://www.cambridge.org', 'King Henry VIII of England', 1534, 'Cambridge, England'),
(8, 'Penguin Books', 'www.penguin.com', 'Allen Lane, Richard Lane, John Lane', 1935, 'City of Westminster, London, England'),
(9, 'Routledge', 'https://www.routledge.com', 'George Routledge', 1835, 'Abingdon-on-Thames'),
(10, 'Elsevier', 'https://www.elsevier.com', 'RELX Group', 1880, 'Amsterdam, Netherlands');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indexes for table `ebook`
--
ALTER TABLE `ebook`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn_UNIQUE` (`isbn`),
  ADD KEY `publisher_id_idx` (`publisher_id`),
  ADD KEY `author_id_idx` (`author_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `custormer_id_idx` (`customer_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id_idx` (`order_id`),
  ADD KEY `ebook_id_idx` (`ebook_id`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `author`
--
ALTER TABLE `author`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `ebook`
--
ALTER TABLE `ebook`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `publisher`
--
ALTER TABLE `publisher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ebook`
--
ALTER TABLE `ebook`
  ADD CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  ADD CONSTRAINT `publisher_id` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `custormer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `ebook_id` FOREIGN KEY (`ebook_id`) REFERENCES `ebook` (`id`),
  ADD CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
