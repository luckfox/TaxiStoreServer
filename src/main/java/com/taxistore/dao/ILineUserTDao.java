package com.taxistore.dao;

import com.taxistore.pojo.LineUser;
import com.taxistore.dto.LineUserDTO;
import com.taxistore.pojo.Result;

import java.util.List;

/**
 * @author luckf
 */
public interface ILineUserTDao
{

    public Result<Integer> deleteLineUserByUserId(String userId);
    public Result<Integer> updateLineUser(LineUser user);
    public Result<LineUser> getLineUserBySerialId(long serialId);
    public Result<LineUser> getLineUserByUserId(String userId);
    public Result<List<LineUserDTO>> getLineUserDTOsWithDriver();
    public Result<LineUserDTO> getLineUserWithDriverBySerialId(long serialId);
    public Result<LineUserDTO> getLineUserWithDriverByUserId(String userId);
    public Result<List<LineUserDTO>> getLineUsersBySearch(int start, int length, String searchColumn,String searchValue,String orderDir,String column);
}
