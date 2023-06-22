CREATE DATABASE `manage-admin` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
use `manage-admin`;


CREATE TABLE `sys_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'role id',
                            `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT 'role name',
                            `role_code` varchar(20) DEFAULT NULL COMMENT 'role code',
                            `description` varchar(255) DEFAULT NULL COMMENT 'description',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time of creation',
                            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'time of update',
                            `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT 'deletion flag, 1 for deleted, 0 for the other',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='role';



#
# Structure for table "sys_user"
#

CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `username` varchar(20) NOT NULL DEFAULT '' COMMENT 'username',
                            `password` varchar(32) NOT NULL DEFAULT '' COMMENT 'password',
                            `name` varchar(50) DEFAULT NULL COMMENT 'name',
                            `phone` varchar(11) DEFAULT NULL COMMENT 'phone number',
                            `head_url` varchar(200) DEFAULT NULL COMMENT 'url of head image',
                            `dept_id` bigint(20) DEFAULT NULL COMMENT 'department id',
                            `post_id` bigint(20) DEFAULT NULL COMMENT 'post id',
                            `open_id` varchar(255) DEFAULT NULL COMMENT 'wechat open id',
                            `description` varchar(255) DEFAULT NULL COMMENT 'description',
                            `status` tinyint(3) DEFAULT NULL COMMENT 'status: 1 for normal, 0 for forbidden',
                            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time of creation',
                            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'time of update',
                            `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT 'delete flag (0: not deleted, 1: deleted)',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='user table';

#
# Data for table "sys_user"
#

INSERT INTO `sys_user` VALUES (1,'admin','96e79218965eb72c92a549dd5a330112','admin','15000000000','http://r61cnlsfq.hn-bkt.clouddn.com/7daa4595-dfde-45da-8513-c5c2b81d20cc',1022,NULL,'',NULL,1,'2021-05-31 18:08:43','2022-12-13 14:52:31',0),(2,'wjl','96e79218965eb72c92a549dd5a330112','王经理','15000000002','http://r61cnlsfq.hn-bkt.clouddn.com/b09b3467-3d99-437a-bd2e-dd8c9be92bb8',1022,6,'',NULL,1,'2022-02-08 10:35:38','2022-12-22 10:05:03',0),(3,'lrsjl','96e79218965eb72c92a549dd5a330112','李人事经理','15000000004',NULL,2018,5,'',NULL,1,'2022-05-24 11:05:40','2022-12-22 10:05:21',0),(4,'lisi','96e79218965eb72c92a549dd5a330112','李四','15000000001',NULL,1021,10,'omwf25izKON9dktgoy0dogqvnGhk',NULL,1,'2022-12-06 09:32:31','2022-12-21 09:25:06',0),(5,'zzjl','96e79218965eb72c92a549dd5a330112','张总经理','15000000003',NULL,10,8,'',NULL,1,'2022-12-07 16:47:00','2022-12-22 10:05:07',0),(6,'xkfzr','96e79218965eb72c92a549dd5a330112','张学科','15000000005',NULL,1010,11,NULL,NULL,1,'2022-12-14 09:18:12','2022-12-14 09:18:12',0),(7,'zhangsan','96e79218965eb72c92a549dd5a330112','张三','15000000006',NULL,1021,10,NULL,NULL,1,'2022-12-26 11:26:45','2022-12-27 09:11:37',0),(11,'zhangsan01','96e79218965eb72c92a549dd5a330112','张三01','15000000007',NULL,1021,10,NULL,NULL,1,'2022-12-26 11:27:33','2022-12-27 09:11:38',0),(12,'zhangsan02','96e79218965eb72c92a549dd5a330112','张三02','15000000008',NULL,1021,10,NULL,NULL,1,'2022-12-26 11:27:39','2022-12-27 09:11:39',0);

#
# Structure for table "sys_user_role"
#
CREATE TABLE `sys_user_role` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'role id',
                                 `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'user id',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'time of creation',
                                 `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'time of update',
                                 `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT 'delete flag (0: not deleted, 1: deleted)',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_role_id` (`role_id`),
                                 KEY `idx_admin_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='user role table';
