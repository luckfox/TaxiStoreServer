package com.utils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FastThumbGenerator {

    public static void processThumb(String inputDir, String outputDir, int scalePercent, int minWidth, int minHeight) {
        File inFolder = new File(inputDir);
        File outFolder = new File(outputDir);
        if (!outFolder.exists()) outFolder.mkdirs();

        File[] imageFiles = inFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

        if (imageFiles == null || imageFiles.length == 0) {
            Log.debug("Can't find images in ！");
            return;
        }

        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (File imgFile : imageFiles) {
            pool.submit(() -> generateThumb(imgFile, outFolder, scalePercent, minWidth, minHeight));
        }

        pool.shutdown();
    }
    public static String generateThumb(String iFile, String oFileFolder, int scalePercent, int minWidth, int minHeight){
        File inFile = new File(iFile);
        File outFileFolder   = new File(oFileFolder);
        return generateThumb(inFile,outFileFolder, scalePercent, minWidth, minHeight);
    }
    /*
     * 處理單張圖片產生縮圖
     */
    public static String generateThumb(File imgFile, File outFolder, int scalePercent, int minWidth, int minHeight) {
        try {
            BufferedImage img = ImageIO.read(imgFile);
            if (img == null) return null;

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

            // 建立縮圖（TYPE_INT_RGB：沒有 alpha，速度快）
            BufferedImage outputImg = new BufferedImage(tw, th, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = outputImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img, 0, 0, tw, th, null);
            g2.dispose();

            // JPEG 壓縮設定
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            if (!writers.hasNext()) throw new RuntimeException("沒有 JPEG writer");

            ImageWriter writer = writers.next();
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.8f); // 壓縮品質

            File outputFile = new File(outFolder, imgFile.getName().replace(".jpg", "_thumb.jpg"));
            writer.setOutput(new FileImageOutputStream(outputFile));
            writer.write(null, new IIOImage(outputImg, null, null), param);
            writer.dispose();

            Log.debug("process OK：" + outputFile.getName());
            return outputFile.getAbsolutePath();
        } catch (IOException e) {
            Log.debug("fail to process：" + imgFile.getName() + " - " + e.getMessage());
            return null;
        }

    }

    public static void main(String[] args) throws IOException {
        // 測試用範例
        processThumb("G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\image\\FN12345", "G:\\我的雲端硬碟\\大中原\\15。系統開發\\TaxiStoreServer\\src\\main\\java\\com\\tools\\thumb\\FN12345", 20, 200, 480);
    }
}
