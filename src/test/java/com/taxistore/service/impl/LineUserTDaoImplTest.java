package com.taxistore.service.impl;

//import static org.junit.jupiter.api.Assertions.*;

import com.taxistore.dao.impl.LineUserTDao;
import com.taxistore.pojo.LineUser;
import com.taxistore.dto.LineUserDTO;
import com.taxistore.pojo.Result;
import com.utils.Log;
import org.testng.annotations.Test;
import java.util.List;


public class LineUserTDaoImplTest {
    private static final LineUserTDao lineUserTDao=new LineUserTDao();

    @Test
    public void testAddUser() {
        LineUser user = new LineUser(0, "U1234567897", "阿瑋", "test@example.com", null, (long)0);
        Result<Long> result = lineUserTDao.insertEntity(user);
        //assertTrue(result.isSuccess());
        Log.normal("result="+result);

        LineUser user1 = new LineUser(0, "U1234567899", "TestUser", "test@example.com", null, (long)0);
        Result<Long> result1 = lineUserTDao.insertEntity(user1);
        //assertTrue(result1.isSuccess());
        Log.normal("result="+result1);
    }

    @Test
    public void testGetUserByUserId() {
        Result<LineUser> result = lineUserTDao.getLineUserByUserId("U1234567895");
        //assertTrue(result.isSuccess());
        //assertNotNull(result.getData());
        //assertEquals("TestUser", result.getData().getDisplayName());
        Log.normal("result="+result);
    }

    @Test
    public void testUpdateUser() {
        LineUser user = new LineUser(0, "U1234567890", "UpdatedUser", "updated@example.com", null, null);
        Result<Integer> result = lineUserTDao.updateEntity(user);
        //assertTrue(result.isSuccess());
        Log.normal("result="+result);

        Result<LineUser> result1 = lineUserTDao.getLineUserByUserId("U1234567890");
        //assertEquals("UpdatedUser", result1.getData().getDisplayName());
        Log.normal("result="+result1);
    }

    @Test
    public void testGetAllUsers() {
        Result<List<LineUserDTO>> result = lineUserTDao.getAllUsersWithDriver();
        //assertTrue(result.isSuccess());
        //assertFalse(result.getData().isEmpty());
        Log.normal("result="+result);
    }

    @Test
    public void testDeleteUserByUserId() {
        Result<Integer> result = lineUserTDao.deleteLineUserByUserId("U1234567890");
        //assertTrue(result.isSuccess());
        Log.normal("result="+result);

        Result<LineUser> result1 = lineUserTDao.getLineUserByUserId("U1234567890");
       // assertFalse(deletedUser.isSuccess());
        Log.normal("result="+result1);
    }

    @Test
    public void testGetUsersBySearch()
    {

        Result<List<LineUserDTO>> result=lineUserTDao.getLineUsersBySearch(0,8,"displayName","阿瑋","","");
        Log.normal("result="+result);
    }
}
