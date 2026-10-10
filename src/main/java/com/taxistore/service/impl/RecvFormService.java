package com.taxistore.service.impl;

import com.taxistore.dao.impl.RecvFormDao;
import com.taxistore.pojo.RecvForm;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;

public class RecvFormService
{
    RecvFormDao recvformDao=new RecvFormDao();
    public Result<List<RecvForm>> GetRecvFormsBySituationId(Long situationId)
    {
        return recvformDao.getEntitiesByField("situation",situationId,"id",false);
    }
    public Result<RecvForm> CreateRecvForm(RecvForm recvForm)
    {
        Result<Long> result=recvformDao.insertEntity(recvForm);
        if(result.isSuccess())
        {
            recvForm.setId(result.getData());
            return  new Result<>(recvForm);
        }else return new Result<>("create fail.","");

    }
    public Result<Integer> DeleteRecvForm(Long recvFormId)
    {
        return recvformDao.deleteByIndex("recvFormId",recvFormId);
    }
    public static void main(String[] args)
    {
        RecvFormService recvFormService=new RecvFormService();
        Result<List<RecvForm>> result=recvFormService.GetRecvFormsBySituationId(1L);
        Log.normal(""+result.toJSON());
    }
}
