/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : ssyvsse

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2018-01-29 00:22:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `operation_time` datetime DEFAULT NULL,
  `operational_context` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES ('1', '127.0.0.1', '2018-01-07 18:39:48', '新增或修改了id=的用户', 'admin');
INSERT INTO `system_log` VALUES ('2', '127.0.0.1', '2018-01-07 18:55:00', '新增或修改了id=4028b88160d033920160d03479d60000的用户', 'admin');
INSERT INTO `system_log` VALUES ('3', '127.0.0.1', '2018-01-07 19:21:23', '修改了id=4028b88160d033920160d03479d60000 的用户角色', 'admin');
INSERT INTO `system_log` VALUES ('4', '127.0.0.1', '2018-01-07 19:24:00', '新增或修改了id=4028b88160d033920160d03479d60000 的用户角色', 'admin');
INSERT INTO `system_log` VALUES ('5', '127.0.0.1', '2018-01-07 19:46:27', '新增或修改了id=4028b88160d033920160d03479d60000 的用户角色', 'ssyvsse');
INSERT INTO `system_log` VALUES ('6', '127.0.0.1', '2018-01-08 20:09:37', '修改或增加了权限id=20 的信息', 'ssyvsse');
INSERT INTO `system_log` VALUES ('7', '127.0.0.1', '2018-01-08 20:19:21', '修改或增加了权限id=16 的信息', 'ssyvsse');
INSERT INTO `system_log` VALUES ('8', '127.0.0.1', '2018-01-08 20:43:26', '修改版本号为:861,945', 'ssyvsse');
INSERT INTO `system_log` VALUES ('9', '127.0.0.1', '2018-01-08 20:44:15', '修改版本号为:861,946', 'ssyvsse');
INSERT INTO `system_log` VALUES ('10', '127.0.0.1', '2018-01-08 20:44:34', '修改版本号为:861,946', 'ssyvsse');
INSERT INTO `system_log` VALUES ('11', '127.0.0.1', '2018-01-08 20:44:50', '修改版本号为:861,946', 'ssyvsse');
INSERT INTO `system_log` VALUES ('12', '127.0.0.1', '2018-01-08 20:44:55', '修改版本号为:861,946', 'ssyvsse');
INSERT INTO `system_log` VALUES ('13', '127.0.0.1', '2018-01-08 20:45:35', '修改版本号为:861,947', 'ssyvsse');
INSERT INTO `system_log` VALUES ('14', '127.0.0.1', '2018-01-08 20:45:42', '修改版本号为:861,947', 'ssyvsse');
INSERT INTO `system_log` VALUES ('15', '127.0.0.1', '2018-01-08 20:46:06', '修改版本号为:861,948', 'ssyvsse');
INSERT INTO `system_log` VALUES ('16', '127.0.0.1', '2018-01-09 19:21:49', '修改id=4028b88160d033920160d03479d60000用户的密码', 'ssyvsse');
INSERT INTO `system_log` VALUES ('17', '127.0.0.1', '2018-01-09 19:24:20', '修改id=4028b88160d033920160d03479d60000用户的密码', 'ssyvsse');
INSERT INTO `system_log` VALUES ('18', '127.0.0.1', '2018-01-09 21:16:25', '新增或修改了id= 的用户角色', 'ssyvsse');
INSERT INTO `system_log` VALUES ('19', '127.0.0.1', '2018-01-09 21:17:12', '修改了id=4028b88160db06ea0160db10964f0000 的用户角色', 'ssyvsse');

-- ----------------------------
-- Table structure for tb_access_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_access_token`;
CREATE TABLE `tb_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `available` int(1) NOT NULL,
  `expired_time` bigint(20) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_access_token
-- ----------------------------
INSERT INTO `tb_access_token` VALUES ('1', '6_y59JS_5Xvo2QqBVC3PAbIaJPUvmc4eZinwMzy19rct8ToD378RcXJITjZ8_t-3x_oN0RNSx11jGFYFGJzgSNOiTc3e-oZeXQtHZbkxNelohy45EhLZJbg7E7A1RhabDEHoPgOedzu6-IImbNPWIjAEALSE', '1', '1517163590232', 'wx_access_token');

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `from_name` varchar(50) DEFAULT NULL,
  `from_where` varchar(50) DEFAULT NULL,
  `text` varchar(1000) DEFAULT NULL,
  `to_name` varchar(50) DEFAULT NULL,
  `to_where` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_message
