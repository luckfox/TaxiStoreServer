package com.tools;

import com.taxistore.config.DatabaseManager;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FastThumbGenerator {

    public static void processThumb(String inputDir, String outputDir, int scalePercent, int minWidth, int minHeight,String fineNo) {
        File inFolder = new File(inputDir);
        File outFolder = new File(outputDir);
        if (!outFolder.exists()) outFolder.mkdirs();

        File[] imageFiles = inFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("😵 找不到 .jpg 圖片！");
            return;
        }

        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (File imgFile : imageFiles) {
            pool.submit(() -> {
                try {
                    BufferedImage img = ImageIO.read(imgFile);
                    if (img == null) return;

                    int ow = img.getWidth();
                    int oh = img.getHeight();
                    int tw = ow * scalePercent / 100;
                    int th = oh * scalePercent / 100;

                    // 修正最小尺寸
                    if (tw < minWidth && th < minHeight) {
                        tw = minWidth;
                        th = oh * minWidth / ow;
                    } else if (tw < minWidth) {
                        tw = minWidth;
                        th = oh * minWidth / ow;
                    } else if (th < minHeight) {
                        th = minHeight;
                        tw = ow * minHeight / oh;
                    }

                    // 轉成 TYPE_INT_RGB（沒有 Alpha 通道，更快）
                    BufferedImage outputImg = new BufferedImage(tw, th, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2 = outputImg.createGraphics();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2.drawImage(img, 0, 0, tw, th, null);
                    g2.dispose();

                    // 設定 JPEG 壓縮（省時間）
                    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
                    if (!writers.hasNext()) throw new RuntimeException("沒有 JPEG writer");

                    ImageWriter writer = writers.next();
                    ImageWriteParam param = writer.getDefaultWriteParam();
                    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    param.setCompressionQuality(0.8f); // 壓縮品質 0.0~1.0

                    File outputFile = new File(outFolder, imgFile.getName().replace(".jpg", "_thumb.jpg"));
                    writer.setOutput(new FileImageOutputStream(outputFile));
                    writer.write(null, new IIOImage(outputImg, null, null), param);
                    writer.dispose();

                    // ✅ 寫入資料庫
                    insertToDatabase(fineNo, imgFile.getAbsolutePath(), outputFile.getAbsolutePath());

                    System.out.println("✅ 完成縮圖：" + outputFile.getName());

                } catch (IOException e) {
                    System.err.println("💥 圖片處理失敗：" + imgFile.getName() + " - " + e.getMessage());
                }
            });
        }

        pool.shutdown();
        try {
            // 最多等 1 小時（超過你就該檢討你圖片是不是太大張啦 🤣）
            if (!pool.awaitTermination(2, TimeUnit.MINUTES)) {
                System.err.println("⚠️ 有些縮圖工作沒完成就超時啦！");
            }
        } catch (InterruptedException e) {
            System.err.println("⚠️ 等待 thread 結束時被打斷：" + e.getMessage());
        }
    }
    /**
     * 將圖片資訊寫入 FineImage 資料表。
     *
     * @param fineNo    罰單編號
     * @param imagePath 原圖路徑
     * @param thumbPath 縮圖路徑
     */
    private static void insertToDatabase(String fineNo, String imagePath, String thumbPath) {
        String sql = "INSERT INTO FineImage (fineNo, imagePath, thumbPath) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fineNo);
            ps.setString(2, imagePath);
            ps.setString(3, thumbPath);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("⚠️ 資料庫寫入失敗：" + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        // 測試用範例
        processThumb("d:\\FineImage\\image\\FN12345", "d:\\FineImage\\thumb\\FN12345", 20, 200, 480,"FN12345");
    }
}
