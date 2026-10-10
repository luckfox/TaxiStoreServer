package com.taxistore.dao.impl;

import com.taxistore.dao.IUserDao;
import com.taxistore.pojo.Password;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;


/**
 * @author luckf
 */
public class UserDao extends AbstractFormDao<Password>  implements IUserDao
{
    private static final String TABLE_NAME="Password ";
    private static final String KEY_NAME = "id";
    public UserDao()
    {
        super(Password.class,TABLE_NAME,KEY_NAME);
    }

    /*
     * 根據表格名稱與欄位條件組裝 SELECT * 查詢語句。
     * @param tableName 表格名稱
     * @param columnNames 欲比對的欄位名稱列表
     * @return 組裝好的 SQL 字串
     */
    public Result<Integer> validateUser(Password password)
    {
        return getCountByColumns(password, List.of("userNo","password"));

    }
    public Result<Password> getUserNameByUserNo(Password password)
    {
        return getEntityByFields(password, List.of("userNo"));

    }
    public Result<Password> getUserNameById(Password password)
    {

        return getEntityByFields(password, List.of("id"));
    }
    public static void main(String[] args)
    {
        String sql= """
                SELECT * FROM password WHERE id=?
                """;


        UserDao dao = new UserDao();
        Password password = new Password();
        password.setId(15L);
        Result<Password> result=dao.getUserNameById(password);

        Result<Password> result2=dao.queryForOne(Password.class,sql,List.of(password.getId()).toArray());
        Log.normal(result2.toJSON());
        Result<Password> result3=dao.queryForOne(Password.class,sql,15L);
        Log.normal(result3.toJSON());
    }
}
