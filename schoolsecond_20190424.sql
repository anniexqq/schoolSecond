ALTER TABLE `goods`
ADD COLUMN `create_time`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `author_name`;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `message` VALUES ('1', '1', '哈士奇', '/images/tu10.jpg', '这个包包大吗？50元，出请联系我：15091819087', '2019-04-25 17:18:00', null, null);