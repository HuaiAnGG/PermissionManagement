/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : permission

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 28/01/2021 13:35:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '技术部');
INSERT INTO `department` VALUES (2, '业务部');
INSERT INTO `department` VALUES (3, '后期部');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `inputtime` datetime NULL DEFAULT NULL COMMENT '入职时间',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '状态(在职 1 离职 0)',
  `admin` tinyint(1) NULL DEFAULT NULL COMMENT '是否为管理员(是 1，否 2)',
  `dep_id` bigint NULL DEFAULT NULL COMMENT '部门 id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_emp_dep`(`dep_id`) USING BTREE,
  CONSTRAINT `fk_emp_dep` FOREIGN KEY (`dep_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'laona', '67a63efa3e14d15911de1fd0437efeb9', '2020-12-28 16:00:00', '123456768900', '123@qq.com', 0, 1, 1);
INSERT INTO `employee` VALUES (41, '老衲', '0d6683c1d2510f72d368fd3c6596fda9', '2020-12-23 16:00:00', '1123123123', '123@qq.com', 1, 0, 1);
INSERT INTO `employee` VALUES (42, '老衲1', 'ef1338e9ea0524b9a06842811a087576', '2021-01-23 16:00:00', '1.3512345678E10', '123@qq.com', NULL, NULL, NULL);
INSERT INTO `employee` VALUES (43, '老衲2', '974cad639fa7640afe26d1a196605d26', '2021-01-23 16:00:00', '1.3512345678E10', '123@qq.com', NULL, NULL, NULL);
INSERT INTO `employee` VALUES (44, '老衲3', 'e12de959fe2f80dab35bff44c3260b16', '2021-01-23 16:00:00', '1.3512345678E10', '123@qq.com', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `parent_id` bigint NULL DEFAULT NULL,
  `permission_id` bigint NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (2, '员工管理', '/employee', 6, 4);
INSERT INTO `menu` VALUES (4, '角色权限管理', '/role', 6, 5);
INSERT INTO `menu` VALUES (6, '系统管理', '', NULL, NULL);
INSERT INTO `menu` VALUES (7, '菜单管理', '/menu', 6, 6);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `pid` bigint NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `presource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限可访问资源',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '员工添加', 'employee:add');
INSERT INTO `permission` VALUES (2, '员工删除', 'employee:delete');
INSERT INTO `permission` VALUES (3, '员工编辑', 'employee:edit');
INSERT INTO `permission` VALUES (4, '员工主页', 'employee:index');
INSERT INTO `permission` VALUES (5, '角色权限主页', 'role:index');
INSERT INTO `permission` VALUES (6, '菜单主页', 'menu:index');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rnum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编号(权限管理)',
  `rname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '管理员');
INSERT INTO `role` VALUES (2, 'hr', '人事');
INSERT INTO `role` VALUES (3, 'manager', '经理');

-- ----------------------------
-- Table structure for role_employee_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_employee_rel`;
CREATE TABLE `role_employee_rel`  (
  `eid` bigint NOT NULL COMMENT '员工id',
  `rid` bigint NOT NULL,
  PRIMARY KEY (`eid`, `rid`) USING BTREE,
  INDEX `fk_role`(`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_employee_rel
-- ----------------------------
INSERT INTO `role_employee_rel` VALUES (1, 1);
INSERT INTO `role_employee_rel` VALUES (36, 1);
INSERT INTO `role_employee_rel` VALUES (41, 1);
INSERT INTO `role_employee_rel` VALUES (37, 2);
INSERT INTO `role_employee_rel` VALUES (40, 2);
INSERT INTO `role_employee_rel` VALUES (35, 3);
INSERT INTO `role_employee_rel` VALUES (38, 3);

-- ----------------------------
-- Table structure for role_permisson_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_permisson_rel`;
CREATE TABLE `role_permisson_rel`  (
  `rid` bigint NOT NULL COMMENT '角色id',
  `pid` bigint NOT NULL COMMENT '权限id',
  PRIMARY KEY (`rid`, `pid`) USING BTREE,
  INDEX `fk_rel_permission`(`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permisson_rel
-- ----------------------------
INSERT INTO `role_permisson_rel` VALUES (2, 1);
INSERT INTO `role_permisson_rel` VALUES (3, 1);
INSERT INTO `role_permisson_rel` VALUES (2, 3);
INSERT INTO `role_permisson_rel` VALUES (1, 4);
INSERT INTO `role_permisson_rel` VALUES (2, 4);
INSERT INTO `role_permisson_rel` VALUES (3, 4);
INSERT INTO `role_permisson_rel` VALUES (3, 5);

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `optime` datetime NULL DEFAULT NULL,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `func` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 236 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES (1, '2021-01-25 10:36:00', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (2, '2021-01-25 10:36:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (3, '2021-01-25 10:36:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (4, '2021-01-25 10:36:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (5, '2021-01-25 10:36:02', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (6, '2021-01-25 10:36:09', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[1]');
INSERT INTO `system_log` VALUES (7, '2021-01-25 10:36:14', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (8, '2021-01-25 10:36:34', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (9, '2021-01-25 10:36:41', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (10, '2021-01-25 10:36:42', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (11, '2021-01-25 10:37:36', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (12, '2021-01-25 10:37:37', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (13, '2021-01-25 10:37:38', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (14, '2021-01-25 10:37:38', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (15, '2021-01-25 10:37:38', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (16, '2021-01-25 10:37:40', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (17, '2021-01-25 10:38:41', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (18, '2021-01-25 10:38:55', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (19, '2021-01-25 10:38:55', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (20, '2021-01-25 10:38:55', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (21, '2021-01-25 10:38:56', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (22, '2021-01-25 10:39:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (23, '2021-01-25 10:39:07', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (24, '2021-01-25 10:39:09', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (25, '2021-01-25 10:39:16', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (26, '2021-01-25 10:39:18', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (27, '2021-01-25 10:39:45', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (28, '2021-01-25 10:39:45', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (29, '2021-01-25 10:39:45', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (30, '2021-01-25 10:39:46', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (31, '2021-01-25 10:39:46', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (32, '2021-01-25 10:39:48', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (33, '2021-01-25 10:40:02', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (34, '2021-01-25 10:40:03', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (35, '2021-01-25 10:40:10', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (36, '2021-01-25 10:40:11', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (37, '2021-01-25 10:40:52', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (38, '2021-01-25 10:41:31', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (39, '2021-01-25 10:41:33', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (40, '2021-01-25 10:41:46', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (41, '2021-01-25 10:42:30', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (42, '2021-01-25 10:43:51', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (43, '2021-01-25 10:43:53', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (44, '2021-01-25 10:43:53', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (45, '2021-01-25 10:43:53', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (46, '2021-01-25 10:43:59', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (47, '2021-01-25 10:44:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (48, '2021-01-25 10:44:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (49, '2021-01-25 10:44:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (50, '2021-01-25 10:44:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (51, '2021-01-25 10:44:03', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (52, '2021-01-25 10:44:53', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (53, '2021-01-25 10:44:54', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (54, '2021-01-25 10:44:55', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (55, '2021-01-25 10:44:55', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (56, '2021-01-25 10:46:05', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (57, '2021-01-25 10:46:06', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (58, '2021-01-25 10:46:06', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (59, '2021-01-25 10:46:06', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (60, '2021-01-25 10:46:17', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (61, '2021-01-25 11:24:14', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (62, '2021-01-25 11:24:19', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (63, '2021-01-25 11:24:21', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (64, '2021-01-25 11:24:21', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (65, '2021-01-25 11:24:22', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (66, '2021-01-25 11:24:24', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (67, '2021-01-25 11:27:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:updateEmployee', '[{\"id\":41,\"username\":\"老衲\",\"password\":\"0d6683c1d2510f72d368fd3c6596fda9\",\"tel\":\"1123123123\",\"email\":\"971375353@qq.com\",\"inputtime\":\"2020-12-24\",\"state\":null,\"admin\":false,\"department\":{\"id\":1,\"name\":null},\"roles\":[{\"rid\":2,\"rnum\":null,\"rname\":null,\"permissions\":[]}]}]');
INSERT INTO `system_log` VALUES (68, '2021-01-25 11:27:13', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (69, '2021-01-25 11:27:38', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:getRoleByEid', '[41]');
INSERT INTO `system_log` VALUES (70, '2021-01-25 11:27:40', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:updateEmployee', '[{\"id\":41,\"username\":\"老衲\",\"password\":\"0d6683c1d2510f72d368fd3c6596fda9\",\"tel\":\"1123123123\",\"email\":\"971375353@qq.com\",\"inputtime\":\"2020-12-23\",\"state\":null,\"admin\":false,\"department\":{\"id\":1,\"name\":null},\"roles\":[{\"rid\":1,\"rnum\":null,\"rname\":null,\"permissions\":[]}]}]');
INSERT INTO `system_log` VALUES (71, '2021-01-25 11:27:40', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (72, '2021-01-25 11:48:42', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (73, '2021-01-25 11:48:43', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (74, '2021-01-25 11:48:44', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (75, '2021-01-25 11:48:44', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (76, '2021-01-25 11:48:44', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (77, '2021-01-25 11:49:19', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (78, '2021-01-25 11:49:20', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (79, '2021-01-25 11:49:20', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (80, '2021-01-25 11:49:20', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (81, '2021-01-25 11:50:47', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (82, '2021-01-25 11:50:48', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (83, '2021-01-25 11:50:48', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (84, '2021-01-25 11:50:49', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (85, '2021-01-25 11:50:49', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (86, '2021-01-25 11:50:58', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (87, '2021-01-25 11:50:58', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (88, '2021-01-25 11:50:58', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (89, '2021-01-25 11:50:59', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (90, '2021-01-25 11:55:27', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (91, '2021-01-25 11:55:28', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (92, '2021-01-25 11:55:28', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (93, '2021-01-25 11:55:28', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (94, '2021-01-25 11:56:09', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (95, '2021-01-25 11:56:10', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (96, '2021-01-25 11:56:10', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (97, '2021-01-25 11:56:10', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (98, '2021-01-25 13:12:11', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (99, '2021-01-25 13:12:13', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (100, '2021-01-25 13:12:14', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (101, '2021-01-25 13:12:14', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (102, '2021-01-25 13:12:14', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (103, '2021-01-25 13:12:24', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (104, '2021-01-25 13:13:30', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (105, '2021-01-25 13:13:32', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (106, '2021-01-25 13:13:32', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (107, '2021-01-25 13:13:32', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (108, '2021-01-25 13:13:33', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (109, '2021-01-25 13:14:38', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (110, '2021-01-25 13:14:39', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (111, '2021-01-25 13:14:39', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (112, '2021-01-25 13:14:39', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (113, '2021-01-25 13:14:39', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (114, '2021-01-25 13:14:40', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (115, '2021-01-25 13:18:24', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (116, '2021-01-25 13:18:25', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (117, '2021-01-25 13:18:25', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (118, '2021-01-25 13:18:25', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (119, '2021-01-25 13:18:47', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (120, '2021-01-25 13:18:59', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (121, '2021-01-25 13:19:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (122, '2021-01-25 13:19:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (123, '2021-01-25 13:19:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (124, '2021-01-25 13:22:17', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (125, '2021-01-25 13:22:17', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (126, '2021-01-25 13:22:18', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (127, '2021-01-25 13:22:18', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (128, '2021-01-25 13:22:18', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (129, '2021-01-25 13:22:20', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (130, '2021-01-25 13:23:24', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (131, '2021-01-25 13:23:24', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (132, '2021-01-25 13:23:24', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (133, '2021-01-25 13:23:24', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (134, '2021-01-25 13:23:34', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (135, '2021-01-25 13:23:34', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (136, '2021-01-25 13:23:34', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (137, '2021-01-25 13:23:34', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (138, '2021-01-25 13:23:34', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (139, '2021-01-25 13:23:36', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (140, '2021-01-25 13:24:10', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (141, '2021-01-25 13:50:58', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (142, '2021-01-25 13:50:59', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (143, '2021-01-25 13:50:59', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (144, '2021-01-25 13:50:59', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (145, '2021-01-25 13:51:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (146, '2021-01-25 13:51:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (147, '2021-01-25 13:54:30', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (148, '2021-01-25 13:54:30', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (149, '2021-01-25 13:54:30', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (150, '2021-01-25 13:54:30', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (151, '2021-01-25 13:54:32', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (152, '2021-01-25 14:51:41', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (153, '2021-01-25 14:51:42', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (154, '2021-01-25 14:51:43', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (155, '2021-01-25 14:51:43', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (156, '2021-01-25 14:51:43', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (157, '2021-01-26 05:59:00', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (158, '2021-01-26 05:59:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (159, '2021-01-26 05:59:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (160, '2021-01-26 05:59:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (161, '2021-01-26 05:59:02', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (162, '2021-01-26 06:24:43', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (163, '2021-01-26 06:24:44', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (164, '2021-01-26 06:24:44', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (165, '2021-01-26 06:24:44', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (166, '2021-01-26 06:25:00', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (167, '2021-01-26 06:25:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (168, '2021-01-26 06:25:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (169, '2021-01-26 06:25:01', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (170, '2021-01-26 08:04:21', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (171, '2021-01-26 08:04:21', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (172, '2021-01-26 08:04:22', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (173, '2021-01-26 08:04:22', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (174, '2021-01-26 08:04:22', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (175, '2021-01-26 08:09:55', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (176, '2021-01-26 08:09:56', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (177, '2021-01-26 08:09:56', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (178, '2021-01-26 08:09:57', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (179, '2021-01-26 08:09:57', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (180, '2021-01-26 08:10:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (181, '2021-01-26 08:10:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (182, '2021-01-26 08:10:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (183, '2021-01-26 08:10:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (184, '2021-01-26 08:15:49', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (185, '2021-01-26 08:15:50', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (186, '2021-01-26 08:15:51', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (187, '2021-01-26 08:15:51', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (188, '2021-01-26 08:15:51', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (189, '2021-01-26 08:57:50', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (190, '2021-01-26 08:57:51', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (191, '2021-01-26 08:57:52', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (192, '2021-01-26 08:57:52', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (193, '2021-01-26 08:57:52', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (194, '2021-01-26 09:02:37', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (195, '2021-01-26 09:02:38', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (196, '2021-01-26 09:02:38', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (197, '2021-01-26 09:02:38', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (198, '2021-01-26 09:02:39', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (199, '2021-01-26 09:15:11', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (200, '2021-01-26 09:15:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (201, '2021-01-26 09:15:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (202, '2021-01-26 09:15:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (203, '2021-01-26 09:15:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (204, '2021-01-26 09:15:30', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:save', '[{\"id\":null,\"username\":\"老衲1\",\"password\":null,\"tel\":\"1.3512345678E10\",\"email\":\"971375353@qq.com\",\"inputtime\":\"2021-01-23\",\"state\":null,\"admin\":null,\"department\":null,\"roles\":[]}]');
INSERT INTO `system_log` VALUES (205, '2021-01-26 10:33:44', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (206, '2021-01-26 10:33:44', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (207, '2021-01-26 10:33:45', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (208, '2021-01-26 10:33:45', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (209, '2021-01-26 10:33:45', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (210, '2021-01-26 10:33:52', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:save', '[{\"id\":null,\"username\":\"老衲1\",\"password\":null,\"tel\":\"1.3512345678E10\",\"email\":\"971375353@qq.com\",\"inputtime\":\"2021-01-23\",\"state\":null,\"admin\":null,\"department\":null,\"roles\":[]}]');
INSERT INTO `system_log` VALUES (211, '2021-01-26 10:35:12', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (212, '2021-01-26 10:35:13', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (213, '2021-01-26 10:35:14', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (214, '2021-01-26 10:35:14', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (215, '2021-01-26 10:35:25', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:save', '[{\"id\":null,\"username\":\"老衲1\",\"password\":null,\"tel\":\"1.3512345678E10\",\"email\":\"971375353@qq.com\",\"inputtime\":\"2021-01-23\",\"state\":null,\"admin\":null,\"department\":null,\"roles\":[]}]');
INSERT INTO `system_log` VALUES (216, '2021-01-26 10:36:33', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (217, '2021-01-26 10:36:33', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (218, '2021-01-26 10:36:33', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (219, '2021-01-26 10:36:33', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (220, '2021-01-26 10:36:39', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:save', '[{\"id\":42,\"username\":\"老衲1\",\"password\":\"ef1338e9ea0524b9a06842811a087576\",\"tel\":\"1.3512345678E10\",\"email\":\"971375353@qq.com\",\"inputtime\":\"2021-01-23\",\"state\":null,\"admin\":null,\"department\":null,\"roles\":[]}]');
INSERT INTO `system_log` VALUES (221, '2021-01-26 10:36:39', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:save', '[{\"id\":43,\"username\":\"老衲2\",\"password\":\"974cad639fa7640afe26d1a196605d26\",\"tel\":\"1.3512345678E10\",\"email\":\"971375353@qq.com\",\"inputtime\":\"2021-01-23\",\"state\":null,\"admin\":null,\"department\":null,\"roles\":[]}]');
INSERT INTO `system_log` VALUES (222, '2021-01-26 10:36:39', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:save', '[{\"id\":44,\"username\":\"老衲3\",\"password\":\"e12de959fe2f80dab35bff44c3260b16\",\"tel\":\"1.3512345678E10\",\"email\":\"971375353@qq.com\",\"inputtime\":\"2021-01-23\",\"state\":null,\"admin\":null,\"department\":null,\"roles\":[]}]');
INSERT INTO `system_log` VALUES (223, '2021-01-26 10:36:39', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (224, '2021-01-26 10:38:33', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (225, '2021-01-26 10:39:04', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (226, '2021-01-26 10:39:04', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (227, '2021-01-26 10:39:05', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (228, '2021-01-26 10:39:05', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (229, '2021-01-26 10:39:06', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (230, '2021-01-26 10:44:14', NULL, 'wiki.laona.service.impl.EmployeeServiceImpl:getEmployeeByUsername', '[\"laona\"]');
INSERT INTO `system_log` VALUES (231, '2021-01-26 10:44:14', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.MenuServiceImpl:getTreeMenu', '[]');
INSERT INTO `system_log` VALUES (232, '2021-01-26 10:44:15', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.DepartmentServiceImpl:getAllDepartment', '[]');
INSERT INTO `system_log` VALUES (233, '2021-01-26 10:44:15', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.RoleServiceImpl:roleList', '[]');
INSERT INTO `system_log` VALUES (234, '2021-01-26 10:44:15', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');
INSERT INTO `system_log` VALUES (235, '2021-01-26 10:44:17', '0:0:0:0:0:0:0:1', 'wiki.laona.service.impl.EmployeeServiceImpl:getAllEmployee', '[{\"page\":1,\"rows\":10,\"keyword\":null}]');

SET FOREIGN_KEY_CHECKS = 1;
