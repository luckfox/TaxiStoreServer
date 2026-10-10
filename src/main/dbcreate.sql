-- 刪除資料庫（如果存在）
DROP DATABASE IF EXISTS TaxiStore;

-- 建立資料庫
CREATE DATABASE TaxiStore
CHARACTER SET utf8
COLLATE utf8_unicode_ci;

-- 使用資料庫
USE TaxiStore;

-- 建立表格：password
CREATE TABLE password (
                          id INT(5) NOT NULL PRIMARY KEY,
                          useDate DATETIME,
                          userNo TEXT(10),
                          userName TEXT(10),
                          password TEXT(10)
);

-- 建立表格：car
CREATE TABLE car (
                     carseq INT(5) NOT NULL PRIMARY KEY,
                     createdate DATETIME
);

-- 將資料從 dazhongyuan 移動到 TaxiStore
-- 移動表格 password
INSERT INTO TaxiStore.password (id, useDate, userNo, userName, PASSWORD)
SELECT id, useDate, userNo, userName, PASSWORD
FROM dazhongyuan.password;

-- 移動表格 car 的 carseq 和 createdate
INSERT INTO TaxiStore.car (carseq, createdate)
SELECT carseq, createdate
FROM dazhongyuan.car;

//複製 資料庫 dazhongyuan下的driver 表格，至資料庫 taxistore
CREATE TABLE taxistore.driver LIKE dazhongyuan.driver;

INSERT INTO taxistore.driver
SELECT * FROM dazhongyuan.driver;

CREATE TABLE taxistore.situation LIKE dazhongyuan.situation;

INSERT INTO taxistore.situation
SELECT * FROM dazhongyuan.situation;

CREATE TABLE taxistore.company LIKE dazhongyuan.company;

INSERT INTO taxistore.company
SELECT * FROM dazhongyuan.company;



CREATE TABLE taxistore.carbrand LIKE dazhongyuan.carbrand;

INSERT INTO taxistore.carbrand
SELECT * FROM dazhongyuan.carbrand;
