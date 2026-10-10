package com.tools;
import com.taxistore.config.DatabaseManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
/*
    將excel的資料表單，讀取資料轉存到mySQL資料庫中
 */
public class ExcelImporter {
    public static void main(String[] args) {
        String excelPath="G:\\我的雲端硬碟\\大中原\\保單\\114\\豪城\\";
        String sourceFile="豪城大保單名冊2.xlsx";
        String excelFilePath = excelPath+sourceFile; // Excel 檔案路徑
        String jdbcUrl = "jdbc:mysql://localhost:3309/taxistore?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "mysql123";
        File excelFile = new File(excelFilePath);
        File directory = excelFile.getParentFile(); // Excel 所在的目錄
        int serialCounter = 1;

        try (
                FileInputStream fis = new FileInputStream(excelFilePath);
                Workbook workbook = new XSSFWorkbook(fis);
                Connection conn = DatabaseManager.getConnection()
        ) {

            // === 查詢目前最大 serialNo ===

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT MAX(serialNo) FROM travel_insurance")) {
                if (rs.next()) {
                    serialCounter = rs.getInt(1) + 1;
                }
            }

            Sheet sheet = workbook.getSheetAt(0);
            String insertSql = "INSERT INTO travel_insurance (serialNo , filename,carNo, companyseq) VALUES (?,?,?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            // 從第1列開始（不跳過表頭,如果要跳過表頭,rowIndex=1）
            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                //Cell cellA = row.getCell(0); // A欄：自增序號
                Cell cellB = row.getCell(1); // B欄：檔案名稱
                Cell cellC = row.getCell(2); // C欄：車號
                Cell cellD = row.getCell(3); // D欄：車行代號

                if (cellB == null || cellC == null || cellD == null) continue;

                String originalFileName = cellB.getStringCellValue().trim()+".jpg";
                String carNo = cellC.getStringCellValue().trim();
                int companySeq = (int) cellD.getNumericCellValue();

                String newFileName = carNo + ".jpg";
                File oldImageFile = new File(directory, originalFileName);
                File newImageFile = new File(directory, newFileName);
                // 嘗試改名
                if (oldImageFile.exists()) {
                    if (!oldImageFile.getName().equals(newImageFile.getName())) {
                        boolean renamed = oldImageFile.renameTo(newImageFile);
                        if (!renamed) {
                            System.out.println("⚠️ 檔案改名失敗：" + oldImageFile.getName() + " → " + newFileName);
                        }
                    }
                } else {
                    System.out.println("📂 找不到圖片：" + oldImageFile.getName() + "，仍繼續寫入資料庫。");
                }


                // 檔案改名（如原檔和新檔不同才進行）
                if (!oldImageFile.getName().equals(newImageFile.getName())) {
                    boolean renamed = oldImageFile.renameTo(newImageFile);
                    if (!renamed) {
                        System.out.println("改名失敗：" + oldImageFile.getName() + " → " + newFileName);
                        continue;
                    }
                }
                pstmt.setInt(1, serialCounter++);
                pstmt.setString(2, originalFileName);
                pstmt.setString(3, carNo);
                pstmt.setInt(4, companySeq);
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("匯入完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
