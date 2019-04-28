/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : schoolsecond

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-04-28 14:54:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `consignee` varchar(20) COLLATE utf8_bin NOT NULL,
  `mobile` varchar(20) COLLATE utf8_bin NOT NULL,
  `transport_day` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '张飞飞', '15236972515', '周六日/节假日收货', '江苏省无锡市滨湖区江南大学1号');
INSERT INTO `address` VALUES ('5', '唐玉', '12151658369', '收货时间不限', '浙江省宁波市镇海区鼓东路8号');
INSERT INTO `address` VALUES ('6', '李凯', '16236964239', '周六日/节假日收货', '北京市市辖区东城区天井路79号');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `goods_desc` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `new_price` varchar(10) COLLATE utf8_bin NOT NULL,
  `old_price` varchar(10) COLLATE utf8_bin NOT NULL,
  `image_url` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `author_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '阿迪达斯双肩包', '双肩包背包大', '50', '298', '/images/tu9.jpg', '徐倩倩', '2019-04-28 10:15:58');
INSERT INTO `goods` VALUES ('2', '变色玻璃杯学生杯', '加水可变色的水杯', '19.5', '50', '/images/tu10.jpg', '徐倩倩', '2019-04-24 16:27:10');
INSERT INTO `goods` VALUES ('3', '李永乐考研数学', '包括全套历年数学真题', '40.5', '56', '/images/tu5.jpg', '徐倩倩', '2019-04-24 16:27:12');
INSERT INTO `goods` VALUES ('4', '全新世界名著文学书籍30册', '名著文学书籍30册全新的', '59.9', '100', '/images/tu8.jpg', 'testUser', '2019-04-24 16:27:15');
INSERT INTO `goods` VALUES ('5', '碎花雪纺连衣裙海边度假', '适合海边度假的连衣裙飘逸', '49', '29.9', '/images/tu2.jpg', 'testUser', '2019-04-24 16:27:18');
INSERT INTO `goods` VALUES ('6', '女包包时尚斜挎小包2019', '可斜挎可手拎的时尚小包', '120', '78.8', '/images/tu1.jpg', 'testUser', '2019-04-24 16:27:24');
INSERT INTO `goods` VALUES ('7', '手链时尚手链不掉色新', '手链是链状的，以祈求平安', '80', '280', '/images/tu11.jpg', 'testUser', '2019-04-24 16:27:22');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `goods_id` int(5) NOT NULL,
  `user_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '留言用户',
  `user_image` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '留言用户头像',
  `comment` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '留言内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `reply_comment_id` int(5) DEFAULT NULL COMMENT '回复某一条留言',
  `reply_user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '回复某用户的用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '哈士奇', '/images/tu10.jpg', '这个包包大吗？50元，出请联系我：15091819087', '2019-04-25 17:18:00', null, null);
INSERT INTO `message` VALUES ('2', '1', '苏格兰牧羊犬', '/images/tu12.jpg', '包包挺大的', '2019-04-26 17:03:33', '1', '哈士奇');
INSERT INTO `message` VALUES ('39', '1', '徐倩倩', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erlDM1EHdAibiaPvpceIjyibALMmTxicHJiaBVpbr7QAARsfUaaKfdmba4UPdL3YQDxJf6QOaBZuhzeFtw/132', '这个双肩包我想要', '2019-04-28 14:28:45', null, '');

-- ----------------------------
-- Table structure for shop_car
-- ----------------------------
DROP TABLE IF EXISTS `shop_car`;
CREATE TABLE `shop_car` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `goods_id` int(5) NOT NULL COMMENT '商品Id',
  `goods_number` varchar(5) COLLATE utf8_bin NOT NULL COMMENT '商品数量',
  `is_pay` varchar(1) COLLATE utf8_bin NOT NULL COMMENT '1:已结算 0:未结算',
  `user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `pay_time` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of shop_car
-- ----------------------------
