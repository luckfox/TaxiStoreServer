package com.taxistore.service;

import com.taxistore.pojo.DataTableResponse;
import com.taxistore.pojo.LineUser;
import com.taxistore.dto.LineUserDTO;
import com.taxistore.pojo.Result;

import java.util.List;

/**
 * @author luckf
 */
public interface ILineUserTService {
    /**
     * @param user：lineuser的資料
     * @return Result<Integer>
     */
    Result<Long> addUser( LineUser user);
    Result<LineUser> getLineUserByUserId(String userId);
    Result<Integer> deleteLineUserById(long serialId);
    Result<Integer> deleteLineUserByUserId(String userId);
    Result<Integer> updateLineUserByUserId(LineUser user);
    Result<LineUserDTO> getLineUserWithDriverBySerialId(long serialId);
    Result<LineUserDTO> getLineUserWithDriverByUserId(String userId);
    Result<List<LineUserDTO>> getLineUsersWithDriver();
    Result<DataTableResponse<LineUserDTO>> getLineUsersBySearch(int draw, int start, int length, String searchColumn,String searchValue, String orderDir, String column);
    Result<LineUser> getLineUserBySerialId(String serialId);

}
