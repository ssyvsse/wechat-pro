/*
Navicat MySQL Data Transfer

Source Server         : dbms
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : smdb

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-06-26 21:05:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for depletion_list
-- ----------------------------
DROP TABLE IF EXISTS `depletion_list`;
CREATE TABLE `depletion_list` (
  `BarCode` varchar(11) CHARACTER SET utf8 NOT NULL COMMENT '条形码',
  `Goods_Name` varchar(11) CHARACTER SET utf8 NOT NULL COMMENT '商品名称',
  `Unit_Price` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '商品价格',
  `Loss` int(255) NOT NULL COMMENT '损耗',
  `Date` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '日期',
  `Reason` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '损耗原因'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of depletion_list
-- ----------------------------
INSERT INTO `depletion_list` VALUES ('A00001', '迷你风扇', '30', '1', '2017-06-26', '摔了');
