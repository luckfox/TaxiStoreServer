package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Hex {
    private static boolean QuickSha256 = true;

    public static String ComputeSha256Hex(File file) throws IOException, NoSuchAlgorithmException {
        return QuickSha256 ? quickJpegHash(file) : calculateSha256Hex(file);

    }

    /*
     * 計算檔案的 SHA-256 Hash 並回傳為十六進位格式字串。
     *
     * @param file 欲計算的檔案
     * @return Hash 值（hex 字串）
     * @throws IOException              檔案讀取錯誤
     * @throws NoSuchAlgorithmException 無此演算法
     */
    private static String calculateSha256Hex(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (InputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[8192];
            int n;
            while ((n = fis.read(buffer)) > 0) {
                digest.update(buffer, 0, n);
            }
        }
        byte[] hashBytes = digest.digest();

        // 轉 hex 字串
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static final int HEADER_SIZE = 1024;

    /*
     * 計算指定 JPEG 檔案的快速 hash（使用前 1KB 與檔案大小）
     *
     * @param file 要計算 hash 的 JPEG 檔案
     * @return SHA-256 hash 字串（十六進位表示）
     * @throws IOException              若讀檔錯誤
     * @throws NoSuchAlgorithmException 若找不到 SHA-256 演算法
     */
    public static String quickJpegHash(File file) throws IOException, NoSuchAlgorithmException {
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("檔案不存在或不是合法檔案: " + file.getPath());
        }

        long fileSize = file.length();
        int bytesToRead = (int) Math.min(HEADER_SIZE, fileSize);
        byte[] headerBytes = new byte[bytesToRead];

        try (FileInputStream fis = new FileInputStream(file)) {
            int read = fis.read(headerBytes, 0, bytesToRead);
            if (read < bytesToRead) {
                throw new IOException("未能完整讀取 JPEG 前段 bytes");
            }
        }

        // 準備大小資訊的 bytes（用 UTF-8 編碼長度字串）
        byte[] sizeBytes = String.valueOf(fileSize).getBytes("UTF-8");

        // 合併兩段 byte array
        byte[] combined = new byte[headerBytes.length + sizeBytes.length];
        System.arraycopy(headerBytes, 0, combined, 0, headerBytes.length);
        System.arraycopy(sizeBytes, 0, combined, headerBytes.length, sizeBytes.length);

        // 計算 SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(combined);

        // 轉為十六進位字串
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}
