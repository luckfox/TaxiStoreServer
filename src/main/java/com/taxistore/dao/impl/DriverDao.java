package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.taxistore.dao.IDriverDao;
import com.taxistore.dto.DriverDTO;
import com.taxistore.dto.SituationDTO;
import com.taxistore.pojo.*;
import com.taxistore.pojo.Driver;
import com.utils.Log;
import com.utils.ResultSetMapper;

import java.sql.*;
import java.util.List;

/**
 * DriverDao 為駕駛資料表對應的 DAO 類別，提供 CRUD 與條件查詢功能。
 */
public class DriverDao extends AbstractFormDao<Driver> implements IDriverDao {

    private static final String TABLE_NAME = "driver";
    private static final String KEY_NAME = "driverseq";

    private static final String sql = """
            SELECT d.*, c.companyname as ownerName, c.specialcode as ownerSC,ca.carno,
            p.postcodeid as postcodeId,
             p.regionName AS regionName,
             p.districtName as districtName,
             p.postcodecode as postcodeCode
            FROM driver d
            LEFT JOIN postcode p ON d.postcode = p.postcodeid
            LEFT JOIN situation s ON d.driverseq = s.driverseq
            LEFT JOIN car ca ON s.carseq = ca.carseq
            LEFT JOIN company c ON ca.companyseq = c.companyseq
            """;
    private static final String IdLikeCondition=" WHERE d.id LIKE CONCAT(?, '%') ORDER BY d.id;";
    private static final String SepcialCodeLikeCondition=" WHERE d.specialcode LIKE CONCAT(?, '%') ORDER BY d.driverseq ;";
    private static final String EquCondition=" WHERE d.driverseq= ? ;";
    private static final String DecCondition=" WHERE d.driverseq < ? ORDER BY d.driverseq DESC limit 1;";
    private static final String IncCondition=" WHERE d.driverseq > ? ORDER BY d.driverseq ASC limit 1;";
    private static final String FinalCondition="ORDER BY d.driverseq DESC LIMIT 0 , 1;";
    private static final String FirstCondition="ORDER BY d.driverseq LIMIT 1;";

    public DriverDao() {
        super(Driver.class, TABLE_NAME,KEY_NAME);
    }

    @Override
    public Result<List<DriverDTO>> getDriversBySpecialCode(String specialCode) {
        return getDriversBySpecialCode(specialCode, 0, 0);
    }

    @Override
    public Result<List<DriverDTO>> getDriversBySpecialCode(String specialCode, long start, long length) {
        //String sql = "SELECT * FROM driver d WHERE d.specialcode LIKE CONCAT(?, '%') ORDER BY d.driverseq";
        return queryDriversWithLimit(sql+SepcialCodeLikeCondition, specialCode, start, length);
    }

    @Override
    public Result<List<DriverDTO>> getDriversByDriverId(String driverId) {
        return getDriversByDriverId(driverId, 0, 0);
    }

    @Override
    public Result<List<DriverDTO>> getDriversByDriverId(String driverId, long start, long length) {
        //String sql = "SELECT * FROM driver d WHERE d.id LIKE CONCAT(?, '%') ORDER BY d.id";
        return queryDriversWithLimit(sql+IdLikeCondition, driverId, start, length);
    }

    private Result<List<DriverDTO>> queryDriversWithLimit(String baseSql, String param, long start, long length) {
        if (param == null || start < 0 || length < 0) return Result.failure("database.query.errorOnParameterOverRange");

        StringBuilder sql = new StringBuilder(baseSql);
        boolean useLimit = !(start == 0 && length == 0);
        if (useLimit) sql.append(" LIMIT ?, ?");

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            stmt.setString(1, param);
            if (useLimit) {
                stmt.setLong(2, start);
                stmt.setLong(3, length);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                List<DriverDTO> drivers = new ResultSetMapper<DriverDTO>().mapResultSetToList(rs, DriverDTO.class);
                return Result.success(drivers);
            }
        } catch (Exception e) {
            return Result.failure("database.query.executionError");
        }
    }


    @Override
    public Result<DriverDTO> getNextDriverDTO(Long driverseq)
    {
        return  queryForOne(DriverDTO.class,sql+ IncCondition,driverseq);
    }
    @Override
    public Result<DriverDTO> getPreviousDriverDTO(Long driverseq)
    {
        return  queryForOne(DriverDTO.class,sql+ DecCondition,driverseq);
    }
    @Override
    public Result<DriverDTO> getLastDriverDTO()
    {
        return queryForOne(DriverDTO.class,sql+FinalCondition);

    }
    @Override
    public Result<DriverDTO> getFirstDriverDTO()
    {
        return queryForOne(DriverDTO.class,sql+FirstCondition);

    }
    @Override
    public Result<DriverDTO> getDriverDTO(Long driverseq)
    {
        return queryForOne(DriverDTO.class,sql+EquCondition,driverseq);
    }



    //=========================================================================================================
    public static void main(String[] args) {
        DriverDao dao = new DriverDao();
        SituationDao situationDao = new SituationDao();
        Driver testDriver = new Driver();
        testDriver.setSerialno("D-001");
        testDriver.setPostcode(10L);
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

        Result<Long> result0=dao.insertEntity(testDriver);
        if(!result0.isSuccess()) return;

        testDriver.setId("A222456789");
        Result<Integer> result1=dao.updateEntity(testDriver);
        Result<SituationDTO> result = situationDao.getDriverReceiptsByCarNo("TDL-7816");

        if (result.isSuccess()) {
            System.out.println(result.getData());
        } else {
            System.err.println("查詢失敗: " + result.getErrorMessage());
        }
        Result<Driver>result2=dao.getItem(KEY_NAME,result0.getData());
        Result<Driver>result3=dao.getPrevious(KEY_NAME,result0.getData());
        Result<Integer>result4=dao.deleteEntity(result0.getData());
        Result<SituationDTO>result5= situationDao.getDriverReceiptsByCarNo("TDX-1612");
        Log.normal(result5.toJSON());

        Result<DriverDTO>result6=dao.getDriverDTO(1610L);
        Log.normal(result6.toJSON());
        Result<DriverDTO>result7=dao.getPreviousDriverDTO(1610L);
        Log.normal(result7.toJSON());
    }
}
