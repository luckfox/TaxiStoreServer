package com.taxistore.service.impl;

import com.taxistore.dao.impl.LineUserTDao;
import com.taxistore.pojo.DataTableResponse;
import com.taxistore.pojo.LineUser;
import com.taxistore.dto.LineUserDTO;
import com.taxistore.pojo.Result;
import com.taxistore.service.ILineUserTService;
import com.utils.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

public class LineUserTService implements ILineUserTService
{

    private final LineUserTDao lineUserTDao = new LineUserTDao();


    @Override
    public Result<Long> addUser(LineUser user)
    {
        user.setDisplayName(user.getDisplayName().replaceAll("[^\\u0000-\\uFFFF]", "?"));
        return lineUserTDao.insertEntity(user);
    }

    @Override
    public Result<Integer> deleteLineUserById(long serialId) {
        return lineUserTDao.deleteEntity(serialId);
    }

    @Override
    public Result<Integer> deleteLineUserByUserId(String userId) {
        return lineUserTDao.deleteEntryByUserId(userId);
    }

    @Override
    public Result<Integer> updateLineUserByUserId(LineUser user)
    {
        return lineUserTDao.updateLineUser(user);
    }

    @Override
    public Result<LineUserDTO> getLineUserWithDriverBySerialId(long serialId)
    {
        return lineUserTDao.getLineUserWithDriverBySerialId(serialId);
    }
    public Result<LineUserDTO> getLineUserWithDriverByDriverSeq(String DriverSeq)
    {
        return lineUserTDao.getLineUserWithDriverByDriverSeq(DriverSeq);
    }
    @Override
    public Result<LineUserDTO> getLineUserWithDriverByUserId(String userId)
    {
        return lineUserTDao.getLineUserWithDriverByUserId(userId);
    }

    @Override
    public Result<List<LineUserDTO>> getLineUsersWithDriver() {
        return lineUserTDao.getAllUsersWithDriver();
    }
    /**
     * 依據搜尋條件取得 LineUserDTO 清單
     *
     * @param start  起始索引 (SQL OFFSET)
     * @param length 每頁顯示的筆數 (SQL LIMIT)
     * @param searchColumn 搜尋欄位
     * @param searchValue 搜尋關鍵字
     * @param orderDir 排序方向 DSC/ASC
     * @param column 排序的欄位
     * @return esult<DataTableResponse<LineUserDTO>>，成功時回傳列表，失敗時回傳錯誤訊息
     */
    public Result<DataTableResponse<LineUserDTO>> getLineUsersBySearch(int draw, int start, int length,String searchColumn, String searchValue,String orderDir,String column)
    {
        int totalRecords = lineUserTDao.countLineUsersBySearch(searchColumn, searchValue);
        Result<List<LineUserDTO>> result = lineUserTDao.getLineUsersBySearch(start, length,searchColumn, searchValue,orderDir,column);
        if(!result.isSuccess()) return Result.failure(result.getErrorMessage());

        DataTableResponse<LineUserDTO> response = new DataTableResponse<>();
        response.setDraw(draw);
        response.setRecordsTotal(totalRecords);
        response.setRecordsFiltered(totalRecords);
        response.setData(result.getData());

        return Result.success(response);
    }

    @Override
    public Result<LineUser> getLineUserBySerialId(String serialId)
    {
        if (serialId == null || serialId.trim().isEmpty()) {
            return Result.failure("serialID 不能為空");
        }
        try
        {
            long serialIdLong = Long.parseLong(serialId.trim());
            return lineUserTDao.getLineUserBySerialId(serialIdLong);
        } catch (NumberFormatException e)
        {
            return Result.failure("serialID 格式錯誤：" + serialId);
        }
    }



    @Override
    public Result<LineUser> getLineUserByUserId(String userId)
    {
        if (userId == null || userId.trim().isEmpty()) {
            return Result.failure("userId 不能為空");
        }

        return lineUserTDao.getLineUserByUserId(userId);

    }

    public Result<LineUserDTO> getLineUserWithDriverBySerialId(String serialId)
    {
        if (serialId == null || serialId.trim().isEmpty()) {
            return Result.failure("serialID 不能為空");
        }
        try
        {
            long serialIdLong = Long.parseLong(serialId.trim());
            return lineUserTDao.getLineUserWithDriverBySerialId(serialIdLong);
        } catch (NumberFormatException e)
        {
            return Result.failure("serialID 格式錯誤：" + serialId);
        }

    }

    /**
     * @param lineUser
     * @return
     */
    public Result<Long> InsertOrUpdateUser(LineUser lineUser)
    {
        // 先查詢 userId 是否存在
        Result<LineUser> result = lineUserTDao.getLineUserByUserId(lineUser.getUserId());

        //Query 發生錯誤。
        if (!result.isSuccess()) return  Result.failure("");

        if(result.getData()==null)
        {
            // 若不存在，則新增
            return lineUserTDao.insertEntity(lineUser);
        } else
        {
            // 若已存在，則更新 displayName
            Result<Integer> r=lineUserTDao.updateLineUser(lineUser);
            return new Result<>(r.getData().longValue());

        }
    }


    /**
     * 產生一個隨機且不重複的 32 字元長度字串。
     * <p>此方法結合 UUID 與 SHA-256 雜湊演算法，能夠確保唯一性與固定長度。</p>
     *
     * @return 長度為 32 的十六進位字串
     */
    public static String generateUnique32CharString() {
        try {
            // 產生隨機 UUID
            String uuid = UUID.randomUUID().toString();

            // 使用 SHA-256 將 UUID 雜湊，並取前 32 字元
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(uuid.getBytes(StandardCharsets.UTF_8));

            // 將 byte[] 轉為十六進位字串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // 補 0
                hexString.append(hex);
            }

            // 回傳前 32 字元
            return hexString.substring(0, 32);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    public static void main(String[] args)
    {
        LineUserTService service=new LineUserTService();
        LineUser lineUser=new LineUser();
        lineUser.setTag("This is Tag");
        lineUser.setUserId(generateUnique32CharString());
        lineUser.setDisplayName("GGMan");
        lineUser.setDriverseq(0L);
        Result<Long> result=service.addUser(lineUser);
        Log.normal(result.toJSON());
        if(result.isSuccess())
        {


            //Result<Integer> result2= service.deleteUserById(result.getData());
            Result<Integer> result2= service.deleteLineUserByUserId(lineUser.getUserId());
            Log.normal(result2.toJSON());
        }

    }

}
