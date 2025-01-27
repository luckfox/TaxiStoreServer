package com.taxistore.dao.impl;

import com.taxistore.dao.IUserDao;
import com.taxistore.pojo.Password;
import com.taxistore.pojo.Result;
import com.utils.Log;


/**
 * @author luckf
 */
public class UserTDao extends BasicTFormOperatorDao<Password>  //implements IUserTDao
{
    public UserTDao()
    {
        super(Password.class,"Password");
    }

    public Result<Object> validateUser(String userNo, String password)
    {

        String sql = "SELECT COUNT(*) FROM password WHERE userNo = ? AND password = ?";
        return queryForSingleValue(sql,userNo,password);

    }
    public Result<Password> getUserNameByUserNo(String userNo)
    {
        String sql = "SELECT userName FROM password WHERE userNo = ?";
        return queryForOne(Password.class,sql,userNo);
    }
/*
    public String getUserNameByUserNo(String userNo)
    {

        String query = "SELECT userName FROM password WHERE userNo = ?";
        try (Connection conn = DBConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, userNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("userName");
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;

    }
    */
}
