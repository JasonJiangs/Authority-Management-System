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
