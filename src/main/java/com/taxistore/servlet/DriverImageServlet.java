package com.taxistore.servlet;

import com.taxistore.pojo.DriverImage;
import com.taxistore.service.impl.DriverImageService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * DriverImageServlet：駕駛人員圖片專用 Servlet，繼承萬用 AbstractImageServlet
 */
@MultipartConfig
@WebServlet("/driverImageServlet")
public class DriverImageServlet extends AbstractImageServlet<DriverImage> {

    final private static String CarImagePath="d:/DriverImage/image/";
    final private static String CarThumbPath="d:/DriverImage/thumb/";
    final private static String OwnerKey="driverseq";

    private final DriverImageService service = new DriverImageService();

    @Override
    protected DriverImageService getService() {
        return service;
    }

    @Override
    protected File getImageDir(String imagePath) {
        return new File(CarImagePath + imagePath);
    }

    @Override
    protected File getThumbDir(String thumbPath) {
        return new File(CarThumbPath + thumbPath);
    }

    @Override
    protected DriverImage createImageEntity() {
        return new DriverImage();
    }

    @Override
    protected void setOwnerKey(DriverImage image, String ownerKey) {
        image.setDriverSeq(Long.parseLong(ownerKey));
    }

    // === 以下綁定你的 action 名稱 ===
    protected void ajaxGetImageMetasByDriverSeq(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ajaxGetImageMetasByOwner(req, res, OwnerKey);
    }

    protected void ajaxGetImageMetaByDriverSeqAndHashCode(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ajaxGetImageMetaByOwnerAndHash(req, res, OwnerKey);
    }

    protected void ajaxUploadDriverImage(HttpServletRequest req, HttpServletResponse res) throws Exception {
        ajaxUploadImage(req, res, OwnerKey);
    }

    protected void ajaxGetImageByImageId(HttpServletRequest req, HttpServletResponse res) throws  IOException {
        super.ajaxGetImageByImageId(req, res);
    }

    protected void ajaxDeleteImageByImageId(HttpServletRequest req, HttpServletResponse res) throws IOException {
        super.ajaxDeleteImageByImageId(req, res);
    }

    protected void ajaxRotationDriverImageByImageId(HttpServletRequest req, HttpServletResponse res) throws Exception {
        super.ajaxRotationImageByImageId(req, res);
    }
}
