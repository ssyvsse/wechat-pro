/*
Navicat MySQL Data Transfer

Source Server         : dbms
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : smdb

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-06-26 21:05:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `E_NO` varchar(24) NOT NULL COMMENT '商户账号',
  `Name` varchar(255) NOT NULL COMMENT '商户名称',
  `Pwd` varchar(16) NOT NULL COMMENT '密码',
  `Email` varchar(255) NOT NULL COMMENT '商户邮箱',
  `Phone` varchar(13) NOT NULL COMMENT '商户联系方式',
  `Address` varchar(255) NOT NULL COMMENT '商户地址 '
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES ('woerma', '沃尔玛', '123456', '11111@111.com', '8613123123131', '中山路');
INSERT INTO `merchant` VALUES ('haoyouduo', '好又多', '654321', '22222@qq.com', '8612313123123', '莲坂国贸');
