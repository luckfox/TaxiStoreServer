package com.taxistore.service.impl;

import com.taxistore.dao.impl.SituationDao;
import com.taxistore.dto.SituationDTO;
import com.taxistore.dto.VehicleInspDTO;
import com.taxistore.dto.VehicleInspStatusDTO;
import com.taxistore.pojo.*;
import com.utils.Log;

import java.util.List;


public class SituationService
{
    private SituationDao situationDao=new SituationDao();
    public Result<SituationDTO> GetLast()
    {
        return situationDao.getLatestSituationDetails();
    }
    public Result<SituationDTO> GetNextItem(Long id)
    {
        return situationDao.getSituationDetailsById(id,true);
    }
    public Result<SituationDTO> GetPreviousItem(Long id)
    {
        return situationDao.getSituationDetailsById(id,false);
    }
    public Result<SituationDTO>GetItem(Long id)
    {
        return situationDao.getSituationDetailsById(id);
    }

    public Result<List<SituationDTO>>GetItemBySpecialCode(String code)
    {
        return situationDao.getSituationDetailsBySpecial(code);
    }
    public Result<List<SituationDTO>>GetItemByCarnoLike(String carNo)
    {
        return situationDao.getSituationDetailsByCarnoLike(carNo);
    }
    public Result<Long>insertSituation(Situation s)
    {
        return situationDao.insertEntity(s);
    }
    public Result<Integer>updateSituation(Situation s)
    {
        return situationDao.updateEntity(s);
    }
    public Result<DataTableResponse<VehicleInspStatusDTO>>GetVehicleInspectionStatus(int draw, int start, int length, String orderDir, String orderColumnIndex, int year, int season, String companySeq)
    {
        Result<List<VehicleInspStatusDTO>> result=situationDao.getVehicleInspectionStatus(year, season, companySeq, start, length, orderDir, orderColumnIndex);
        if(result.isSuccess()==false) return Result.failure(result.getErrorMessage());
        DataTableResponse<VehicleInspStatusDTO> response = new DataTableResponse<>();
        Result<Integer> r= situationDao.countVehicleInspectionStatus(year,season,companySeq);
        if(r.isSuccess()==false) return Result.failure(r.getErrorMessage());
        int totalRecords=r.getData();

        response.setDraw(draw);
        response.setRecordsTotal(totalRecords);
        response.setRecordsFiltered(totalRecords);
        response.setData(result.getData());

        return Result.success(response);

    }
    public Result<DataTableResponse<VehicleInspDTO>>GetCarsForInspection(int draw, int start, int length, String orderDir, String column, int queryMonth)
    {
        Result<List<VehicleInspDTO>> result= situationDao.GetCarsForInspection(start,length,orderDir,column,queryMonth);
        if(result.isSuccess()==false) return Result.failure(result.getErrorMessage());
        DataTableResponse<VehicleInspDTO> response = new DataTableResponse<>();

        Result<Integer> r= situationDao.GetTotalCarsForInspection(queryMonth);//計算共有幾筆資料。
        if(r.isSuccess()==false) return Result.failure(r.getErrorMessage());
        int totalRecords=r.getData();

        response.setDraw(draw);
        response.setRecordsTotal(totalRecords);
        response.setRecordsFiltered(totalRecords);
        response.setData(result.getData());

        return Result.success(response);
    }
    public static void main(String[] args) {

        SituationService situationService=new SituationService();
        Result<DataTableResponse<VehicleInspDTO>> r=situationService.GetCarsForInspection(1,5,100,"desc","2",1);
        Log.normal(r.toJSON());
        Result<DataTableResponse<VehicleInspStatusDTO>> r2=situationService.GetVehicleInspectionStatus(1,0,10,"desc","0",2024,1,"25m");


        Log.normal(r2.toJSON());
    }
}
