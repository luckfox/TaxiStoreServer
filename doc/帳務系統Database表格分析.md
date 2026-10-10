### 表格：fee

* 說明：定義會計科目；用來定義每個科目的功能

CREATE TABLE `fee` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,	//科目序號
 `code` varchar(255) DEFAULT NULL,					//科目代碼
 `accountCode` varchar(255) DEFAULT NULL,		//科目名稱
 `payForm` varchar(255) DEFAULT NULL,			  //產生代付單
 `genForm` varchar(255) DEFAULT NULL,			  //產生同行代收單
 `recvForm` varchar(255) DEFAULT NULL,			   //產生收款單
 `billByDays` varchar(255) DEFAULT NULL,			//按天數過帳
 `notToIncome` varchar(255) DEFAULT NULL,		 //不轉營業項目
 `notToAccount` varchar(255) DEFAULT NULL,		//不轉會計帳
 `special` varchar(255) DEFAULT NULL,				//本列是否能刪除
 PRIMARY KEY (`id`),
 UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8

### 表格：fine

* 說明：罰單欄位

CREATE TABLE `fine` (
 `fineSeq` bigint(20) NOT NULL AUTO_INCREMENT,			//罰單序號
 `createdate` datetime DEFAULT NULL,								 //罰單建檔時間
 `fineNo` varchar(255) DEFAULT NULL,								 //罰單編號
 `tactName` varchar(255) DEFAULT NULL,							 //罰單車輛車主
 `fineDate` datetime DEFAULT NULL,									//違規日期
 `fineHH` int(11) DEFAULT NULL,											//違規時
 `fineNN` int(11) DEFAULT NULL,											//違規分
 `situationId` bigint(20) DEFAULT NULL,							  //司機和車輛的連結序號 
 `fineRuleId` bigint(20) DEFAULT NULL,								//違規法規之條
 `fineContact` varchar(255) DEFAULT NULL,						 //違規法規之項
 `fineCreate` varchar(255) DEFAULT NULL,						   //違規法規之款 
 `fineClDate` datetime DEFAULT NULL,								 
 `amount` int(11) DEFAULT NULL,											//違規金額	
 `fineAdd` varchar(255) DEFAULT NULL,								//違規地點
 `fineText1` varchar(255) DEFAULT NULL,				
 `fineText2` varchar(255) DEFAULT NULL,
 `passDate` datetime DEFAULT NULL,
 `fineStatus` varchar(255) DEFAULT NULL,
 `payDate` datetime DEFAULT NULL,
 `payStatus` varchar(255) DEFAULT NULL,
 `remark` varchar(5000) DEFAULT NULL,
 PRIMARY KEY (`fineSeq`),
 KEY `FK20EDBA392EB883` (`fineRuleId`),
 KEY `FK20EDBACFCDD7BB` (`situationId`),
 KEY `fine_fineno` (`fineNo`),
 CONSTRAINT `FK20EDBA392EB883` FOREIGN KEY (`fineRuleId`) REFERENCES `fine_rule` (`fineRuleSeq`),
 CONSTRAINT `FK20EDBACFCDD7BB` FOREIGN KEY (`situationId`) REFERENCES `situation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13945 DEFAULT CHARSET=utf8





//收款單據細目，一張收款單據(收款/代付)，可以包含多項細目。

CREATE TABLE `recventry` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,				//收款單據細目序號	
 `FORMID` bigint(20) DEFAULT NULL,								  //收款單據單號	
 `acc` bigint(20) DEFAULT NULL,										//細目之科目序號。
 `summary` varchar(5000) DEFAULT NULL,						 //收款單據細目說明
 `debitcredit` varchar(255) DEFAULT NULL,					//借方，貸方(收款D，代付C)
 `amount` int(11) DEFAULT NULL,										//細目金額
 `noteno` varchar(255) DEFAULT NULL,							 //單據編號，例如罰單入帳號，則存放發單編號。
 `notebank` varchar(255) DEFAULT NULL,
 `notebranch` varchar(255) DEFAULT NULL,			
 `notepaydate` datetime DEFAULT NULL,								
 `RecvFormId` bigint(20) DEFAULT NULL,							//收款單據單號
 `entryorder` int(11) DEFAULT NULL,								 //細目在收款單據中的序號(順序)
 PRIMARY KEY (`id`),
 UNIQUE KEY `noteno_UNIQUE` (`noteno`),
 KEY `FKEC8FFE2C4C79536B` (`RecvFormId`),
 KEY `FKEC8FFE2CC3490D2B` (`acc`),
 KEY `FKEC8FFE2C76917E05` (`FORMID`),
 CONSTRAINT `FKEC8FFE2C4C79536B` FOREIGN KEY (`RecvFormId`) REFERENCES `recvform` (`id`),
 CONSTRAINT `FKEC8FFE2C76917E05` FOREIGN KEY (`FORMID`) REFERENCES `recvform` (`id`),
 CONSTRAINT `FKEC8FFE2CC3490D2B` FOREIGN KEY (`acc`) REFERENCES `fee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=155303 DEFAULT CHARSET=utf8



/*
駕駛員收款作業

每個收款作業會有數張收款單和代付單所組成。

*/

CREATE TABLE `recvform` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,		//收款單據序號。
 `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,	//單據建檔時間
 `situation` bigint(20) DEFAULT NULL,					//司機和車輛的連結序號
 `type` varchar(255) DEFAULT NULL,						//代收/代付
 `remark` varchar(5000) DEFAULT NULL,				  //註釋	
 `userNo` varchar(255) DEFAULT NULL,					//建檔人員代碼
 `userName` varchar(255) DEFAULT NULL,				//建檔人員姓名
 PRIMARY KEY (`id`),
 KEY `FKFF5FF3AA367C5220` (`situation`),
 CONSTRAINT `FKFF5FF3AA367C5220` FOREIGN KEY (`situation`) REFERENCES `situation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133194 DEFAULT CHARSET=utf8



/*

付款作業。

付款作業可能來自各項 車租、租送車分期款、保全、行費、借款等自動過帳產生的科目

*/

CREATE TABLE `payform` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,			//付款單據序號。
 `createdate` datetime DEFAULT NULL,						//單據建檔時間
 `situation` bigint(20) DEFAULT NULL,						//司機和車輛的連結序號
 `type` varchar(255) DEFAULT NULL,							//代收/代付
 `remark` varchar(5000) DEFAULT NULL,					 //註釋		
 `userNo` varchar(255) DEFAULT NULL,						//建檔人員代碼
 `userName` varchar(255) DEFAULT NULL,					//建檔人員姓名
 PRIMARY KEY (`id`),
 KEY `FKFBE4B54C367C5220` (`situation`),
 CONSTRAINT `FKFBE4B54C367C5220` FOREIGN KEY (`situation`) REFERENCES `situation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29099 DEFAULT CHARSET=utf8