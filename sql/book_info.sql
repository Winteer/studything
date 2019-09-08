/*
Navicat MySQL Data Transfer

Source Server         : localHost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : winter

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-09-08 19:22:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '预定时间',
  `phone` varchar(11) DEFAULT NULL COMMENT '预定号码',
  `room` varchar(10) DEFAULT NULL COMMENT '密室名称',
  `number` int(2) DEFAULT NULL COMMENT '人员数量',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始游戏时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束游戏时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '该记录创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='预定信息表';

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('2', '2019-09-08 19:05:32', '12345', '玉观音', '1', '2019-09-10 19:04:57', '2019-09-08 19:05:03', '2019-09-08 19:05:06');
INSERT INTO `book_info` VALUES ('3', '2019-09-08 19:05:39', '123', '大宋奇案', '2', '2019-09-08 19:05:18', '2019-09-08 19:05:21', '2019-09-08 19:05:13');
