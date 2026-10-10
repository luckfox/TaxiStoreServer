package com.taxistore.dao.impl;
import com.taxistore.pojo.LineUser;
import com.taxistore.dto.LineUserDTO;
import com.taxistore.pojo.Result;
import com.utils.Log;
import org.junit.Test;

import java.util.List;


public class LineUserTDaoTest
{
    LineUserTDao lineUserTDao=new LineUserTDao();
    @Test
    public void getAllUsers()
    {
        Result<List<LineUserDTO>> allUsers = lineUserTDao.getAllUsersWithDriver();
        Log.normal(allUsers.toString());
    }
    @Test
    public void getUserByUserId()
    {
        Result<LineUser> user=lineUserTDao.getLineUserByUserId("lineuser005");
        Log.normal(user.toString());
    }
    @Test
    public void getUserBySerialId()
    {
        Result<LineUser> user=lineUserTDao.getLineUserBySerialId(9);
        Log.normal(user.toString());

    }
}
