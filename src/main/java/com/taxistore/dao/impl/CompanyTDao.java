package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.taxistore.dao.ICompanyTDao;
import com.taxistore.pojo.Company;
import com.taxistore.pojo.CompanyDTO;
import com.taxistore.pojo.Result;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CompanyTDao extends BasicTFormOperatorDao<Company> implements ICompanyTDao
{
    private static final String TableName="company";
    private static final String KeyName="companyseq";
    private static final String SelectSQL="";
    private static final String COMPANY_COLUMNS = "co1.serialno, co1.companyname, co1.companyab, co1.specialcode, co1.taxno, co1.owner, co2.companyname AS ownerName, co1.customerCategory, cc.CUSTCATENAME, co1.contact, co1.banno, co1.telno, co1.portable, co1.faxno, co1.postcode, co1.email, co1.address, co1.contact2, co1.invaddr, co1.remark";
    public CompanyTDao()
    {
        super(Company.class,TableName);
    }

    @Override
    public Result<Integer> updateCompany(Company c)
    {

        if(c==null) return new Result("invalid.input","input value is null");

        String sql=
                "UPDATE " +
                        "company " +
                "SET " +
                        "serialno= ?,"
                        +"companyname= ?,"
                        +"companyab= ?,"
                        +"specialcode= ?,"
                        +"taxno= ?,"
                        +"owner= ?,"
                        +"customerCategory= ?,"
                        +"contact= ?,"
                        +"banno= ?,"
                        +"telno= ?,"
                        +"portable= ?,"
                        +"faxno= ?,"
                        +"postcode= ?,"
                        +"email= ?,"
                        +"address= ?,"
                        +"contact2= ?,"
                        +"invaddr= ?,"
                        +"remark= ? " +
                "WHERE " +
                        "companyseq= ?;";

        Result<Integer> result= update(sql, c.getSerialno(),
                            c.getCompanyname(),
                            c.getCompanyab(),
                            c.getSpecialcode(),
                            c.getTaxno(),
                            c.getOwner(),
                            c.getCustomerCategory(),
                            c.getContact(),
                            c.getBanno(),
                            c.getTelno(),
                            c.getPortable(),
                            c.getFaxno(),
                            c.getPostcode(),
                            c.getEmail(),
                            c.getAddress(),
                            c.getContact2(),
                            c.getInvaddr(),
                            c.getRemark(),
                            c.getCompanyseq());

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

    /**
     * 增加一筆紀錄，如果成功則回傳key值。
     *
     * @param c
     * @return
     */
    @Override
    public Result<Long> addCompany(Company c)
    {
        if (c == null) return new Result<>("invalid.input", "Input value is null");

        // Creating a new object of the class Date
        long time = System.currentTimeMillis();
        java.sql.Timestamp createdate = new java.sql.Timestamp(time);

        String sql =
                "INSERT INTO " +
                        "company(createdate," +
                        "serialno," +
                        "companyname," +
                        "companyab," +
                        "specialcode," +
                        "taxno," +
                        "owner," +
                        "customerCategory," +
                        "contact," +
                        "banno," +
                        "telno," +
                        "portable," +
                        "faxno," +
                        "postcode," +
                        "email," +
                        "address," +
                        "contact2," +
                        "invaddr," +
                        "remark) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            // Set the parameters for the PreparedStatement
            ps.setTimestamp(1, createdate);
            ps.setString(2, c.getSerialno());
            ps.setString(3, c.getCompanyname());
            ps.setString(4, c.getCompanyab());
            ps.setString(5, c.getSpecialcode());
            ps.setString(6, c.getTaxno());
            ps.setLong (7, c.getOwner());
            ps.setLong (8, c.getCustomerCategory());
            ps.setString(9, c.getContact());
            ps.setString(10, c.getBanno());
            ps.setString(11, c.getTelno());
            ps.setString(12, c.getPortable());
            ps.setString(13, c.getFaxno());
            ps.setLong (14, c.getPostcode());
            ps.setString(15, c.getEmail());
            ps.setString(16, c.getAddress());
            ps.setString(17, c.getContact2());
            ps.setString(18, c.getInvaddr());
            ps.setString(19, c.getRemark());

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
                    Long generatedId = generatedKeys.getLong(1); // Assumes the key is an integer
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
    public Result<CompanyDTO> getCompany(long serialNo) {
        String sql=
                "SELECT "+
                        "co1.companyseq, "+
                        "co1.createdate, "+
                        "co1.serialno, "+
                        "co1.companyname, "+
                        "co1.companyab, "+
                        "co1.specialcode, "+
                        "co1.taxno, "+
                        "co1.owner, "+
                        "co2.companyname AS ownerName, "+
                        "co2.specialcode AS ownerSpecialCode, "+
                        "co1.customerCategory, "+
                        "cc.CUSTCATENAME AS customerCategoryName, "+
                        "cc.CUSTCATECODE AS customerCategoryCode, "+
                        "co1.contact, "+
                        "co1.banno, "+
                        "co1.telno, "+
                        "co1.portable, "+
                        "co1.faxno, "+
                        "co1.postcode, "+
                        "co1.email,"+
                        "co1.address, "+
                        "co1.contact2,"+
                        "co1.invaddr, "+
                        "co1.remark "+
                        "FROM "+
                        "company co1 "+
                        "LEFT JOIN "+
                        "company co2 ON co1.owner = co2.companyseq "+
                        "LEFT JOIN "+
                        "customercategory cc ON co1.customerCategory = cc.custcateid "+
                        "WHERE "+
                        "co1.companyseq =?;";
                 return queryForOne(CompanyDTO.class,sql,serialNo);
    }


    public Result<List<CompanyDTO>> getCompaniesBySpecialCode(String specialCode)
    {
        //String sql_query=String  sql = "SELECT " + COMPANY_COLUMNS + " FROM company co1 ...";
        String sql=
        "SELECT "+
                "co1.companyseq, "+
                "co1.createdate, "+
                "co1.serialno, "+
                "co1.companyname, "+
                "co1.companyab, "+
                "co1.specialcode, "+
                "co1.taxno, "+
                "co1.owner, "+
                "co2.companyname AS ownerName, "+
                "co2.specialcode AS ownerSpecialCode, "+
                "co1.customerCategory, "+
                "cc.CUSTCATENAME AS customerCategoryName, "+
                "cc.CUSTCATECODE AS customerCategoryCode, "+
                "co1.contact, "+
                "co1.banno, "+
                "co1.telno, "+
                "co1.portable, "+
                "co1.faxno, "+
                "co1.postcode, "+
                "co1.email,"+
                "co1.address, "+
                "co1.contact2,"+
                "co1.invaddr, "+
                "co1.remark "+
        "FROM "+
            "company co1 "+
        "LEFT JOIN "+
            "company co2 ON co1.owner = co2.companyseq "+
        "LEFT JOIN "+
            "customercategory cc ON co1.customerCategory = cc.custcateid "+
        "WHERE "+
            "co1.specialcode LIKE CONCAT(?, '%');";

        return queryForList(CompanyDTO.class, sql, specialCode);
    }

    @Override
    public Result<CompanyDTO> getNextCompany(long serialNo)
    {
        String sql=
                "SELECT "+
                        "co1.companyseq, "+
                        "co1.createdate, "+
                        "co1.serialno, "+
                        "co1.companyname, "+
                        "co1.companyab, "+
                        "co1.specialcode, "+
                        "co1.taxno, "+
                        "co1.owner, "+
                        "co2.companyname AS ownerName, "+
                        "co2.specialcode AS ownerSpecialCode, "+
                        "co1.customerCategory, "+
                        "cc.CUSTCATENAME AS customerCategoryName, "+
                        "cc.CUSTCATECODE AS customerCategoryCode, "+
                        "co1.contact, "+
                        "co1.banno, "+
                        "co1.telno, "+
                        "co1.portable, "+
                        "co1.faxno, "+
                        "co1.postcode, "+
                        "co1.email,"+
                        "co1.address, "+
                        "co1.contact2,"+
                        "co1.invaddr, "+
                        "co1.remark "+
                "FROM "+
                        "company co1 "+
                "LEFT JOIN "+
                        "company co2 ON co1.owner = co2.companyseq "+
                "LEFT JOIN "+
                        "customercategory cc ON co1.customerCategory = cc.custcateid "+
                "WHERE "+
                        "co1.companyseq > ? "+
                "ORDER BY co1.companyseq ASC "+
                "limit 1";

                return queryForOne(CompanyDTO.class,sql,serialNo);
    }

    @Override
    public Result<CompanyDTO> getPreviousCompany(long serialNo)
    {
        String sql=
                "SELECT "+
                        "co1.companyseq, "+
                        "co1.createdate, "+
                        "co1.serialno, "+
                        "co1.companyname, "+
                        "co1.companyab, "+
                        "co1.specialcode, "+
                        "co1.taxno, "+
                        "co1.owner, "+
                        "co2.companyname AS ownerName, "+
                        "co2.specialcode AS ownerSpecialCode, "+
                        "co1.customerCategory, "+
                        "cc.CUSTCATENAME AS customerCategoryName, "+
                        "cc.CUSTCATECODE AS customerCategoryCode, "+
                        "co1.contact, "+
                        "co1.banno, "+
                        "co1.telno, "+
                        "co1.portable, "+
                        "co1.faxno, "+
                        "co1.postcode, "+
                        "co1.email,"+
                        "co1.address, "+
                        "co1.contact2,"+
                        "co1.invaddr, "+
                        "co1.remark "+
                "FROM "+
                        "company co1 "+
                "LEFT JOIN "+
                        "company co2 ON co1.owner = co2.companyseq "+
                "LEFT JOIN "+
                        "customercategory cc ON co1.customerCategory = cc.custcateid "+
                "WHERE "+
                        "co1.companyseq < ? "+
                "ORDER BY co1.companyseq DESC "+
                "limit 1";
                return queryForOne(CompanyDTO.class,sql,serialNo);
    }

    @Override
    public Result<CompanyDTO> getLastCompany()
    {
        String sql=
                "SELECT "+
                        "co1.companyseq, "+
                        "co1.createdate, "+
                        "co1.serialno, "+
                        "co1.companyname, "+
                        "co1.companyab, "+
                        "co1.specialcode, "+
                        "co1.taxno, "+
                        "co1.owner, "+
                        "co2.companyname AS ownerName, "+
                        "co2.specialcode AS ownerSpecialCode, "+
                        "co1.customerCategory, "+
                        "cc.CUSTCATENAME AS customerCategoryName, "+
                        "cc.CUSTCATECODE AS customerCategoryCode, "+
                        "co1.contact, "+
                        "co1.banno, "+
                        "co1.telno, "+
                        "co1.portable, "+
                        "co1.faxno, "+
                        "co1.postcode, "+
                        "co1.email,"+
                        "co1.address, "+
                        "co1.contact2,"+
                        "co1.invaddr, "+
                        "co1.remark "+
                "FROM "+
                        "company co1 "+
                "LEFT JOIN "+
                        "company co2 ON co1.owner = co2.companyseq "+
                "LEFT JOIN "+
                        "customercategory cc ON co1.customerCategory = cc.custcateid "+
                "ORDER BY co1.companyseq DESC "+
                "limit 0,1";
                return queryForOne(CompanyDTO.class,sql);
    }

    @Override
    public Result<CompanyDTO> getFirstCompany()
    {
        String sql=
                "SELECT "+
                        "co1.companyseq, "+
                        "co1.createdate, "+
                        "co1.serialno, "+
                        "co1.companyname, "+
                        "co1.companyab, "+
                        "co1.specialcode, "+
                        "co1.taxno, "+
                        "co1.owner, "+
                        "co2.companyname AS ownerName, "+
                        "co2.specialcode AS ownerSpecialCode, "+
                        "co1.customerCategory, "+
                        "cc.CUSTCATENAME AS customerCategoryName, "+
                        "cc.CUSTCATECODE AS customerCategoryCode, "+
                        "co1.contact, "+
                        "co1.banno, "+
                        "co1.telno, "+
                        "co1.portable, "+
                        "co1.faxno, "+
                        "co1.postcode, "+
                        "co1.email,"+
                        "co1.address, "+
                        "co1.contact2,"+
                        "co1.invaddr, "+
                        "co1.remark "+
                "FROM "+
                        "company co1 "+
                "LEFT JOIN "+
                        "company co2 ON co1.owner = co2.companyseq "+
                "LEFT JOIN "+
                        "customercategory cc ON co1.customerCategory = cc.custcateid "+
                "ORDER BY co1.companyseq "+
                "limit 1";
        return queryForOne(CompanyDTO.class,sql);
    }
    public Result<Integer> deleteCompanyBySerialNo(long serialNo)
    {
        String sql="DELETE " +
                "FROM " +
                    "COMPANY " +
                "WHERE " +
                    "company.companyseq  = ?";
        Result<Integer> result=  update(sql, serialNo);

        return result;
    }

}
