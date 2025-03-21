package org.zsbscs.shortlink.admin.test;

public class UserTableShardingTest {
    public static final String SQL = "CREATE TABLE `t_user_%d` (\n" +
            "  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "  `username` varchar(255) NOT NULL COMMENT '用户名',\n" +
            "  `password` varchar(255) NOT NULL COMMENT '密码',\n" +
            "  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实姓名',\n" +
            "  `phone` varchar(128) NOT NULL COMMENT '手机号',\n" +
            "  `mail` varchar(512) NOT NULL COMMENT '邮箱',\n" +
            "  `deletion_time` bigint DEFAULT NULL COMMENT '注销时间戳',\n" +
            "  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "  `update_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
            "  `del_flag` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '删除标识',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  UNIQUE KEY `idx_unique_username` (`username`) USING BTREE COMMENT '用户名唯一索引'\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1901895146106720259 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
    public static void main(String[] args) {
        for(int i =0; i<16; i++){
            System.out.printf((SQL)+"%n",i);
        }
    }
}
