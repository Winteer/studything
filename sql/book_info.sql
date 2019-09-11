/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-09-11 16:12:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book_info`
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_time` timestamp NULL DEFAULT NULL COMMENT '预定时间',
  `phone` varchar(11) DEFAULT NULL COMMENT '预定号码',
  `room` varchar(10) DEFAULT NULL COMMENT '密室名称',
  `number` int(2) DEFAULT NULL COMMENT '人员数量',
  `pay_mode` varchar(10) DEFAULT NULL COMMENT '支付方式',
  `income` varchar(6) DEFAULT NULL COMMENT '实际收取',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始游戏时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束游戏时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '该记录创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='预定信息表';

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('12', '2019-09-11 09:28:22', '17695002017', '大宋奇案', '3', '支付宝', '207', null, null, '2019-09-11 09:29:21');
INSERT INTO `book_info` VALUES ('13', '2019-09-11 09:31:04', '15296909719', '玉观音', '3', '微信', '207', '2019-09-11 09:39:05', null, '2019-09-11 09:31:33');
INSERT INTO `book_info` VALUES ('14', '2019-09-11 09:33:50', '18855581216', '玉观音', '3', '现金', '207', '2019-09-11 09:38:59', null, '2019-09-11 09:34:10');
INSERT INTO `book_info` VALUES ('15', '2019-09-11 10:39:13', '15196909719', '玉观音', '5', '现金', '345', null, null, '2019-09-11 10:39:33');
INSERT INTO `book_info` VALUES ('16', '2019-09-10 00:00:00', '17695002017', '大宋奇案', '2', '微信', '138', null, null, '2019-09-11 15:24:26');
INSERT INTO `book_info` VALUES ('17', '2019-09-10 15:38:35', '17695002017', '玉观音', '2', '支付宝', '138', null, null, '2019-09-11 15:38:54');
