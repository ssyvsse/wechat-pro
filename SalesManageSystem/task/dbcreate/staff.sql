/*
Navicat MySQL Data Transfer

Source Server         : dbms
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : smdb

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-06-26 20:48:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `E_NO` int(6) NOT NULL COMMENT '工号',
  `Name` varchar(30) NOT NULL COMMENT '姓名',
  `PWD` varchar(16) NOT NULL COMMENT '密码',
  `Gender` char(4) DEFAULT NULL COMMENT '性别',
  `Birth` varchar(30) DEFAULT NULL COMMENT '出生日期',
  `Address` varchar(255) DEFAULT NULL COMMENT '家庭地址',
  `Phone` varchar(13) DEFAULT NULL COMMENT '电话',
  `ID_card` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `Em_type` varchar(11) NOT NULL COMMENT '员工类型',
  `FromMarket` varchar(255) NOT NULL COMMENT '所属商户',
  PRIMARY KEY (`E_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('100001', '林良彬', 'p3wgn', '男', '1992-08-02', '软件园', '13959237924', '3502041XXXXXXXXXXX', '管理员', '叽叽歪');
INSERT INTO `staff` VALUES ('100002', '郑佳禹', 'zxcvbn', '男', '1995-05-xx', '江头', '12315', '35XXXXXXXXXXXXXXXX', '管理员', '北京烤鸭');
INSERT INTO `staff` VALUES ('100003', '王震', '123456', '男', '1992-08-xx', '机场', '12306', '35XXXXXXXXXXXXXXXX', '管理员', '沃尔玛');
INSERT INTO `staff` VALUES ('100004', '黄云涛', '000000', '男', '1993-xx-xx', '洞里', '120', '35xxxxxxxxxxxxxxxx', '员工', '家乐福');
INSERT INTO `staff` VALUES ('100005', '彭福贵', '111111', '男', '1991-xx-xx', '水上漂', '110', '35xxxxxxxxxxxxxxxx', '员工', '华为');
INSERT INTO `staff` VALUES ('100006', '赖腾文', '654321', '男', '1994-xx-xx', '纺湖', '12580', '350xxxxxxxxxxxxxxx', '设计师', '土木建设');
