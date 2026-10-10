package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.taxistore.dao.ILineUserTDao;
import com.taxistore.pojo.LineUser;
import com.taxistore.dto.LineUserDTO;
import com.taxistore.pojo.Result;
import com.utils.Log;
import com.utils.ResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class LineUserTDao extends AbstractFormDao<LineUser> implements ILineUserTDao
{

    private static final String TABLE_NAME="line_users ";
    private static final String KEY_NAME = "serialID";
    String   sql="""
                SELECT l.serialID,
                l.userId, 
                l.displayName, 
                l.tag, 
                l.createDate, 
                l.driverseq AS driverSeq,
                COALESCE(d.specialcode,'') AS driverSC,
                COALESCE(d.id,'') AS driverId,
                COALESCE(d.driverName,'') AS driverName,
                COALESCE(d.portable,'') AS driverPortable
                FROM line_users l
                LEFT JOIN driver d ON d.driverseq = l.driverseq
                """;


    private static final String EquSerialIdCondition = " WHERE serialID = ? ;";
    private static final String EquUserIdCondition=" WHERE userId = ? ;";
    private static final String EquDriverSeqCondition=" WHERE l.driverseq = ? ;";



    /** 額外加入的欄位，會在 insertEntity 時自動補值 */
    private static final Map<String, Supplier<Object>> extraFields = Map.of(
            "createDate",() -> new Timestamp(System.currentTimeMillis())
    );

    public LineUserTDao()
    {
        super(LineUser.class, TABLE_NAME, KEY_NAME, extraFields);
    }

    public Object getExtraFieldValue(String fieldName)
    {
        // 檢查是否有對應的額外欄位，如果有則返回其值
        Supplier<Object> valueSupplier = extraFields.get(fieldName);
        return valueSupplier != null ? valueSupplier.get() : null;
    }
    public Result<Integer> deleteEntryByUserId(String userId)
    {
        List<String> list = List.of("userId");
        LineUser lineUser=new LineUser();
        lineUser.setUserId(userId);
        Result<Integer>result=this.deleteEntitiesByFields(lineUser,list);
        return result;
    }

    @Override
    public Result<Integer> deleteLineUserByUserId(String userId) {
        String sql = " DELETE "+
                " FROM "+
                TABLE_NAME+
                " WHERE userId = ?";
        return update(sql, userId);
    }

    @Override
    public Result<Integer> updateLineUser(LineUser user)
    {
        return updateEntity(user);
    }
    @Override
    public Result<LineUser> getLineUserBySerialId(long serialId)
    {
        String sql = " SELECT "+
                " serialNo, " +
                " userId, "+
                " displayName, "+
                " tag, "+
                " l.createDate, "+
                " l.driverseq, "+
                " FROM "+
                TABLE_NAME + " l "+
                " WHERE serialID = ?";
        return queryForOne(LineUser.class, sql, serialId);
    }

    public Result<LineUserDTO> getLineUserWithDriverByDriverSeq(String DriverSeq)
    {
        return queryForOne(LineUserDTO.class, sql+EquDriverSeqCondition, DriverSeq);
    }
    @Override
    public Result<LineUserDTO> getLineUserWithDriverByUserId(String UserId)
    {
        return queryForOne(LineUserDTO.class, sql+EquUserIdCondition, UserId);
    }
    @Override
    public Result<LineUserDTO> getLineUserWithDriverBySerialId(long serialId)
    {
        return queryForOne(LineUserDTO.class, sql+EquSerialIdCondition, serialId);
    }

    public Result<List<LineUserDTO>> getAllUsersWithDriver()
    {
        return queryForList(LineUserDTO.class, sql);
    }
    @Override
    public Result<LineUser> getLineUserByUserId(String userId)
    {

        String sql = " SELECT "+
                " serialID, " +
                " userId, "+
                " displayName, "+
                " tag, "+
                " l.createDate, "+
                " l.driverseq "+
                " FROM "+
                TABLE_NAME + " l "+
                " WHERE userID = ?";
        return queryForOne(LineUser.class, sql, userId);
    }

    @Override
    public Result<List<LineUserDTO>> getLineUserDTOsWithDriver() {
        return null;
    }

    private static final Map<String, String> SEARCH_COLUMNS = Map.of(
            "displayName", "l.displayName",
            "driverId", "d.id",
            "driverName", "d.driverName",
            "driverSC","d.specialcode",
            "tag","l.tag"
    );

    public int countLineUsersBySearch(String searchColumn, String searchValue) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM line_users l LEFT JOIN driver d ON d.driverseq = l.driverseq");
        List<Object> params = new ArrayList<>();

        if (!"all".equalsIgnoreCase(searchColumn)) {
            String searchCondition = SEARCH_COLUMNS.get(searchColumn);
            if (searchCondition != null && searchValue != null && !searchValue.trim().isEmpty()) {
                sql.append(" WHERE ").append(searchCondition).append(" LIKE CONCAT(?,'%')");
                params.add(searchValue.trim());
            }
        }

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Result<List<LineUserDTO>> getLineUsersBySearch(int start, int length,String searchColumn, String searchValue,String orderDir,String orderColumn) {

        // 1. 建立 SQL 與參數
        StringBuilder _sql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        _sql.append(sql);

        // 2. 搜尋條件
        if(! "all".equalsIgnoreCase(searchColumn)) {
            String searchCondition = SEARCH_COLUMNS.get(searchColumn);
            if (searchCondition == null) {
                return Result.failure("Internal server error. Please contact support.");
            }

            if (searchValue != null && !searchValue.trim().isEmpty()) {
                _sql.append(" WHERE ").append(searchCondition).append(" LIKE CONCAT(?,'%')");
                params.add(searchValue.trim());
            }
        }

        // 3. 排序條件
        if (orderDir != null ) {
            if (!"asc".equalsIgnoreCase(orderDir) && !"desc".equalsIgnoreCase(orderDir)) {
                orderDir = "asc";
            }
            _sql.append(" ORDER BY ").append(orderColumn).append(" ").append(orderDir);
        }

        // 4. 分頁限制
        _sql.append(" LIMIT ?, ?");
        params.add(start < 0 ? 0 : start);
        params.add(length <= 0 ? 10 : length);

        // 5. 執行查詢 + 映射
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(_sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                List<LineUserDTO> list = new ResultSetMapper<LineUserDTO>().mapResultSetToList(rs, LineUserDTO.class);
                return Result.success(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("Database error: " + e.getMessage());
        }
    }


    public static void main(String[] args)
    {
        LineUserTDao dao=new LineUserTDao();
        

        Result<List<LineUserDTO>>result=dao.getLineUsersBySearch(0,10,"driverId","A1","asc","3");
        Log.normal(result.toJSON());
    }
}
