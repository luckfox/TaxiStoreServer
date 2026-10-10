package com.taxistore.service.impl;

import com.taxistore.dao.impl.CarDTODao;
import com.taxistore.dto.CarDTO;
import com.taxistore.pojo.Result;

import java.util.List;


/**
 * @author luckf
 * @function 根據需求，提供Form表單式的資料回傳。
 */
public class CarDTOService
{
    private final static CarDTODao dao= new CarDTODao();

    public Result<CarDTO> getFirstCarDTO()
    {
        return dao.getFirstCarDTO();
    }
    public Result<CarDTO> getLastCarDTO()
    {
        return dao.getLastCarDTO();
    }
    public Result<CarDTO> getNextCarDTO(long serialNo)
    {
        return dao.getNextCarDTO(serialNo);
    }
    public Result<CarDTO> getCarDTO(long serialNo)
    {
        return dao.getCarDTO(serialNo);

    }
    public Result<CarDTO> getPreviousCarDTO(long serialNo)
    {
        return dao.getPreviousCarDTO(serialNo);
    }
    public Result<CarDTO> deleteCar(long serialNo)
    {
        Result<Integer> result=dao.deleteEntity(serialNo);
        if(result.isSuccess() && (Integer)result.getData()!=0)
        {
            //表示，成功刪除。
            Result<CarDTO> prev = dao.getPreviousCarDTO(serialNo);
            if (prev.isSuccess() && prev.getData() != null) return prev;  //取得上一筆

            Result<CarDTO> next = dao.getNextCarDTO(serialNo);
            if (next.isSuccess() && next.getData() != null) return next;  //取得下一筆
            // 沒有前後公司，回傳空成功物件
            return new Result<>();
        }
        return new Result<>("database.delete.fail","");
    }
    public Result<List<CarDTO>> getCarDTOsByCarNoLike(String carNoPart)
    {
        return dao.getCarDTOsByCarNoLike(carNoPart);
    }
    public Result<List<CarDTO>> getCarDTOsByMechanicssPartLike(String MechanicssPart)
    {
        return dao.getCarDTOsByMechanicssPartLike(MechanicssPart);
    }

    public Result<List<CarDTO>> getCarDTOsByCarLineNoPartLike(String MechanicssPart)
    {
        return dao.getCarDTOsByCarlineNoPartLike(MechanicssPart);
    }


}
