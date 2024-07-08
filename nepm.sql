/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : nepm

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 09/09/2023 17:25:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aqi
-- ----------------------------
DROP TABLE IF EXISTS `aqi`;
CREATE TABLE `aqi`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '空气质量指数级别等级',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '空气质量指数类别',
  `aqiExplain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '空气质量指数级别描述',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表示颜色',
  `impact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '健康影响情况',
  `step` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '采取的建议',
  `so2Min` int(0) NOT NULL COMMENT '级别最低so2浓度',
  `so2Max` int(0) NOT NULL COMMENT '级别最高so2浓度',
  `coMin` int(0) NOT NULL COMMENT '级别最低co浓度',
  `coMax` int(0) NOT NULL COMMENT '级别最高co浓度',
  `spmMin` int(0) NOT NULL COMMENT '级别最低PM2.5浓度',
  `spmMax` int(0) NOT NULL COMMENT '级别最高PM2.5浓度',
  `iaqiMin` int(0) NOT NULL COMMENT '级别最低AQI',
  `iaqiMax` int(0) NOT NULL COMMENT '级别最高AQI',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aqi
-- ----------------------------
INSERT INTO `aqi` VALUES (1, '一级', '优', '空气质量令人满意，基本无空气污染对健康没有危害。', '绿色', '空气质量令人满意，基本无空气污染。', '各类人群可多参加户外活动，多呼吸一下清新的空气。', 0, 50, 0, 2, 0, 35, 0, 50, '');
INSERT INTO `aqi` VALUES (2, '二级', '良好', '除少数对某些污染物特别敏感的人群外，不会对人体健康产生危害。', '黄色', '空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响。', '除少数对某些污染物特别容易过敏的人群外，其他人群可以正常进行室外活动。', 50, 150, 3, 5, 36, 75, 50, 100, '');
INSERT INTO `aqi` VALUES (3, '三级', '轻度污染', '敏感人群症状会有轻度加剧，对健康人群没有明显影响。', '橙色', '易感人群症状有轻度加剧，健康人群出现刺激症状。', '儿童、老年人及心脏病、呼吸系统疾病患者应尽量减少体力消耗大的户外活动。', 150, 475, 6, 14, 76, 115, 100, 150, '');
INSERT INTO `aqi` VALUES (4, '四级', '中度污染', '敏感人群症状进一步加剧，可能对健康人群的心脏、呼吸系统有影响。', '红色', '进一步加剧易感人群症状，可能对健康人群心脏、呼吸系统有影响。', '儿童、老年人及心脏病、呼吸系统疾病患者应尽量减少外出，停留在室内，一般人群应适量减少户外运动。', 475, 800, 15, 24, 116, 150, 150, 200, '');
INSERT INTO `aqi` VALUES (5, '五级', '重度污染', '空气状况很差，会对每个人的健康都产生比较严重的危害。', '紫色', '心脏病和肺病患者症状显著加剧，运动耐受力降低，健康人群普遍出现症状。', '儿童、老年人及心脏病、肺病患者应停留在室内，停止户外运动，一般人群尽量减少户外运动。', 800, 1600, 25, 36, 151, 250, 200, 300, '');
INSERT INTO `aqi` VALUES (6, '六级', '严重污染', '空气状况极差，所有人的健康都会受到严重危害。', '红褐色', '健康人群运动耐受力降低，有明显强烈症状，提前出现某些疾病。', '儿童、老年人和病人应停留在室内，避免体力消耗，除有特殊需要的人群外，一般人群尽量不要停留在室外。', 1600, 2620, 37, 60, 251, 500, 300, 500, '');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `sId` int(0) DEFAULT 0 COMMENT '公共监督员编号',
  `pId` int(0) DEFAULT 0 COMMENT '反馈信息所在省编号',
  `cId` int(0) DEFAULT 0 COMMENT '所在市区编号',
  `tId` int(0) DEFAULT 0 COMMENT '县（区）编号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '详细地址',
  `estimatedGrade` int(0) DEFAULT 1 COMMENT '预估等级',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '反馈信息描述',
  `fDate` date DEFAULT NULL COMMENT '反馈日期',
  `fTime` time(0) DEFAULT NULL COMMENT '反馈时间',
  `gId` int(0) DEFAULT NULL COMMENT '指派的网格员编号',
  `assignDate` date DEFAULT NULL COMMENT '指派日期',
  `assignTime` time(0) DEFAULT NULL COMMENT '指派时间',
  `state` int(0) NOT NULL DEFAULT 0 COMMENT '信息状态：\r\n0：未指派，\r\n1：已指派，\r\n2：已确认',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (1, 5, 1, 4, 9, '向阳大街', 2, '整体还可以，但还不够', '2023-01-11', '12:47:38', 2, '2023-01-11', '12:47:38', 2);
