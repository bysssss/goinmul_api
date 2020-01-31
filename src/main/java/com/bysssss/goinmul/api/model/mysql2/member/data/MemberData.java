package com.bysssss.goinmul.api.model.mysql2.member.data;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of={"seq"}) // @EqualsAndHashCode(of={"memberId","memberPw"})
@Builder
@Data
public class MemberData {
	Long seq;
	String memberId;
	String memberPw;
	Integer memberCd;
	String memberType;
	String memberNickname;
	String memberThumbnailUrl;
	String memberSex;
	String memberInfo;
	String cellphone;
	String telephone;
	Date passwordAt;
	Date createAt;
	Date updateAt;
}
/*
CREATE TABLE IF NOT EXISTS `TB_TEST_MEMBER` (
`SEQ` BIGINT NOT NULL AUTO_INCREMENT,
`MEMBER_ID` VARCHAR(100) NOT NULL,
`MEMBER_PW` VARCHAR(100) NOT NULL,
`MEMBER_CD` INT DEFAULT 0,
`MEMBER_TYPE` VARCHAR(16) DEFAULT 'TYPE0',
`MEMBER_NICKNAME` VARCHAR(50) DEFAULT NULL,
`MEMBER_THUMBNAIL_URL` VARCHAR(128) DEFAULT NULL,
`MEMBER_SEX` VARCHAR(16) DEFAULT NULL,
`MEMBER_INFO` TEXT DEFAULT NULL,
`CELLPHONE` VARCHAR(50) NOT NULL,
`TELEPHONE` VARCHAR(50) DEFAULT NULL,
`PASSWORD_AT` DATETIME DEFAULT CURRENT_TIMESTAMP,
`CREATE_AT` DATETIME DEFAULT CURRENT_TIMESTAMP,
`UPDATE_AT` DATETIME DEFAULT NULL,
PRIMARY KEY (`SEQ`),
UNIQUE `UNIQ_MEM_0` (`MEMBER_ID`),
INDEX `IDX_MEM_0` (`MEMBER_ID`,`MEMBER_PW`),
INDEX `IDX_MEM_1` (`MEMBER_CD`),
INDEX `IDX_MEM_2` (`MEMBER_TYPE`),
INDEX `IDX_MEM_3` (`MEMBER_NICKNAME`,`MEMBER_SEX`),
INDEX `IDX_MEM_4` (`CELLPHONE`),
INDEX `IDX_MEM_5` (`PASSWORD_AT`)
)ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8;
*/