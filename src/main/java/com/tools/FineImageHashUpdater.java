package com.tools;
import com.taxistore.config.DatabaseManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * 工具程式：根據 fineNo 從 FineImage 表格取得圖片路徑，計算 hashCode 後更新回資料庫。
 */
public class FineImageHashUpdater {

    // ✅ 請依你實際資料庫設定調整
    private static final String DB_URL = "jdbc:mysql://localhost:3309/taxistore";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mysql123";


    /*
     * 根據 fineNo 查詢 FineImage 中的圖片路徑與主鍵 ID。
     */
    private static List<ImageRecord> getFineImages(Connection conn, String fineNo) throws SQLException {
        List<ImageRecord> result = new ArrayList<>();
        String sql = "SELECT ImageId, ImagePath FROM fineimage WHERE fineNo = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fineNo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("ImageId");
                    String path = rs.getString("ImagePath");
                    result.add(new ImageRecord(id, path));
                }
            }
        }
        return result;
    }

    /*
     * 計算指定圖片檔案的 SHA-256 雜湊值。
     */
    private static String calculateImageHash(String imagePath) {
        File file = new File(imagePath);
        if (!file.exists()) return null;

        try (InputStream is = new FileInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[8192];
            int n;
            while ((n = is.read(buffer)) > 0) {
                digest.update(buffer, 0, n);
            }
            byte[] hashBytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * 將計算結果更新回資料表。
     */
    private static void updateImageHash(Connection conn, long id, String hash) throws SQLException {
        String sql = "UPDATE fineimage SET hashCode = ? WHERE ImageId = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hash);
            ps.setLong(2, id);
            ps.executeUpdate();
        }
    }

    /*
     * 內部記錄類別：存放 image id 與 path。
     */
    private static class ImageRecord {
        private final long id;
        private final String imagePath;

        public ImageRecord(long id, String imagePath) {
            this.id = id;
            this.imagePath = imagePath;
        }

        public long getId() {
            return id;
        }

        public String getImagePath() {
            return imagePath;
        }
    }
    public static void main(String[] args) {
        String fineNo = "FN12345"; // 可改為傳參或 console 輸入

        try (Connection conn = DatabaseManager.getConnection()) {
            List<ImageRecord> images = getFineImages(conn, fineNo);

            for (ImageRecord record : images) {
                String hash = calculateImageHash(record.getImagePath());
                if (hash != null) {
                    updateImageHash(conn, record.getId(), hash);
                    System.out.println("✅ 已更新 ID=" + record.getId() + "，hash=" + hash);
                } else {
                    System.err.println("⚠️ 無法讀取或計算影像：" + record.getImagePath());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
