package com.taxistore.dao.impl;

import com.taxistore.config.DatabaseManager;
import com.taxistore.dto.SituationDTO;
import com.taxistore.dto.VehicleInspDTO;
import com.taxistore.dto.VehicleInspStatusDTO;
import com.taxistore.pojo.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.utils.utils.DateUtils.formatDate;
import static com.utils.utils.DateUtils.formatTime;

/**
 * SituationDao 用於處理與 situation 相關的查詢
 */
public class SituationDao extends AbstractFormDao<Situation>{

    private static final String TABLE_NAME = "situation";
    private static final String KEY_NAME = "id";
    private static final String BASE_SQL = """
        SELECT 
            s.id AS situationId,
            c.carseq AS carseq,
            c.carno AS carno,
            d.driverseq AS driverseq,
            d.drivername AS driverName, 
            d.id AS driverId,
            d.portable AS portable,
            d.specialcode AS specialCode,
            o.companyname AS companyName,
            o.companyseq AS companyseq,
            s.createDate AS createDate,
            s.giveCarDate,
            s.terminateDate,
            s.status,
            s.recvDeposit AS recvDeposit,
            s.retDeposit AS retDeposit,
            r.id AS recvformId, 
            r.createdate AS recvformDate, 
            r.type, 
            r.remark, 
            r.userNo, 
            r.userName
        FROM situation s
        JOIN car c ON s.carseq = c.carseq
        JOIN driver d ON s.driverseq = d.driverseq
        JOIN company o ON o.companyseq = c.companyseq    
        LEFT JOIN recvform r ON s.id = r.situation
    """;

    private static final String sql = """
              SELECT 
                    s.id AS situationId,
                    c.carseq AS carseq,
                    c.carno AS carno,
                    d.driverseq AS driverseq,
                    d.drivername AS driverName, 
                    d.id AS driverId,
                    d.portable AS portable,
                    d.specialcode AS specialCode,
                    o.companyname AS companyName,
                    o.companyseq AS companyseq,
                    s.createDate AS createDate,
                    s.giveCarDate,
                    s.terminateDate,
                    s.status,
                    s.recvDeposit AS recvDeposit,
                    s.retDeposit AS retDeposit,
                    r.id AS recvformId, 
                    r.createdate AS recvformDate, 
                    r.type, 
                    r.remark, 
                    r.userNo, 
                    r.userName
                FROM situation s
                JOIN car c ON s.carseq = c.carseq
                JOIN driver d ON s.driverseq = d.driverseq
                JOIN company o ON o.companyseq = c.companyseq    
                LEFT JOIN recvform r ON s.id = r.situation
            """;

    private static final String ORDER_DEFAULT = " ORDER BY s.createDate DESC, r.createdate DESC ";

    private static final String WHERE_BY_ID = " WHERE s.id = ? ";
    private static final String WHERE_BY_CARNO_LIKE = " WHERE c.carno LIKE CONCAT('%',CONCAT(?,'%')) ";
    private static final String WHERE_BY_SPECIAL_LIKE = " WHERE d.specialcode LIKE CONCAT(?,'%') ";


    private static final String EquCondition=" WHERE s.id= ? ;";

    private static final String LikeSpecialCodeCondition="WHERE d.specialcode like CONCAT(?,'%') ORDER BY s.createDate DESC, r.createdate DESC";
    private static final String LikeCarNoCondition=" WHERE c.carno like CONCAT('%',CONCAT(?,'%')) ORDER BY s.createDate DESC, r.createdate DESC";
    private static final String DecCondition=" WHERE s.id < ? ORDER BY s.id DESC limit 1;";
    private static final String IncCondition=" WHERE s.id > ? ORDER BY s.id ASC limit 1;";
    private static final String FinalCondition="ORDER BY s.id DESC LIMIT 0 , 1;";
    private static final String FirstCondition="ORDER BY s.id LIMIT 1;";

    public SituationDao() {
        super(Situation.class, TABLE_NAME,KEY_NAME);
    }


//================================================================================================================

