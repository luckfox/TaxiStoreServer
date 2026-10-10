package com.taxistore.dao;

import com.taxistore.dto.CarDTO;
import com.taxistore.pojo.Result;

import java.util.List;

public interface ICarDTODao
{
    Result<CarDTO> getFirstCarDTO();
    Result<CarDTO> getLastCarDTO();
    Result<CarDTO> getPreviousCarDTO(long serialNo);
    Result<CarDTO> getNextCarDTO(long serialNo);
    Result<CarDTO> getCarDTO(long serialNo);
    Result<List<CarDTO>> getCarDTOs();
    Result<List<CarDTO>> getCarDTOsByCarNoLike(String carNoPart);
}
