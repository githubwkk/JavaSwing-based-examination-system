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

 Date: 11/08/2022 00:11:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`, `name`) USING BTREE,
  INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '王科可', '1');
INSERT INTO `user` VALUES ('202201', '马化腾', '12345');
INSERT INTO `user` VALUES ('202202', '马云', '888');
INSERT INTO `user` VALUES ('202203', '王军', '666666');
INSERT INTO `user` VALUES ('202204', '小明', '123456');
INSERT INTO `user` VALUES ('202205', '张三', '7878');
INSERT INTO `user` VALUES ('202206', '雷军', '12345678');
INSERT INTO `user` VALUES ('202208', '刘钊', '2');
INSERT INTO `user` VALUES ('202209', '吴兵', '242');
INSERT INTO `user` VALUES ('202210', '吴军', '33333333');
INSERT INTO `user` VALUES ('202211', '李四', 'lisi4');
INSERT INTO `user` VALUES ('202212', '王五', '55555');
INSERT INTO `user` VALUES ('202213', '赵天', '0775833');
INSERT INTO `user` VALUES ('202214', '老六', '63636363');
INSERT INTO `user` VALUES ('202215', '刘强', '78787788');
INSERT INTO `user` VALUES ('202216', '黄磊', '88888888');
INSERT INTO `user` VALUES ('202217', '吴爱玲', '00000000');
INSERT INTO `user` VALUES ('202218', '黄敏', '2312');
INSERT INTO `user` VALUES ('202219', '罗辑', '1999');
INSERT INTO `user` VALUES ('20225588', '大王', '123');
INSERT INTO `user` VALUES ('202288', '程鑫', '12322222');

SET FOREIGN_KEY_CHECKS = 1;
