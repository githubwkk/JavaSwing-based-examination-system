/*
 Navicat Premium Data Transfer

 Source Server         : wangkeke
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 11/08/2022 00:11:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grades
-- ----------------------------
DROP TABLE IF EXISTS `grades`;
CREATE TABLE `grades`  (
  `name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考生姓名',
  `grade` int NULL DEFAULT NULL COMMENT '考生成绩',
  `date` date NOT NULL COMMENT '考试日期',
  INDEX `name`(`name` ASC) USING BTREE,
  CONSTRAINT `更新成绩` FOREIGN KEY (`name`) REFERENCES `user` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of grades
-- ----------------------------
INSERT INTO `grades` VALUES ('王科可', 10, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 10, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('马化腾', 100, '2022-07-13');
INSERT INTO `grades` VALUES ('李四', 50, '2022-07-28');
INSERT INTO `grades` VALUES ('王军', 12, '2022-07-28');
INSERT INTO `grades` VALUES ('张三', 88, '2022-07-28');
INSERT INTO `grades` VALUES ('雷军', 89, '2022-07-26');
INSERT INTO `grades` VALUES ('雷军', 89, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 100, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 92, '2022-07-13');
INSERT INTO `grades` VALUES ('王科可', 95, '2022-07-13');
INSERT INTO `grades` VALUES ('马化腾', 100, '2022-07-28');
INSERT INTO `grades` VALUES ('李四', 45, '2022-07-13');
INSERT INTO `grades` VALUES ('王军', 78, '2022-07-13');
INSERT INTO `grades` VALUES ('张三', 99, '2022-07-13');
INSERT INTO `grades` VALUES ('雷军', 87, '2022-07-13');
INSERT INTO `grades` VALUES ('雷军', 89, '2022-07-28');
INSERT INTO `grades` VALUES ('张三', 12, '2022-07-26');
INSERT INTO `grades` VALUES ('刘钊', 33, '2022-07-26');
INSERT INTO `grades` VALUES ('吴兵', 34, '2022-07-26');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-28');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-30');
INSERT INTO `grades` VALUES ('大王', 0, '2022-07-30');
INSERT INTO `grades` VALUES ('王科可', 5, '2022-07-30');
INSERT INTO `grades` VALUES ('王科可', 0, '2022-07-30');

-- ----------------------------
-- Triggers structure for table grades
-- ----------------------------
DROP TRIGGER IF EXISTS `TimeInsert`;
delimiter ;;
CREATE TRIGGER `TimeInsert` BEFORE INSERT ON `grades` FOR EACH ROW set new.DATE = NOW()
;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
