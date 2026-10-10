package com.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 圖片旋轉大師 - ImageRotator
 * 可將指定的 JPG 圖片旋轉 90、180、270 度
 */
public class ImageRotator {

    /**
     * 對指定 JPG 檔案進行旋轉並另存新檔
     *
     * @param inputPath  原始 JPG 圖片路徑
     * @param angle      旋轉角度（僅接受 90、180、270）
     * @throws IOException 若讀取或寫入圖片失敗
     */
    /**
     * 將指定的 JPG 圖片旋轉指定角度，並覆蓋原圖
     *
     * @param inputPath 原始 JPG 圖片檔案路徑
     * @param angle     旋轉角度（僅支援 90, 180, 270）
     * @throws IOException 若圖片處理失敗
     */
    public static void rotateImage(String inputPath, int angle) throws IOException
    {
        if (angle != 90 && angle != 180 && angle != 270)
        {
            throw new IllegalArgumentException("拜託～只能轉 90, 180, 270 度啦！");
        }

        // 讀進原始圖片
        File inputFile = new File(inputPath);
        BufferedImage originalImage = ImageIO.read(inputFile);
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // 新圖片尺寸（180度不變，90/270寬高交換）
        int newWidth = (angle == 180) ? width : height;
        int newHeight = (angle == 180) ? height : width;

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g2d = rotatedImage.createGraphics();

        // 設定旋轉矩陣
        AffineTransform transform = new AffineTransform();

        switch (angle) {
            case 90:
                transform.translate(newWidth, 0);
                transform.rotate(Math.toRadians(90));
                break;
            case 180:
                transform.translate(newWidth, newHeight);
                transform.rotate(Math.toRadians(180));
                break;
            case 270:
                transform.translate(0, newHeight);
                transform.rotate(Math.toRadians(270));
                break;
        }

        g2d.drawImage(originalImage, transform, null);
        g2d.dispose();

        // 寫回原始檔案（覆蓋）
        boolean success = ImageIO.write(rotatedImage, "jpg", inputFile);
        if (success) {
            Log.normal(inputPath+" 圖片成功旋轉 " + angle + " 度，已覆蓋原圖！");
        } else {
            throw new IOException("💥 無法寫入圖片，可能是檔案損毀或寫入權限問題");
        }
    }
    // 範例 main 方法
    public static void main(String[] args) throws IOException {
        rotateImage("G:\\我的雲端硬碟\\照片\\昆蟲\\istockphoto-512596224-1024x1024.jpg", 90);  // 你可以換成 180 或 270
    }
}
