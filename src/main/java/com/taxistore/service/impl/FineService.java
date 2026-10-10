package com.taxistore.service.impl;
import com.taxistore.dao.impl.FineDTODao;
import com.taxistore.dao.impl.FineDao;
import com.taxistore.pojo.Fine;
import com.taxistore.dto.FineDTO;
import com.taxistore.pojo.Result;

import java.util.List;

public class FineService {
    private final static FineDTODao dao = new FineDTODao();
    private final static FineDao fineDao = new FineDao();

    public Result<FineDTO> getFirstFineDTO()
    {
        return dao.getFirstFineDTO();
    }
    public Result<FineDTO> getLastFineDTO()
    {
        return dao.getLastFineDTO();
    }
    public Result<FineDTO> getNextFineDTO(long serialNo)
    {
        return dao.getNextFineDTO(serialNo);
    }
    public Result<FineDTO> getPreviousFineDTO(long serialNo)
    {
        return dao.getPreviousFineDTO(serialNo);
    }
    public Result<FineDTO> getFineDTO(long serialNo){
        return dao.getFineDTO(serialNo);
    }
    public Result<FineDTO> getFineDTOByFineNo(String fineNo){
        return dao.getFineByFineNo(fineNo);
    }
    public Result<List<FineDTO>>getFineDTOByFineNoLike(String fineNoPart)
    {
        return dao.getFineDTOByFineNoLike(fineNoPart);
    }
    public Result<FineDTO> deleteFine(long serialNo)
    {
        Result<Integer> result=dao.deleteEntity(serialNo);
        if(result.isSuccess() && (Integer)result.getData()!=0)
        {
            //表示，成功刪除。
            Result<FineDTO> prev = dao.getPreviousFineDTO(serialNo);
            if (prev.isSuccess() && prev.getData() != null) return prev;  //取得上一筆

            Result<FineDTO> next = dao.getNextFineDTO(serialNo);
            if (next.isSuccess() && next.getData() != null) return next;  //取得下一筆
            // 沒有前後公司，回傳空成功物件
            return new Result<>();
        }
        return new Result<>("database.delete.fail","");
    }
    public Result<Integer> updateEntityByNonNullFields(Fine fine)
    {
       return fineDao.updateEntityByNonNullFields(fine);
    }
}
