package com.taxistore.service.impl;


import com.taxistore.pojo.Result;
import com.utils.ImageProcessor;

/**
 *      提供Servlet需要存取圖片的服務
 */

public class ImageService
{

    public Result<String> uploadToImgur(String base64Image)
    {
        String result;
        if((result=ImageProcessor.uploadToImgur(base64Image))==null)
        {
            return Result.failure("upload.image.fail");
        }
        return Result.success(result);
    }
    public Result<Integer> pushImage(String userId, String imageUrl)
    {
        int result;
        if((result=ImageProcessor.pushImage(userId,imageUrl))!=200)
        {
            return Result.failure("push.image.fail");

        }
        return Result.success(result);
    }
    //ImageProcessor
}
