package com.taxistore.mapper;

import org.apache.ibatis.annotations.*;
import com.taxistore.pojo.Company;
import com.taxistore.pojo.CompanyDTO;
import java.util.List;

/**
 * @author luckf
 */
@Mapper
public interface CompanyMapper {

    @Update("""
        UPDATE company SET
            serialno = #{serialno},
            companyname = #{companyname},
            companyab = #{companyab},
            specialcode = #{specialcode},
            taxno = #{taxno},
            owner = #{owner},
            customerCategory = #{customerCategory},
            contact = #{contact},
            banno = #{banno},
            telno = #{telno},
            portable = #{portable},
            faxno = #{faxno},
            postcode = #{postcode},
            email = #{email},
            address = #{address},
            contact2 = #{contact2},
            invaddr = #{invaddr},
            remark = #{remark}
        WHERE companyseq = #{companyseq}
    """)
    int updateCompany(Company company);

    @Insert("""
        INSERT INTO company (
            createdate, serialno, companyname, companyab, specialcode, taxno, owner,
            customerCategory, contact, banno, telno, portable, faxno, postcode,
            email, address, contact2, invaddr, remark
        ) VALUES (
            NOW(), #{serialno}, #{companyname}, #{companyab}, #{specialcode},
            #{taxno}, #{owner}, #{customerCategory}, #{contact}, #{banno}, #{telno},
            #{portable}, #{faxno}, #{postcode}, #{email}, #{address}, #{contact2},
            #{invaddr}, #{remark}
        )
    """)
    @Options(useGeneratedKeys = true, keyProperty = "companyseq")
    int addCompany(Company company);

    @Select("""
        SELECT co1.serialno, co1.companyname, co1.companyab, co1.specialcode, co1.taxno,
               co1.owner, co2.companyname AS ownerName, co1.customerCategory, cc.CUSTCATENAME,
               co1.contact, co1.banno, co1.telno, co1.portable, co1.faxno, co1.postcode,
               co1.email, co1.address, co1.contact2, co1.invaddr, co1.remark
        FROM company co1
        LEFT JOIN company co2 ON co1.owner = co2.companyseq
        LEFT JOIN customercategory cc ON co1.customerCategory = cc.custcateid
        WHERE co1.specialcode LIKE CONCAT(#{specialCode}, '%')
    """)
    List<CompanyDTO> getCompaniesBySpecialCode(String specialCode);

    @Select("""
        SELECT co1.serialno, co1.companyname, co1.companyab, co1.specialcode, co1.taxno,
               co1.owner, co2.companyname AS ownerName, co1.customerCategory, cc.CUSTCATENAME,
               co1.contact, co1.banno, co1.telno, co1.portable, co1.faxno, co1.postcode,
               co1.email, co1.address, co1.contact2, co1.invaddr, co1.remark
        FROM company co1
        LEFT JOIN company co2 ON co1.owner = co2.companyseq
        LEFT JOIN customercategory cc ON co1.customerCategory = cc.custcateid
        WHERE co1.companyseq > #{serialNo}
        ORDER BY co1.companyseq ASC
        LIMIT 1
    """)
    CompanyDTO getNextCompany(long serialNo);

    @Select("""
        SELECT co1.serialno, co1.companyname, co1.companyab, co1.specialcode, co1.taxno,
               co1.owner, co2.companyname AS ownerName, co1.customerCategory, cc.CUSTCATENAME,
               co1.contact, co1.banno, co1.telno, co1.portable, co1.faxno, co1.postcode,
               co1.email, co1.address, co1.contact2, co1.invaddr, co1.remark
        FROM company co1
        LEFT JOIN company co2 ON co1.owner = co2.companyseq
        LEFT JOIN customercategory cc ON co1.customerCategory = cc.custcateid
        WHERE co1.companyseq < #{serialNo}
        ORDER BY co1.companyseq DESC
        LIMIT 1
    """)
    CompanyDTO getPreviousCompany(long serialNo);

    @Select("""
        SELECT co1.serialno, co1.companyname, co1.companyab, co1.specialcode, co1.taxno,
               co1.owner, co2.companyname AS ownerName, co1.customerCategory, cc.CUSTCATENAME,
               co1.contact, co1.banno, co1.telno, co1.portable, co1.faxno, co1.postcode,
               co1.email, co1.address, co1.contact2, co1.invaddr, co1.remark
        FROM company co1
        LEFT JOIN company co2 ON co1.owner = co2.companyseq
        LEFT JOIN customercategory cc ON co1.customerCategory = cc.custcateid
        ORDER BY co1.companyseq ASC
        LIMIT 1
    """)
    CompanyDTO getFirstCompany();

    @Select("""
        SELECT co1.serialno, co1.companyname, co1.companyab, co1.specialcode, co1.taxno,
               co1.owner, co2.companyname AS ownerName, co1.customerCategory, cc.CUSTCATENAME,
               co1.contact, co1.banno, co1.telno, co1.portable, co1.faxno, co1.postcode,
               co1.email, co1.address, co1.contact2, co1.invaddr, co1.remark
        FROM company co1
        LEFT JOIN company co2 ON co1.owner = co2.companyseq
        LEFT JOIN customercategory cc ON co1.customerCategory = cc.custcateid
        ORDER BY co1.companyseq DESC
        LIMIT 1
    """)
    CompanyDTO getLastCompany();
}
