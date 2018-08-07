/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : ebshop

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 07/08/2018 11:46:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_name` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `year_of_birth` int(11) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `website` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organization` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES (1, 'AI', 'Sweigart', 1970, 'Software developer. UI designer. Tech book author.', 'http://inventwithpython.com/', NULL);
INSERT INTO `author` VALUES (2, 'Ken', 'Kousen', 1968, 'Java Champion and Oracle Developer Champion', 'https://www.linkedin.com/in/kenkousen', '\"        \r\nKousen IT, Inc., No Fluff Just Stuff, Rensselaer at Hartford\"');
INSERT INTO `author` VALUES (3, 'Anthony', ' Aragues', 1972, 'Data + Design + Security', 'https://www.linkedin.com/in/anthonyaragues/', 'Bluefin Payment Systems, SuddenDevelopment');
INSERT INTO `author` VALUES (4, 'Craig', 'Walls', 1973, 'Software developer', 'https://www.linkedin.com/in/habuma', 'Pivotal');
INSERT INTO `author` VALUES (5, 'Anna', 'Le', 1996, 'Test update for author 1', 'www.test1.com', 'free');
INSERT INTO `author` VALUES (6, 'Robert', 'Martin', 1952, 'Software Engineer', 'https://blog.cleancoder.com/', NULL);
INSERT INTO `author` VALUES (7, 'Robin ', 'Nixon', 1950, 'Technical writer and web developer', 'https://robinnixon.com', NULL);
INSERT INTO `author` VALUES (8, 'Anna', 'Le', 1996, 'Test update for author 1', 'www.test1.com', 'free');
INSERT INTO `author` VALUES (9, 'Nicholas', 'C. Zakas', 1978, 'Nicholas C. Zakas has been working on web applications since 2000, focusing on frontend development, and is known for writing and speaking about frontend best practices.', 'https://www.nczonline.net/', NULL);
INSERT INTO `author` VALUES (10, 'HaiAnh', 'Pham', 1996, 'Test update for author 1', 'www.test1.com', 'free');
INSERT INTO `author` VALUES (11, 'Matt', 'Neuburg', 1954, 'Matt Neuburg has a PhD in Classics and has taught at many universities and colleges. He has been programming computers since 1968. He has written applications for Mac OS X and iOS, is a former editor of MacTech Magazine, and is a long-standing contributin', 'https://www.linkedin.com/in/mattneuburg', ' freelancer');
INSERT INTO `author` VALUES (12, 'Mario', 'Fusco', 1974, 'Principal Software Engineer', 'https://twitter.com/mariofusco', 'Jug Milano');
INSERT INTO `author` VALUES (13, 'Jo', 'Guldi', 1984, 'Assistant Professor in the History of Britain and its Empire at Brown', 'https://www.joguldi.com/vita', 'Brown University');
INSERT INTO `author` VALUES (14, 'Arlin', 'Crotts', 1980, 'Professor of Astronomy at Columbia University and has won numerous awards for his work. Having observed objects as distant as ten billion light years and as close as the Moon, he finds the problems of lunar science particularly intriguing.', 'www.astro.columbia.edu/~arlin/research.html', 'Columbia University');
INSERT INTO `author` VALUES (15, 'Ralph', 'W. Fasold', 1976, 'Professor Emeritus and past Chair of the Department of Linguistics at Georgetown University. He is the author of four books and editor or co-editor of six others', 'http://www.cambridge.org/gb/academic/subjects/languages-linguistics/english-language-and-linguistics-general-interest/introduction-language-and-linguistics-2nd-edition?format=PB&isbn=9781107637993', 'Georgetown University');
INSERT INTO `author` VALUES (16, 'Caitlin', 'Moran', 1995, 'An award-winning columnist and bestselling author of How to Be a Woman', 'https://www.penguin.co.uk/authors/caitlin-moran/1024791/', 'Freelancer');
INSERT INTO `author` VALUES (17, 'Timothy', 'Donald Cook', 1960, 'Chief Executive Officer of Apple Inc.', 'https://en.wikipedia.org/wiki/Tim_Cook', 'Apple Inc');
INSERT INTO `author` VALUES (18, 'Angela', 'Jean Ahrendts', 1960, 'American businesswoman and the Senior Vice President of Retail at Apple Inc', 'https://en.wikipedia.org/wiki/Angela_Ahrendts', 'Apple Inc');
INSERT INTO `author` VALUES (19, 'Katherine', 'Leatherman Adams', 1964, 'General Counsel and Senior Vice President', 'https://www.apple.com/leadership/katherine-adams', 'Apple Inc');
INSERT INTO `author` VALUES (28, 'Tu', 'Thieu', 1994, 'Nothing', 'www', 'Higgsup');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email_UNIQUE`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Anh', 'Pham', 'anhpth@gmail.com', '0987117234', 'Ha Noi');
INSERT INTO `customer` VALUES (2, 'Tu', 'Thieu', 'tutt@gmail.com', '0987127328', 'Thai Binh');
INSERT INTO `customer` VALUES (3, 'Thanh', 'Tong', 'thanhtong@gmail.com', '0983453948', 'Ha Noi');
INSERT INTO `customer` VALUES (4, 'Hiep', 'Tuan', 'tuanhiep@gmail.com', '0123874958', 'Ha Noi');
INSERT INTO `customer` VALUES (5, 'Thuy', 'Ngo', 'ngothuy@gmail.com', '01682233242', 'Thanh Hoa');
INSERT INTO `customer` VALUES (6, 'Titus', 'Thieu', 'tu.thieuthanh@gmail.com', '0983652369', 'HN');
INSERT INTO `customer` VALUES (7, 'Ti', 'Thieu', 'thieuthanh@gmail.com', '0983652369', 'HN');

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author_id` bigint(20) NULL DEFAULT NULL,
  `publisher_id` bigint(20) NULL DEFAULT NULL,
  `publication_date` date NOT NULL,
  `pages` int(11) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `quantity` int(11) UNSIGNED NOT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `isbn_UNIQUE`(`isbn`) USING BTREE,
  INDEX `publisher_id_idx`(`publisher_id`) USING BTREE,
  INDEX `author_id_idx`(`author_id`) USING BTREE,
  CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `publisher_id` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ebook
