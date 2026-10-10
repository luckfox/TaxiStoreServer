package com.taxistore.dao.impl;

import com.taxistore.dto.DriverDTO;
import com.taxistore.dto.SituationDTO;
import com.taxistore.pojo.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DriverDao3Test {
    private DriverDao dao;
    private Driver testDriver;

    @BeforeEach
    void setUp() {
        dao = new DriverDao();

        testDriver = new Driver();
        testDriver.setSerialno("D-001");
        testDriver.setPostcode(1234L);
        testDriver.setDrivername("測試駕駛");
        testDriver.setSpecialcode("SC001");
        testDriver.setId("A123456789");
        testDriver.setBirthday(new Timestamp(System.currentTimeMillis()));
        testDriver.setPortable("0912345678");
        testDriver.setEmail("driver@test.com");
        testDriver.setCommadd("台北市信義區");
        testDriver.setCommtel("0223456789");
        testDriver.setHouseholda("新北市中和區");
        testDriver.setHouseholdt("0222223333");
        testDriver.setIndate(new Timestamp(System.currentTimeMillis()));
        testDriver.setOutdate(null);
        testDriver.setContact2("張先生");
        testDriver.setContactel("0933123456");
        testDriver.setPfine(200);
        testDriver.setPfineper(1);
        testDriver.setD10000("true");
        testDriver.setPfineday(30);
        testDriver.setRemark("測試用資料");
        testDriver.setGuarantor("保證人");
        testDriver.setRelation("親戚");
        testDriver.setGuarantorid("G123456789");
        testDriver.setGuarantortel("0987654321");
        testDriver.setGuarantoradd("新竹市");
        testDriver.setLastPostFineDate(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testInsertAndGetBySpecialCode() {

        setUp();
        Result<Long> insertResult = dao.insertEntity(testDriver);


        assertTrue(insertResult.isSuccess());
        assertNotNull(insertResult.getData());

        Result<List<DriverDTO>> queryResult = dao.getDriversBySpecialCode("SC001", 0, 10);
        assertTrue(queryResult.isSuccess());
        assertFalse(queryResult.getData().isEmpty());
    }

    @Test
    public void testUpdateDriver() {

        Result<Long> insertResult = dao.insertEntity(testDriver);
        assertTrue(insertResult.isSuccess());

        testDriver.setDriverseq(insertResult.getData());
        testDriver.setEmail("newemail@test.com");
        Result<Integer> updateResult = dao.updateEntity(testDriver);
        assertTrue(updateResult.isSuccess());
        assertTrue(updateResult.getData() > 0);
    }

    @Test
    public void testNavigationMethods() {
        Result<Driver> first = dao.getFirstEntity();
        Result<Driver> last = dao.getLastEntity();

        assertTrue(first.isSuccess());
        assertTrue(last.isSuccess());

        if (first.getData() != null) {
            Result<Driver> next = dao.getNextEntity(first.getData().getDriverseq());
            assertTrue(next.isSuccess() || next.getErrorMessage().equals("database.query.lastrecord"));
        }

        if (last.getData() != null) {
            Result<Driver> prev = dao.getPreviousEntity(last.getData().getDriverseq());
            assertTrue(prev.isSuccess() || prev.getErrorMessage().equals("database.query.lastrecord"));
        }
    }

    @Test
    public void testGetDriverReceiptsByCarNo() {
        SituationDao situationDao = new SituationDao();
        Result<SituationDTO> result = situationDao.getDriverReceiptsByCarNo("TDL-7816");
        assertNotNull(result);
        if (result.isSuccess()) {
            System.out.println("駕駛名稱：" + result.getData().getDriverName());
        } else {
            System.out.println("查無資料或錯誤：" + result.getErrorMessage());
        }
    }
}