-- ----------------------------

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `is_hide` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `source_key` varchar(255) DEFAULT NULL,
  `source_url` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf5ra2gn0xedeida2op8097sr5` (`parent_id`),
  CONSTRAINT `FKf5ra2gn0xedeida2op8097sr5` FOREIGN KEY (`parent_id`) REFERENCES `tb_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES ('1', '2018-01-07 19:25:15', '系统管理', null, '0', '2', '系统管理', '1', 'admin:xtpz:index', '/admin/xtpz', '1', '2018-01-07 19:28:09', null);
INSERT INTO `tb_resource` VALUES ('2', '2018-01-07 19:42:13', '权限管理', null, '0', '2', '权限管理', '1', 'system:qxgl:index', '/admin/qxgl', '1', '2018-01-07 19:42:13', null);
INSERT INTO `tb_resource` VALUES ('3', '2018-01-07 19:52:41', '用户管理', null, '0', '2', '用户管理', '1', 'system:user:index', '/admin/user/index', '1', '2018-01-07 19:52:58', '2');
INSERT INTO `tb_resource` VALUES ('4', '2018-01-07 19:52:46', '用户编辑', null, '0', '3', '用户编辑', '1', 'system:user:edit', '/admin/user/edit*', '2', '2018-01-07 19:53:01', '3');
INSERT INTO `tb_resource` VALUES ('5', '2018-01-07 19:52:50', '用户添加', null, '0', '3', '用户添加', '2', 'system:user:add', '/admin/user/add', '2', '2018-01-07 19:53:05', '3');
INSERT INTO `tb_resource` VALUES ('6', '2018-01-07 19:52:54', '用户删除', null, '0', '3', '用户删除', '3', 'system:user:deleteBatch', '/admin/user/deleteBatch', '2', '2018-01-07 19:53:08', '3');
INSERT INTO `tb_resource` VALUES ('7', '2018-01-07 20:20:24', '角色管理', null, '0', '2', '角色管理', '2', 'system:role:index', '/admin/role/index', '1', '2018-01-07 20:21:08', null);
INSERT INTO `tb_resource` VALUES ('8', '2018-01-07 20:20:27', '角色分配', null, '0', '3', '角色分配', '4', 'system:user:grant', '/admin/user/grant/**', '2', '2018-01-07 20:21:11', '7');
INSERT INTO `tb_resource` VALUES ('9', '2018-01-07 20:20:31', '角色编辑', null, '0', '3', '角色编辑', '1', 'system:role:edit', '/admin/role/edit*', '2', '2018-01-07 20:21:13', '7');
INSERT INTO `tb_resource` VALUES ('10', '2018-01-07 20:20:35', '角色添加', null, '0', '3', '角色添加', '2', 'system:role:add', '/admin/role/add', '2', '2018-01-07 20:21:15', '7');
INSERT INTO `tb_resource` VALUES ('11', '2018-01-07 20:20:38', '角色删除', null, '0', '3', '角色删除', '3', 'system:role:deleteBatch', '/admin/role/deleteBatch', '2', '2018-01-07 20:21:17', '7');
INSERT INTO `tb_resource` VALUES ('12', '2018-01-07 20:20:43', '资源管理', null, '0', '2', '资源管理', '3', 'system:resource:index', '/admin/resource/index', '1', '2018-01-07 20:21:19', null);
INSERT INTO `tb_resource` VALUES ('13', '2018-01-07 20:20:46', '资源分配', null, '0', '3', '资源分配', '4', 'system:role:grant', '/admin/role/grant/**', '2', '2018-01-07 20:21:21', '12');
INSERT INTO `tb_resource` VALUES ('14', '2018-01-07 20:20:49', '资源编辑', null, '0', '3', '资源编辑', '1', 'system:resource:edit', '/admin/resource/edit*', '2', '2018-01-07 20:21:23', '12');
INSERT INTO `tb_resource` VALUES ('15', '2018-01-07 20:20:52', '资源添加', null, '0', '3', '资源添加', '2', 'system:resource:add', '/admin/resource/add', '2', '2018-01-07 20:21:25', '12');
INSERT INTO `tb_resource` VALUES ('16', '2018-01-07 20:20:54', '资源删除', '', '1', '3', '资源删除', '3', 'system:resource:deleteBatch', '/admin/resource/deleteBatch', '2', '2018-01-08 20:19:21', '12');
INSERT INTO `tb_resource` VALUES ('18', '2018-01-08 19:34:16', '枚举值管理', null, '0', '3', '枚举值管理', '3', 'system:sys:enum', '/enum/enum', '1', '2018-01-08 19:34:24', '1');
INSERT INTO `tb_resource` VALUES ('19', '2018-01-08 19:34:19', '日志管理', null, '0', '3', '日志管理', '1', 'system:sys:log', '/admin/log/index', '1', '2018-01-08 19:34:26', '1');
INSERT INTO `tb_resource` VALUES ('20', '2018-01-08 19:34:21', '版本号管理', '', '0', '3', '版本号管理', '1', 'system:admin:version', '/admin/version', '1', '2018-01-08 20:09:37', '1');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role_key` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '2018-01-07 09:56:30', '超级管理员', 'administrator', 'administrator', '0', '2018-01-07 09:56:30');
INSERT INTO `tb_role` VALUES ('2', '2018-01-07 10:18:48', '副管理员', 'guest', 'guest', '0', '2018-01-07 10:18:50');

-- ----------------------------
-- Table structure for tb_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_resource`;
CREATE TABLE `tb_role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `FK868kc8iic48ilv5npa80ut6qo` (`resource_id`),
  CONSTRAINT `FK7ffc7h6obqxflhj1aq1mk20jk` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`),
  CONSTRAINT `FK868kc8iic48ilv5npa80ut6qo` FOREIGN KEY (`resource_id`) REFERENCES `tb_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_resource
-- ----------------------------
INSERT INTO `tb_role_resource` VALUES ('1', '1');
INSERT INTO `tb_role_resource` VALUES ('2', '1');
INSERT INTO `tb_role_resource` VALUES ('1', '2');
INSERT INTO `tb_role_resource` VALUES ('2', '2');
INSERT INTO `tb_role_resource` VALUES ('1', '3');
INSERT INTO `tb_role_resource` VALUES ('2', '3');
INSERT INTO `tb_role_resource` VALUES ('1', '4');
INSERT INTO `tb_role_resource` VALUES ('2', '4');
INSERT INTO `tb_role_resource` VALUES ('1', '5');
INSERT INTO `tb_role_resource` VALUES ('2', '5');
INSERT INTO `tb_role_resource` VALUES ('1', '6');
INSERT INTO `tb_role_resource` VALUES ('2', '6');
INSERT INTO `tb_role_resource` VALUES ('1', '7');
INSERT INTO `tb_role_resource` VALUES ('2', '7');
INSERT INTO `tb_role_resource` VALUES ('1', '8');
INSERT INTO `tb_role_resource` VALUES ('2', '8');
INSERT INTO `tb_role_resource` VALUES ('1', '9');
INSERT INTO `tb_role_resource` VALUES ('2', '9');
INSERT INTO `tb_role_resource` VALUES ('1', '10');
INSERT INTO `tb_role_resource` VALUES ('2', '10');
INSERT INTO `tb_role_resource` VALUES ('1', '11');
INSERT INTO `tb_role_resource` VALUES ('2', '11');
INSERT INTO `tb_role_resource` VALUES ('1', '12');
INSERT INTO `tb_role_resource` VALUES ('2', '12');
INSERT INTO `tb_role_resource` VALUES ('1', '13');
INSERT INTO `tb_role_resource` VALUES ('2', '13');
INSERT INTO `tb_role_resource` VALUES ('1', '14');
INSERT INTO `tb_role_resource` VALUES ('2', '14');
INSERT INTO `tb_role_resource` VALUES ('1', '15');
INSERT INTO `tb_role_resource` VALUES ('2', '15');
INSERT INTO `tb_role_resource` VALUES ('1', '16');
INSERT INTO `tb_role_resource` VALUES ('2', '16');
INSERT INTO `tb_role_resource` VALUES ('2', '18');
INSERT INTO `tb_role_resource` VALUES ('1', '19');
INSERT INTO `tb_role_resource` VALUES ('2', '19');
INSERT INTO `tb_role_resource` VALUES ('2', '20');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `alias` int(11) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `delete_status` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `head_portrait` varchar(255) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `locked` int(11) DEFAULT NULL,
  `login_type` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `old` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `qid` int(11) DEFAULT NULL,
  `regist_url` varchar(255) DEFAULT NULL,
  `register_type` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `sessionid` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `sound` int(11) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_ip` varchar(255) DEFAULT NULL,
  `wechat_id` int(11) DEFAULT NULL,
  `binding` varchar(20) DEFAULT NULL,
  `google_open` varchar(20) DEFAULT NULL,
  `secret` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('4028b88160cf95010160cf9593b00000', 'admin', '2UAELABQEJ35SS1BEE5C9BPUK0', 'admin', null, null, '2', null, null, null, null, '2018-01-07 15:46:14', '0', null, null, null, '2018-01-28 18:34:27', '0', 'background', null, null, null, null, null, null, null, null, null, '-1', null, '2018-01-07 15:46:14', '127.0.0.1', null, '绑定', '开启', 'T74XCLPTM64ROQ4D');