INSERT INTO `feedback` VALUES (2, 7, 2, 6, 13, '向阳大街', 2, '整体还可以，但还不够', '2023-01-28', '12:55:25', 3, '2023-01-28', '17:00:00', 2);
INSERT INTO `feedback` VALUES (3, 5, 3, 7, 15, '向阳大街', 2, '整体还可以，但还不够', '2023-02-24', '08:53:07', 2, '2023-02-24', '09:53:07', 2);
INSERT INTO `feedback` VALUES (4, 6, 1, 4, 10, '向阳大街', 3, '阴霾天气', '2023-03-15', '15:01:00', 3, '2023-03-15', '15:46:21', 2);
INSERT INTO `feedback` VALUES (5, 6, 2, 5, 12, '阳光大街', 4, '气味刺鼻', '2023-03-27', '15:01:00', 4, '2023-03-27', '17:35:48', 2);
INSERT INTO `feedback` VALUES (6, 5, 3, 7, 16, '向阳大街', 4, '呼吸困难', '2023-03-29', '16:09:45', 3, '2023-03-29', '17:39:49', 2);
INSERT INTO `feedback` VALUES (7, 6, 1, 4, 9, '贴砍樵街道', 5, '阴霾太严重', '2023-04-21', '09:14:13', 2, '2023-04-21', '09:48:59', 2);
INSERT INTO `feedback` VALUES (8, 7, 2, 5, 11, '百搭岭小学', 4, '空气质量太差', '2023-05-17', '09:53:18', 3, '2023-05-17', '10:51:17', 2);
INSERT INTO `feedback` VALUES (9, 6, 3, 8, 17, '喜久村', 4, '感觉有轻度污染。', '2023-05-28', '15:55:27', 4, '2023-05-28', '16:49:45', 2);
INSERT INTO `feedback` VALUES (10, 5, 2, 5, 12, '新时代商厦', 4, '怀疑有人在制毒气，太臭了。', '2023-06-08', '10:25:55', 2, '2023-06-08', '11:50:01', 2);
INSERT INTO `feedback` VALUES (11, 5, 2, 6, 14, '向阳小学', 3, '阴霾太重', '2023-06-22', '09:01:47', 3, '2023-06-22', '09:03:31', 2);
INSERT INTO `feedback` VALUES (12, 6, 3, 8, 18, '实验中学', 5, '呼吸困难', '2023-07-14', '12:45:32', 2, '2023-07-14', '13:50:32', 2);
INSERT INTO `feedback` VALUES (13, 7, 3, 7, 15, '爱华街', 4, '呼吸困难', '2023-08-28', '12:45:54', 3, '2023-08-28', '13:50:52', 2);
INSERT INTO `feedback` VALUES (14, 7, 2, 6, 13, '河北路38号', 3, '感觉有轻度污染。', '2023-08-30', '09:47:05', 4, '2023-08-30', '11:51:12', 2);
INSERT INTO `feedback` VALUES (15, 5, 1, 4, 10, '周末镇', 4, '感觉有轻度污染。', '2023-09-01', '09:29:35', 4, '2023-09-01', '09:51:34', 2);
INSERT INTO `feedback` VALUES (16, 5, 1, 4, 9, '111', 4, '呼吸困难。。。', '2023-09-03', '00:25:20', 2, '2023-09-03', '17:56:16', 2);
INSERT INTO `feedback` VALUES (17, 5, 1, 4, 10, '恒安小区', 3, '空气令人不适。', '2023-09-03', '17:52:49', 3, '2023-09-06', '15:38:27', 2);
INSERT INTO `feedback` VALUES (18, 6, 2, 6, 13, '人民广场', 4, '空气令人不适', '2023-09-03', '17:53:48', 13, '2023-09-06', '15:38:59', 2);
INSERT INTO `feedback` VALUES (19, 7, 3, 8, 17, '西南天下', 4, '令人不适', '2023-09-03', '17:54:46', 13, '2023-09-06', '15:40:13', 2);
INSERT INTO `feedback` VALUES (20, 8, 3, 7, 16, '胶州一级中学', 3, '。。。', '2023-09-03', '17:55:33', 3, '2023-09-06', '15:40:40', 2);
INSERT INTO `feedback` VALUES (21, 8, 1, 4, 9, '东城中心', 3, '呼吸不顺畅', '2023-09-03', '19:18:57', 2, '2023-09-06', '15:41:18', 2);
INSERT INTO `feedback` VALUES (22, 8, 1, 4, 9, '东城中心', 3, '呼吸不顺畅', '2023-09-03', '19:22:53', 2, '2023-09-06', '15:41:43', 2);
INSERT INTO `feedback` VALUES (23, 8, 1, 4, 9, '东城中心', 3, '呼吸不顺畅', '2023-09-03', '19:28:51', 3, '2023-09-03', '19:28:51', 2);
INSERT INTO `feedback` VALUES (24, 8, 1, 4, 9, '东城中心', 3, '呼吸不顺畅', '2023-09-03', '19:33:55', 3, '2023-09-03', '19:33:55', 2);
INSERT INTO `feedback` VALUES (25, 7, 3, 8, 17, '德城中心', 3, '呼吸不顺畅', '2023-09-03', '19:40:43', 3, '2023-09-09', '10:30:02', 2);
INSERT INTO `feedback` VALUES (26, 5, 2, 5, 12, '河北大街', 4, '空气质量差', '2023-09-03', '21:16:19', 4, '2023-09-03', '21:16:19', 2);
INSERT INTO `feedback` VALUES (27, 5, 3, 8, 18, '所开发为', 3, 'dskfj', '2023-09-06', '23:07:45', 2, '2023-09-09', '10:22:54', 2);
INSERT INTO `feedback` VALUES (28, 5, 1, 4, 10, '朝阳', 3, '阴霾严重', '2023-09-09', '10:00:33', 3, '2023-09-09', '10:00:33', 2);
INSERT INTO `feedback` VALUES (29, 11, 2, 5, 12, '安国', 3, '呼吸困难', '2023-09-09', '10:47:00', 12, '2023-09-09', '10:47:00', 2);
INSERT INTO `feedback` VALUES (30, 10, 3, 7, 16, '胶州', 3, '呼吸困难', '2023-09-09', '10:52:42', 13, '2023-09-09', '10:53:20', 2);
INSERT INTO `feedback` VALUES (31, 9, 2, 6, 14, '大城', 3, '轻度污染', '2023-09-09', '10:56:53', 14, '2023-09-09', '10:56:53', 1);
INSERT INTO `feedback` VALUES (32, 9, 1, 4, 9, '东城', 3, '呼吸困难', '2023-09-09', '11:02:28', 2, '2023-09-09', '11:02:28', 2);
INSERT INTO `feedback` VALUES (33, 8, 3, 8, 18, '乐龄', 3, '呼吸困难', '2023-09-09', '11:05:15', NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for feedbackstate
-- ----------------------------
DROP TABLE IF EXISTS `feedbackstate`;
CREATE TABLE `feedbackstate`  (
  `id` int(0) NOT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedbackstate
-- ----------------------------
INSERT INTO `feedbackstate` VALUES (0, '未指派');
INSERT INTO `feedbackstate` VALUES (1, '已指派');
INSERT INTO `feedbackstate` VALUES (2, '已确认');

-- ----------------------------
-- Table structure for grid
-- ----------------------------
DROP TABLE IF EXISTS `grid`;
CREATE TABLE `grid`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '省/市/县名称',
  `leaderId` int(0) DEFAULT NULL COMMENT '上一级Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid
-- ----------------------------
INSERT INTO `grid` VALUES (1, '北京市', 0);
INSERT INTO `grid` VALUES (2, '河北省', 0);
INSERT INTO `grid` VALUES (3, '山东省', 0);
INSERT INTO `grid` VALUES (4, '市辖区', 1);
INSERT INTO `grid` VALUES (5, '保定市', 2);
INSERT INTO `grid` VALUES (6, '廊坊市', 2);
INSERT INTO `grid` VALUES (7, '青岛市', 3);
INSERT INTO `grid` VALUES (8, '德州市', 3);
INSERT INTO `grid` VALUES (9, '东城区', 4);
INSERT INTO `grid` VALUES (10, '朝阳区', 4);
INSERT INTO `grid` VALUES (11, '莲池区', 5);
INSERT INTO `grid` VALUES (12, '安国市', 5);
INSERT INTO `grid` VALUES (13, '安次区', 6);
INSERT INTO `grid` VALUES (14, '大城县', 6);
INSERT INTO `grid` VALUES (15, '即墨区', 7);
INSERT INTO `grid` VALUES (16, '胶州市', 7);
INSERT INTO `grid` VALUES (17, '德城区', 8);
INSERT INTO `grid` VALUES (18, '乐陵市', 8);

-- ----------------------------
-- Table structure for sex
-- ----------------------------
DROP TABLE IF EXISTS `sex`;
CREATE TABLE `sex`  (
  `id` int(0) NOT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sex
-- ----------------------------
INSERT INTO `sex` VALUES (0, '男');
INSERT INTO `sex` VALUES (1, '女');

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `id` int(0) NOT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES (0, '未指派');
INSERT INTO `state` VALUES (1, '已指派');
INSERT INTO `state` VALUES (2, '已确认');

-- ----------------------------
-- Table structure for statistics
-- ----------------------------
DROP TABLE IF EXISTS `statistics`;
CREATE TABLE `statistics`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pId` int(0) NOT NULL DEFAULT 0 COMMENT '省份id',
  `cId` int(0) NOT NULL DEFAULT 0 COMMENT '城市id',
  `tId` int(0) NOT NULL DEFAULT 0 COMMENT '县/区id',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '详细地址',
  `so2Value` int(0) NOT NULL DEFAULT 0 COMMENT 'so2实测浓度',
  `so2Level` int(0) NOT NULL DEFAULT 1 COMMENT 'so2指数级别',
  `coValue` int(0) NOT NULL DEFAULT 0 COMMENT 'co实测浓度',
  `coLevel` int(0) NOT NULL DEFAULT 1 COMMENT 'co指数级别',
  `spmValue` int(0) NOT NULL DEFAULT 0 COMMENT '实测悬浮颗粒物浓度',
  `spmLevel` int(0) NOT NULL DEFAULT 1 COMMENT 'pm2.5指数级别',
  `aqiId` int(0) NOT NULL DEFAULT 1 COMMENT '空气质量指数级别',
  `confirmDate` date DEFAULT NULL COMMENT '确认日期',
  `confirmTime` time(0) DEFAULT NULL COMMENT '确认时间',
  `gId` int(0) DEFAULT NULL COMMENT '网格员id',
  `sId` int(0) NOT NULL DEFAULT 0 COMMENT '反馈者id',
  `fId` int(0) DEFAULT NULL COMMENT '反馈信息id',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '实测备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of statistics
-- ----------------------------
INSERT INTO `statistics` VALUES (1, 1, 4, 9, '向阳大街', 67, 2, 4, 2, 444, 6, 5, '2023-01-12', '09:38:00', 2, 5, 1, '情况属实');
INSERT INTO `statistics` VALUES (2, 2, 6, 13, '向阳大街', 67, 2, 4, 2, 444, 2, 2, '2023-01-29', '10:38:00', 3, 7, 2, '情况属实');
INSERT INTO `statistics` VALUES (3, 3, 7, 15, '向阳大街', 5, 1, 2, 1, 5, 2, 1, '2023-02-24', '14:54:59', 2, 5, 3, '已完成监测');
INSERT INTO `statistics` VALUES (4, 1, 4, 10, '向阳大街', 5, 3, 2, 3, 5, 6, 4, '2023-03-16', '15:18:37', 3, 6, 4, '已完成监测');
INSERT INTO `statistics` VALUES (5, 2, 5, 12, '阳光大街', 5, 1, 2, 4, 5, 2, 3, '2023-03-28', '16:00:04', 4, 6, 5, '情况已核实');
INSERT INTO `statistics` VALUES (6, 3, 7, 16, '向阳大街', 300, 4, 5, 2, 666, 3, 3, '2023-03-31', '16:02:54', 3, 5, 6, '已完成监测');
INSERT INTO `statistics` VALUES (7, 1, 4, 9, '贴砍樵街道', 99, 2, 34, 3, 888, 3, 3, '2023-04-21', '16:03:53', 2, 6, 7, '已完成监测');
INSERT INTO `statistics` VALUES (8, 2, 5, 11, '百搭岭小学', 988, 5, 88, 5, 444, 5, 4, '2023-05-17', '16:04:36', 3, 7, 8, '已完成监测');
INSERT INTO `statistics` VALUES (9, 3, 8, 17, '喜久村', 5, 2, 2, 1, 5, 3, 1, '2023-05-29', '16:05:20', 4, 6, 9, '已完成监测');
INSERT INTO `statistics` VALUES (10, 2, 5, 12, '新时代商厦', 1234, 6, 55, 4, 1666, 6, 5, '2023-06-08', '16:17:01', 2, 5, 10, '已完成监测');
INSERT INTO `statistics` VALUES (11, 2, 6, 14, '向阳小学', 1234, 6, 99, 6, 444, 5, 6, '2023-06-22', '16:18:36', 3, 5, 11, '已完成监测');
INSERT INTO `statistics` VALUES (12, 3, 8, 18, '实验中学', 5, 1, 2, 1, 5, 1, 1, '2023-07-14', '16:40:19', 2, 6, 12, '已完成监测');
INSERT INTO `statistics` VALUES (13, 3, 7, 15, '爱华街', 5, 3, 2, 5, 5, 4, 6, '2023-08-28', '16:56:17', 3, 7, 13, '已完成监测');
INSERT INTO `statistics` VALUES (14, 2, 6, 13, '河北路38号', 60, 2, 3, 2, 100, 3, 3, '2023-08-30', '13:45:06', 4, 7, 14, '已检测完毕。');
INSERT INTO `statistics` VALUES (15, 1, 4, 10, '周末镇', 80, 2, 6, 3, 78, 3, 3, '2023-09-01', '10:05:03', 4, 5, 15, '已检测');
INSERT INTO `statistics` VALUES (16, 2, 5, 12, '河北大街', 300, 3, 6, 3, 249, 5, 5, '2023-09-03', '21:21:34', 4, 5, 26, '已完成实测');
INSERT INTO `statistics` VALUES (17, 1, 4, 9, '111', 444, 3, 6, 3, 88, 3, 3, '2023-09-08', '19:07:31', 2, 5, 16, '实测为三级');
INSERT INTO `statistics` VALUES (18, 1, 4, 9, '东城中心', 666, 4, 3, 2, 44, 2, 4, '2023-09-08', '19:33:00', 2, 8, 0, '已完成实测');
INSERT INTO `statistics` VALUES (19, 1, 4, 9, '东城中心', 444, 3, 5, 2, 69, 2, 3, '2023-09-08', '19:50:09', 3, 8, 24, '已完成实测');
INSERT INTO `statistics` VALUES (20, 1, 4, 9, '东城中心', 123, 2, 5, 2, 89, 3, 3, '2023-09-08', '19:54:00', 3, 8, 23, NULL);
INSERT INTO `statistics` VALUES (21, 3, 7, 16, '胶州一级中学', 111, 2, 4, 2, 109, 3, 3, '2023-09-08', '19:56:38', 3, 8, 20, '已完成实测');
INSERT INTO `statistics` VALUES (22, 2, 6, 13, '人民广场', 444, 3, 5, 2, 100, 3, 3, '2023-09-08', '20:07:45', 13, 6, 18, '已完成实测');
INSERT INTO `statistics` VALUES (23, 1, 4, 10, '恒安小区', 111, 2, 5, 2, 89, 3, 3, '2023-09-09', '10:14:42', 3, 5, 17, '已完成实测');
INSERT INTO `statistics` VALUES (24, 1, 4, 9, '东城中心', 123, 2, 4, 2, 89, 3, 3, '2023-09-09', '10:16:46', 2, 8, 22, '已完成实测');
INSERT INTO `statistics` VALUES (25, 3, 8, 18, '所开发为', 123, 2, 4, 2, 89, 3, 3, '2023-09-09', '10:41:32', 2, 5, 27, '已完成实测');
INSERT INTO `statistics` VALUES (26, 3, 8, 17, '德城中心', 123, 2, 4, 2, 144, 4, 4, '2023-09-09', '10:43:21', 3, 7, 25, '已完成实测');
INSERT INTO `statistics` VALUES (27, 1, 4, 10, '朝阳', 89, 2, 3, 2, 60, 2, 2, '2023-09-09', '10:44:06', 3, 5, 28, '已完成实测');
INSERT INTO `statistics` VALUES (28, 2, 5, 12, '安国', 123, 2, 4, 2, 89, 3, 3, '2023-09-09', '10:48:07', 12, 11, 29, '已完成实测');
INSERT INTO `statistics` VALUES (29, 3, 8, 17, '西南天下', 123, 2, 4, 2, 89, 3, 3, '2023-09-09', '10:53:58', 13, 7, 19, '已完成实测');
INSERT INTO `statistics` VALUES (30, 3, 7, 16, '胶州', 123, 2, 4, 2, 89, 3, 3, '2023-09-09', '10:54:20', 13, 10, 30, '已完成实测');
INSERT INTO `statistics` VALUES (31, 1, 4, 9, '东城', 123, 2, 4, 2, 89, 3, 3, '2023-09-09', '11:04:06', 2, 9, 32, '已完成实测');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '33333' COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `sex` int(0) DEFAULT NULL COMMENT '性别\r\n0：男\r\n1：女\r\n',
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '出生日期',
  `pId` int(0) DEFAULT NULL COMMENT '省份id',
  `cId` int(0) DEFAULT NULL COMMENT '城市id',
  `tId` int(0) DEFAULT NULL COMMENT '县/区id',
  `state` int(0) DEFAULT NULL COMMENT '网格员状态：\r\n0：可工作，\r\n1：临时抽调，\r\n2：休假，\r\n3：其他',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像信息',
  `roleId` int(0) DEFAULT 3 COMMENT '角色id：\r\n1：系统管理员，\r\n2：网格员，\r\n3：公众监督员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin1', '11111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (2, 'zhangsan', '22222', '章杉', '12222222221', 1, '1997-06-18', 1, 4, 9, 0, 'zhangsan.jpg', 2);
INSERT INTO `user` VALUES (3, 'lisi', '22222', '里斯', '12222222222', 0, '1985-12-25', 1, 4, 10, 1, NULL, 2);
INSERT INTO `user` VALUES (4, 'feiqin', '22222', '菲勤', '12222222223', 0, '2000-04-16', 2, 5, 11, 2, NULL, 2);
INSERT INTO `user` VALUES (5, 'pangke', '33333', '庞克', '13333333331', 0, '1988-09-30', NULL, NULL, NULL, NULL, 'pangke.jpg', 3);
INSERT INTO `user` VALUES (6, 'wangzan', '33333', '王赞', '13333333332', 1, '2000-11-15', NULL, NULL, NULL, NULL, 'wangzan.jpg', 3);
INSERT INTO `user` VALUES (7, 'xinxin', '33333', '信心', '13333333333', 1, '1999-01-01', NULL, NULL, NULL, NULL, NULL, 3);
INSERT INTO `user` VALUES (8, 'cookie', '33333', '王饼干', '13333333334', 1, '2000-11-25', NULL, NULL, NULL, NULL, NULL, 3);
INSERT INTO `user` VALUES (9, 'liuxing', '33333', '流星', '13333333335', 1, '1988-08-07', NULL, NULL, NULL, NULL, NULL, 3);
INSERT INTO `user` VALUES (10, 'zhaoer', '33333', '昭儿', '13333333336', 1, '1991-06-03', NULL, NULL, NULL, NULL, NULL, 3);
INSERT INTO `user` VALUES (11, 'lisisi', '33333', '李思思', '13333333337', 0, '2006-09-03', NULL, NULL, NULL, NULL, NULL, 3);
INSERT INTO `user` VALUES (12, 'aaaaa', '22222', 'aaa', '12222222226', 0, '2023-09-03', 2, 5, 12, 0, NULL, 2);
INSERT INTO `user` VALUES (13, 'zhaoxiao', '22222', '赵晓', '12222222224', 0, '1995-12-27', 2, 6, 13, 1, NULL, 2);
INSERT INTO `user` VALUES (14, 'kangkai', '22222', '康恺', '12222222225', 1, '1992-03-04', 2, 6, 14, 0, NULL, 2);
INSERT INTO `user` VALUES (15, 'xukang', '22222', '徐康', '12222222228', 1, '2023-09-04', 3, 7, 15, 2, NULL, 2);
INSERT INTO `user` VALUES (16, 'qiqi', '22222', '七七', '12222222227', 1, '1997-07-07', 2, 5, 11, 0, NULL, 2);
INSERT INTO `user` VALUES (17, 'xiangdong', '22222', '向东', '12222222229', 0, '2000-03-24', 1, 4, 10, 0, NULL, 2);

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES (1, '系统管理员');
INSERT INTO `userrole` VALUES (2, '网格员');
INSERT INTO `userrole` VALUES (3, '公众监督员');

SET FOREIGN_KEY_CHECKS = 1;
