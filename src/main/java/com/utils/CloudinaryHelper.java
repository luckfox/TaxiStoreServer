package com.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * CloudinaryHelper
 *
 * 本類別負責與 Cloudinary API 互動，包含：
 * <ul>
 *   <li>讀取設定檔取得 API Key、Secret</li>
 *   <li>圖片上傳</li>
 *   <li>刪除圖片</li>
 *   <li>列出資源清單</li>
 *   <li>依條件批次刪除過期圖片</li>
 * </ul>
 *
 * 依賴：
 * <ul>
 *   <li>需準備 application.properties 檔案，內容包含 cloudinary.cloudName、cloudinary.api_key、cloudinary.api_secret</li>
 * </ul>
 */
public class CloudinaryHelper {

    private final Cloudinary cloudinary;
    private static final Properties config = PropertiesUtil.load("application.properties");
    private static final String CLOUD_NAME = config.getProperty("cloudinary.cloudName");
    private static final String API_KEY = config.getProperty("cloudinary.api_key");
    private static final String API_SECRET = config.getProperty("cloudinary.api_secret");

    /**
     * 建構子：初始化 Cloudinary 連線設定
     *
     * @throws IOException 若讀取設定檔失敗
     */
    public CloudinaryHelper()
    {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET
        ));
        System.out.println("Cloudinary 已初始化，使用帳號: " + cloudinary.config.apiKey);
    }

    /**
     * 上傳本地圖片到 Cloudinary
     *
     * @param filePath 本地檔案完整路徑
     * @return 上傳後圖片的 secure_url，若失敗則回傳 null
     */
    public String uploadImage(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("檔案不存在: " + filePath);
            return null;
        }
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            System.out.println("上傳成功: public_id=" + uploadResult.get("public_id"));
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            System.err.println("上傳失敗: " + e.getMessage());
            return null;
        }
    }

    /**
     * 依 public_id 刪除 Cloudinary 中圖片
     *
     * @param publicId 圖片 public_id
     * @return true 刪除成功；false 刪除失敗
     */
    public boolean deleteImageByPublicId(String publicId) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            String status = (String) result.get("result");
            if ("ok".equals(status)) {
                System.out.println("刪除成功: public_id=" + publicId);
                return true;
            } else {
                System.err.println("刪除失敗: public_id=" + publicId + "，結果=" + status);
                return false;
            }
        } catch (IOException e) {
            System.err.println("刪除失敗: " + e.getMessage());
            return false;
        }
    }

    /**
     * 取得指定 public_id 的圖片詳細資訊
     *
     * @param publicId 圖片 public_id
     * @return 回傳該圖片資訊，失敗時回傳 null
     */
    public Map<String, Object> getResourceByPublicId(String publicId) {
        try {
            ApiResponse result = cloudinary.api().resource(publicId, ObjectUtils.emptyMap());
            @SuppressWarnings("unchecked")
            Map<String, Object> resource = result;
            return resource;
        } catch (Exception e) {
            System.err.println("取得資源失敗: " + e.getMessage());
            return null;
        }
    }

    /**
     * 刪除 N 天以前上傳的所有圖片
     *
     * @param days 保留幾天內的圖片，刪除更舊的圖片
     */
    public void deleteImagesOlderThan(int days) {
        List<Map<String, Object>> allResources = listAllResources();
        OffsetDateTime thresholdDate = OffsetDateTime.now().minusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        int deletedCount = 0;

        for (Map<String, Object> resource : allResources) {
            try {
                String createdAtStr = (String) resource.get("created_at");
                OffsetDateTime createdAt = OffsetDateTime.parse(createdAtStr, formatter);
                if (createdAt.isBefore(thresholdDate)) {
                    String publicId = (String) resource.get("public_id");
                    if (deleteImageByPublicId(publicId)) {
                        deletedCount++;
                    }
                }
            } catch (Exception e) {
                System.err.println("處理單筆資源失敗: " + e.getMessage());
            }
        }
        System.out.println("總計刪除 " + deletedCount + " 筆 " + days + " 天以前的圖片");
    }

    /**
     * 取得目前帳號下所有資源（會自動處理分頁）
     *
     * @return 所有資源清單，每筆為 Map<String, Object>
     */
    public List<Map<String, Object>> listAllResources() {
        List<Map<String, Object>> allResources = new ArrayList<>();
        String nextCursor = null;

        try {
            do {
                Map<String, Object> params = new HashMap<>();
                params.put("max_results", 100);
                if (nextCursor != null) {
                    params.put("next_cursor", nextCursor);
                }

                ApiResponse result = cloudinary.api().resources(params);
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> resources = (List<Map<String, Object>>) result.get("resources");
                allResources.addAll(resources);
                nextCursor = (String) result.get("next_cursor");
            } while (nextCursor != null);
        } catch (Exception e) {
            System.err.println("取得全部資源失敗: " + e.getMessage());
        }
        return allResources;
    }

    /**
     * 列印所有目前帳戶中的圖片資源資訊
     */
    public void printAllResources() {
        List<Map<String, Object>> resources = listAllResources();
        for (Map<String, Object> res : resources) {
            System.out.println("=====");
            System.out.println("檔名: " + res.get("public_id"));
            System.out.println("格式: " + res.get("format"));
            System.out.println("大小: " + res.get("bytes") + " bytes");
            System.out.println("建立時間: " + res.get("created_at"));
            System.out.println("URL: " + res.get("secure_url"));
        }
    }

    /**
     * 主程式 - 簡易測試
     */
    public static void main(String[] args) {
        CloudinaryHelper helper = new CloudinaryHelper();

        // 測試上傳
        // String localFilePath = "C:/test/image.jpg";
        // helper.uploadImage(localFilePath);

        // 列印目前所有資源
        helper.printAllResources();

        // 測試刪除 30 天以前的圖片
        // helper.deleteImagesOlderThan(30);

    }
}
