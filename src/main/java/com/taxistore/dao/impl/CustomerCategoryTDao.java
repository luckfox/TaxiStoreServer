package com.taxistore.dao.impl;

import com.taxistore.dao.ICustomerCategoryTDao;
import com.taxistore.pojo.CustomerCategory;
import com.taxistore.pojo.Result;
import com.utils.Log;

import java.util.List;

public class CustomerCategoryTDao extends  AbstractFormDao<CustomerCategory> implements ICustomerCategoryTDao
{
    private static final String TABLE_NAME = "CustomerCategory";
    private static final String KEY_NAME = "CustCateID";

    public CustomerCategoryTDao()
    {
        super(CustomerCategory.class,TABLE_NAME,KEY_NAME);
    }


    public Result<CustomerCategory> getCustomerCategory(long serialNo) {
        String sql=
                "SELECT "+
                        "CustCateID, "+
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
                        "CustCateCode, "+
                        "CustCateName "+
                "FROM "+
                        "CustomerCategory "+
                "WHERE "+
                        "CustCateCode LIKE CONCAT(?, '%');";

        return queryForList(CustomerCategory.class, sql, specialCode);
    }

    public static void main(String[] args) {
        CustomerCategoryTDao dao = new CustomerCategoryTDao();
        CustomerCategory cc = new CustomerCategory();

        cc.setCustCateName("Lexus II");
        cc.setCustCateCode("lx");
        Result<Long>result =dao.insertEntity(cc);
        if(result.isSuccess())
        {
            cc.setCustCateID(result.getData());
            cc.setCustCateName("Lexus III");
            Result<Integer> result1=dao.updateEntity(cc);

            Log.normal(result1.toString());
            Result<List<CustomerCategory>> result2=dao.getEntitiesByFieldLike("CustCateCode","QU");
            Log.normal(result2.toString());
        }

    }
}
