SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for executor
-- ----------------------------
DROP TABLE IF EXISTS `executor`;
CREATE TABLE `executor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `executor_id` varchar(32) NOT NULL,
  `ip` varchar(32) NOT NULL,
  `port` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `keep_alive_time` int(11) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `ip:port` (`ip`,`port`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;