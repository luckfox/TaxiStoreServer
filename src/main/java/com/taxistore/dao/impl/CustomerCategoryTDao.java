package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.taxistore.dao.ICustomerCategoryTDao;
import com.taxistore.pojo.CompanyDTO;
import com.taxistore.pojo.CustomerCategory;
import com.taxistore.pojo.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CustomerCategoryTDao extends BasicTFormOperatorDao<CustomerCategory> implements ICustomerCategoryTDao
{

    public CustomerCategoryTDao()
    {
        super(CustomerCategory.class, "CustomerCategory");
    }


    @Override
    public Result<Integer> updateCustomerCategory(CustomerCategory c)
    {
        if(c==null) return new Result("invalid.input","input value is null");

        String sql="UPDATE "+
                        "CustomerCategory "+
                    "SET " +
                        "CustCateCode= ?, "+
                        "CustCateName= ? "+
                    "WHERE CustCateID= ?;";

        Result<Integer> result= update(sql,
                c.getCustCateCode(),
                c.getCustCateName(),
                c.getCustCateID());

        if (result.isSuccess() && result.getData() == 0)
        {
            /**
             * 雖然成功執行SQL，但實際上變更的項目為0
             */
            return new Result<Integer>()
                    .setSuccess(false)
                    .setErrorMessage("database.update.fail","No record update");
        }
        return result;
    }

    @Override
    public Result<Long> addCustomerCategory(CustomerCategory c)
    {
        if (c == null) return new Result<>("invalid.input", "Input value is null");


        String sql =
                "INSERT "+
                        "INTO " +
                            "CustomerCategory( " +
                            "CustCateCode, "+
                            "CustCateName) " +
                        "VALUES (?,?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            // Set the parameters for the PreparedStatement
            ps.setString(1, c.getCustCateCode());
            ps.setString(2, c.getCustCateName());


            // Execute the update and retrieve the affected rows
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0)
            {
                return new Result<Long>().setSuccess(false).setErrorMessage("database.update.fail", "No record updated");
            }

            // Retrieve the generated keys
            try (ResultSet generatedKeys = ps.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    // Assumes the key is an integer
                    Long generatedId = generatedKeys.getLong(1);
                    return new Result<Long>().setSuccess(true).setData(generatedId);
                } else
                {
                    return new Result<Long>().setSuccess(false).setErrorMessage("database.key.fail", "No generated key returned");
                }
            }
        } catch (Exception e)
        {
            return new Result<Long>().setSuccess(false).setErrorMessage("database.error", e.getMessage());
        }
    }
    @Override
    public Result<CustomerCategory> getCustomerCategory(long serialNo) {
        String sql=
                "SELECT "+
                        "CustCateCode, "+
                        "CustCateName "+
                "FROM "+
                        "CustomerCategory "+
                "WHERE "+
                        "CustCateID =?;";
        return queryForOne(CustomerCategory.class,sql,serialNo);
    }
    public Result<List<CustomerCategory>> getCustomerCategoryBySpecialCode(String specialCode)
    {
        //String sql_query=String  sql = "SELECT " + COMPANY_COLUMNS + " FROM company co1 ...";
        String sql=
                "SELECT "+
                        "CustCateID, "+
                        "CustCateName "+
                "FROM "+
                        "CustomerCategory "+
                "WHERE "+
                        "CustCateCode LIKE CONCAT(?, '%');";

        return queryForList(CustomerCategory.class, sql, specialCode);
    }
}
