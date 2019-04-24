ALTER TABLE `goods`
ADD COLUMN `create_time`  datetime NULL DEFAULT NULL COMMENT '发布时间' AFTER `author_name`;
