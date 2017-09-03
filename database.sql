CREATE TABLE IF NOT EXISTS `sys_user`(
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '账号',
  `password` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '密码',
  `nickname` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '称呼',
  `phone` VARCHAR(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '邮箱',
  `birthday` DATE NOT NULL DEFAULT '0000-01-01' COMMENT '生日',
  `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '最新修改时间',
  `deleted_at` TIMESTAMP DEFAULT NULL COMMENT 'null 表示被删除， not null 表示已被删除、时间表示删除时间',
  PRIMARY KEY (`id`),
  UNIQUE account_index(`account`)
)CHARSET = UTF8 ENGINE = INNODB COMMENT '用户表';