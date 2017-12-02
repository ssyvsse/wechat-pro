/*
Navicat MySQL Data Transfer

Source Server         : dbms
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : smdb

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-06-26 21:04:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodityinfo
-- ----------------------------
DROP TABLE IF EXISTS `commodityinfo`;
CREATE TABLE `commodityinfo` (
  `BarCode` varchar(6) NOT NULL COMMENT '条形码',
  `Goods_Name` varchar(30) DEFAULT NULL COMMENT '商品名称',
  `Measurement_Unit` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `Unit_Price` varchar(5) DEFAULT NULL COMMENT '单价',
  `Type` varchar(20) NOT NULL COMMENT '商品类型',
  `Count` int(6) DEFAULT NULL COMMENT '库存数量',
  `LastModified` varchar(30) DEFAULT NULL COMMENT '最后更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of commodityinfo
-- ----------------------------
INSERT INTO `commodityinfo` VALUES ('A00001', '迷你风扇', '个', '30', '日用品', '10', '2017-06-26');
INSERT INTO `commodityinfo` VALUES ('A00002', '便携水壶', '个', '22', '日用品', '20', '2017-06-26');
INSERT INTO `commodityinfo` VALUES ('A00003', '冰冻牛肉', '包', '35', '生鲜', '5', '2017-06-26');
INSERT INTO `commodityinfo` VALUES ('A00004', '卷纸', '包', '12', '日用品', '10', '2017-06-26');
