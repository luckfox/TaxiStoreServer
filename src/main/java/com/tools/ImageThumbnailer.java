package com.tools;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
 * 這是縮圖大師，專門幫你把資料夾內的 .jpg 變成縮圖，還有比例跟最小尺寸保護。
 */
public class ImageThumbnailer {

    /*
     * 縮圖主函數，參數照你講的來：
     * @param inputDir 原始圖片資料夾路徑
     * @param outputDir 縮圖要放的資料夾路徑
     * @param scalePercent 縮圖比例（例如20表示縮到20%）
     * @param minWidth 最小寬度
     * @param minHeight 最小高度
     */
    public static void processThumb(String inputDir, String outputDir, int scalePercent, int minWidth, int minHeight) {
        File inFolder = new File(inputDir);
        File outFolder = new File(outputDir);

        if (!inFolder.exists() || !inFolder.isDirectory()) {
            System.err.println("喔不，輸入路徑不存在或不是資料夾: " + inputDir);
            return;
        }

        if (!outFolder.exists()) {
            outFolder.mkdirs();
            System.out.println("幫你建立輸出資料夾囉: " + outputDir);
        }

        File[] files = inFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

        if (files == null || files.length == 0) {
            System.out.println("資料夾裡面找不到 .jpg 啦，別鬧了！");
            return;
        }

        for (File imgFile : files) {
            try {
                BufferedImage original = ImageIO.read(imgFile);
                if (original == null) {
                    System.out.println("跳過奇怪的檔案：" + imgFile.getName());
                    continue;
                }

                int origW = original.getWidth();
                int origH = original.getHeight();

                // 計算縮圖寬高 (先用比例算)
                int thumbW = origW * scalePercent / 100;
                int thumbH = origH * scalePercent / 100;

                // 先判斷最小寬高條件
                boolean lessThanMinW = thumbW < minWidth;
                boolean lessThanMinH = thumbH < minHeight;

                if (lessThanMinW && lessThanMinH) {
                    // 以最小寬度優先處理 (等比例)
                    thumbW = minWidth;
                    thumbH = (int) ((double) origH / origW * thumbW);
                } else if (lessThanMinW) {
                    thumbW = minWidth;
                    thumbH = (int) ((double) origH / origW * thumbW);
                } else if (lessThanMinH) {
                    thumbH = minHeight;
                    thumbW = (int) ((double) origW / origH * thumbH);
                }

                // 縮圖動作
                Image tmp = original.getScaledInstance(thumbW, thumbH, Image.SCALE_SMOOTH);
                BufferedImage thumb = new BufferedImage(thumbW, thumbH, BufferedImage.TYPE_INT_RGB);

                Graphics2D g2d = thumb.createGraphics();
                g2d.drawImage(tmp, 0, 0, null);
                g2d.dispose();

                // 輸出檔名加 _thumb
                String outputFileName = imgFile.getName().substring(0, imgFile.getName().lastIndexOf("."))
                        + "_thumb.jpg";
                File outputFile = new File(outFolder, outputFileName);

                ImageIO.write(thumb, "jpg", outputFile);
                System.out.println("已縮圖：" + imgFile.getName() + " → " + outputFileName);

            } catch (Exception e) {
                System.err.println("處理 " + imgFile.getName() + " 時出錯：" + e.getMessage());
            }
        }

        System.out.println("縮圖大作戰完成！嘎嘎！🐔");
    }

    // 範例 main 測試
    public static void main(String[] args) {
        // 輸入路徑, 輸出路徑, 縮圖比例%, 最小寬, 最小高
        processThumb("G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\image\\FN12345", "G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\thumb\\FN12345", 20, 200, 480);
    }
}
