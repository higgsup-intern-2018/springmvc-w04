/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : ebshop

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 21/07/2018 23:23:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (1, 'Paul', 'Pogba', 25);
INSERT INTO `person` VALUES (2, 'Benjamin', 'Pavard', 22);
INSERT INTO `person` VALUES (3, 'Antoine', 'Griezmann', 27);
INSERT INTO `person` VALUES (4, 'Kylian', 'Mbappé', 19);
INSERT INTO `person` VALUES (5, 'Kevin', 'De Bruyne', 27);
INSERT INTO `person` VALUES (6, 'Eden', 'Hazard', 27);

SET FOREIGN_KEY_CHECKS = 1;