INSERT INTO `tb_user` VALUES ('4028b88160d033920160d03479d60000', 'ssyvsse', 'O4E9VIULBAS3DEG1D8MAPSIJ6', 'ssyvsse', '13959237924', null, '2', '2018-01-07 00:00:00', null, 'ssss', null, '2018-01-07 18:39:48', '0', 'ssss', 'ssyvsse@163.com', null, '2018-01-28 19:29:55', '0', 'background', null, null, null, null, null, null, null, null, '1', '-1', null, '2018-01-07 19:46:27', '127.0.0.1', null, '绑定', '开启', 'RA4MGQFPUVCBLRKE');
INSERT INTO `tb_user` VALUES ('4028b88160db06ea0160db10964f0000', 'tt900613', 'O4E9VIULBAS3DEG1D8MAPSIJ6', 'tt900613', '13959237923', null, '2', '2000-01-09 00:00:00', null, 'ssssss', null, '2018-01-09 21:16:25', '0', 'ssssss', 'ssyvsse@163.com', null, '2018-01-28 19:19:32', '0', 'background', null, null, null, null, null, null, null, null, '0', '-1', null, '2018-01-09 21:16:25', '127.0.0.1', null, '绑定', '开启', 'YJOK3ZICF7ZFTAT7');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKea2ootw6b6bb0xt3ptl28bymv` (`role_id`),
  CONSTRAINT `FK7vn3h53d0tqdimm8cp45gc0kl` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKea2ootw6b6bb0xt3ptl28bymv` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('4028b88160cf95010160cf9593b00000', '1');
INSERT INTO `tb_user_role` VALUES ('4028b88160d033920160d03479d60000', '2');
INSERT INTO `tb_user_role` VALUES ('4028b88160db06ea0160db10964f0000', '2');

-- ----------------------------
-- Table structure for tb_wx_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_wx_user_info`;
CREATE TABLE `tb_wx_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  `headimgurl` varchar(255) DEFAULT NULL,
  `language` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `openid` varchar(100) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `subscribe` int(11) DEFAULT NULL,
  `subscribe_time` bigint(20) DEFAULT NULL,
  `unionid` varchar(255) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wx_user_info
-- ----------------------------

DROP TABLE IF EXISTS `six_lottery`;
CREATE TABLE `six_lottery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(255) DEFAULT NULL,
  `before_nums` varchar(255) DEFAULT NULL,
  `before_specialnums` varchar(255) DEFAULT NULL,
  `after_nums` varchar(255) DEFAULT NULL,
  `after_specialnums` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `opendate` varchar(255) DEFAULT NULL,
  `audit_userid` int(11) DEFAULT NULL,
  `audittime` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `money` decimal(19,2) DEFAULT NULL,
  `shortpy` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `typename` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `platform` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4941 DEFAULT CHARSET=utf8;
