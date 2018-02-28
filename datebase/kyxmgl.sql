/*
Navicat MySQL Data Transfer

Source Server         : hyk
Source Server Version : 50717
Source Host           : 119.29.142.195:3306
Source Database       : kyxmgl

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-02-28 20:14:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) NOT NULL COMMENT '登录账号/用户名',
  `adminRealName` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_applicant
-- ----------------------------
DROP TABLE IF EXISTS `t_applicant`;
CREATE TABLE `t_applicant` (
  `applicantId` varchar(40) NOT NULL COMMENT '项目负责人主键guid',
  `applicantName` varchar(20) NOT NULL COMMENT '项目负责人姓名',
  `applicantSex` varchar(10) DEFAULT NULL COMMENT '项目负责人性别',
  `applicantBirth` date DEFAULT NULL COMMENT '项目负责人出生日期',
  `applicantEdu` varchar(10) DEFAULT NULL COMMENT '项目负责人学历',
  `applicantUniv` varchar(100) DEFAULT NULL COMMENT '项目负责人毕业院校',
  `aResDirection` varchar(100) DEFAULT NULL COMMENT '项目负责人主要研究方向',
  `applicantInfo` varchar(255) DEFAULT NULL COMMENT '项目负责人简介',
  `applicantTel` varchar(15) DEFAULT NULL COMMENT '项目负责人电话',
  `applicantMailbox` varchar(30) DEFAULT NULL COMMENT '负责人邮箱',
  `applicantImgurl` varchar(100) DEFAULT NULL COMMENT '负责人证件照存放路径',
  PRIMARY KEY (`applicantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `companyId` varchar(40) NOT NULL COMMENT '项目合作单位唯一guid',
  `companyName` varchar(100) NOT NULL COMMENT '项目合作单位名称',
  `companyType` int(11) DEFAULT NULL COMMENT '单位类型',
  `companyAddress` varchar(200) DEFAULT NULL COMMENT '单位地址',
  `cResDirection` varchar(100) DEFAULT NULL COMMENT '单位研究方向',
  `companyInfo` varchar(255) DEFAULT NULL COMMENT '合作单位简要信息',
  `companyWebUrl` varchar(50) DEFAULT NULL COMMENT '单位网址',
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_company_type
-- ----------------------------
DROP TABLE IF EXISTS `t_company_type`;
CREATE TABLE `t_company_type` (
  `companyTypeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '单位类型id',
  `companyTypeName` varchar(100) NOT NULL COMMENT '单位类型名称',
  PRIMARY KEY (`companyTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_pro_app
-- ----------------------------
DROP TABLE IF EXISTS `t_pro_app`;
CREATE TABLE `t_pro_app` (
  `proAppId` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目-负责人关联表  id',
  `proId` varchar(40) NOT NULL COMMENT '项目guid',
  `applicantId` varchar(40) NOT NULL COMMENT '参与人/联系人 id',
  `applicantType` int(11) NOT NULL COMMENT '0负责人，1项目联系人，2项目参与人',
  PRIMARY KEY (`proAppId`),
  KEY `proIdInProApp` (`proId`),
  KEY `appIdInProApp` (`applicantId`),
  CONSTRAINT `appIdInProApp` FOREIGN KEY (`applicantId`) REFERENCES `t_applicant` (`applicantId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `proIdInProApp` FOREIGN KEY (`proId`) REFERENCES `t_project` (`proId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8 COMMENT='科研项目与项目负责人的关联，这里考虑的是一个项目有多个负责人，一个负责人可能有多个项目';

-- ----------------------------
-- Table structure for t_pro_company
-- ----------------------------
DROP TABLE IF EXISTS `t_pro_company`;
CREATE TABLE `t_pro_company` (
  `proCompanyId` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目-合作单位关联表  id',
  `proId` varchar(40) NOT NULL COMMENT '外键，项目guid',
  `companyId` varchar(40) NOT NULL COMMENT '外键，项目合作单位guid',
  `isMain` int(11) DEFAULT NULL COMMENT '承担单位/合作单位',
  PRIMARY KEY (`proCompanyId`),
  KEY `proIdInProCompany` (`proId`),
  KEY `companyIdInProCompany` (`companyId`),
  CONSTRAINT `companyIdInProCompany` FOREIGN KEY (`companyId`) REFERENCES `t_company` (`companyId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `proIdInProCompany` FOREIGN KEY (`proId`) REFERENCES `t_project` (`proId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8 COMMENT='关联company，以获取 承担单位名称、单位类型、单位地址 等';

-- ----------------------------
-- Table structure for t_pro_type
-- ----------------------------
DROP TABLE IF EXISTS `t_pro_type`;
CREATE TABLE `t_pro_type` (
  `proTypeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目类型guiid',
  `proTypeName` varchar(100) NOT NULL COMMENT '项目类型名',
  PRIMARY KEY (`proTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `proId` varchar(40) NOT NULL COMMENT 'guid',
  `proNum` varchar(40) NOT NULL COMMENT '项目编号',
  `proName` varchar(255) NOT NULL COMMENT '科研项目名',
  `proLeaderId` varchar(40) DEFAULT NULL COMMENT '项目负责人guid',
  `proCompanyId` varchar(40) DEFAULT NULL COMMENT '项目承担企业guid',
  `proStartTime` date DEFAULT NULL COMMENT '项目起始时间',
  `proEndTime` date DEFAULT NULL COMMENT '项目完成时间',
  `proType` int(11) DEFAULT NULL COMMENT '项目类型id',
  `proAscription` varchar(50) DEFAULT NULL COMMENT '项目归口处室，归属部门',
  `techSrc` varchar(50) DEFAULT NULL COMMENT '技术来源',
  `industryVal` decimal(14,4) DEFAULT '0.0000' COMMENT '累计工业或农业总产值',
  `saleVal` decimal(14,4) DEFAULT '0.0000' COMMENT '累计销售收入',
  `profit` decimal(14,4) DEFAULT '0.0000' COMMENT '累计利润总额',
  `payTaxes` decimal(14,4) DEFAULT '0.0000' COMMENT ' 累计缴税总额',
  `foreignExchange` decimal(14,4) DEFAULT '0.0000' COMMENT '累计创汇额',
  `addIndustryVal` decimal(14,4) DEFAULT '0.0000' COMMENT '新增累计工业或农业总产值',
  `addSaleVal` decimal(14,4) DEFAULT '0.0000' COMMENT '新增累计销售收入',
  `addProfit` decimal(14,4) DEFAULT '0.0000' COMMENT '新增累计利润总额',
  `addPayTaxes` decimal(14,4) DEFAULT '0.0000' COMMENT ' 新增累计缴税总额',
  `addForeignExchange` decimal(14,4) DEFAULT '0.0000' COMMENT '新增累计创汇额',
  `employment` int(11) DEFAULT '0' COMMENT '增加就业人数',
  `personnel` int(11) DEFAULT '0' COMMENT '培养科技创新人才',
  `doctor` int(11) DEFAULT '0' COMMENT ' 取得博士学位人数',
  `master` int(11) DEFAULT '0' COMMENT '取得硕士学位',
  `senior` int(11) DEFAULT '0' COMMENT '晋升高级职称人数',
  `mid` int(11) DEFAULT '0' COMMENT '晋升中级职称人数',
  `newProd` int(11) DEFAULT '0' COMMENT '新产品/软件（个）',
  `newEqNum` int(11) DEFAULT '0' COMMENT '新设备（台/件）',
  `newMaterial` int(11) DEFAULT '0' COMMENT '新材料（种）',
  `newTechnique` int(11) DEFAULT '0' COMMENT '新技术',
  `newProcess` int(11) DEFAULT '0' COMMENT '新工艺（项）',
  `patentApply` int(11) DEFAULT '0' COMMENT '申请专利数',
  `patentAdopt` int(11) DEFAULT '0' COMMENT '授权专利数',
  `invPatApply` int(11) DEFAULT '0' COMMENT '发明专利申请数',
  `invPatAdopt` int(11) DEFAULT '0' COMMENT '发明专利授权数',
  `sftCopyrightApply` int(11) DEFAULT '0' COMMENT '软件著作权（项）申请数',
  `sftCopyrightAdopt` int(11) DEFAULT '0' COMMENT '软件著作权（项）授权数',
  `provincePaper` int(11) DEFAULT '0' COMMENT '发表论文数（篇）省级以上科技刊物',
  `otherPaper` int(11) DEFAULT '0' COMMENT '发表论文数（篇）其他刊物',
  `nationalAwards` int(11) DEFAULT '0' COMMENT '国家级奖项',
  `provincialAwards` int(11) DEFAULT '0' COMMENT '省级奖项',
  `municipalAwards` int(11) DEFAULT '0' COMMENT '市级奖项',
  `otherAwards` int(11) DEFAULT '0' COMMENT '其他类型奖项',
  `ets` int(11) DEFAULT '0' COMMENT '企业技术标准Enterprise technical standard',
  `indts` int(11) DEFAULT '0' COMMENT '行业技术标准',
  `nts` int(11) DEFAULT '0' COMMENT '国家技术标准National technical standard',
  `intts` int(11) DEFAULT '0' COMMENT '国际技术标准International technical standard',
  PRIMARY KEY (`proId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
