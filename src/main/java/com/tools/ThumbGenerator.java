package com.tools;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

/**
 * 圖片縮圖工具：支援多執行緒與最小長寬處理。
 */
public class ThumbGenerator {

    public static void processThumb(String inputDirPath, String outputDirPath,
                                    int scalePercent, int minWidth, int minHeight) throws IOException {
        File inputDir = new File(inputDirPath);
        File outputDir = new File(outputDirPath);

        if (!inputDir.exists() || !inputDir.isDirectory()) {
            throw new IllegalArgumentException("輸入資料夾不存在或不是目錄");
        }

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File[] imageFiles = inputDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));
        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("找不到任何 .jpg 圖片！");
            return;
        }

        int threadCount = Math.min(Runtime.getRuntime().availableProcessors(), imageFiles.length);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (File imgFile : imageFiles) {
            executor.submit(() -> {
                try {
                    BufferedImage originalImage = ImageIO.read(imgFile);
                    if (originalImage == null) return;

                    int width = originalImage.getWidth();
                    int height = originalImage.getHeight();

                    int newWidth = width * scalePercent / 100;
                    int newHeight = height * scalePercent / 100;

                    // 最小寬度 / 高度限制處理
                    if (newWidth < minWidth && newHeight < minHeight) {
                        double scaleByMinWidth = (double) minWidth / width;
                        newWidth = minWidth;
                        newHeight = (int) (height * scaleByMinWidth);
                    } else if (newWidth < minWidth) {
                        double scale = (double) minWidth / width;
                        newWidth = minWidth;
                        newHeight = (int) (height * scale);
                    } else if (newHeight < minHeight) {
                        double scale = (double) minHeight / height;
                        newHeight = minHeight;
                        newWidth = (int) (width * scale);
                    }

                    String outputFileName = imgFile.getName().replaceAll("\\.jpg$", "_thumb.jpg");
                    File outputFile = new File(outputDir, outputFileName);

                    Thumbnails.of(imgFile)
                            .size(newWidth, newHeight)
                            .outputFormat("jpg")
                            .toFile(outputFile);

                    System.out.println("完成縮圖：" + outputFile.getName());

                } catch (IOException e) {
                    System.err.println("處理圖片失敗：" + imgFile.getName());
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // 等待所有任務完成
        }

        System.out.println("所有圖片縮圖完畢！");
    }

    public static void main(String[] args) throws IOException {
        // 測試用範例
        processThumb("G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\image\\FN12345", "G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\thumb\\FN12345", 20, 200, 480);
    }
}