    public Result<SituationDTO> getLatestSituationDetails() {
        return fetchSituationDetails(sql+FinalCondition, null);
    }
     /*
     * @param situationId 目前 `situation` 的 ID
     * @param isNext `true` 取得下一筆, `false` 取得上一筆
     * @return 司機與該車的收款單資訊
     */
    public Result<SituationDTO> getSituationDetailsById(long situationId, boolean isNext) {

        return fetchSituationDetails(isNext?sql+IncCondition:sql+DecCondition, situationId);
    }
    public Result<SituationDTO> getSituationDetailsById(long situationId)
    {
        return fetchSituationDetails(sql+EquCondition, situationId);
    }


    /*
     * 根據車牌號碼查詢對應的司機基本資料與所有收款單。
     * <p>
     * - 回傳 {@link SituationDTO}，包含司機、車輛、收款單資訊。
     * - 若查無資料，回傳失敗訊息。
     *
     * @param carNo 車牌號碼
     * @return 查詢結果封裝於 {@link Result} 物件中
     */
    public Result<SituationDTO> getDriverReceiptsByCarNo(String carNo) {
        String sql = """
        SELECT s.id AS situationId, c.carno AS carNo, d.drivername AS driverName, d.id AS driverId,
               d.portable AS portable, d.specialcode AS specialCode, o.companyname AS companyName,
               s.createDate AS createDate, s.recvDeposit, s.retDeposit,
               r.id AS recvformId, r.createdate AS recvformDate, r.type, r.remark, r.userNo, r.userName
        FROM situation s
        JOIN car c ON c.carseq = s.carseq
        JOIN driver d ON s.driverseq = d.driverseq
        JOIN company o ON o.companyseq = c.companyseq
        LEFT JOIN recvform r ON s.id = r.situation
        WHERE c.carno = ?
        ORDER BY r.createdate DESC
    """;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, carNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                SituationDTO dto = null;
                List<RecvForm> receipts = new ArrayList<>();

                while (rs.next()) {
                    if (dto == null) {
                        dto = new SituationDTO();

                        // 基本欄位設定
                        dto.setSituationId(rs.getLong("situationId"));
                        dto.setCarno(rs.getString("carNo"));
                        dto.setDriverName(rs.getString("driverName"));
                        dto.setDriverId(rs.getString("driverId"));
                        dto.setPortable(rs.getString("portable"));
                        dto.setSpecialCode(rs.getString("specialCode"));
                        dto.setCompanyName(rs.getString("companyName"));
                        dto.setCreateDate(formatDate(rs.getTimestamp("createDate")));
                        dto.setCreateTime(formatTime(rs.getTimestamp("createDate")));
                        dto.setRecvDeposit(rs.getInt("recvDeposit"));
                        dto.setRetDeposit(rs.getInt("retDeposit"));

                    }

                    Long recvformId = rs.getLong("recvformId");
                    if (!rs.wasNull()) {
                        RecvForm receipt = new RecvForm(
                                recvformId,
                                rs.getTimestamp("recvformDate"),
                                rs.getLong("situationId"),
                                rs.getString("type"),
                                rs.getString("remark"),
                                rs.getString("userNo"),
                                rs.getString("userName")
                        );
                        receipts.add(receipt);
                    }
                }

                if (dto != null) {
                    dto.setReceipts(receipts);
                    return Result.success(dto);
                } else {
                    return Result.failure("查無此車牌對應的司機與收款單");
                }

            }
        } catch (Exception e) {
            return Result.failure("資料庫查詢發生錯誤：" + e.getMessage());
        }
    }
    /**
     * 根據 carNo 查詢 Situation 詳細資訊
     *
     * @param carno 車號
     * @return List<SituationDTO> 符合條件的所有 situation 記錄
     */
    public Result<List<SituationDTO>> getSituationDetailsByCarnoLike(String carno) {

        List<SituationDTO> situationList = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql+LikeCarNoCondition)) {

            pstmt.setString(1, carno);
            ResultSet rs = pstmt.executeQuery();

            SituationDTO situationDTO = null;
            List<RecvForm> receipts = null;
            Long lastSituationId = null;

            while (rs.next()) {
                Long situationId = rs.getLong("situationId");

                // 若遇到新 situationId，則建立新 DTO 物件
                if (!situationId.equals(lastSituationId)) {
                    if (situationDTO != null) {
                        // 先將上次的 DTO 加入列表
                        situationList.add(situationDTO);
                    }

                    // 解析 createDate
                    Timestamp date = rs.getTimestamp("createDate");
                    String createDate = date != null ? dateFormat.format(date) : null;
                    String createTime = date != null ? timeFormat.format(date) : null;

                    date = rs.getTimestamp("giveCarDate");
                    String giveCarDate = date != null ? dateFormat.format(date) : null;

                    date =rs.getTimestamp("terminateDate");
                    String terminateDate = date != null ? dateFormat.format(date) : null;


                    // 將執行SQL後的資料裝載到 driverReceiptsDTO 物件
                    receipts = new ArrayList<>();
                    situationDTO = new SituationDTO(
                            situationId,
                            rs.getLong("carseq"),
                            rs.getString("carno"),
                            rs.getLong("driverseq"),
                            rs.getString("driverName"),
                            rs.getString("driverId"),
                            rs.getString("portable"),
                            rs.getString("specialCode"),
                            rs.getString("companyName"),
                            rs.getLong("companyseq"),
                            createDate,
                            createTime,
                            giveCarDate,
                            terminateDate,
                            rs.getString("status"),
                            rs.getInt("recvDeposit"),
                            rs.getInt("retDeposit"),
                            receipts
                    );
                    lastSituationId = situationId;
                }

                // 讀取收款單
                Long recvformId = rs.getLong("recvformId");
                if (!rs.wasNull()) {
                    RecvForm receipt = new RecvForm(
                            recvformId,
                            rs.getTimestamp("recvformDate"),
                            situationId,
                            rs.getString("type"),
                            rs.getString("remark"),
                            rs.getString("userNo"),
                            rs.getString("userName")
                    );
                    receipts.add(receipt);
                }
            }//end of while

            // 加入最後一筆
            if (situationDTO != null) {
                situationList.add(situationDTO);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            return Result.failure("資料庫錯誤：" + e.getMessage());
        }
        return Result.success(situationList);
    }
    /**
     * 根據 specialCode 查詢 Situation 詳細資訊
     *
     * @param specialCode 司機的特殊代碼
     * @return List<SituationDetailsDTO> 符合條件的所有 situation 記錄
     */
    public Result<List<SituationDTO>> getSituationDetailsBySpecial(String specialCode) {

        List<SituationDTO> situationList = new ArrayList<>();

        // 格式化日期和時間
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");



        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql+LikeSpecialCodeCondition)) {

            pstmt.setString(1, specialCode);
            ResultSet rs = pstmt.executeQuery();

            SituationDTO situationDTO = null;
            List<RecvForm> receipts = null;
            Long lastSituationId = null;

            while (rs.next()) {
                Long situationId = rs.getLong("situationId");

                if(rs.wasNull()) continue; //如果為null，則跳過此筆資料

                // 解析並取得 createDate、createTime
                Timestamp date = rs.getTimestamp("createDate");
                String createDate = date != null ? dateFormat.format(date) : null;
                String createTime = date != null ? timeFormat.format(date) : null;

                date = rs.getTimestamp("giveCarDate");
                String giveCarDate = date != null ? dateFormat.format(date) : null;

                date =rs.getTimestamp("terminateDate");
                String terminateDate = date != null ? dateFormat.format(date) : null;

                // 若遇到新 situationId，則建立新 DTO 物件
                if (!situationId.equals(lastSituationId)) {
                    if (situationDTO != null) {
                        // 先將上次的 DTO 加入列表
                        situationList.add(situationDTO);
                    }
                    // 建立新的 SituationDetailsDTO 物件
                    receipts = new ArrayList<>();
                    situationDTO = new SituationDTO(
                            situationId,
                            rs.getLong("carseq"),
                            rs.getString("carNo"),
                            rs.getLong("driverseq"),
                            rs.getString("driverName"),
                            rs.getString("driverId"),
                            rs.getString("portable"),
                            rs.getString("specialCode"),
                            rs.getString("companyName"),
                            rs.getLong("companyseq"),
                            createDate,
                            createTime,
                            giveCarDate,
                            terminateDate,
                            rs.getString("status"),
                            rs.getInt("recvDeposit"),
                            rs.getInt("retDeposit"),
                            receipts
                    );
                    lastSituationId = situationId;
                }

                // 讀取收款單
                Long recvformId = rs.getLong("recvformId");
                if (!rs.wasNull()) {
                    RecvForm receipt = new RecvForm(
                            recvformId,
                            rs.getTimestamp("recvformDate"),
                            situationId,
                            rs.getString("type"),
                            rs.getString("remark"),
                            rs.getString("userNo"),
                            rs.getString("userName")
                    );
                    receipts.add(receipt);
                }
            }//end of while

            // 加入最後一筆
            if (situationDTO != null) {
                situationList.add(situationDTO);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            return Result.failure("資料庫錯誤：" + e.getMessage());
        }
        return Result.success(situationList);
    }


    /*
     * 查詢指定公司在某年某季度的掛牌營業狀態。
     * @param year        指定年份
     * @param quarter     指定季度 (1~4)
     * @param specialCode 公司的特別碼
     * @return 車輛掛牌與繳銷狀態列表
     * @throws SQLException 如果資料庫查詢失敗
     */

    public Result<List<VehicleInspStatusDTO>> getVehicleInspectionStatus(
            int year, int season, String companySeq,
            Integer start, Integer length,String orderDir, String orderColumn ) {
        return (Result<List<VehicleInspStatusDTO>>) fetchVehicleInspectionStatus(year, season, companySeq, start, length,orderDir, orderColumn,false);
    }

    public Result<Integer> countVehicleInspectionStatus(int year, int season, String comapnySeq) {
        return (Result<Integer>) fetchVehicleInspectionStatus(year, season, comapnySeq, null, null, null, null, true);
    }

    /**
     * 查詢指定公司在某年某季度的掛牌營業狀態，或計算符合條件的資料筆數。
     *
     * @param year        指定年份
     * @param quarter     指定季度 (1~4)
     * @param companySeq  公司的序號 (若為 "0" 或 null，則不限定公司)
     * @param start       查詢起始索引 (可為 null，表示不使用分頁)
     * @param length      查詢筆數 (可為 null，表示不使用分頁)
     * @param orderIndex  排序欄位名稱 (可為 null，表示使用預設排序)
     * @param orderDir    排序方向 (可為 null，表示升序)
     * @param countOnly   是否只回傳筆數
     * @return 符合條件的資料列表，或是符合條件的資料筆數 (當 `countOnly=true` 時)
     */
    private Result<?> fetchVehicleInspectionStatus (
            int year, int quarter, String companySeq,
            Integer start, Integer length, String orderDir, String orderIndex,
            boolean countOnly) {

        // ✅ 計算季度的開始與結束日期
        LocalDate startDate = LocalDate.of(year, (quarter - 1) * 3 + 1, 1);
        LocalDate endDate = startDate.plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());

        // ✅ 動態選擇 `SELECT` 子句
        String selectClause = countOnly
                ? "SELECT COUNT(*) AS total_count "
                : """
        SELECT
            _c.carno,
            _s.driverseq,
            _d.drivername,
            _c.caryear,
            _c.shingledat,
            _owner.companyname,
            _s.terminateDate,
            CASE WHEN _c.shingledat BETWEEN ? AND ? THEN _c.shingledat ELSE NULL END AS newRegistration,
            CASE WHEN _s.terminateDate BETWEEN ? AND ? THEN _s.terminateDate ELSE NULL END AS terminatedStatus
        """;

        // ✅ SQL 主體
        StringBuilder sql = new StringBuilder(selectClause + """
    FROM situation AS _s
    LEFT JOIN car AS _c ON _c.carseq = _s.carseq
    LEFT JOIN driver AS _d ON _s.driverseq = _d.driverseq
    LEFT JOIN company AS _owner ON _c.companyseq = _owner.companyseq
    WHERE
        _c.shingledat <= ?
        AND (
            _s.terminateDate IS NULL OR _s.terminateDate >= ?
            OR _c.shingledat BETWEEN ? AND ?
            OR _s.terminateDate BETWEEN ? AND ?
        )
    """);

        // ✅ 處理 `companySeq` 條件 (若 companySeq !=""  && companySeq 不是 null，則加入條件)
        boolean hasCompanyFilter = companySeq != null &&  !companySeq.isEmpty();
        if (hasCompanyFilter) {
            sql.append(" AND _c.companyseq = ?");
        }

        // ✅ 排序與分頁 (僅適用於 `countOnly == false`)
        if (!countOnly) {
            String orderColumn = getColumnForVehicleInspStatusDTO_ByIndex(orderIndex);
            sql.append(" ORDER BY ").append(orderColumn != null ? orderColumn : "_c.carno")
                    .append(" ").append(orderDir != null ? orderDir : "ASC");

            if (start != null && length != null) {
                sql.append(" LIMIT ?, ?");
            }
        }

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            // ✅ 只有 `countOnly == false` 才綁定 `BETWEEN` 條件
            if (!countOnly) {
                stmt.setTimestamp(paramIndex++, Timestamp.valueOf(startDate.atStartOfDay()));
                stmt.setTimestamp(paramIndex++, Timestamp.valueOf(endDate.atStartOfDay()));
                stmt.setTimestamp(paramIndex++, Timestamp.valueOf(startDate.atStartOfDay()));
                stmt.setTimestamp(paramIndex++, Timestamp.valueOf(endDate.atStartOfDay()));
            }

            // ✅ 主要查詢條件
            stmt.setTimestamp(paramIndex++, Timestamp.valueOf(endDate.atStartOfDay()));
            stmt.setTimestamp(paramIndex++, Timestamp.valueOf(startDate.atStartOfDay()));
            stmt.setTimestamp(paramIndex++, Timestamp.valueOf(startDate.atStartOfDay()));
            stmt.setTimestamp(paramIndex++, Timestamp.valueOf(endDate.atStartOfDay()));
            stmt.setTimestamp(paramIndex++, Timestamp.valueOf(startDate.atStartOfDay()));
            stmt.setTimestamp(paramIndex++, Timestamp.valueOf(endDate.atStartOfDay()));

            // ✅ 如果 `companySeq` 有值，則綁定它
            if (hasCompanyFilter) {
                stmt.setString(paramIndex++, companySeq);
            }

            // ✅ 分頁參數
            if (!countOnly && start != null && length != null) {
                stmt.setInt(paramIndex++, start);
                stmt.setInt(paramIndex++, length);
            }

            // ✅ 執行查詢
            try (ResultSet rs = stmt.executeQuery()) {
                if (countOnly) {
                    // ✅ 只回傳計數結果
                    return rs.next() ? Result.success(rs.getInt("total_count")) : Result.success(0);
                } else {
                    // ✅ 回傳查詢結果
                    List<VehicleInspStatusDTO> items = new ArrayList<>();
                    while (rs.next()) {
                        VehicleInspStatusDTO item = new VehicleInspStatusDTO(
                                rs.getString("carno"),
                                rs.getString("driverseq"),
                                rs.getString("drivername"),
                                rs.getTimestamp("caryear"),
                                rs.getString("companyname"),
                                rs.getTimestamp("shingledat"),
                                rs.getTimestamp("terminateDate"),
                                rs.getTimestamp("newRegistration"),
                                rs.getTimestamp("terminatedStatus")
                        );
                        items.add(item);
                    }
                    return Result.success(items);
                }
            }
        } catch (SQLException e) {
            return Result.failure("資料庫錯誤：" + e.getMessage());
        }
    }

        /*
         * 查詢需要驗車的車輛。
         * @param queryMonth 指定查詢月份
         * @return 需要驗車的車輛列表
         * @throws SQLException 如果資料庫查詢失敗
         */
        public Result<List<VehicleInspDTO>> GetCarsForInspection(int start, int length, String orderDir, String columnIndex, int queryMonth)
        {
            List<VehicleInspDTO>  items= new ArrayList<>();
            String sortOrder = "ASC".equalsIgnoreCase(orderDir) ? "ASC" : "DESC";
            String orderColumn=getColumnForVehicleInspDTO_ByIndex(columnIndex);
            String sql = """
            SELECT  
                _c.carno,
                _d.driverseq,
                _d.drivername,
                _c.caryear,
                _owner.companyname,
                _c.shingledat
            FROM situation AS _s
            LEFT JOIN car AS _c ON _c.carseq = _s.carseq    
            LEFT JOIN driver AS _d ON _s.driverseq = _d.driverseq  
            LEFT JOIN company AS _owner ON _c.companyseq = _owner.companyseq
            WHERE 
                ISNULL(_s.terminateDate)
                AND _s.status != 3
                AND (
                    (TIMESTAMPDIFF(YEAR, _c.caryear, CURRENT_DATE()) < 5
                     AND MONTH(_c.shingledat) = ?
                     AND YEAR(_c.shingledat) = YEAR(CURRENT_DATE()))
                    OR 
                    (TIMESTAMPDIFF(YEAR, _c.caryear, CURRENT_DATE()) >= 5
                     AND (MONTH(_c.shingledat) = ?
                          OR MONTH(_c.shingledat) = CASE WHEN (? + 6) > 12 THEN (? - 6) ELSE (? + 6) END))
                ) ORDER BY """ + " "+orderColumn + " " + sortOrder + " LIMIT ? OFFSET ?";


        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, queryMonth);
            stmt.setInt(2, queryMonth);
            stmt.setInt(3, queryMonth);
            stmt.setInt(4, queryMonth);
            stmt.setInt(5, queryMonth);
            stmt.setInt(6, length);
            stmt.setInt(7, start);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    VehicleInspDTO item = new VehicleInspDTO(
                            rs.getString("carno"),
                            rs.getString("driverseq"),
                            rs.getString("drivername"),
                            rs.getTimestamp("caryear"),
                            rs.getString("companyname"),
                            rs.getTimestamp("shingledat"),
                            null,
                            null
                    );
                    items.add(item);
                }
            }
        }catch (SQLException e)
        {
            return Result.failure("資料庫錯誤：" + e.getMessage());
        }

        return  Result.success(items);
    }
    private String getColumnForVehicleInspStatusDTO_ByIndex(String columnIndex)
    {
        if(columnIndex==null) return "carno";
        switch(columnIndex)
        {
            case "0":return "carno";
            case "1":return "driverSeq";
            case "2":return "driverName";
            case "3":return "caryear";
            case "4":return "companyName";
            case "5":return "shingledat";
            case "6":return "terminateDate";
            case "7":return "newRegistration";
            case "8":return "terminatedStatus";
            default:return "carno";
        }
    }
    /**
     * 根據 DataTables 傳來的索引，返回對應的 SQL 欄位名稱
     */
    private String getColumnForVehicleInspDTO_ByIndex(String columnIndex) {
        switch (columnIndex) {
            case "0": return "carno";
            case "1":return "driverseq";
            case "2":return "driverName";
            case "3":return "caryear";
            case "4":return "companyName";
            case "5":return "shingleDat";
            default: return "carno";  // 預設排序為 `carno`
        }
    }
    /**
     * 執行 SQL 查詢，並回傳車牌、司機名稱及收款單資訊
     *
     * @param sql SQL 查詢語法
     * @param param `situation.id`，如果為 `null` 則不帶參數
     * @return 司機與該車的收款單資訊
     */
    private Result<SituationDTO> fetchSituationDetails(String sql, Long param) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (param != null) {
                pstmt.setLong(1, param);
            }
            // 格式化日期和時間
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");


            ResultSet rs = pstmt.executeQuery();
            Long carseq=0L;
            String carNo=null;
            Long driverseq=0L;
            String driverName = null;
            String driverId = null;
            String specialCode = null;
            String companyName = null;
            Long companyseq=0L;
            String portable = null;
            Long situationId = null;  // 修正為 null，避免未賦值
            Timestamp date = null;  // 修正為 null
            Timestamp date1=null;
            Timestamp date2=null;
            String status=null;
            Integer recvDeposit=0;
            Integer retDeposit=0;
            List<RecvForm> receipts = new ArrayList<>();
            // 解析 createDate

            while (rs.next()) {
                if (carNo == null) {
                    carseq=rs.getLong("carseq");
                    carNo = rs.getString("carno");
                    driverseq=rs.getLong("driverseq");
                    driverName = rs.getString("driverName");
                    driverId = rs.getString("driverId");
                    specialCode = rs.getString("specialCode");
                    portable = rs.getString("portable");
                    situationId = rs.getLong("situationId");
                    companyName = rs.getString("companyName");
                    companyseq = rs.getLong("companyseq");
                    date = rs.getTimestamp("createDate"); // 修正這裡
                    date1=rs.getTimestamp("giveCarDate");
                    date2=rs.getTimestamp("terminateDate");
                    status=rs.getString("status");
                    recvDeposit=rs.getInt("recvDeposit");
                    retDeposit=rs.getInt("retDeposit");
                }

                Long recvformId = rs.getLong("recvformId");
                if (recvformId != 0) {
                    RecvForm receipt = new RecvForm(
                            recvformId,
                            rs.getTimestamp("recvformDate"),
                            rs.getLong("situationId"),
                            rs.getString("type"),
                            rs.getString("remark"),
                            rs.getString("userNo"),
                            rs.getString("userName")
                    );
                    receipts.add(receipt);
                }
            }

            if (carNo != null) {
                // 解析 createDate
                String createDate = date != null ? dateFormat.format(date) : null;
                String createTime = date != null ? timeFormat.format(date) : null;
                String giveCarDate= date1 != null ? dateFormat.format(date1) : null;
                String terminateDate=date2 != null ? dateFormat.format(date2) : null;
                return Result.success(new SituationDTO(
                        situationId,
                        carseq,
                        carNo,
                        driverseq,
                        driverName,
                        driverId,
                        portable,
                        specialCode,
                        companyName,
                        companyseq,
                        createDate,
                        createTime,
                        giveCarDate,
                        terminateDate,
                        status,
                        recvDeposit,
                        retDeposit,
                        receipts));
            } else {
                return Result.failure("找不到符合條件的 situation 資料");
            }
        } catch (SQLException e) {
            return Result.failure("資料庫錯誤：" + e.getMessage());
        }
    }
    /**
     * 新增一筆 situation 資料，並回傳其 id
     *
     * SQL:
     * """
     * INSERT INTO situation (carseq, driverseq, createdate)
     * VALUES (?, ?, NOW());
     * SELECT LAST_INSERT_ID();
     * """
     *
     * @param carseq   車輛序號
     * @param driverseq 司機序號
     * @return `situation.id` 或錯誤訊息
     */
    public Result<Long> createSituation(long carseq, long driverseq) {
        String insertSql = "INSERT INTO situation (carseq, driverseq, createdate) VALUES (?, ?, NOW());";
        String getIdSql = "SELECT LAST_INSERT_ID();";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement getIdStmt = conn.prepareStatement(getIdSql)) {

            // 設定參數並執行插入
            insertStmt.setLong(1, carseq);
            insertStmt.setLong(2, driverseq);
            int affectedRows = insertStmt.executeUpdate();

            if (affectedRows == 0) {
                return Result.failure("新增失敗，沒有影響任何資料");
            }

            // 取得新插入資料的 id
            try (ResultSet rs = getIdStmt.executeQuery()) {
                if (rs.next()) {
                    return Result.success(rs.getLong(1));
                }
            }
            return Result.failure("無法取得 situation ID");

        } catch (SQLException e) {
            return Result.failure("資料庫錯誤：" + e.getMessage());
        }
    }
    public Result<Integer> GetTotalCarsForInspection(int queryMonth) {
        String sql = """
        SELECT COUNT(*)
        FROM situation AS _s 
        LEFT JOIN car AS _c ON _c.carseq = _s.carseq    
        LEFT JOIN driver AS _d ON _s.driverseq = _d.driverseq  
        LEFT JOIN company AS _owner ON _c.companyseq = _owner.companyseq
        WHERE 
            ISNULL(_s.terminateDate)
            AND _s.status != 3
            AND (
                (TIMESTAMPDIFF(YEAR, _c.caryear, CURRENT_DATE()) < 5
                 AND MONTH(_c.shingledat) = ?
                 AND YEAR(_c.shingledat) = YEAR(CURRENT_DATE()))
                OR 
                (TIMESTAMPDIFF(YEAR, _c.caryear, CURRENT_DATE()) >= 5
                 AND (MONTH(_c.shingledat) = ?
                      OR MONTH(_c.shingledat) = CASE WHEN (? + 6) > 12 THEN (? - 6) ELSE (? + 6) END))
            )""";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, queryMonth);
            stmt.setInt(2, queryMonth);
            stmt.setInt(3, queryMonth);
            stmt.setInt(4, queryMonth);
            stmt.setInt(5, queryMonth);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Result.success(rs.getInt(1)); // 回傳總筆數
                }
            }
        } catch (SQLException e) {
            return Result.failure("資料庫錯誤：" + e.getMessage());
        }

        return Result.success(0); // 預設為 0 筆資料
    }


    public static void main(String[] args) {
        SituationDao dao = new SituationDao();

        // 1. 測試新增
        Situation s = new Situation();
        s.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
        s.setSeqNo("TEST-001");
        s.setGiveCarDate(new java.sql.Timestamp(System.currentTimeMillis()));
        s.setTerminateDate(null);

        // TODO: 這兩個值要換成資料庫裡已經存在的 carseq 和 driverseq
        s.setCarseq(1L);
        s.setDriverseq(1L);

        s.setRecvDeposit(1000);
        s.setRetDeposit(0);
        s.setKeepMoney(0);
        s.setAccRecAmount(0);
        s.setAccPayAmount(0);
        s.setStatus("ACTIVE");
        s.setMemo("JUnit 測試用");
        s.setLastPostFineDate(null);

        Result<Long> insertResult = dao.insertEntity(s);
        if (!insertResult.isSuccess()) {
            System.out.println("新增失敗: " + insertResult.getErrorMessage());
            return;
        }
        Long newId = insertResult.getData();
        System.out.println("新增成功，ID = " + newId);

        // 2. 測試查詢單筆
        Result<Situation> fetchResult = dao.getEntity(newId);
        if (fetchResult.isSuccess()) {
            System.out.println("查詢成功: " + fetchResult.getData());
        } else {
            System.out.println("查詢失敗: " + fetchResult.getErrorMessage());
        }

        // 3. 測試更新
        Situation fetched = fetchResult.getData();
        fetched.setMemo("更新後的備註");
        Result<Integer> updateResult = dao.updateEntity(fetched);
        System.out.println("更新狀態: " + updateResult);

        // 4. 測試依欄位查詢
        Result<List<Situation>> listByStatus = dao.getEntitiesByField("status", "ACTIVE");
        System.out.println("狀態為 ACTIVE 的筆數: " + listByStatus.getData().size());

        // 5. 測試刪除
        Result<Integer> deleteResult = dao.deleteEntity(newId);
        System.out.println("刪除結果: " + deleteResult);
    }

}