-- ----------------------------
INSERT INTO `ebook` VALUES (1, '1593278225', 'Cracking Codes with Python', 'Learn how to program in Python', 1, 1, '2018-05-02', 424, 19.33, 11, 0);
INSERT INTO `ebook` VALUES (2, '1593275994', 'Automate the Boring Stuff with Python', 'Learn how to use Python to write programs that do in minutes ', 1, 1, '2015-04-12', 504, 23.96, 8, 0);
INSERT INTO `ebook` VALUES (3, '1593278535', 'Coding with Minecraft', 'Step-by-step coding projects will show you how to write programs that automatically dig mines, collect materials, craft items, and build anything that you can imagine', 1, 1, '2018-06-13', 256, 26.95, 9, 0);
INSERT INTO `ebook` VALUES (4, '	149197317X', 'Modern Java Recipes', 'For developers comfortable with previous Java versions, this guide covers nearly all of Java SE 8, and includes a chapter focused on changes coming in Java 9', 2, 2, '2017-08-12', 322, 39.27, 25, 0);
INSERT INTO `ebook` VALUES (5, '14919110078X', 'Performance iOS Apps', 'You \'ll learn how to design and optimize iOS apps that deliver a smooth experience even when the network is poor and memory is low.', 4, 8, '1970-01-18', 464, 39.61, 3, 0);
INSERT INTO `ebook` VALUES (6, '1491978910', 'Learning PHP, MySQL & JavaScript, 5th Edition', 'Learning PHP, MySQL & JavaScript, 5th Edition', 7, 2, '2018-04-01', 832, 35.00, 45, 0);
INSERT INTO `ebook` VALUES (7, '596804849', 'Ubuntu: Up and Running', ' You\'ll learn how Ubuntu works, how to quickly configure and maintain Ubuntu 10.04, and how to use this unique operating system for networking, business, and home entertainment.', 7, 2, '2010-07-15', 464, 32.54, 40, 0);
INSERT INTO `ebook` VALUES (8, '1449327680', 'Maintainable JavaScript', 'With the JavaScript practices in this book - including code style, programming tips, and automation - you\'ll learn how to write maintainable code that other team members can easily understand, adapt, and extend.', 9, 2, '2012-09-15', 242, 32.66, 24, 0);
INSERT INTO `ebook` VALUES (9, '1593275404', 'The Principles of Object-Oriented JavaScript', '\nIn The Principles of Object-Oriented JavaScript, Nicholas C. Zakas thoroughly explores JavaScript\'s object-oriented nature, revealing the language\'s unique implementation of inheritance and other key characteristics.\n', 9, 1, '2014-08-20', 120, 19.96, 35, 0);
INSERT INTO `ebook` VALUES (10, '123456789X', 'test2', 'this is test 2', 1, 4, '2018-07-24', 254, 5.44, 6, 1);
INSERT INTO `ebook` VALUES (11, '9781617291203', 'Spring in Action', 'a hands-on guide to the Spring \r Framework, updated for version 4', 4, 4, '2014-03-01', 624, 39.99, 20, 0);
INSERT INTO `ebook` VALUES (12, '9781617292545', 'Spring Boot in Action', 'A developer-focused guide to writing \r applications using Spring Boot', 4, 4, '2016-04-07', 264, 35.99, 45, 0);
INSERT INTO `ebook` VALUES (13, '1617290890', 'Clean Code', 'step by step from writing your first simple tests \rto developing robust test sets that are \r maintainable, readable, and trustworthy.', 5, 4, '2013-05-09', 292, 38.28, 35, 0);
INSERT INTO `ebook` VALUES (14, '9780132350884', 'Clean Code', 'This book is a must for any developer, software engineer, project manager, team lead, or systems analyst with an interest in producing better code.', 6, 3, '2009-09-12', 431, 42.17, 20, 0);
INSERT INTO `ebook` VALUES (15, '9780134494166', 'Clean Architecture', 'By applying universal rules of software architecture, you can dramatically improve developer productivity throughout the life of any software system', 6, 3, '2017-02-03', 432, 30.81, 25, 0);
INSERT INTO `ebook` VALUES (16, '9780137081073', 'The Clean Coder', 'This book is packed with practical advice–about everything from estimating and coding to refactoring and testing', 6, 3, '2011-02-13', 210, 16.88, 45, 0);
INSERT INTO `ebook` VALUES (17, '9780134052502', 'The Software Craftsman', 'If you want to develop software with pride and professionalism; love what you do and do it with excellence; and build a career with autonomy, mastery, and purpose.', 8, 3, '2014-04-17', 288, 32.20, 35, 0);
INSERT INTO `ebook` VALUES (18, '9780131479418', 'Agile Estimating and Planning', 'A view of planning that\'s balanced between theory and practice, and it is supported by enough concrete experiences to lend it credibility', 10, 3, '2005-04-04', 362, 39.80, 45, 0);
INSERT INTO `ebook` VALUES (19, '9781617291999', 'Java 8 in Action', 'A clearly written guide to the new features of Java 8', 12, 4, '2014-09-15', 424, 39.99, 30, 0);
INSERT INTO `ebook` VALUES (20, '9781617293566', 'Modern Java in Action', 'you\'ll build on your existing Java language skills with the newest features and techniques', 12, 4, '2018-09-18', 550, 43.99, 24, 0);
INSERT INTO `ebook` VALUES (21, '1617293865', 'JavaScript on Things', 'avaScript on Things introduces the exciting world of programming small electronics!', 6, 6, '2014-04-27', 448, 31.97, 16, 0);
INSERT INTO `ebook` VALUES (22, '9780672324536', 'Data Structures and Algorithms in Java', 'Besides clear and simple example programs, the author includes a workshop as a small demonstration program executable on a Web browser', 7, 5, '2002-05-18', 800, 72.08, 22, 1);
INSERT INTO `ebook` VALUES (23, '9780596009205', 'Head First Java', 'Head First Java combines puzzles, strong visuals, mysteries, and soul-searching interviews with famous Java objects to engage you in many different ways', 8, 6, '2005-08-22', 688, 44.31, 44, 1);
INSERT INTO `ebook` VALUES (24, '9780134685991', 'Effective Java', 'Updated techniques and best practices on classic topics, including objects, classes, methods, libraries, and generics', 9, 7, '2008-06-14', 416, 52.50, 19, 1);
INSERT INTO `ebook` VALUES (25, '9780984782857', 'Cracking the Coding Interview', 'help you through interview, teaching you what you need to know and enabling you to perform at your very best', 10, 8, '2015-07-01', 708, 53.69, 33, 0);
INSERT INTO `ebook` VALUES (26, '9781107432437', 'The History Manifesto', 'The History Manifesto is a call to arms to historians and everyone interested in the role of history in contemporary society', 13, 7, '2014-08-28', 175, 22.99, 26, 0);
INSERT INTO `ebook` VALUES (27, '9780521762243', 'The New Moon', 'How can exploring the Moon benefit development on Earth?', 13, 7, '2014-09-09', 552, 40.99, 45, 0);
INSERT INTO `ebook` VALUES (28, '9781107637993', 'An Introductuion to Language & Linguistics', 'This best-selling textbook addresses the full scope of language, from the traditional subjects of structural linguistics', 15, 9, '2014-10-14', 576, 42.99, 51, 0);
INSERT INTO `ebook` VALUES (29, '\r\n9780062433770', 'How to be Famous', 'The Sunday Times Number One bestseller about a young women making it in a world where men hold all the power', 16, 8, '2016-02-24', 312, 14.99, 19, 0);
INSERT INTO `ebook` VALUES (30, '9780091940744', 'How To Be a Woman', 'She wants women to stop seeing feminists as radical man-haters and to start seeing them as advocates for true equality', 16, 10, '2011-08-29', 328, 6.60, 62, 0);
INSERT INTO `ebook` VALUES (31, '1787121518', 'Machine Learning with Swift:Artificial Intelligence for IOS', 'This book will be your guide as you embark on an exciting journey in machine learning using the popular Swift language.', 11, 5, '2016-04-23', 378, 39.99, 55, 0);
INSERT INTO `ebook` VALUES (32, '321382838', 'Building Java Programs: A back to Basics Approach', 'This book will be your guide for Java language.', 2, 2, '2007-07-06', 896, 33.98, 40, 0);
INSERT INTO `ebook` VALUES (33, '9783319894904', 'Fundamentals of Java Programming', 'This textbook on Java programming teaches the fundamental skills for getting started in a command-line environmen', 3, 9, '2012-11-13', 515, 119.19, 72, 0);
INSERT INTO `ebook` VALUES (34, '9781788997454', 'Java Deep Learning Projects', 'you will have stepped up your expertise when it comes to deep learning in Java', 4, 6, '2013-03-07', 436, 49.99, 56, 0);
INSERT INTO `ebook` VALUES (35, '1484235932', 'Java EE 8 Recipes', 'provides you with effective and proven solutions that can be used to accomplish just about any task that you may encounter', 5, 7, '2016-12-25', 760, 59.99, 49, 0);
INSERT INTO `ebook` VALUES (36, '1593278055', 'Learn Java the Easy Way', 'Learn Java the Easy Way takes the chore out of learning Java with hands-on projects that will get you building real, functioning apps right away.', 9, 5, '2015-02-24', 312, 21.38, 56, 0);
INSERT INTO `ebook` VALUES (37, '1484231554', 'Learn Android Studio 3', 'Learn the latest and most productive tools in the Android tools ecosystem, ensuring quick Android app development and minimal effort on your part.', 12, 4, '2014-04-08', 260, 36.92, 11, 0);
INSERT INTO `ebook` VALUES (38, '1785285718', 'Building a RESTful Web Service with Sprin', 'This book takes you through the design of RESTful web services and leverages the Spring Framework to implement these services', 7, 1, '2015-12-06', 381, 29.99, 53, 0);
INSERT INTO `ebook` VALUES (39, '1509304525', 'Begin to Code with Python', 'it’s the first Python beginner’s guide that puts you in control of your own learning, and empowers you to build unique programs to solve problems', 1, 2, '2013-09-14', 528, 33.99, 26, 0);
INSERT INTO `ebook` VALUES (40, '1788398920', 'Predictive Analytics with TensorFlow', 'This book is for anyone who wants to build predictive models with the power of TensorFlow from scratch.', 6, 3, '2011-02-23', 558, 48.51, 33, 0);
INSERT INTO `ebook` VALUES (41, '1617293458', 'CSS in Depth', 'This instantly useful book is packed with creative examples and powerful best practices that will sharpen your technical skills and inspire your sense of design', 10, 10, '2016-01-15', 472, 37.45, 19, 0);
INSERT INTO `ebook` VALUES (42, '1430224150', 'Dive into Python 3', 'each chapter starts with a real, complete code sample, proceeds to pick it apart and explain the pieces', 11, 9, '2014-06-03', 360, 31.51, 62, 0);
INSERT INTO `ebook` VALUES (43, '1484228227', 'Python Machine Learning Case Studies', 'The book uses a hands-on case study-based approach to crack real-world applications to which machine learning concepts can be applied', 14, 6, '2012-12-21', 204, 40.81, 64, 0);
INSERT INTO `ebook` VALUES (44, '1784395412', 'Python Requests Essentials', 'This is book for python administrator or developer interested in interacting with web APIs', 15, 8, '2015-08-22', 422, 33.99, 93, 0);
INSERT INTO `ebook` VALUES (45, '150867387', 'A Smarter Way to Learn HTML & CSS', 'Research shows that you learn four times as effectively when you practice after you read. So each chapter is paired with free, interactive exercises–more than 1,000 of them in all', 8, 4, '2016-03-04', 322, 17.96, 85, 0);
INSERT INTO `ebook` VALUES (46, '1491906421', 'CSS Refactoring', 'This book is ideal for seasoned front-end developers cleaning up an existing project, as well as those starting a new project for the first time.', 16, 5, '2017-09-06', 360, 23.90, 77, 0);
INSERT INTO `ebook` VALUES (47, '1491999226', 'Programming iOS 11', 'this book provides a structured explanation of all essential real-world iOS app components.', 2, 7, '2013-05-18', 1172, 59.99, 20, 0);
INSERT INTO `ebook` VALUES (48, '1484226887', 'NA Book', '.Very NA guy', 5, 9, '1970-01-18', 352, 39.61, 48, 0);
INSERT INTO `ebook` VALUES (49, '159327601X', 'iOS Application Security', 'In this book, mobile security expert David Thiel reveals common iOS coding mistakes that create serious security problems and shows you how to find and fix them.', 5, 2, '2014-10-20', 296, 29.99, 35, 0);
INSERT INTO `ebook` VALUES (50, '1484226889', 'NA Book', '.Very NA guy', 6, 8, '1970-01-18', 352, 39.61, 2, 0);
INSERT INTO `ebook` VALUES (51, '15312563231242', 'NA Book', '.Very NA guy', 7, 3, '1970-01-18', 352, 39.61, 2, 0);

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orders_id` bigint(20) NULL DEFAULT NULL,
  `ebook_id` bigint(20) NULL DEFAULT NULL,
  `quantity` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UC_DETAIL`(`orders_id`, `ebook_id`) USING BTREE,
  INDEX `order_id_idx`(`orders_id`) USING BTREE,
  INDEX `ebook_id_idx`(`ebook_id`) USING BTREE,
  CONSTRAINT `ebook_id` FOREIGN KEY (`ebook_id`) REFERENCES `ebook` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_id` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES (50, 1, 50, 12);
INSERT INTO `order_details` VALUES (51, 10, 4, 21);
INSERT INTO `order_details` VALUES (52, 2, 50, 12);
INSERT INTO `order_details` VALUES (53, 2, 29, 32);
INSERT INTO `order_details` VALUES (54, 2, 23, 12);
INSERT INTO `order_details` VALUES (55, 3, 49, 43);
INSERT INTO `order_details` VALUES (56, 3, 47, 3);
INSERT INTO `order_details` VALUES (57, 4, 9, 4);
INSERT INTO `order_details` VALUES (58, 4, 1, 34);
INSERT INTO `order_details` VALUES (59, 4, 7, 43);
INSERT INTO `order_details` VALUES (60, 4, 14, 5);
INSERT INTO `order_details` VALUES (61, 5, 46, 7);
INSERT INTO `order_details` VALUES (62, 5, 37, 8);
INSERT INTO `order_details` VALUES (63, 6, 46, 9);
INSERT INTO `order_details` VALUES (64, 7, 7, 23);
INSERT INTO `order_details` VALUES (65, 8, 8, 14);
INSERT INTO `order_details` VALUES (66, 9, 32, 5);
INSERT INTO `order_details` VALUES (67, 9, 49, 34);
INSERT INTO `order_details` VALUES (68, 9, 42, 7);
INSERT INTO `order_details` VALUES (69, 9, 33, 9);
INSERT INTO `order_details` VALUES (70, 13, 50, 9);
INSERT INTO `order_details` VALUES (71, 13, 40, 6);
INSERT INTO `order_details` VALUES (72, 15, 35, 5);
INSERT INTO `order_details` VALUES (73, 16, 35, 12);
INSERT INTO `order_details` VALUES (74, 16, 37, 11);
INSERT INTO `order_details` VALUES (75, 16, 47, 16);
INSERT INTO `order_details` VALUES (76, 18, 35, 14);
INSERT INTO `order_details` VALUES (77, 26, 41, 18);
INSERT INTO `order_details` VALUES (78, 22, 29, 22);
INSERT INTO `order_details` VALUES (79, 25, 12, 21);
INSERT INTO `order_details` VALUES (80, 10, 1, 2);
INSERT INTO `order_details` VALUES (81, 27, 6, 3);
INSERT INTO `order_details` VALUES (82, 37, 1, 2);
INSERT INTO `order_details` VALUES (83, 37, 2, 2);
INSERT INTO `order_details` VALUES (84, 38, 1, 2);
INSERT INTO `order_details` VALUES (85, 38, 2, 2);
INSERT INTO `order_details` VALUES (86, 40, 1, 4);
INSERT INTO `order_details` VALUES (87, 40, 2, 4);
INSERT INTO `order_details` VALUES (88, 42, 1, 4);
INSERT INTO `order_details` VALUES (89, 42, 2, 4);
INSERT INTO `order_details` VALUES (90, 45, 1, 4);
INSERT INTO `order_details` VALUES (91, 45, 2, 4);
INSERT INTO `order_details` VALUES (92, 46, 1, 4);
INSERT INTO `order_details` VALUES (93, 46, 2, 4);
INSERT INTO `order_details` VALUES (94, 47, 3, 1);
INSERT INTO `order_details` VALUES (95, 47, 2, 1);
INSERT INTO `order_details` VALUES (96, 50, 1, 1);
INSERT INTO `order_details` VALUES (97, 50, 2, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NULL DEFAULT NULL,
  `created_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `custormer_id_idx`(`customer_id`) USING BTREE,
  CONSTRAINT `custormer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, '2018-07-10 00:00:00');
INSERT INTO `orders` VALUES (2, 1, '2017-12-15 00:00:00');
INSERT INTO `orders` VALUES (3, 1, '2017-10-16 00:00:00');
INSERT INTO `orders` VALUES (4, 1, '2018-07-03 00:00:00');
INSERT INTO `orders` VALUES (5, 1, '2018-07-10 00:00:00');
INSERT INTO `orders` VALUES (6, 1, '2017-12-15 00:00:00');
INSERT INTO `orders` VALUES (7, 1, '2017-10-16 00:00:00');
INSERT INTO `orders` VALUES (8, 1, '2018-07-03 00:00:00');
INSERT INTO `orders` VALUES (9, 1, '2017-11-05 00:00:00');
INSERT INTO `orders` VALUES (10, 2, '2018-07-01 00:00:00');
INSERT INTO `orders` VALUES (13, 4, '2018-04-01 00:00:00');
INSERT INTO `orders` VALUES (14, 4, '2018-04-01 00:00:00');
INSERT INTO `orders` VALUES (15, 4, '2018-07-04 00:00:00');
INSERT INTO `orders` VALUES (16, 3, '2018-07-09 00:00:00');
INSERT INTO `orders` VALUES (17, 3, '2018-05-06 00:00:00');
INSERT INTO `orders` VALUES (18, 1, '2017-11-05 00:00:00');
INSERT INTO `orders` VALUES (22, 4, '2018-04-01 00:00:00');
INSERT INTO `orders` VALUES (24, 4, '2018-07-04 00:00:00');
INSERT INTO `orders` VALUES (25, 3, '2018-07-09 00:00:00');
INSERT INTO `orders` VALUES (26, 3, '2018-05-06 00:00:00');
INSERT INTO `orders` VALUES (27, 5, '2018-07-04 00:00:00');
INSERT INTO `orders` VALUES (28, 5, '2017-12-08 00:00:00');
INSERT INTO `orders` VALUES (29, 5, '2018-02-07 00:00:00');
INSERT INTO `orders` VALUES (30, 5, '2018-05-31 00:00:00');
INSERT INTO `orders` VALUES (31, 5, '2018-07-04 00:00:00');
INSERT INTO `orders` VALUES (32, 5, '2017-12-08 00:00:00');
INSERT INTO `orders` VALUES (33, 5, '2018-02-07 00:00:00');
INSERT INTO `orders` VALUES (34, 5, '2018-05-31 00:00:00');
INSERT INTO `orders` VALUES (35, 4, '2018-07-29 22:59:52');
INSERT INTO `orders` VALUES (36, 4, '2018-07-29 23:00:15');
INSERT INTO `orders` VALUES (37, 4, '2018-07-29 23:01:09');
INSERT INTO `orders` VALUES (38, 4, '2018-07-30 08:42:54');
INSERT INTO `orders` VALUES (39, 2, '2018-07-30 08:45:07');
INSERT INTO `orders` VALUES (40, 2, '2018-07-30 08:45:14');
INSERT INTO `orders` VALUES (41, 2, '2018-07-30 08:45:15');
INSERT INTO `orders` VALUES (42, 2, '2018-07-30 08:51:54');
INSERT INTO `orders` VALUES (43, 6, '2018-07-30 09:06:11');
INSERT INTO `orders` VALUES (44, 6, '2018-07-30 09:06:46');
INSERT INTO `orders` VALUES (45, 6, '2018-07-30 09:07:06');
INSERT INTO `orders` VALUES (46, 6, '2018-07-30 10:31:24');
INSERT INTO `orders` VALUES (47, 7, '2018-07-30 10:32:39');
INSERT INTO `orders` VALUES (48, 7, '2018-07-30 11:23:09');
INSERT INTO `orders` VALUES (49, 7, '2018-07-30 11:23:15');
INSERT INTO `orders` VALUES (50, 7, '2018-07-30 11:23:23');

-- ----------------------------
-- Table structure for publisher
-- ----------------------------
DROP TABLE IF EXISTS `publisher`;
CREATE TABLE `publisher`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `website` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `founder` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `founded_year` int(11) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of publisher
-- ----------------------------
INSERT INTO `publisher` VALUES (1, 'No Starch Pressed ', 'https://nostarch.com', 'Jame Pollocka', 1995, 'San Francisc California, America');
INSERT INTO `publisher` VALUES (2, 'O\'Reilly Media', 'https://www.oreilly.com/', 'Tim O\'Reilly', 1978, 'Sebastopol, California, US');
INSERT INTO `publisher` VALUES (3, 'Prentice Hall', 'http://prenticehall.com/', 'Charles Gerstenberg', 1913, 'New Jersey');
INSERT INTO `publisher` VALUES (4, 'Manning Publications', 'https://www.manning.com/', 'Marjan Bace', 1990, 'New York');
INSERT INTO `publisher` VALUES (5, 'Apress', 'https://www.apress.com/us', 'Gary Cornell and Dan Appleman', 1999, 'New York');
INSERT INTO `publisher` VALUES (6, 'MIT Press\r\n', 'https://mitpress.mit.edu', 'James R. Killian, Jr.', 1932, 'Cambridge, Massachusetts');
INSERT INTO `publisher` VALUES (7, 'Cambridge University Press', 'http://www.cambridge.org', 'King Henry VIII of England', 1534, 'Cambridge, England');
INSERT INTO `publisher` VALUES (8, 'Penguin Books', 'www.penguin.com', 'Allen Lane, Richard Lane, John Lane', 1935, 'City of Westminster, London, England');
INSERT INTO `publisher` VALUES (9, 'Routledge', 'https://www.routledge.com', 'George Routledge', 1835, 'Abingdon-on-Thames');
INSERT INTO `publisher` VALUES (10, 'No Starch Pressed ', 'https://nostarch.com', 'Jame Pollocka', 1995, 'San Francisc California, America');
INSERT INTO `publisher` VALUES (13, 'Art Publisher', 'art.com', 'Art', 1998, 'New York');

SET FOREIGN_KEY_CHECKS = 1;
