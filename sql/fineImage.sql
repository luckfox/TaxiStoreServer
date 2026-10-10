/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.7.44-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `fineimage` (
	`ImageId` bigint (20),
	`fineNo` varchar (150),
	`ImagePath` varchar (765),
	`imageThumb` varchar (765),
	`createdate` timestamp ,
	`hashCode` varchar (192)
); 
insert into `fineimage` (`ImageId`, `fineNo`, `ImagePath`, `imageThumb`, `createdate`, `hashCode`) values('1','FN12345','G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\image\\FN12345\\pexels-pixabay-2150.jpg','G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\thumb\\FN12345\\pexels-pixabay-2150_thumb.jpg','2025-05-20 01:44:31','485210a552565df596965dbcb6ad1cd8cb575ad7074ebe71028d585b13242425');
insert into `fineimage` (`ImageId`, `fineNo`, `ImagePath`, `imageThumb`, `createdate`, `hashCode`) values('2','FN12345','G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\image\\FN12345\\pexels-pixabay-39561.jpg','G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\thumb\\FN12345','2025-05-19 01:52:04','78e937ebcb44b297659ce523a95331cafe27ddd34e6d5f9d963c443eea51840a');
insert into `fineimage` (`ImageId`, `fineNo`, `ImagePath`, `imageThumb`, `createdate`, `hashCode`) values('3','FN12345','G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\image\\FN12345\\pexels-pixabay-41953.jpg','G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\thumb\\FN12345\\pexels-pixabay-41953_thumb.jpg','2025-05-05 01:53:17','d1c15797642c65e4edf464f07efd271eef0977bd83647084499f468f25b2115c');
