
package com.taxistore.dao.impl;

import com.taxistore.dao.ICompanyDao;
import com.taxistore.pojo.Company;
import com.taxistore.dto.CompanyDTO;
import com.taxistore.pojo.Result;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * DAO 實作類別，用於操作 company 資料表。
 * 提供新增、修改、刪除、查詢等功能，並回傳封裝結果的 Result 物件。
 */
public class CompanyDao extends AbstractFormDao<Company> implements ICompanyDao {

    private static final String TABLE_NAME = "company";
    private static final String KEY_NAME = "companyseq";
    private static final Map<String, Supplier<Object>> extraFields = Map.of(
            "createdate", () -> new Timestamp(System.currentTimeMillis())
    );

    private static final String COMPANY_DTO_COLUMNS = String.join(", ",
            "co1.companyseq",
            "co1.createdate",
            "co1.serialno",
            "co1.companyname",
            "co1.companyab",
            "co1.specialcode",
            "co1.taxno",
            "co1.owner",
            "co2.companyname AS ownerName",
            "co2.specialcode AS ownerSpecialCode",
            "co1.customerCategory",
            "cc.CUSTCATENAME AS customerCategoryName",
            "cc.CUSTCATECODE AS customerCategoryCode",
            "co1.contact",
            "co1.banno",
            "co1.telno",
            "co1.portable",
            "co1.faxno",
            "co1.postcode",
            "co1.email",
            "co1.address",
            "co1.contact2",
            "co1.invaddr",
            "co1.remark"
    );

    private static final String COMPANY_FROM_JOIN =
            " FROM company co1 " +
                    " LEFT JOIN company co2 ON co1.owner = co2.companyseq " +
                    " LEFT JOIN customercategory cc ON co1.customerCategory = cc.custcateid ";


    public CompanyDao() {
        super(Company.class, TABLE_NAME,KEY_NAME,extraFields);
    }

    /*
     * 取得第一筆公司資料（根據 companyseq 排序）
     */
    @Override
    public Result<CompanyDTO> getFirstCompanyDTO() {
        String sql = "SELECT " + COMPANY_DTO_COLUMNS + COMPANY_FROM_JOIN + " ORDER BY co1.companyseq ASC LIMIT 1";
        return queryForOne(CompanyDTO.class, sql);
    }

    /*
     * 取得最後一筆公司資料（根據 companyseq 排序）
     */
    @Override
    public Result<CompanyDTO> getLastCompanyDTO() {
        String sql = "SELECT " + COMPANY_DTO_COLUMNS + COMPANY_FROM_JOIN + " ORDER BY co1.companyseq DESC LIMIT 1";
        return queryForOne(CompanyDTO.class, sql);
    }

    /*
     * 取得下一筆公司資料（大於指定序號）
     */
    @Override
    public Result<CompanyDTO> getNextCompanyDTO(long serialNo) {
        String sql = "SELECT " + COMPANY_DTO_COLUMNS + COMPANY_FROM_JOIN + " WHERE co1.companyseq > ? ORDER BY co1.companyseq ASC LIMIT 1";
        return queryForOne(CompanyDTO.class, sql, serialNo);
    }

    /*
     * 取得上一筆公司資料（小於指定序號）
     */
    @Override
    public Result<CompanyDTO> getPreviousCompanyDTO(long serialNo) {
        String sql = "SELECT " + COMPANY_DTO_COLUMNS + COMPANY_FROM_JOIN + " WHERE co1.companyseq < ? ORDER BY co1.companyseq DESC LIMIT 1";
        return queryForOne(CompanyDTO.class, sql, serialNo);
    }

    /*
     * 根據序號取得單筆公司資料
     */
    @Override
    public Result<CompanyDTO> getCompanyDTO(long serialNo) {
        String sql = "SELECT " + COMPANY_DTO_COLUMNS + COMPANY_FROM_JOIN + " WHERE co1.companyseq = ?";
        return queryForOne(CompanyDTO.class, sql, serialNo);
    }

    /*
     * 根據特殊代碼模糊查詢公司清單
     */
    @Override
    public Result<List<CompanyDTO>> getCompaniesDTOBySpecialCode(String specialCode) {
        String sql = "SELECT " + COMPANY_DTO_COLUMNS + COMPANY_FROM_JOIN + " WHERE co1.specialcode LIKE CONCAT(?, '%')";
        return queryForList(CompanyDTO.class, sql, specialCode);
    }



    public static void main(String[] args)
    {
        CompanyDao dao = new CompanyDao();
        Company c=new Company();
        c.setSerialno("2345");
        c.setCompanyname("company2");
        c.setCompanyab("companyab");
        c.setSpecialcode("specialcode");
        c.setTaxno("taxno");
        c.setContact("12345");
        c.setBanno("12345");
        c.setTelno("12345");
        c.setPortable("12345");
        c.setFaxno("12345");
        c.setContact2("12345");
        c.setInvaddr("12345");
        c.setRemark("12345");
        c.setEmail("luckfox0927@gmail.com");
        c.setCustomerCategory(5L);
        c.setOwner(6L);
        Result<Long> result=dao.insertEntity(c);
        if (!result.isSuccess()) return;
        c.setTelno("0971672310");
        Result<Integer>result1=dao.updateEntity(c);

        Result<List<CompanyDTO>> result2=dao.getCompaniesDTOBySpecialCode("25m");
        dao.deleteEntity(result.getData());
    }
}
