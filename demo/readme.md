swagger-url
[http://localhost:8090/demo/swagger-ui.html]

druid-url
[http://localhost:8090/demo/druid/index.html]
admin 123456

rabbitmq-url
[http://localhost:15672/#/exchanges]
guest guest

权限
/auth/login 登录


tarot
CREATE TABLE `user_info` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sagacore_id` varchar(50) NOT NULL DEFAULT '' COMMENT 'sagacore社区编号',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `pwd` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `birthday` varchar(100) DEFAULT NULL COMMENT '生日',
  `user_title` varchar(100) DEFAULT NULL COMMENT '用户输入名字',
  `user_img_url` varchar(300) DEFAULT NULL COMMENT '用户头像路径',
  `user_profile` varchar(500) DEFAULT NULL COMMENT '用户简介',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_login_id` (`sagacore_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8