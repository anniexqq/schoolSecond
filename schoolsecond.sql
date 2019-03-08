DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `consignee` varchar(20) COLLATE utf8_bin NOT NULL,
  `mobile` varchar(20) COLLATE utf8_bin NOT NULL,
  `transport_day` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


INSERT INTO `address` VALUES ('1', '张飞飞', '15236972515', '周六日/节假日收货', '江苏省无锡市滨湖区江南大学1号');
INSERT INTO `address` VALUES ('5', '唐玉', '12151658369', '收货时间不限', '浙江省宁波市镇海区鼓东路8号');
INSERT INTO `address` VALUES ('6', '李凯', '16236964239', '周六日/节假日收货', '北京市市辖区东城区天井路79号');


DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `desc` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `new_price` varchar(10) COLLATE utf8_bin NOT NULL,
  `old_price` varchar(10) COLLATE utf8_bin NOT NULL,
  `image_url` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `author_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


INSERT INTO `goods` VALUES ('1', '阿迪达斯双肩包', '双肩包背包大', '50', '298', '/images/tu9.jpg', 'myUser');
INSERT INTO `goods` VALUES ('2', '变色玻璃杯学生杯', '加水可变色的水杯', '19.5', '50', '/images/tu10.jpg', 'myUser');
INSERT INTO `goods` VALUES ('3', '李永乐考研数学', '包括全套历年数学真题', '40.5', '56', '/images/tu5.jpg', 'testUser');
INSERT INTO `goods` VALUES ('4', '全新世界名著文学书籍30册', '名著文学书籍30册全新的', '59.9', '100', '/images/tu8.jpg', 'testUser');
INSERT INTO `goods` VALUES ('5', '碎花雪纺连衣裙海边度假', '适合海边度假的连衣裙飘逸', '49', '29.9', '/images/tu2.jpg', 'testUser');
INSERT INTO `goods` VALUES ('6', '女包包时尚斜挎小包2019', '可斜挎可手拎的时尚小包', '120', '78.8', '/images/tu1.jpg', 'testUser');
INSERT INTO `goods` VALUES ('7', '手链时尚手链不掉色新', '手链是链状的，以祈求平安', '80', '280', '/images/tu11.jpg', 'myUser');


DROP TABLE IF EXISTS `shop_car`;
CREATE TABLE `shop_car` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `goods_id` int(5) NOT NULL COMMENT '商品Id',
  `goods_number` varchar(5) COLLATE utf8_bin NOT NULL COMMENT '商品数量',
  `is_pay` varchar(1) COLLATE utf8_bin NOT NULL COMMENT '1:已结算 0:未结算',
  `user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `pay_time` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


INSERT INTO `shop_car` VALUES ('37', '7', '3', '1', 'myUser', '2019-03-06 13:49:43');
INSERT INTO `shop_car` VALUES ('38', '6', '1', '1', 'myUser', '2019-03-06 13:50:14');
