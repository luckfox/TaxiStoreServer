package com.taxistore.servlet;

import com.taxistore.pojo.CarImage;
import com.taxistore.service.impl.CarImageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 處理車輛圖片的 Servlet
 */
@MultipartConfig
@WebServlet("/carImageServlet")
public class CarImageServlet extends AbstractImageServlet<CarImage> {

    // *
    //    罰單完整路徑 d:/FineImage/image/FN12345
    // *
    final private static String CarImagePath="d:/CarImage/image/";
    final private static String CarThumbPath="d:/CarImage/thumb/";
    final private static String OwnerKey="carseq";
    private final CarImageService service = new CarImageService();
    // === 以下綁定你的 action 名稱 ===
    protected void ajaxGetImageMetasByCarSeq(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        ajaxGetImageMetasByOwner(request, response, OwnerKey);
    }
    protected void ajaxGetImageMetaByCarSeqAndHashCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ajaxGetImageMetaByOwnerAndHash(request, response, OwnerKey);
    }

    protected void ajaxUploadCarImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ajaxUploadImage(request, response, OwnerKey);
    }

    protected void ajaxGetImageByImageId(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        super.ajaxGetImageByImageId(request, response);
    }

    protected void ajaxDeleteImageByImageId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.ajaxDeleteImageByImageId(request, response);
    }

    protected void ajaxRotationCarImageByImageId(HttpServletRequest req, HttpServletResponse response) throws Exception {
        super.ajaxRotationImageByImageId(req, response);
    }
    //=======================================================================================================
    @Override
    protected CarImageService getService() {
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
    protected CarImage createImageEntity() {
        return new CarImage();
    }

    @Override
    protected void setOwnerKey(CarImage image, String carSeq) {
        image.setCarSeq(Long.parseLong(carSeq));
    }
}
