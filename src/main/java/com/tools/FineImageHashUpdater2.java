package com.tools;

import com.taxistore.config.DatabaseManager;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import static com.utils.Sha256Hex.quickJpegHash;

public class FineImageHashUpdater2 {

    /*
     * 遍歷 FineImage 表格，依 imageId 逐筆更新 hashCode。
     * 若檔案不存在或發生例外，將略過並繼續處理下一筆。
     */
    public static void updateAllImageHashes(Connection conn) throws SQLException {
        String selectSQL = "SELECT imageId, imagePath FROM FineImage ORDER BY imageId ASC";
        String updateSQL = "UPDATE FineImage SET hashCode = ? WHERE imageId = ?";

        try (
                PreparedStatement selectStmt = conn.prepareStatement(selectSQL);
                PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
                ResultSet rs = selectStmt.executeQuery()
        ) {
            while (rs.next()) {
                long imageId = rs.getLong("imageId");
                String imagePath = rs.getString("imagePath");

                File file = new File(imagePath);
                String hash = null;

                if (!file.exists()) {
                    System.err.printf("❌ 檔案不存在：imageId=%d, path=%s%n", imageId, imagePath);
                } else {
                    try {
                        hash = quickJpegHash(file);
                    } catch (IOException | NoSuchAlgorithmException e) {
                        System.err.printf("⚠️ 計算失敗 imageId=%d, path=%s：%s%n", imageId, imagePath, e.getMessage());
                    }
                }

                updateStmt.setString(1, hash); // 若 hash 為 null，會寫入 NULL
                updateStmt.setLong(2, imageId);
                updateStmt.executeUpdate();

                System.out.printf("✅ imageId=%d | path=%s | hash=%s%n", imageId, imagePath, (hash != null ? hash : "略過"));
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DatabaseManager.getConnection()) {
            updateAllImageHashes(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
