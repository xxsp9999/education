/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.21 : Database - education
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`education` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `education`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_se483n9gx321f5tym1uuiuh87` (`parent`),
  CONSTRAINT `FK_se483n9gx321f5tym1uuiuh87` FOREIGN KEY (`parent`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`code`,`create_date`,`level`,`name`,`remark`,`parent`) values (1,'1','2019-03-03 21:45:46',1,'学生',NULL,NULL);

/*Table structure for table `loginnum` */

DROP TABLE IF EXISTS `loginnum`;

CREATE TABLE `loginnum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `total_login_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `loginnum` */

insert  into `loginnum`(`id`,`department`,`ip_address`,`login_time`,`name`,`number`,`total_login_num`) values (1,'学生','127.0.0.1','2019-03-03 21:52:04','谢兴','2015081124',2),(2,'学生','127.0.0.1','2019-03-03 22:07:58','谢兴','2015081124',3),(3,'学生','127.0.0.1','2019-03-03 22:08:26','谢兴','2015081124',4),(4,'学生','127.0.0.1','2019-03-05 12:06:31','谢兴','2015081124',5),(5,'学生','127.0.0.1','2019-03-05 12:08:21','谢兴','2015081124',6),(6,'学生','127.0.0.1','2019-03-08 15:38:19','谢兴','2015081124',7),(7,'学生','127.0.0.1','2019-03-10 22:11:43','谢兴','2015081124',8),(8,'学生','127.0.0.1','2019-03-10 22:12:20','谢兴','2015081124',9),(9,'学生','127.0.0.1','2019-03-10 22:16:27','谢兴','2015081124',10);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `btAryData` varchar(255) DEFAULT NULL,
  `btAryTranData` varchar(255) DEFAULT NULL,
  `btCheck` varchar(255) DEFAULT NULL,
  `btCmd` varchar(255) DEFAULT NULL,
  `btDataLen` varchar(255) DEFAULT NULL,
  `btPacketType` varchar(255) DEFAULT NULL,
  `btReadId` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `orderNumber` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message` */

/*Table structure for table `module` */

DROP TABLE IF EXISTS `module`;

CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `op_type` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sort` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hva92ak65xoel6h5msemv1rv4` (`create_user`),
  KEY `FK_m1aet2cmeemwgbmbd2napn7id` (`parent`),
  KEY `FK_d5tnvmcqi1r0797jxnrqr9owb` (`update_user`),
  CONSTRAINT `FK_d5tnvmcqi1r0797jxnrqr9owb` FOREIGN KEY (`update_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_hva92ak65xoel6h5msemv1rv4` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_m1aet2cmeemwgbmbd2napn7id` FOREIGN KEY (`parent`) REFERENCES `module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `module` */

insert  into `module`(`id`,`code`,`create_date`,`img`,`level`,`name`,`op_type`,`remark`,`sort`,`type`,`update_date`,`url`,`create_user`,`parent`,`update_user`) values (1,'1','2019-03-03 21:48:24',NULL,'0','学生',NULL,NULL,'1',NULL,'2019-03-03 21:49:49',NULL,1,NULL,1),(2,'2','2019-03-03 21:48:29',NULL,'0','老师',NULL,NULL,'2',NULL,'2019-03-03 21:49:51',NULL,1,NULL,1),(3,'3','2019-03-03 21:48:34',NULL,'0','辅导员',NULL,NULL,'3',NULL,'2019-03-03 21:49:53',NULL,1,NULL,1),(4,'4','2019-03-03 21:48:44',NULL,'0','系领导',NULL,NULL,'4',NULL,'2019-03-03 21:49:55',NULL,1,NULL,1),(5,'5','2019-03-03 21:49:30',NULL,'0','管理员',NULL,NULL,'5',NULL,'2019-03-03 21:49:57',NULL,1,NULL,1);

/*Table structure for table `module_role` */

DROP TABLE IF EXISTS `module_role`;

CREATE TABLE `module_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `module` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_y4838w4i7e93l0rp8lneg86c` (`create_user`),
  KEY `FK_iikdmksaoxr0fyn9goaek4w7v` (`module`),
  KEY `FK_rqcxw2kcs5w4ghvcf4ky2q8ib` (`role`),
  KEY `FK_bm3aikc520mhvhgyf9a0kx7lp` (`update_user`),
  CONSTRAINT `FK_bm3aikc520mhvhgyf9a0kx7lp` FOREIGN KEY (`update_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_iikdmksaoxr0fyn9goaek4w7v` FOREIGN KEY (`module`) REFERENCES `module` (`id`),
  CONSTRAINT `FK_rqcxw2kcs5w4ghvcf4ky2q8ib` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_y4838w4i7e93l0rp8lneg86c` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `module_role` */

insert  into `module_role`(`id`,`create_date`,`remark`,`state`,`update_date`,`create_user`,`module`,`role`,`update_user`) values (1,'2019-03-03 21:50:48',NULL,1,'2019-03-03 21:51:07',1,1,1,1),(2,'2019-03-03 21:50:50',NULL,1,'2019-03-01 21:51:09',1,2,1,1),(3,'2019-03-03 21:50:52',NULL,1,'2019-03-03 21:51:16',1,3,1,1),(4,'2019-03-03 21:50:56',NULL,1,'2019-03-03 21:51:18',1,4,1,1),(5,'2019-03-03 21:50:54',NULL,1,'2019-03-03 21:51:19',1,5,1,1);

/*Table structure for table `params` */

DROP TABLE IF EXISTS `params`;

CREATE TABLE `params` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_type` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `deleteable` int(11) DEFAULT NULL,
  `desciption` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qkyjrt139ffqrqu0lc7d77asq` (`create_user`),
  KEY `FK_haxpo0ed0hu2xas4e8owtvvmt` (`update_user`),
  CONSTRAINT `FK_haxpo0ed0hu2xas4e8owtvvmt` FOREIGN KEY (`update_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_qkyjrt139ffqrqu0lc7d77asq` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `params` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r625vsjw7e2drbtmqv8qtu8ya` (`create_user`),
  KEY `FK_oh5erhtrukjl5f90crhy7f96f` (`update_user`),
  CONSTRAINT `FK_oh5erhtrukjl5f90crhy7f96f` FOREIGN KEY (`update_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_r625vsjw7e2drbtmqv8qtu8ya` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`code`,`create_date`,`name`,`remark`,`update_date`,`create_user`,`update_user`) values (1,'1','2019-03-03 21:47:30','管理员',NULL,'2019-03-03 21:47:41',1,1);

/*Table structure for table `role_department` */

DROP TABLE IF EXISTS `role_department`;

CREATE TABLE `role_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t5b3dp2yd8g5025ektxjhoekw` (`create_user`),
  KEY `FK_4sxx1wjb2kwlmmfw6mv902erg` (`department`),
  KEY `FK_431g2agf29ho2yu99r4fc3fy1` (`role`),
  KEY `FK_hjita5f9637s730vyn4b5sw28` (`update_user`),
  CONSTRAINT `FK_431g2agf29ho2yu99r4fc3fy1` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_4sxx1wjb2kwlmmfw6mv902erg` FOREIGN KEY (`department`) REFERENCES `department` (`id`),
  CONSTRAINT `FK_hjita5f9637s730vyn4b5sw28` FOREIGN KEY (`update_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_t5b3dp2yd8g5025ektxjhoekw` FOREIGN KEY (`create_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role_department` */

insert  into `role_department`(`id`,`create_date`,`remark`,`update_date`,`create_user`,`department`,`role`,`update_user`) values (1,'2019-03-03 21:48:04',NULL,'2019-03-03 21:48:08',1,1,1,1);

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `dept_name` varchar(255) DEFAULT NULL COMMENT '部门号',
  `message` varchar(255) DEFAULT NULL COMMENT '提示信息',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  `task_url` varchar(255) DEFAULT NULL COMMENT '任务页面路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `task` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `month_login_num` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `staffJob` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `total_login_num` int(11) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_aht1eu3cyg114yvueeegg0gy4` (`department`),
  CONSTRAINT `FK_aht1eu3cyg114yvueeegg0gy4` FOREIGN KEY (`department`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`month_login_num`,`name`,`number`,`password`,`phone`,`staffJob`,`state`,`total_login_num`,`department`) values (1,1,'谢兴','2015081124','4QrcOUm6Wau+VuBX8g+IPg==','18230899211','管理员',1,10,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
