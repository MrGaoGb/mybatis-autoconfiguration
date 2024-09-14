/*
 Navicat Premium Data Transfer

 Source Server         : Guns_开发环境(218)
 Source Server Type    : PostgreSQL
 Source Server Version : 110009
 Source Host           : 192.168.1.218:5432
 Source Catalog        : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110009
 File Encoding         : 65001

 Date: 14/09/2024 15:19:49
*/


-- ----------------------------
-- Table structure for prd_stork
-- ----------------------------
DROP TABLE IF EXISTS "public"."prd_stork";
CREATE TABLE "public"."prd_stork" (
  "id" serial NOT NULL,
  "pid" text COLLATE "pg_catalog"."default",
  "stork_count" text COLLATE "pg_catalog"."default",
  "p_name" text COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Primary Key structure for table prd_stork
-- ----------------------------
ALTER TABLE "public"."prd_stork" ADD CONSTRAINT "prd_stork_pkey" PRIMARY KEY ("id");
