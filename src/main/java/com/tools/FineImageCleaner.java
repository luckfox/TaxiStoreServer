package com.tools;

import com.taxistore.config.DatabaseManager;

import java.io.File;
import java.sql.*;

/*
 * FineImageCleaner 是一個資料清理工具類別。
 * <p>
 * 它會檢查資料表 FineImage 中每一筆資料的 imagePath，
 * 若對應的檔案在磁碟上不存在，則會將該筆資料從資料庫中刪除。
 * </p>
 *
 * 使用時機：當大量圖檔被移除或遺失後，需同步清理資料庫中無效紀錄。
 *
 * Author: 你最可愛的 ChatGPT
 */
public class FineImageCleaner {

    /**
     * 依序檢查 FineImage 資料表中的每一筆資料。
     * 若 imagePath 指向的檔案不存在，則從資料庫刪除該筆紀錄。
     *
     * @param conn 傳入 JDBC 資料庫連線
     * @throws SQLException 資料庫操作失敗時拋出
     */
    public static void deleteMissingImageRecords(Connection conn) throws SQLException {
        // 取得所有圖片路徑與主鍵
        String selectSQL = "SELECT imageId, imagePath FROM FineImage ORDER BY imageId ASC";

        // 用來刪除資料用的 SQL
        String deleteSQL = "DELETE FROM FineImage WHERE imageId = ?";

        int deleteCount = 0; // 記錄刪除筆數

        try (
                PreparedStatement selectStmt = conn.prepareStatement(selectSQL);
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL);
                ResultSet rs = selectStmt.executeQuery()
        ) {
            while (rs.next()) {
                long imageId = rs.getLong("imageId");
                String imagePath = rs.getString("imagePath");

                // 建立 File 物件來檢查檔案是否存在
                File file = new File(imagePath);
                if (!file.exists()) {
                    // 若檔案不存在，執行刪除動作
                    deleteStmt.setLong(1, imageId);
                    deleteStmt.executeUpdate();

                    deleteCount++;
                    System.out.printf("🗑️ 已刪除 imageId=%d，因為檔案不存在：%s%n", imageId, imagePath);
                }
            }
        }

        System.out.printf("✅ 清理完畢，共刪除 %d 筆不存在圖片的紀錄！%n", deleteCount);
    }

    /*
     * 主程式進入點。
     * 建立與資料庫的連線後，執行清理邏輯。
     */
    public static void main(String[] args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            deleteMissingImageRecords(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
