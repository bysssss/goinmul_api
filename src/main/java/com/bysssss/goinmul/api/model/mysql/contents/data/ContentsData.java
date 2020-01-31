package com.bysssss.goinmul.api.model.mysql.contents.data;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContentsData {
	Long seq;
	Long memberSeq;
	String contentsTitle;
	String contentsDesc;
	Integer contentsCount;
	Integer testInt;
	Double testDbl;
	Double testGps;
	String testYn;
	Date createAt;
	Date updateAt;
}
/*
CREATE TABLE IF NOT EXISTS `TB_TEST_CONTENTS` (
`SEQ` BIGINT NOT NULL AUTO_INCREMENT,
`MEMBER_SEQ` BIGINT NOT NULL,
`CONTENTS_TITLE` VARCHAR(128) NOT NULL,
`CONTENTS_DESC` TEXT DEFAULT NULL,
`CONTENTS_COUNT` INT UNSIGNED DEFAULT 0,
`TEST_INT` INT DEFAULT NULL,
`TEST_DBL` DOUBLE DEFAULT NULL,
`TEST_GPS` DECIMAL(13,10) DEFAULT NULL,
`TEST_YN` CHAR(1) DEFAULT 'N',
`CREATE_AT` DATETIME DEFAULT CURRENT_TIMESTAMP,
`UPDATE_AT` DATETIME DEFAULT NULL,
PRIMARY KEY (`SEQ`),
INDEX `IDX_CNTS_0` (`CONTENTS_TITLE`),
INDEX `IDX_CNTS_1` (`CREATE_AT`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/