/*
 Navicat Premium Data Transfer

 Source Server         : sch
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : lucky

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 10/09/2019 18:06:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
                               `name` varchar(16)  DEFAULT NULL COMMENT '分类名',
                               `status` tinyint(1) DEFAULT 1 COMMENT '分类状态 2：无效 1：有效',
                               `parent_category_id` int(11) NOT NULL DEFAULT 0 COMMENT '父分类id',
                               `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
                               `creator_name` varchar(64) DEFAULT NULL COMMENT '创建人名称',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `last_modify_by` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
                               `last_modifier_name` varchar(64) DEFAULT NULL COMMENT '最后修改人名称',
                               `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_good
-- ----------------------------
DROP TABLE IF EXISTS `t_good`;
CREATE TABLE `t_good`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
                           `name` varchar(64) DEFAULT NULL COMMENT '商品名',
                           `stock` int(20) DEFAULT NULL COMMENT '商品库存',
                           `price` bigint(20) DEFAULT 1 COMMENT '商品价格',
                           `status` tinyint(11) DEFAULT 1 COMMENT '商品状态 2:无效 1：有效',
                           `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
                           `creator_name` varchar(64) DEFAULT NULL COMMENT '创建人名称',
                           `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                           `last_modify_by` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
                           `last_modifier_name` varchar(64) DEFAULT NULL COMMENT '最后修改人名称',
                           `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                           `description` varchar(255)  DEFAULT NULL COMMENT '商品描述',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_good_category
-- ----------------------------
DROP TABLE IF EXISTS `t_good_category`;
CREATE TABLE `t_good_category`  (
                                    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
                                    `good_id` int(11) NOT NULL COMMENT '商品id',
                                    `category_id` int(11) NOT NULL COMMENT '分类id',
                                    `good_name` varchar(255)  DEFAULT NULL COMMENT '商品名',
                                    `category_name` varchar(255) DEFAULT NULL COMMENT '分类名',
                                    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
                                    `creator_name` varchar(64) DEFAULT NULL COMMENT '创建人名称',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `last_modify_by` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
                                    `last_modifier_name` varchar(64) DEFAULT NULL COMMENT '最后修改人名称',
                                    `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_good_image
-- ----------------------------
DROP TABLE IF EXISTS `t_good_image`;
CREATE TABLE `t_good_image`  (
                                 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品图片id',
                                 `good_id` int(11) NOT NULL COMMENT '商品id',
                                 `type` int(11) DEFAULT 0 COMMENT '图片类型 0：展示图片 1：小图片',
                                 `url` varchar(255) DEFAULT NULL COMMENT '图片地址',
                                 `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
                                 `creator_name` varchar(64) DEFAULT NULL COMMENT '创建人名称',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `good_id`(`good_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
                            `user_id` int(11) NOT NULL COMMENT '用户id',
                            `order_no` varchar(64)  NOT NULL COMMENT '订单编号',
                            `status` tinyint(2) NOT NULL COMMENT '订单状态 0:已取消 1：待支付 2：已完成',
                            `address` varchar(255)  DEFAULT NULL COMMENT '收货地址',
                            `create_date` datetime(0) DEFAULT NULL COMMENT '创建日期',
                            `pay_date` datetime(0) DEFAULT NULL COMMENT '支付日期',
                            `delete_time` datetime(0) DEFAULT NULL COMMENT '取消日期',
                            `receiver` varchar(16)  DEFAULT NULL COMMENT '收货人姓名',
                            `phone` varchar(16)  DEFAULT NULL COMMENT '收获手机号',
                            `total_price` bigint(20) DEFAULT NULL COMMENT '订单总价',
                            PRIMARY KEY (`id`) USING BTREE,
                            INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
                                 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单项id',
                                 `order_id` int(11) NOT NULL COMMENT '订单id',
                                 `good_id` int(11) NOT NULL COMMENT '商品id',
                                 `good_name` varchar(32) NOT NULL COMMENT '商品名称',
                                 `number` int(11) DEFAULT NULL COMMENT '商品数量',
                                 `price` bigint(20) NOT NULL COMMENT '商品价格',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `good_id`(`good_id`) USING BTREE,
                                 INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_point_history
-- ----------------------------
DROP TABLE IF EXISTS `t_point_history`;
CREATE TABLE `t_point_history`  (
                                    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '积分历史id',
                                    `user_id` int(11) NOT NULL COMMENT '用户id',
                                    `user_name` varchar(32) NOT NULL COMMENT '用户名',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    `score` int(11) DEFAULT NULL COMMENT '积分变动',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_property
-- ----------------------------
DROP TABLE IF EXISTS `t_property`;
CREATE TABLE `t_property`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性id',
                               `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性名',
                               `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
                               `creator_name` varchar(64) DEFAULT NULL COMMENT '创建人名称',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `last_modify_by` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
                               `last_modifier_name` varchar(64) DEFAULT NULL COMMENT '最后修改人名称',
                               `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_property_value
-- ----------------------------
DROP TABLE IF EXISTS `t_property_value`;
CREATE TABLE `t_property_value`  (
                                     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性值id',
                                     `property_id` int(11) NOT NULL COMMENT '属性id',
                                     `good_id` int(11) NOT NULL COMMENT '商品id',
                                     `value` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性值',
                                     `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
                                     `creator_name` varchar(64) DEFAULT NULL COMMENT '创建人名称',
                                     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                     `last_modify_by` bigint(20) DEFAULT NULL COMMENT '最后修改人id',
                                     `last_modifier_name` varchar(64) DEFAULT NULL COMMENT '最后修改人名称',
                                     `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     INDEX `good_id`(`good_id`) USING BTREE,
                                     INDEX `property_id`(`property_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '属性值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
                           `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '用户状态 0：无效 1：有效',
                           `name` varchar(16)  NOT NULL COMMENT '用户名',
                           `password` varchar(32) NOT NULL COMMENT '密码',
                           `create_date` datetime(0) DEFAULT NULL COMMENT '创建日期',
                           `address` varchar(255)  DEFAULT NULL COMMENT '地址',
                           `salt` tinyint(4) DEFAULT NULL COMMENT '密码盐',
                           `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
                           `email` varchar(64)  DEFAULT NULL COMMENT '邮箱',
                           `head_image_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
                           `total_score` bigint(20) NOT NULL DEFAULT 0 COMMENT ' 用户总积分',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT =1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
                            `name` varchar(16)  NOT NULL COMMENT '用户名',
                            `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT =1  CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员信息表' ROW_FORMAT = Dynamic;

